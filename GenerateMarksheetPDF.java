/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fddb;

/**
 *
 * @author Admin
 */


import java.io.FileOutputStream;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class GenerateMarksheetPDF {
  
    private static String FILE ="";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
    private static String StudentName;
    private static String StudentEmail;
    private static String StudentEnNo;
    private static String StudentGender;
    private static String StudentSemester;
    private static String StudentSubject1Name;
    private static String StudentSubject1Marks;
    private static String StudentSubject2Name;
    private static String StudentSubject2Marks;
    private static String StudentSubject3Name;
    private static String StudentSubject3Marks;
    private static String StudentSubject4Name;
    private static String StudentSubject4Marks;
    private static String StudentSubject5Name;
    private static String StudentSubject5Marks;
    private static String StudentSubject6Name;
    private static String StudentSubject6Marks;
    private static String StudentSubject7Name;
    private static String StudentSubject7Marks;
    
    public GenerateMarksheetPDF(String mobNo){

   
        try {
            getStudentDetailsAndMarks(mobNo);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("d:/MarkSheets/"+StudentEnNo+".pdf"));
            document.open();            
            addMetaData(document);
            addTitlePage(document);           
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public static String getStudentDetailsAndMarks(String mbno){
        
        String toReturn="";
        
        try{
            Connection con=ConnectionPro.getConnection();
          
           String query = "Select sname,eno,email,gender,sem,subj1,m1,subj2,m2,subj3,m3,subj4,m4,subj5,m5,subj6,m6,subj7,m7 from Marks where mobno="+mbno;
           
           PreparedStatement pt = con.prepareStatement(query);          
          
          ResultSet rs = pt.executeQuery(); 
           while(rs.next()) {
        
         StudentName=rs.getString("sname");
         StudentEnNo=rs.getString("eno");
         StudentEmail=rs.getString("email");
         StudentGender=rs.getString("gender");
         StudentSemester=rs.getString("sem");
         StudentSubject1Name=rs.getString("subj1");
         StudentSubject1Marks=rs.getString("m1");
         StudentSubject2Name=rs.getString("subj2");
         StudentSubject2Marks=rs.getString("m2");
         StudentSubject3Name=rs.getString("subj3");
         StudentSubject3Marks=rs.getString("m3");
         StudentSubject4Name=rs.getString("subj4");
         StudentSubject4Marks=rs.getString("m4");
          StudentSubject5Name=rs.getString("subj5");
         StudentSubject5Marks=rs.getString("m5");
         StudentSubject6Name=rs.getString("subj6");
         StudentSubject6Marks=rs.getString("m6");
         StudentSubject7Name=rs.getString("subj7");
         StudentSubject7Marks=rs.getString("m7");  
         toReturn=StudentEnNo+StudentEmail+StudentSubject1Marks+StudentSubject2Marks+StudentSubject3Marks+StudentSubject4Marks+StudentSubject5Marks
                    +StudentSubject6Marks+StudentSubject7Marks;
         
         
         }
        }catch(Exception e){
           e.printStackTrace();
        }
        return toReturn;
    }

 


// iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void addTitlePage(Document document)
            throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);        
        Paragraph preface1 = new Paragraph("RTM NAGPUR UNIVERSITY", catFont);       
        preface1.setAlignment(Element.ALIGN_CENTER);        
        preface.add(preface1);
        addEmptyLine(preface, 1);        
        Paragraph preface2 = new Paragraph("Statement of Marks", catFont);        
        preface2.setAlignment(Element.ALIGN_CENTER);        
        preface.add(preface2);    
        addEmptyLine(preface, 1);        
        Paragraph preface3 = new Paragraph("Name of Student : " +StudentName , smallBold);
        preface.add(preface3);        
        addEmptyLine(preface, 1);
        Paragraph preface4 = new Paragraph("Enrollment No : " +StudentEnNo , smallBold);
        preface.add(preface4);        
        addEmptyLine(preface, 1);
        Paragraph preface5 = new Paragraph("Roll No : 42356", smallBold);
        preface.add(preface5);        
        addEmptyLine(preface, 1);
        Paragraph preface6 = new Paragraph("Gender : "+StudentGender, smallBold);
        preface.add(preface6);        
        addEmptyLine(preface, 1);
        Paragraph preface7 = new Paragraph("Semester :"+StudentSemester, smallBold);
        preface.add(preface7);        
        addEmptyLine(preface, 2);
        document.add(preface);     
        createTable(document); 
        AddQRCodetoMarkSheet(document);
       
    }   
    
    private static void createTable(Document d)
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("Subject Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Marks"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        PdfPCell c2 = new PdfPCell(new Phrase(StudentSubject1Name));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        c2 = new PdfPCell(new Phrase(StudentSubject1Marks));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);
        
        PdfPCell c3 = new PdfPCell(new Phrase(StudentSubject2Name));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c3);

        c3 = new PdfPCell(new Phrase(StudentSubject2Marks));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c3);
        
        PdfPCell c4 = new PdfPCell(new Phrase(StudentSubject3Name));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c4);

        c4 = new PdfPCell(new Phrase(StudentSubject3Marks));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c4);
        
        PdfPCell c5 = new PdfPCell(new Phrase(StudentSubject4Name));
        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c5);

        c5 = new PdfPCell(new Phrase(StudentSubject4Marks));
        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c5);
        
        PdfPCell c6 = new PdfPCell(new Phrase(StudentSubject5Name));
        c6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c6);

        c6 = new PdfPCell(new Phrase(StudentSubject5Marks));
        c6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c6);
        
        PdfPCell c7 = new PdfPCell(new Phrase(StudentSubject6Name));
        c7.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c7);

        c7 = new PdfPCell(new Phrase(StudentSubject6Marks));
        c7.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c7);
        
        PdfPCell c8 = new PdfPCell(new Phrase(StudentSubject7Name));
        c8.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c8);

        c8 = new PdfPCell(new Phrase(StudentSubject7Marks));
        c8.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c8);
        
        PdfPCell c9 = new PdfPCell(new Phrase("Total out of 700: "));
        c9.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c9);

     
       int m1=Integer.parseInt(StudentSubject1Marks);
       int m2=Integer.parseInt(StudentSubject2Marks);
       int m3=Integer.parseInt(StudentSubject3Marks);
       int m4=Integer.parseInt(StudentSubject4Marks);
       int m5=Integer.parseInt(StudentSubject5Marks);
       int m6=Integer.parseInt(StudentSubject6Marks);
       int m7=Integer.parseInt(StudentSubject7Marks);
       int t=m1+m2+m3+m4+m5+m6+m7;
       String total=String.valueOf(t);  
        c9 = new PdfPCell(new Phrase(total));
        c9.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c9);
        d.add(table);

    }
    
     private static void AddQRCodetoMarkSheet(Document d)
            throws BadElementException, DocumentException, IOException {
         
        Paragraph preface = new Paragraph("Scan this QR code to validate this marksheet..", smallBold);
        d.add(preface);        
        addEmptyLine(preface, 1);
         //Add Image
    Image image1 = Image.getInstance("D:\\MarksQRcodes\\"+StudentEnNo+".png");
    d.add(image1);       

    }
    

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}