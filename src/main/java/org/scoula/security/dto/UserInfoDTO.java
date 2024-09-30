package org.scoula.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private int user_no; // 기존의 `username`을 `user_id`로 변경
    private String user_id; // 이메일 필드 포함

    // MemberVO를 UserInfoDTO로 변환하는 메서드
    public static UserInfoDTO of(MemberVO member) {
        return new UserInfoDTO(
                member.getUser_no(), // 변경된 필드 반영
                member.getUser_id() // 누락된 이메일 정보 추가

        );
    }
}
