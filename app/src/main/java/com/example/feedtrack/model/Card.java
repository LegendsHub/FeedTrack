package com.example.feedtrack.model;

public class Card {
    private String title;
    private String description;
    private int lecturesPresent;
    private int lecturesConducted;
    private int percentages ;

    public Card(String title, String description, int lecturesPresent, int lecturesConducted) {
        this.title = title;
        this.description = description;
        this.lecturesPresent = lecturesPresent;
        this.lecturesConducted = lecturesConducted;
        this.percentages=(int) (((float)lecturesPresent / lecturesConducted) * 100);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLecturesPresent() {
        return lecturesPresent;
    }

    public int getLecturesConducted() {
        return lecturesConducted;
    }
    public int getPercentages() {
        return percentages;
    }


}
