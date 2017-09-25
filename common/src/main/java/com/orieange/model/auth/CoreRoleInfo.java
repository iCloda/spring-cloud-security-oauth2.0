package com.orieange.model.auth;

import com.orieange.model.basic.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="CORE_ROLE_INFO")
public class CoreRoleInfo extends AbstractEntity {

    @Column(name="RoleName")
    private String roleName;

    @Column(name="Description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "RELATION_ROLE_MENUS", joinColumns = @JoinColumn(name = "RoleID"), inverseJoinColumns = @JoinColumn(name = "MenuID"))
    @OrderBy("id")
    private Set<CoreMenuInfo> menus;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CoreMenuInfo> getMenus() {
        return menus;
    }

    public void setMenus(Set<CoreMenuInfo> menus) {
        this.menus = menus;
    }
}
