package org.example;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;

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

        Table table = new Table(twoColWidth);
        table.addCell(new Cell().add("Facture").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());

            Table nestedTable = new Table(new float[]{twocol/2, twocol/2});
                nestedTable.addCell(new Cell().add("Facture N°:").setBold().setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add("11/5495/330").setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add("Date Facture").setBold().setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add("31/05/2024").setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

        Border grayBorder = new SolidBorder(Color.GRAY, 2f);
        Table divider = new Table(fullWidth);
        divider.setBorder(grayBorder);

        doc.add(table);
        doc.add(divider);






        doc.close();
    }
}
