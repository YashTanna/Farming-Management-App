package com.example.farmingmanagemant;

public class DataClassLoan {

    private String dataTitle;
    private int dataDesc;  // Reference to string resource (R.string)
    private String dataLang;  // This could be the "Next" or language label
    private int dataImage;  // Reference to drawable resource (R.drawable)
    private String dataUrl;  // URL for "Next" button navigation

    // Constructor
    public DataClassLoan(String dataTitle, int dataDesc, String dataLang, int dataImage, String dataUrl) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
        this.dataUrl = dataUrl;
    }

    // Getters
    public String getDataTitle() {
        return dataTitle;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public int getDataImage() {
        return dataImage;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    // Setters
    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public void setDataDesc(int dataDesc) {
        this.dataDesc = dataDesc;
    }

    public void setDataLang(String dataLang) {
        this.dataLang = dataLang;
    }

    public void setDataImage(int dataImage) {
        this.dataImage = dataImage;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}

