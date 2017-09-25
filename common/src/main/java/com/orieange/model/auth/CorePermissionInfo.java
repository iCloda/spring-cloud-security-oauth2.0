package com.orieange.model.auth;

import com.orieange.model.basic.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CORE_PERMISSION_INFO")
public class CorePermissionInfo extends AbstractEntity {

    @Column(name="Name")
    private String name;

    @Column(name="Value")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
