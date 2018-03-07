package com.small.business.model.user;

import com.small.business.model.base.BaseMessage;

public class Consultant extends BaseMessage {

    private String name;
    private Long consultantId;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Long getConsultantId() {

        return consultantId;
    }

    public void setConsultantId(Long consultantId) {

        this.consultantId = consultantId;
    }

    @Override
    public String toString() {

        return "Consultant [name=" + name + ", consultantId=" + consultantId + "]";
    }

}
