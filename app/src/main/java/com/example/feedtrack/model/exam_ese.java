package com.example.feedtrack.model;

public class exam_ese {
    String prn,understanding,cover,needs,suggestions,difficulty;

    public exam_ese(String prn, String understanding, String cover, String needs, String suggestions, String difficulty) {
        this.prn = prn;
        this.understanding = understanding;
        this.cover = cover;
        this.needs = needs;
        this.suggestions = suggestions;
        this.difficulty = difficulty;
    }

    public exam_ese() {
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getUnderstanding() {
        return understanding;
    }

    public void setUnderstanding(String understanding) {
        this.understanding = understanding;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
