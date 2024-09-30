package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private int user_no; // admin
    private int card_no; //
    private String user_id; //
    private String name;
    private String birthdate;
    private String password;
    private int mbti_no;
    private String user_img_url;
    private String update_date;
    MultipartFile avatar;
    private List<String> authList; // 권한 목록, join 처리 필요

    public static MemberDTO of(MemberVO m) {
        return MemberDTO.builder()
                .user_no(m.getUser_no())
                .card_no(m.getCard_no())
                .user_id(m.getUser_id())
                .name(m.getName())
                .birthdate(m.getBirthdate())
                .password(m.getPassword())
                .mbti_no(m.getMbti_no())
                .user_img_url(m.getUser_img_url())
                .update_date(m.getUpdate_date())
                .build();
    }

    public MemberVO toVO() {
        return MemberVO.builder()
                .user_no(user_no)
                .card_no(card_no)
                .user_id(user_id)
                .name(name)
                .birthdate(birthdate)
                .password(password)
                .mbti_no(mbti_no)
                .user_img_url(user_img_url)
                .update_date(update_date)
                .build();
    }
}