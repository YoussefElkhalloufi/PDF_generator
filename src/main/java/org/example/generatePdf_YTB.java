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

        Table table = new Table(twoColWidth);
        table.addCell(new Cell().add("Facture").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());

            Table nestedTable = new Table(new float[]{twocol/2, twocol/2});
                nestedTable.addCell(new Cell().add("Facture N°:").setBold().setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add("11/5495/330").setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add("Date Facture").setBold().setBorder(Border.NO_BORDER));
                nestedTable.addCell(new Cell().add(String.valueOf(LocalDate.now())).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

        Border grayBorder = new SolidBorder(Color.GRAY, 2f);
        Table divider = new Table(fullWidth);
        divider.setBorder(grayBorder);

        doc.add(table);
        doc.add(divider);

        //Infos vendeur
        Paragraph infosVendeur = new Paragraph()
                .add(new Text("Société : ").setBold())
                .add("FacturEase\n")
                .add(new Text("Adresse : ").setBold())
                .add("123 Rue Principale\n")
                .add(new Text("Ville : ").setBold())
                .add("Oujda\n")
                .add(new Text("Pays : ").setBold())
                .add("XYZ\n")
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(12);



        //Infos Acheteur
        Paragraph infosAcheteur = new Paragraph()
                .add(new Text("Nom : ").setBold())
                .add("Jean Dupont\n")
                .add(new Text("Cin : ").setBold())
                .add("FC59627\n")
                .add(new Text("Telephone : ").setBold())
                .add("+212 6 27 86 02 25\n")
                .add(new Text("Adresse : ").setBold())
                .add("456 Avenue des Chênes\n")
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(12);

        // Create a table with two columns
        Table infoTable = new Table(new float[]{twocol150, twocol});
        infoTable.setWidthPercent(100);

        // Create the first cell containing the seller's information
        Cell vendeurCell = new Cell().add(infosVendeur).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER);
        infoTable.addCell(vendeurCell);

        // Create the second cell containing the buyer's information
        Cell acheteurCell = new Cell().add(infosAcheteur).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER);
        infoTable.addCell(acheteurCell).setTextAlignment(TextAlignment.LEFT);

        // Add the table to the document
        doc.add(infoTable);







        doc.close();
    }
}
