package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinDTO {
    String user_id;
    String password;
    String birthdate;
    private MultipartFile avatar; // 사용자 아바타 (이미지 파일)

    public MemberVO toVO() {
        return MemberVO.builder()
                .user_id(user_id)
                .password(password)
                .birthdate(birthdate)
                .build();
    }
}