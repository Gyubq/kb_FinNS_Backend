package org.scoula.member.service;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;

public interface MemberService {

    /**
     * 사용자 ID 중복 체크
     * @param userId 사용자 ID
     * @return 중복 여부 (true: 중복됨, false: 중복되지 않음)
     */

    boolean findById(String userId);

    /**
     * 사용자 정보 조회
     * @param userId 사용자 ID
     * @return 사용자 정보 DTO
     */
    MemberDTO get(String userId);

    /**
     * 회원 가입 처리
     * @param member 회원 가입 DTO
     * @return 가입된 사용자 정보 DTO
     */
    MemberDTO join(MemberJoinDTO member);

    /**
     * 사용자 정보 업데이트
     * @param member 사용자 업데이트 DTO
     * @return 업데이트된 사용자 정보 DTO
     */
    MemberDTO update(MemberUpdateDTO member);

    /**
     * 비밀번호 변경 처리
     * @param changePassword 비밀번호 변경 DTO
     */
    void changePassword(ChangePasswordDTO changePassword);
}
