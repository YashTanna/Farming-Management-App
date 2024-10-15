package com.example.farmingmanagemant;

public class DataClass {

    private String dataTitle;
    private int dataDesc;
    private String dataLang;
    private int dataImage;
    private String dataUrl;  // Add URL field

    // Constructor
    public DataClass(String dataTitle, int dataDesc, String dataLang, int dataImage, String dataUrl) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
        this.dataUrl = dataUrl;  // Initialize URL
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
        return dataUrl;  // Getter for URL
    }
}
