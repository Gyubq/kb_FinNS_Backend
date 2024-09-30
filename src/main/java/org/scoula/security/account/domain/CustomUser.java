package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
@Setter
public class CustomUser extends User { // 확장
    private MemberVO member; // 실질적인 사용자 데이터

    // MemberVO를 사용하는 생성자
    public CustomUser(MemberVO vo) {
        // 빈 권한 컬렉션 전달
        super(vo.getUser_id(), vo.getPassword(), Collections.emptyList()); // 빈 권한으로 생성
        this.member = vo; // super 호출 이후 멤버 변수 초기화
    }
}
