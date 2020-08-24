package com.example.myclock.Database;
import android.media.Image;

public class Note {
    private String title;
    private String text;
    private Image image;

    public Note(String title, String text, Image image) {
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public Image getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}