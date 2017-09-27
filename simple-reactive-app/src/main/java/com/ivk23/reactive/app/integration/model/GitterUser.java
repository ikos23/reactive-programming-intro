package com.ivk23.reactive.app.integration.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ivk23.reactive.app.integration.GitterUserDeserializer;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Ivan Kos
 */
@Document
@JsonDeserialize(using = GitterUserDeserializer.class)
public class GitterUser implements Serializable {

    private String id;

    private String username;

    private String displayName;

    private String avatarUrl;

    public GitterUser() {
    }

    public GitterUser(String id) {
        this.id = id;
    }

    public GitterUser(String id, String username, String displayName, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

