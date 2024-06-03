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
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class generatePdf_AI {
    public static void main(String[] args) throws FileNotFoundException {

        Produit p1 = new Produit("Pc HP fgh fgh fgh fgh fgh fgh bureau",15,1850);
        Produit p2 = new Produit("Souris HP",15,25);
        Produit p3 = new Produit("Clavier azerty",15,85);
        Produit p4 = new Produit("Ecran ACER",15,550);



        ArrayList<Produit> produits = new ArrayList<>();
        produits.add(p1);
        produits.add(p2);
        produits.add(p3);
        produits.add(p4);

        genererFacture("factureGenerated_AI.pdf","Coca Cola","61000 quartier industriel, 154 n°18","Oujda","Maroc",
                        125400,"FC 56800","Salmi Abdessamad","El aioun sidi mellouck",
                    "AdboSalm125@gmail.com","0627860225", produits, 500,500, 500,500,500,500,500);
    }

    //TODO : hadi kadir ghi lees produits
    public static void genererFacture(String emplacement, String libelleEntr, String adresseEntr, String villeEntr, String paysEntr,
                                      int numFact, String cin, String nomComplet, String adresse, String mail, String tel, ArrayList<Produit> produits,
                                      double totalFac, double remisePercentage, double remiseVal,double netComm, double tvaPercentage,double tvaVal, double totalTTC) throws FileNotFoundException {
        String chemin = emplacement;
        PdfWriter ecrivainPdf = new PdfWriter(chemin);
        PdfDocument documentPdf = new PdfDocument(ecrivainPdf);
        documentPdf.setDefaultPageSize(PageSize.A4);

        Document document = new Document(documentPdf);

        Paragraph spaces = new Paragraph();

        // Header: Title
        Paragraph title = new Paragraph("Facture").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(24);
        document.add(title);

        // Seller Information
        Paragraph infosVendeur = new Paragraph()
                .add(libelleEntr+"\n")
                .add(adresseEntr+"\n")
                .add(villeEntr+"\n")
                .add(paysEntr+"\n")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12);

        document.add(infosVendeur);

        Border grayBorder = new SolidBorder(Color.GRAY, 2f);
        Table divider = new Table(new float[]{190f*3});
        divider.setBorder(grayBorder);
        document.add(divider);

        spaces.add("\n");
        document.add(spaces);

        float threeCol =190f;
        float twocol = 285f;
        float twocol150 = twocol + 150 ;
        float twoColWidth[] = {twocol150, twocol};
        float fullWidth[] = {threeCol*3};

        Table tableInfos = new Table(new float[]{450f,270f});
        Table infosFacture = new Table(new float[]{100f, 170f});
        infosFacture.addCell(new Cell().add("Facture N° :").setBold().setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add(String.valueOf(numFact)).setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add("Date :").setBold().setBorder(Border.NO_BORDER));
        infosFacture.addCell(new Cell().add(LocalDate.now().toString()).setBorder(Border.NO_BORDER));



        Table infosAcheteur = new Table(new float[]{105f, 345f});
        infosAcheteur.addCell(new Cell().add("CIN :").setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add(cin).setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("Nom complet :") .setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add(nomComplet).setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("Adresse :").setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add(adresse).setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("Telephone :").setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add(tel).setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add("E-mail :").setBold().setBorder(Border.NO_BORDER));
        infosAcheteur.addCell(new Cell().add(mail).setBorder(Border.NO_BORDER));



        tableInfos.addCell(new Cell().add(infosAcheteur).setBorder(Border.NO_BORDER));
        tableInfos.addCell(new Cell().add(infosFacture));

        document.add(tableInfos);




        Table table = new Table(new float[]{330f,90f,150f,150f});
        //table.setWidthPercent(100);
        table.addCell(new Cell().add("Désignation").setTextAlignment(TextAlignment.CENTER).setBold());
        table.addCell(new Cell().add("Quantité").setTextAlignment(TextAlignment.CENTER).setBold());
        table.addCell(new Cell().add("Prix unitaire").setTextAlignment(TextAlignment.CENTER).setBold());
        table.addCell(new Cell().add("Montant total").setTextAlignment(TextAlignment.CENTER).setBold());


        for (Produit produit : produits) {
            table.addCell(produit.getDesignation());
            table.addCell(new Cell().add(String.valueOf(produit.getQuantite())).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(String.valueOf(produit.getPrix())).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(String.valueOf(produit.getTotal())).setTextAlignment(TextAlignment.CENTER));
        }
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("Montant total HT "));
        table.addCell(new Cell().add(String.valueOf(totalFac)).setTextAlignment(TextAlignment.CENTER));

        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("Remise " + remisePercentage +" %"));
        table.addCell(new Cell().add(String.valueOf(remiseVal)).setTextAlignment(TextAlignment.CENTER));

        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("Net commercial"));
        table.addCell(new Cell().add(String.valueOf(netComm)).setTextAlignment(TextAlignment.CENTER));

        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("Tva " + tvaPercentage + " %"));
        table.addCell(new Cell().add(String.valueOf(tvaVal)).setTextAlignment(TextAlignment.CENTER));

        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("Net à payer TTC").setBold());
        table.addCell(new Cell().add(totalTTC+" DH").setTextAlignment(TextAlignment.CENTER).setBold());
        document.add(spaces);
        document.add(table);

        // Footer: Seller Name and Signature Space
        Paragraph footer = new Paragraph("Nom du Vendeur: [Espace pour Signature]").setTextAlignment(TextAlignment.RIGHT).setFontSize(12);
        document.add(footer);

        //TODO : see if l'entreprise a quoi la base ( service ou produit ), si oui 3ed dir li lteht
        //TODO : see if la facture mentionné a des services ( le type dialha )
        document.close();
    }
}

