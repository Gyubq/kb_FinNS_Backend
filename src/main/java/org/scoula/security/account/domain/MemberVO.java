package org.scoula.security.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO {
    private int user_no; // admin
    private int card_no; //
    private String user_id; //
    private String name;
    private String birthdate;
    private String password;
    private int mbti_no;
    private String user_img_url;
    private String update_date;
    UserDetails details;
}