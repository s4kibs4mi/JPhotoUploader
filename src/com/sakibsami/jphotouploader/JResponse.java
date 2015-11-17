package com.sakibsami.jphotouploader;

/**
 * JPhotoUploader is a Java Library to share photos online easily
 * It uses uploads.im api to host photos
 * Oh It takes a night to find out the site
 * There's lots of site like it on the net but this one has no limitation
 * Please don't spam
 * ***********************
 * Create By            **
 * Sakib Sami           **
 * s4kibs4mi@gmail.com  **
 * www.sakibsami.com    **
 * ***********************
 * ***********************
 * JResponse is to Handle photo upload response
 */

public class JResponse {
    private String response;
    private String photoName;
    private String photoURL;
    private String photoViewURL;
    private String photoWidth;
    private String photoHeight;
    private String photoSize;
    private String photoThumbURL;
    private String photoThumbWidth;
    private String photoThumbHeight;

    public String getResponse() {
        return response;
    }

    public String getPhotoName() {
        return photoName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getPhotoViewURL() {
        return photoViewURL;
    }

    public String getPhotoWidth() {
        return photoWidth;
    }

    public String getPhotoHeight() {
        return photoHeight;
    }

    public String getPhotoSize() {
        return photoSize;
    }

    public String getPhotoThumbURL() {
        return photoThumbURL;
    }

    public String getPhotoThumbWidth() {
        return photoThumbWidth;
    }

    public String getPhotoThumbHeight() {
        return photoThumbHeight;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setPhotoViewURL(String photoViewURL) {
        this.photoViewURL = photoViewURL;
    }

    public void setPhotoWidth(String photoWidth) {
        this.photoWidth = photoWidth;
    }

    public void setPhotoHeight(String photoHeight) {
        this.photoHeight = photoHeight;
    }

    public void setPhotoSize(String photoSize) {
        this.photoSize = photoSize;
    }

    public void setPhotoThumbURL(String photoThumbURL) {
        this.photoThumbURL = photoThumbURL;
    }

    public void setPhotoThumbWidth(String photoThumbWidth) {
        this.photoThumbWidth = photoThumbWidth;
    }

    public void setPhotoThumbHeight(String photoThumbHeight) {
        this.photoThumbHeight = photoThumbHeight;
    }
}
