package com.example.newsfetcher;

import com.google.gson.annotations.SerializedName;

public class Articles {
    @SerializedName("title")
    private String title ;
    @SerializedName("description")
    private String description ;

    @SerializedName("urlToImage")
    private String urlToImage ;
    @SerializedName("url")
    private String url ;
    @SerializedName("content")
    private String content ;

    public Articles(String title, String description, String urlToImage, String url, String content) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
