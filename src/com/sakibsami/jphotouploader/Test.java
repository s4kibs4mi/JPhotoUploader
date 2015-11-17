package com.sakibsami.jphotouploader;

import java.io.File;

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
 * This is the test example
 */

public class Test {
    public static void main(String args[]) {
        JPhotoUploader jPhotoUploader = new JPhotoUploader();
        jPhotoUploader.add(new File("/Users/s4kib/Desktop/bangladesh.jpg"));
        JResponse jResponse = jPhotoUploader.upload();
        System.out.println(jResponse.getResponse());
        System.out.println(jResponse.getPhotoName());
        System.out.println(jResponse.getPhotoURL());
        System.out.println(jResponse.getPhotoViewURL());
        System.out.println(jResponse.getPhotoWidth());
        System.out.println(jResponse.getPhotoHeight());
        System.out.println(jResponse.getPhotoThumbURL());
        System.out.println(jResponse.getPhotoThumbWidth());
        System.out.println(jResponse.getPhotoThumbHeight());
        System.out.println(jResponse.getPhotoSize());
    }
}
