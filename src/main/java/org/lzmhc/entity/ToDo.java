package org.lzmhc.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class ToDo {
    @Id
    private String id;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at", columnDefinition = "TEXT DEFAULT (datetime('now','localtime'))")
    private LocalDateTime create_at;
    @Column(name = "updated_at", columnDefinition = "TEXT DEFAULT (datetime('now','localtime'))")
    private LocalDateTime update_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public LocalDateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        this.update_at = update_at;
    }
}
