package com.small.business.model.audit;

import java.io.Serializable;
import java.util.Calendar;

public class AuditItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String dataRequest;
    private String dataResponse;
    private AuditStatus status;
    private AuditType type;
    private String user;
    private Calendar trackTime;

    public AuditItem(AuditType auditType) {
        if (auditType == null) {
            throw new IllegalArgumentException("auditType is required.");
        }

        this.type = auditType;
        this.trackTime = Calendar.getInstance();
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getDataRequest() {

        return dataRequest;
    }

    public void setDataRequest(String dataRequest) {

        this.dataRequest = dataRequest;
    }

    public String getDataResponse() {

        return dataResponse;
    }

    public void setDataResponse(String dataResponse) {

        this.dataResponse = dataResponse;
    }

    public AuditStatus getStatus() {

        return status;
    }

    public void setStatus(AuditStatus status) {

        this.status = status;
    }

    public AuditType getType() {

        return type;
    }

    public void setType(AuditType type) {

        this.type = type;
    }

    public static long getSerialversionuid() {

        return serialVersionUID;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public Calendar getTrackTime() {

        return trackTime;
    }

    public void setTrackTime(Calendar trackTime) {

        this.trackTime = trackTime;
    }

    @Override
    public String toString() {

        return "AuditItem [id=" + id + ", dataRequest=" + dataRequest + ", dataResponse=" + dataResponse + ", status=" + status + ", type=" + type + ", user=" + user + "]";
    }

    public static AuditItem create(AuditType auditType) {

        return new AuditItem(auditType);
    }

}
