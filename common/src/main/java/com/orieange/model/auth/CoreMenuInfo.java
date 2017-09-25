package com.orieange.model.auth;

import com.orieange.model.basic.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="CORE_MENU_INFO")
public class CoreMenuInfo extends AbstractEntity {

    @Column(name="Code")
    private String code;

    @Column(name="PCode")
    private String pCode;

    @Column(name="MenuName")
    private String menuName;

    @Column(name="Description")
    private String description;

    @Column(name="Img")
    private String img;

    @Column(name="Disabled")
    private Boolean disabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "RELATION_MENU_PERMISSIONS", joinColumns = @JoinColumn(name = "MenuID"), inverseJoinColumns = @JoinColumn(name = "permissionID"))
    @OrderBy("id")
    private Set<CorePermissionInfo> permissions;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Set<CorePermissionInfo> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<CorePermissionInfo> permissions) {
        this.permissions = permissions;
    }
}
