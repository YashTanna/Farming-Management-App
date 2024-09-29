package com.example.farmingmanagemant;

public class Lone_item {

    private String loan_name;
    private String loan_description;
    private int image;

    public Lone_item(String loan_name, String loan_description, int image) {
        this.loan_name = loan_name;
        this.loan_description = loan_description;
        this.image = image;
    }

    public String getLoan_name() {
        return loan_name;
    }

    public String getLoan_description() {
        return loan_description;
    }

    public int getImage() {
        return image;
    }

    public void setLoan_name(String loan_name) {
        this.loan_name = loan_name;
    }

    public void setLoan_description(String loan_description) {
        this.loan_description = loan_description;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
