package org.example;

import com.codingerror.model.AddressDetails;
import com.codingerror.model.HeaderDetails;
import com.codingerror.model.Product;
import com.codingerror.model.ProductTableHeader;
import com.codingerror.service.CodingErrorPdfInvoiceCreator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pdf_READY_toUse {

    //TODO : see WTF is going on with this, NB : it generated the pdf successfully but it doesnt open, even in a PDF READER ONLIINE
    public static void main(String[] args) throws FileNotFoundException {
        LocalDate ld= LocalDate.now();
        String pdfName= ld+".pdf";
        CodingErrorPdfInvoiceCreator cepdf=new CodingErrorPdfInvoiceCreator(pdfName);
        cepdf.createDocument();

        //Create Header start
        HeaderDetails header=new HeaderDetails();
        header.setInvoiceNo("RK35623").setInvoiceDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build();
        cepdf.createHeader(header);
        //Header End

        //Create Address start
        AddressDetails addressDetails=new AddressDetails();
        addressDetails
                .setBillingName("FacturEase")
                .setBillingCompany("Coding Error")
                .setBillingName("Bhaskar")
                .setBillingAddress("Bangluru,karnataka,india\n djdj\ndsjdsk")
                .setBillingEmail("codingerror303@gmail.com")
                .setShippingName("Customer Name \n")
                .setShippingAddress("Banglore Name sdss\n swjs\n")
                .build();

        cepdf.createAddress(addressDetails);
        //Address end

        //Product Start
        ProductTableHeader productTableHeader=new ProductTableHeader();
        cepdf.createTableHeader(productTableHeader);
        List<Product> productList= new ArrayList<>();
        productList.add(new Product("Apples",5,150));
        productList.add(new Product("Watches",5,150));
        productList.add(new Product("Tvs",5,150));
        productList.add(new Product("Phones",5,150));
        productList=cepdf.modifyProductList(productList);
        cepdf.createProduct(productList);
        //Product End

        System.out.println("pdf genrated");
    }
}