package com.hospital.security.services;

import com.hospital.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final String email;

    public UserDetailsImpl(String id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail()
        );
    }

    public String getId() { return id; }
    public String getEmail() { return email; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Customize for roles if needed
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
