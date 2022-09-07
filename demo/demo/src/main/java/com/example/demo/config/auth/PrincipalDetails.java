package com.example.demo.config.auth;

import com.example.demo.domain.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 1년동안 회원이 로그인을 하지 않으면 휴먼 계정으로 하기로 했다!
    @Override
    public boolean isEnabled() {
        // if(현재시간 - 로그인시간 > 1년) return false
        return true;
    }
}

// 오브젝트 접근을 위해서는 [ Security Session -> Authentication 객체 -> UserDetails 겍체 ] 를 꺼내야 함