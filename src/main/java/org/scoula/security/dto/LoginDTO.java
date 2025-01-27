package org.scoula.security.dto;

import
        com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private String user_id;
    private String password;

    public static LoginDTO of(HttpServletRequest request) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(request.getInputStream(), LoginDTO.class); // JSON == 객체
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("user_id 또는 password가 없습니다.");
        }
    }
}