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
    public  Card(){}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLecturesPresent() {
        return lecturesPresent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLecturesPresent(int lecturesPresent) {
        this.lecturesPresent = lecturesPresent;
    }

    public void setConducted(int lecturesConducted) {
        this.lecturesConducted = lecturesConducted;
    }

    public void setPercentages(int percentages) {
        this.percentages = percentages;
    }

    public int getConducted() {
        return lecturesConducted;
    }
    public int getPercentages() {
        return (int) (((float)lecturesPresent / lecturesConducted) * 100);
    }


}
