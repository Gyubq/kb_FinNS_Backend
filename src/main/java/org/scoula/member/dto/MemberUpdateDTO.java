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
    private String username;
    private String email;
    private String birth;
    private MultipartFile avatar;

    // 비밀번호 변경 필드 추가
    private String oldPassword; // 이전 비밀번호
    private String newPassword; // 새 비밀번호

    // 기존 비밀번호 필드의 용도를 명확히 하기 위해 제거하거나 통합합니다.

    // MemberVO로 변환하는 메서드
    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .email(email)
                .birth(birth)
                .build();
    }
}
