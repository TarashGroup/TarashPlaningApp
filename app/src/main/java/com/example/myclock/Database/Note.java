package com.example.myclock.Database;
import android.media.Image;

public class Note {
    private Integer self_ID;
    private String title;
    private String text;
    private Image image;
    private int totalSeen, correct;
    private boolean favorite;

    public Note(String title, String text, Image image) {
        this.title = title;
        this.text = text;
        this.image = image;
        totalSeen = 0;
        favorite = false;
    }

    public String getText() {
        return text;
    }

    public void setSelf_ID (Integer ID) {
        this.self_ID = ID;
    }

    public Integer getSelf_ID() {
        return self_ID;
    }

    public Image getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalSeen() {
        return totalSeen;
    }

    public void setTotalSeen(int totalSeen) {
        this.totalSeen = totalSeen;
        updateSql();
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
        updateSql();
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
        updateSql();
    }

    private void updateSql () {
        //AllNotes.updateByID(self_ID, this);
    }
}
