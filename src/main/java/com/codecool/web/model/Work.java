package com.codecool.web.model;

public class Work {

    int id;
    int poetId;
    String title;
    String content;
    String written;


    public Work(int id, int poetId, String title, String content, String written) {
        this.id = id;
        this.poetId = poetId;
        this.title = title;
        this.content = content;
        this.written = written;
    }

    public int getId() {
        return id;
    }

    public int getPoetId() {
        return poetId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWritten() {
        return written;
    }
}
