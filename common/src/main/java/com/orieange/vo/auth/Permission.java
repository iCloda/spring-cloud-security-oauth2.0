package com.orieange.vo.auth;

import com.orieange.model.basic.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Table;

public class Permission extends AbstractEntity {

    private String name;

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
