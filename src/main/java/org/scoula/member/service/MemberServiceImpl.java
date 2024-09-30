package org.scoula.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;
import org.scoula.member.exception.PasswordMissmatchException;
import org.scoula.member.mapper.MemberMapper;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final PasswordEncoder passwordEncoder;
    final MemberMapper mapper;

    @Override
    public boolean findById(String userId) {
        MemberVO member = mapper.findById(userId);
        // true: DB에 해당 아이디 정보가 이미 있음
        return member != null;
    }

    @Override
    public MemberDTO get(String userId) {
        MemberVO member = Optional.ofNullable(mapper.get(userId))
                .orElseThrow(NoSuchElementException::new);
        return MemberDTO.of(member);
    }

    private void saveAvatar(MultipartFile avatar, String userId) {
        // 아바타 업로드
        if (avatar != null && !avatar.isEmpty()) {
            File dest = new File("c:/upload/avatar", userId + ".png");
            try {
                avatar.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException("아바타 업로드 실패", e);
            }
        }
    }

    @Transactional
    @Override
    public MemberDTO join(MemberJoinDTO dto) {
        MemberVO member = dto.toVO();

        // 비밀번호 암호화
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);

        log.info("mapperVO :" + member);
        mapper.insert(member);

        saveAvatar(dto.getAvatar(), member.getUser_id());

        return get(member.getUser_id());
    }

    @Transactional
    @Override
    public MemberDTO update(MemberUpdateDTO member) {
        // 유저 조회 및 null 확인
        MemberVO vo = Optional.ofNullable(mapper.get(member.getUser_id()))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 사용자입니다."));

        // 비밀번호가 존재하면 비밀번호 검증 및 변경
        if (member.getOldPassword() != null && !member.getOldPassword().isEmpty()) {
            if (!passwordEncoder.matches(member.getOldPassword(), vo.getPassword())) {
                throw new PasswordMissmatchException(); // 기존 비밀번호 일치 확인
            }
            // 새 비밀번호 암호화
            String encodedNewPassword = passwordEncoder.encode(member.getNewPassword());
            vo.setPassword(encodedNewPassword);
        }


        vo.setBirthdate(member.getBirthdate()); // Date 타입으로 변경

        // DB에 업데이트
        mapper.update(vo);

        // 아바타 파일 저장
        saveAvatar(member.getAvatar(), member.getUser_id());

        return get(member.getUser_id());
    }

    @Override
    public void changePassword(ChangePasswordDTO changePassword) {
        MemberVO member = Optional.ofNullable(mapper.get(changePassword.getUser_no()))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 사용자입니다."));

        // 이전 비밀번호 검증
        if (!passwordEncoder.matches(changePassword.getOldPassword(), member.getPassword())) {
            throw new PasswordMissmatchException();
        }

        // 새 비밀번호 암호화 및 업데이트
        changePassword.setNewPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        mapper.updatePassword(changePassword);
    }
}
