package com.sakibsami.jphotouploader;

import com.squareup.okhttp.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;

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
 * JPhotoUploader is the main class which handles all photo uploading task
 */

public class JPhotoUploader {
    private String URL = "http://uploads.im/api?upload";
    private OkHttpClient client;
    private Request request;
    private RequestBody singleRequestBody;
    private ArrayList<RequestBody> requestBodies;

    public JPhotoUploader() {
        client = new OkHttpClient();
    }

    public void add(File fileToUpload) {
        singleRequestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart("file", fileToUpload.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload))
                .addFormDataPart("format", "xml")
                .build();
    }

    public void addAll(ArrayList<File> filesToUpload) {
        requestBodies = new ArrayList<>();

        for (File fileToUpload : filesToUpload) {
            requestBodies.add(new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("file", fileToUpload.getName(),
                            RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload))
                    .addFormDataPart("format", "xml")
                    .build());
        }
    }

    public JResponse upload() {
        try {
            request = new Request.Builder().url(URL).post(singleRequestBody).build();
            return formatResponse(client.newCall(request).execute().body().string());
        } catch (Exception ex) {
            JResponse jResponse = new JResponse();
            jResponse.setResponse(ex.getMessage());
            return jResponse;
        }
    }

    public JResponse upload(int index) {
        try {
            request = new Request.Builder().url(URL).post(requestBodies.get(index)).build();
            return formatResponse(client.newCall(request).execute().body().string());
        } catch (Exception ex) {
            JResponse jResponse = new JResponse();
            jResponse.setResponse(ex.getMessage());
            return jResponse;
        }
    }

    public ArrayList<JResponse> uploadAll() {
        ArrayList<JResponse> responses = new ArrayList<>();
        for (RequestBody requestBody : requestBodies) {
            try {
                request = new Request.Builder().url(URL).post(requestBody).build();
                responses.add(formatResponse(client.newCall(request).execute().body().string()));
            } catch (Exception ex) {
                JResponse jResponse = new JResponse();
                jResponse.setResponse(ex.getMessage());
                responses.add(jResponse);
            }
        }
        return responses;
    }

    private JResponse formatResponse(String xmlString) {
        JResponse jResponse = new JResponse();
        Document document = Jsoup.parse(xmlString);
        int response = Integer.parseInt(document.getElementsByTag("status_code").text());
        jResponse.setResponse(document.getElementsByTag("status_code").text());

        if (response == 200) {
            jResponse.setPhotoName(document.getElementsByTag("img_name").text());
            jResponse.setPhotoURL(document.getElementsByTag("img_url").text());
            jResponse.setPhotoViewURL(document.getElementsByTag("img_view").text());
            jResponse.setPhotoWidth(document.getElementsByTag("img_width").text());
            jResponse.setPhotoHeight(document.getElementsByTag("img_height").text());
            jResponse.setPhotoSize(document.getElementsByTag("img_size").text());
            jResponse.setPhotoThumbURL(document.getElementsByTag("thumb_url").text());
            jResponse.setPhotoThumbWidth(document.getElementsByTag("thumb_width").text());
            jResponse.setPhotoThumbHeight(document.getElementsByTag("thumb_height").text());
        }
        return jResponse;
    }
}
