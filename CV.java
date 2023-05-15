package com.company;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
public class CV {
    private JPanel cvPanel;
    private JTextField name;
    private JTextField address;
    private JTextField contact;
    private JTextField email;
    private JTextField skills1;
    private JTextField skills2;
    private JTextField skills3;
    private JTextField skills4;
    private JComboBox work;
    private JTextField college;
    private JTextField qualiA;
    private JButton SELECTIMAGEButton;
    private JLabel img;
    private JButton GENERATERESUMEButton;
    private JTextField qualiB;
    private JTextField location;
    JFrame frame = new JFrame();
    public CV() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(cvPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        SELECTIMAGEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","jpg","png");
                fileChooser.addChoosableFileFilter(filter);
                int rs = fileChooser.showSaveDialog(null);
                if(rs==JFileChooser.APPROVE_OPTION){
                    File selectedImage = fileChooser.getSelectedFile();
                    location.setText(selectedImage.getAbsolutePath());
                    img.setIcon(resize(location.getText()));
                }
            }
        });
        GENERATERESUMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(name.getText()==null||contact.getText()==null||address.getText()==null||email.getText()==null){
                    JOptionPane.showMessageDialog(null,"PLEASE ENTER ALL DETAILS TO GENERATE CV");
                }else{
                    try {
                        String nameOfFile = "D:\\PDFYAHA\\my.pdf";
                        Document myDocument = new Document();
                        PdfWriter.getInstance(myDocument, new FileOutputStream(nameOfFile));
                        myDocument.open();
                        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(location.getText());
                        img.setAbsolutePosition(473f, 750f);
                        img.scaleAbsolute(80f,70f);
                        PdfPTable table = new PdfPTable(2);
                        myDocument.add(img);
                        myDocument.add(new Paragraph(name.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,32, com.itextpdf.text.Font.BOLD,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,9,BaseColor.DARK_GRAY)));
                        myDocument.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,9, BaseColor.DARK_GRAY)));
                        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                        myDocument.add(new Paragraph("CONTACT DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,9, com.itextpdf.text.Font.BOLD,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph(email.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph(contact.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph(address.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,7, BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                        myDocument.add(new Paragraph("SKILLS",FontFactory.getFont(FontFactory.TIMES_BOLD,9, com.itextpdf.text.Font.BOLD,BaseColor.DARK_GRAY )));
                        table.setHeaderRows(1);
                        table.addCell(skills1.getText());
                        table.addCell(skills2.getText());
                        table.addCell(skills3.getText());
                        table.addCell(skills4.getText());
                        myDocument.add(table);
                        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                        myDocument.add(new Paragraph("QUALIFICATIONS", FontFactory.getFont(FontFactory.TIMES_BOLD,9, com.itextpdf.text.Font.BOLD, BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph(college.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph(qualiA.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph(qualiB.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                        myDocument.add(new Paragraph("WORK EXPERIENCE",FontFactory.getFont(FontFactory.TIMES_BOLD,10, com.itextpdf.text.Font.BOLD,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph(""+work.getSelectedItem(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,BaseColor.DARK_GRAY)));
                        myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                        myDocument.add(new Paragraph("REFERENCES",FontFactory.getFont(FontFactory.TIMES_BOLD,9, com.itextpdf.text.Font.BOLD,BaseColor.DARK_GRAY )));
                        myDocument.add(new Paragraph("Available upon request",FontFactory.getFont(FontFactory.TIMES_BOLD,6,BaseColor.DARK_GRAY )));
                        myDocument.close();
                        JOptionPane.showMessageDialog(null,"CV was successfully generated");
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }
                }
            }
        });
    }
    public ImageIcon resize(String path){
        ImageIcon myImg = new ImageIcon(path);
        Image image = myImg.getImage();
        Image newImage = image.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(newImage);
        return finalImage;
    }
}