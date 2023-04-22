package com.example.feedtrack.model;

public class attendance {
    String prn,sub;
    int conducted,attended,percentage;

    public attendance(String prn, String sub, int conducted, int attended, int percentage) {
        this.prn = prn;
        this.sub = sub;
        this.conducted = conducted;
        this.attended = attended;
        this.percentage = percentage;
    }

    public attendance() {
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public int getConducted() {
        return conducted;
    }

    public void setConducted(int conducted) {
        this.conducted = conducted;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getPercentage() {
        return (int)((float)attended/(float)conducted)*100;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
