JPhotoUploader
--------------
JPhotoUploader is an open source java library to share image online easily.
Also one can embedded it in any java project.
The project uses upload.im site's api to host image files. The site has no limitation but only maximum image file size limit is 64MB.
It's also useable in any Android project.


----------


**Build**
Just download the project and import in Intellj Idea or you can use the jar.


----------


**Get Started**
[Download the jar file from here](https://github.com/s4kibs4mi/JPhotoUploader/blob/master/out/artifacts/JPhotoUploader_jar/JPhotoUploader.jar?raw=true)

*Standalone Application :*
Run the jar file using Java 8 jre and you are done.

![ Main Screen ](https://github.com/s4kibs4mi/JPhotoUploader/blob/master/screens/Screen1.png?raw=true)

![ Screen with files ](https://github.com/s4kibs4mi/JPhotoUploader/blob/master/screens/Screen2.png?raw=true)

1. Click on add button to add files.
2.  Choose All / Selected to upload files. If selected the select files and click upload to upload the files.
3. Once uploaded the status will be changed.Click on a file to get full response information.
4. Click reset button to reset the panel.
5. Click donate button to consider a donation on paypal.


----------


*Emdedded in application :*
*Upload Single File*

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

*Upload Multiple Files :*

    JPhotoUploader jPhotoUploader = new JPhotoUploader();

    // Add all files in a list
    ArrayList<File> files = new ArrayList<>();
    files.add(new File("/Users/s4kib/Desktop/bangladesh.jpg"));
    files.add(new File("/Users/s4kib/Desktop/bangladesh2.jpg"));
    files.add(new File("/Users/s4kib/Desktop/bangladesh3.jpg"));

    // Call addAll to add all files
    jPhotoUploader.addAll(files);

    // Call uploadAll to upload all files
    ArrayList<JResponse> jResponses = jPhotoUploader.uploadAll();

    // jPhotoUploader.upload(2); // To upload a specific file from list

    for (JResponse jResponse : jResponses) {
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


**[Here is the full tutorial & Documentation](http://www.sakibsami.com/2015/11/jphotouploader-usage-documentation.html)**

Licensing
---------
The Project is available under two licenses, the GPL and a commercial license. If you are willing to integrate this project into a close source application, please contact me at s4kibs4mi at gmail.com. Thank you.
