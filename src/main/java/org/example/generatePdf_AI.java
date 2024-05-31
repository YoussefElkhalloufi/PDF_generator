package org.example;


import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class generatePdf_AI {
    public static void main(String[] args) throws FileNotFoundException {
        String chemin = "facture.pdf";
        PdfWriter ecrivainPdf = new PdfWriter(chemin);
        PdfDocument documentPdf = new PdfDocument(ecrivainPdf);
        documentPdf.setDefaultPageSize(PageSize.A4);

        Document document = new Document(documentPdf);

        // Header: Title
        Paragraph title = new Paragraph("Facture").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(24);
        document.add(title);

        // Seller Information
        Paragraph infosVendeur = new Paragraph()
                .add("Société: ABC Corporation\n")
                .add("Adresse: 123 Rue Principale\n")
                .add("Ville: Villeville\n")
                .add("Pays: XYZ\n")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12);

        document.add(infosVendeur);

        // Buyer Information
        Paragraph infosAcheteur = new Paragraph()
                .add("Nom: Jean Dupont\n")
                .add("Adresse: 456 Avenue des Chênes\n")
                .add("Ville: Autreville\n")
                .add("Pays: XYZ\n")
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(12);

        document.add(infosAcheteur);

        // Invoice Information
        Paragraph infosFacture = new Paragraph()
                .add("\nInformations sur la Facture:\n")
                .add("Numéro: 12345\n")
                .add("Date: 31/05/2024\n")
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(12);

        document.add(infosFacture);

        // Products Information
        List<Produit> produits = Arrays.asList(
                new Produit("Produit A", 10, 50),
                new Produit("Produit B", 20, 30),
                new Produit("Produit C", 5, 100)
        );

        Table table = new Table(4);
        table.setWidthPercent(100);
        table.addCell("Produit");
        table.addCell("Quantité");
        table.addCell("Prix");
        table.addCell("Total");

        for (Produit produit : produits) {
            table.addCell(produit.getNom());
            table.addCell(String.valueOf(produit.getQuantite()));
            table.addCell(String.valueOf(produit.getPrix()));
            table.addCell(String.valueOf(produit.getTotal()));
        }

        document.add(new Paragraph("\nInformations sur les Produits:").setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        document.add(table);

        // Footer: Seller Name and Signature Space
        Paragraph footer = new Paragraph("Nom du Vendeur: [Espace pour Signature]").setTextAlignment(TextAlignment.RIGHT).setFontSize(12);
        document.add(footer);

        document.close();
    }

    static class Produit {
        private String nom;
        private int quantite;
        private double prix;

        public Produit(String nom, int quantite, double prix) {
            this.nom = nom;
            this.quantite = quantite;
            this.prix = prix;
        }

        public String getNom() {
            return nom;
        }

        public int getQuantite() {
            return quantite;
        }

        public double getPrix() {
            return prix;
        }

        public double getTotal() {
            return quantite * prix;
        }
    }
}
