package com.example.userservice.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.List;

public class FollowerDto {

    private String id;
    private String email;
    private String orgId;
    private String requester_id;
    private Integer status;

    public String getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(String requester_id) {
        this.requester_id = requester_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
