package org.example;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class generatePdf_YTB {
    public static void main(String[] args) throws FileNotFoundException {

        String path = "facture_ytb.pdf";
        PdfWriter pdfW = new PdfWriter(path);
        PdfDocument pdfDoc = new PdfDocument(pdfW);
        pdfDoc.setDefaultPageSize(PageSize.A4);
        Document doc = new Document(pdfDoc);

        float threeCol =190f;
        float twocol = 285f;
        float twocol150 = twocol + 150 ;
        float twoColWidth[] = {twocol150, twocol};
        float fullWidth[] = {threeCol*3};

        Paragraph paragraph = new Paragraph();
        Paragraph copyRight = new Paragraph();
        copyRight.add("Copyright © by FacturEase 2024").setTextAlignment(TextAlignment.CENTER);
        doc.add(copyRight);


        Table table = new Table(new float[]{500,220});
        table.addCell(new Cell().add("Facture").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());

            Table nestedTable = new Table(new float[]{100, 185});
                nestedTable.addCell(new Cell().add("N° :").setBold().setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add("11/5495/330").setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add("Date :").setBold().setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add(String.valueOf(LocalDate.now())).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

        Border grayBorder = new SolidBorder(Color.GRAY, 2f);
        Table divider = new Table(fullWidth);
        divider.setBorder(grayBorder);

        doc.add(table);
        doc.add(paragraph);
        doc.add(divider);

        paragraph.add("\n");
        doc.add(paragraph);

        Table tableInfos = new Table(new float[]{360f,360f});
        Table infosFacture = new Table(new float[]{60f, 210f});
        infosFacture.addCell(new Cell().add("Société :").setBold().setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("Maroc telecom Corporation").setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("Adresse :").setBold().setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("72 hay el mohammadi n° 21 el aioun sidi mellouck").setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("Ville :").setBold().setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("El aioun sidi mellouck taourirt").setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("Pays :").setBold().setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("Maroc").setBorder(Border.NO_BORDER));



        Table infosAcheteur = new Table(new float[]{100f, 350f});
        infosAcheteur.addCell(new Cell().add("Nom :") .setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("Youssef el khalloufi").setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("Adresse :").setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("El aioun sidi mellouck").setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("Telephone :").setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("06-27-86-02-25").setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("CIN :").setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("FC59627").setBorder(Border.NO_BORDER));

        tableInfos.addCell(new Cell().add(infosAcheteur).setBorder(Border.NO_BORDER));
        tableInfos.addCell(new Cell().add(infosFacture));

        doc.add(tableInfos);

        doc.close();
    }
}
