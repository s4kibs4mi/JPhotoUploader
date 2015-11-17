package com.sakibsami.jphotouploader;

import com.alee.laf.WebLookAndFeel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

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
 * JPhotoUploaderUI to do tasks visually
 */

public class JPhotoUploaderUI extends JFrame {
    private JLabel appLogo;
    private JScrollPane scrollPane1;
    private JTable photoFileHolder;
    private PhotoHolderModel photoHolderModel;
    private JComboBox options;
    private JButton addbtn;
    private JButton uploadBtn;
    private JLabel cpLabel;
    private JButton donationBtn;
    private JButton resetBtn;
    private JPhotoUploader jPhotoUploader;
    private Vector<JResponse> responseHolder;

    public JPhotoUploaderUI() {
        initComponents();
    }

    private void initComponents() {
        appLogo = new JLabel();
        scrollPane1 = new JScrollPane();
        photoFileHolder = new JTable();
        photoHolderModel = new PhotoHolderModel();
        options = new JComboBox();
        addbtn = new JButton();
        uploadBtn = new JButton();
        cpLabel = new JLabel();
        resetBtn = new JButton();
        donationBtn = new JButton();
        jPhotoUploader = new JPhotoUploader();
        responseHolder = new Vector<>();

        photoFileHolder.setModel(photoHolderModel);

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("JPhotoUploader");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- appLogo ----
        appLogo.setText("JPhotoUploader");
        appLogo.setHorizontalAlignment(SwingConstants.CENTER);
        appLogo.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        appLogo.setForeground(new Color(153, 0, 0));
        contentPane.add(appLogo);
        appLogo.setBounds(30, 10, 835, 80);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(photoFileHolder);
            photoFileHolder.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        viewResponseActionListener();
                    }
                }
            });
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(30, 95, 705, 360);
        contentPane.add(options);
        options.setBounds(740, 95, 125, 35);
        options.addItem("All");
        options.addItem("Selected");

        //---- addbtn ----
        addbtn.setText("Add");
        contentPane.add(addbtn);
        addbtn.setBounds(740, 135, 125, 35);
        addbtn.addActionListener(e -> photoAddBtnListener());

        //---- uploadBtn ----
        uploadBtn.setText("Upload");
        contentPane.add(uploadBtn);
        uploadBtn.setBounds(740, 420, 125, 35);
        uploadBtn.addActionListener(e -> uploadBtnActionListener());

        //---- cpLabel ----
        cpLabel.setText("Developer : Sakib Sami <s4kibs4mi@gmail.com> | www.sakibsami.com");
        cpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cpLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        contentPane.add(cpLabel);
        cpLabel.setBounds(30, 460, 835, 30);

        //---- responseBtn ----
        resetBtn.setText("Reset");
        donationBtn.setText("Donate");
        contentPane.add(resetBtn);
        contentPane.add(donationBtn);
        donationBtn.setBounds(740, 340, 125, 35);
        resetBtn.setBounds(740, 380, 125, 35);
        resetBtn.addActionListener(e -> resetBtnActionListener());
        donationBtn.addActionListener(e -> donationBtnActionListener());

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(900, 525);
        setLocationRelativeTo(getOwner());
    }

    private void photoAddBtnListener() {
        JFileChooser jFileChooser = new JFileChooser();
        int res = jFileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            Vector vector = new Vector();
            vector.add(selectedFile.getName());
            vector.add(selectedFile.getAbsolutePath());
            vector.add("None");
            vector.add("None");
            photoHolderModel.addItem(vector);
        }
    }

    private void uploadBtnActionListener() {
        Thread t = new Thread() {
            @Override
            public void run() {
                switch (options.getSelectedIndex()) {
                    case 0:
                        int tableLength = photoHolderModel.getRowCount();
                        for (int i = 0; i < tableLength; i++) {
                            jPhotoUploader.add(new File(photoHolderModel.getValueAt(i, 1).toString()));
                            JResponse jResponse = jPhotoUploader.upload();
                            responseHolder.add(i, jResponse);

                            photoHolderModel.setValueAt(jResponse.getResponse(), i, 2);
                            if (jResponse.getResponse().toLowerCase().contains("200")) {
                                photoHolderModel.setValueAt(jResponse.getPhotoViewURL(), i, 3);
                            }
                        }
                        break;
                    case 1:
                        int row = photoFileHolder.getSelectedRow();
                        if (row > -1) {
                            jPhotoUploader.add(new File(photoHolderModel.getValueAt(row, 1).toString()));
                            JResponse jResponse = jPhotoUploader.upload();
                            responseHolder.add(row, jResponse);

                            photoHolderModel.setValueAt(jResponse.getResponse(), row, 2);
                            if (jResponse.getResponse().toLowerCase().contains("200")) {
                                photoHolderModel.setValueAt(jResponse.getPhotoViewURL(), row, 3);
                            }
                        }
                        break;
                }
            }
        };
        t.start();
    }

    private void donationBtnActionListener() {
        String donationURL = "https://paypal.me/s4kibs4mi";
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        try {
            if (os.indexOf("win") >= 0) {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + donationURL);
            } else if (os.indexOf("mac") >= 0) {
                rt.exec("open " + donationURL);

            } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
                String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                        "netscape", "opera", "links", "lynx"};
                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++)
                    cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + donationURL + "\" ");
                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            } else {
                JOptionPane.showMessageDialog(this, "PLease visit : " + donationURL, "Upload Information", JOptionPane.NO_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "PLease visit : " + donationURL, "Upload Information", JOptionPane.NO_OPTION);
        }
    }

    private void resetBtnActionListener() {
        new JPhotoUploaderUI().setVisible(true);
        dispose();
    }

    private void viewResponseActionListener() {
        int row = photoFileHolder.getSelectedRow();
        if (row > -1) {
            JResponse jResponse = responseHolder.get(row);
            JEditorPane jEditorPane = new JEditorPane();
            jEditorPane.setEditorKit(new HTMLEditorKit());
            jEditorPane.setEditable(false);

            String responseHtml = "";
            responseHtml += "Status : " + jResponse.getResponse() + "<br>" +
                    "File Name : " + jResponse.getPhotoName() + "<br>" +
                    "Internal Path : " + photoHolderModel.getValueAt(row, 1) + "<br>" +
                    "Photo URL : <a href=\"" + jResponse.getPhotoURL() + "\">" + jResponse.getPhotoURL() + "</a><br>" +
                    "Photo View URL : <a href=\"" + jResponse.getPhotoViewURL() + "\">" + jResponse.getPhotoViewURL() + "</a><br>" +
                    "Photo Width : " + jResponse.getPhotoWidth() + "<br>" +
                    "Photo Height : " + jResponse.getPhotoHeight() + "<br>" +
                    "Photo Size : " + jResponse.getPhotoSize() + "<br>" +
                    "Photo Thumb URL : <a href=\"" + jResponse.getPhotoThumbURL() + "\">" + jResponse.getPhotoThumbURL() + "</a><br>" +
                    "Photo Thumb Width : " + jResponse.getPhotoThumbWidth() + "<br>" +
                    "Photo Thumb Height : " + jResponse.getPhotoThumbHeight() + "<br>" +
                    "<br>[ CTRL + A = Mark All, CTRL + C = Copy ]";

            jEditorPane.setText(responseHtml);
            JOptionPane.showMessageDialog(this, jEditorPane, "Upload Information", JOptionPane.NO_OPTION);
        }
    }

    public static void main(String args[]) {
        try {
            WebLookAndFeel.install();
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (Exception ex) {

        }
        new JPhotoUploaderUI().setVisible(true);
    }
}
