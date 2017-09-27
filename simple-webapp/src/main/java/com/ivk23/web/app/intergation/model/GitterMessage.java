package com.ivk23.web.app.intergation.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author Ivan Kos
 */
@Entity
@Table(name = "messages")
public class GitterMessage implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "text")
    private String text;
    @Column(name = "html")
    private String html;
    @Column(name = "sent_date")
    private Instant sent;

    @Transient
    private GitterUser fromUser;

    @Column(name = "user")
    private String username;

    public GitterMessage() {
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

}

