package org.scoula.member.mapper;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {

    /**
     * 사용자 정보 조회
     * @param userId 사용자 ID
     * @return MemberVO 객체
     */
    MemberVO get(String userId);

    /**
     * 사용자 ID 중복 체크를 위한 조회
     * @param userId 사용자 ID
     * @return MemberVO 객체
     */
    MemberVO findById(String userId); // id 중복 체크시 사용

    /**
     * 회원 정보 추가
     * @param member MemberVO 객체
     * @return insert 결과 (영향받은 행 수)
     */
    int insert(MemberVO member); // 회원 정보 추가


    /**
     * 회원 정보 업데이트
     * @param member MemberVO 객체
     * @return update 결과 (영향받은 행 수)
     */
    int update(MemberVO member);

    /**
     * 비밀번호 업데이트
     * @param changePasswordDTO 비밀번호 변경 DTO
     * @return update 결과 (영향받은 행 수)
     */
    int updatePassword(ChangePasswordDTO changePasswordDTO);
}
