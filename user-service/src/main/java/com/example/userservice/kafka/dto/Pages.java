package com.example.userservice.kafka.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Pages implements Serializable {
    private String pageId;
    private String userId;
    private Timestamp userViewTime;
    private String pageName;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getUserViewTime() {
        return userViewTime;
    }

    public void setUserViewTime(Timestamp userViewTime) {
        this.userViewTime = userViewTime;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
