package com.example.userservice.dto;

import java.util.List;

public class FollowersDto {
    private String id;
    private String email;
    private String orgId;
    private List<String> followers;
    private List<String> following;
    private List<String> pending;
    private List<String> rejected;

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

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public List<String> getPending() {
        return pending;
    }

    public void setPending(List<String> pending) {
        this.pending = pending;
    }

    public List<String> getRejected() {
        return rejected;
    }

    public void setRejected(List<String> rejected) {
        this.rejected = rejected;
    }
}
