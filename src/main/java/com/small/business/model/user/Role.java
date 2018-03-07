package com.small.business.model.user;

import com.small.business.model.base.BaseMessage;

public class Role extends BaseMessage {

    private String name;
    private Long roleId;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Long getRoleId() {

        return roleId;
    }

    public void setRoleId(Long roleId) {

        this.roleId = roleId;
    }

    @Override
    public String toString() {

        return "Role [name=" + name + ", roleId=" + roleId + "]";
    }

}
