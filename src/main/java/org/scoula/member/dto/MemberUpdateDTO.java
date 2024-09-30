package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
    private int user_no; // 사용자 번호
    private String user_id; // 사용자 ID
    private String name; // 사용자 이름
    private String birthdate; // 생년월일
    private String password; // 사용자 비밀번호
    private int mbti_no; // MBTI 번호
    private MultipartFile avatar; // 사용자 아바타 (이미지 파일)

    // 비밀번호 변경 필드
    private String oldPassword; // 이전 비밀번호
    private String newPassword; // 새 비밀번호

    // MemberVO로 변환하는 메서드
    public MemberVO toVO() {
        return MemberVO.builder()
                .user_no(user_no)
                .user_id(user_id)
                .name(name)
                .birthdate(birthdate)
                .password(newPassword != null ? newPassword : password) // 새 비밀번호가 존재할 경우 설정
                .mbti_no(mbti_no)
                .build();
    }
}
