package org.askforai._core.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails principal;
    private String token;

    public JwtAuthenticationToken(UserDetails principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = token;
        setAuthenticated(true); // 인증된 상태로 설정
    }

    @Override
    public Object getCredentials() {
        return token; // 토큰을 자격 증명으로 반환
    }

    @Override
    public Object getPrincipal() {
        return principal; // UserDetails를 주체로 반환
    }
}
