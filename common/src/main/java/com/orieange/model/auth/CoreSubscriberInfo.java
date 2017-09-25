package com.orieange.model.auth;

import com.orieange.model.basic.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CORE_SUBSCRIBER_INFO")
public class CoreSubscriberInfo extends AbstractEntity {

    @Column(name="LoginName")
    private String loginName;

    @Column(name="Username")
    private String username;

    @Column(name="Password")
    private String password;

    @Column(name="Mobile")
    private String mobile;

    @Column(name="Email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "RELATION_USER_ROLES", joinColumns = @JoinColumn(name = "UserID"), inverseJoinColumns = @JoinColumn(name = "RoleID"))
    @OrderBy("id")
    private Set<CoreRoleInfo> roles;

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

    public Set<CoreRoleInfo> getRoles() {
        return roles;
    }

    public void setRoles(Set<CoreRoleInfo> roles) {
        this.roles = roles;
    }

}
