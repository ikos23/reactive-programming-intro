package com.ivk23.reactive.app.integration.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ivk23.reactive.app.integration.GitterMessageDeserializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Ivan Kos
 */
@Document
@JsonDeserialize(using = GitterMessageDeserializer.class)
public class GitterMessage implements Serializable {

    @Id
    private String id;

    private String text;

    private String html;

    private Instant sent;

    private GitterUser fromUser;

    private String username;

    public GitterMessage() {
    }

    public GitterMessage(String text, Instant sent, String username) {
        this.text = text;
        this.sent = sent;
        this.username = username;
    }

    public GitterMessage(String id, String text, String html, Instant sent, GitterUser fromUser) {
        this.id = id;
        this.text = text;
        this.html = html;
        this.sent = sent;
        this.fromUser = fromUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public GitterUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(GitterUser fromUser) {
        this.fromUser = fromUser;
    }

    public Instant getSent() {
        return sent;
    }

    public void setSent(Instant sent) {
        this.sent = sent;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String u) {
        this.username = u;
    }

    public String getUserID() {
        return this.fromUser.getId();
    }

    @Override
    public String toString() {
        return "GitterMessage{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", html='" + html + '\'' +
                ", sent=" + sent +
                ", fromUser=" + fromUser +
                ", username='" + username + '\'' +
                '}';
    }
}

