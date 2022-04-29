package org.example.common;

import java.io.Serializable;
import java.util.Arrays;

public class Message implements Serializable {
    static final long serialVersionUID = 1;
    private int number;
    private int [] content;
    private String author;

    public Message(int number, int [] content, String author) {
        this.number = number;
        this.content = content;
        this.author = author;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int[] getContent() {
        return content;
    }

    public void setContent(int[] content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Message{" +
                "number=" + number +
                ", content=" + Arrays.toString(content) +
                ", author='" + author + '\'' +
                '}';
    }
}
