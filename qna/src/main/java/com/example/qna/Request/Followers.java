package com.example.qna.Request;

import java.io.Serializable;
import java.util.List;

public class Followers implements Serializable {
    private List<String> followers;

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}
