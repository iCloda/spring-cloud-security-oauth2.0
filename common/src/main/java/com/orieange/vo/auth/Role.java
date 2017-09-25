package com.orieange.vo.auth;

import com.orieange.model.basic.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

public class Role extends AbstractEntity {

    private String roleName;

    private String description;

    private Set<Menu> menus;

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

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
