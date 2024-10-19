package com.example.farmingmanagemant;

public class DataClassVideo {

    private String dataTitle;
    private int imageResId;
    private String url;

    public DataClassVideo(String dataTitle, int imageResId, String url) {
        this.dataTitle = dataTitle;
        this.imageResId = imageResId;
        this.url = url;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getUrl() {
        return url;
    }
}
