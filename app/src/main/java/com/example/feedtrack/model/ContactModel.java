package com.example.feedtrack.model;

public class ContactModel
{
    public String prn;

    public  ContactModel(String prn)
    {
        this.prn=prn;
    }
    public ContactModel(){}

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }
}
