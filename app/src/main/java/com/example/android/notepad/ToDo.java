package com.example.android.notepad;

public class ToDo {
    private long id;
    private String content;
    private boolean completed;

    public ToDo(long id, String content, boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
