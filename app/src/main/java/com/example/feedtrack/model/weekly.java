package com.example.feedtrack.model;

public class weekly {
    String interactive,understand,detail,relevant,suggestions,prn;

    public weekly(String interactive, String understand, String detail, String relevant, String suggestions) {
        this.interactive = interactive;
        this.understand = understand;
        this.detail = detail;
        this.relevant = relevant;
        this.suggestions = suggestions;
    }

    public weekly() {
    }
    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }
    public String getInteractive() {
        return interactive;
    }

    public void setInteractive(String interactive) {
        this.interactive = interactive;
    }

    public String getUnderstand() {
        return understand;
    }

    public void setUnderstand(String understand) {
        this.understand = understand;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRelevant() {
        return relevant;
    }

    public void setRelevant(String relevant) {
        this.relevant = relevant;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
}
