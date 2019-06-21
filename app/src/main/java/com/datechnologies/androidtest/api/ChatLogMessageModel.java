package com.datechnologies.androidtest.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * A data model that represents a chat log message fetched from the D & A Technologies Web Server.
 */

public class ChatLogMessageModel {

    @SerializedName("data")
    public List<Datum> data = new ArrayList<>();

    public List<Datum> getData() {
        return data;
    }

    public static class Datum {

        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("avatar_url")
        @Expose
        public String avatarUrl;
        @SerializedName("message")
        @Expose
        public String message;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
