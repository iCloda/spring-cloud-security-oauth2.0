package com.orieange.vo.auth;

import com.orieange.model.basic.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

public class Subscriber extends AbstractEntity {

    private String loginName;

    private String username;

    private String password;

    private String mobile;

    private String email;

    private Set<Role> roles;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Set<String> userAuthotities = new HashSet<>();

    public Set<String> getUserAuthotities() {
        return userAuthotities;
    }

    public void setUserAuthotities(Set<String> userAuthotities) {
        this.userAuthotities = userAuthotities;
    }
}
