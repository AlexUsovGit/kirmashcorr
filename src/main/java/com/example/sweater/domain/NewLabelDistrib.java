package com.example.sweater.domain;

import com.example.sweater.repos.ProductRepo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class NewLabelDistrib {

    @Autowired
    private ProductRepo productRepo;
    private String str;
    /*    private Product product;*/
    private DistributionDoc distributionDoc;
    private List<DistributionDoc> distributionDocs;
    private List<String> shops = new ArrayList<>();


    public static final String FONT = "arial.ttf";

    private List<Product> products;

    public List<DistributionDoc> getDistributionDocs() {
        return distributionDocs;
    }

    public void setDistributionDocs(List<DistributionDoc> distributionDocs) {
        this.distributionDocs = distributionDocs;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public NewLabelDistrib() {
    }

    public NewLabelDistrib(List<DistributionDoc> distributionDocs, List<Product> products) {
        this.distributionDocs = distributionDocs;
        this.products = products;
        shops.add("М1:ГРИН");
        shops.add("М2:СОВЕТСКАЯ");
        shops.add("М3:ПРИВОКЗАЛЬНАЯ");
        shops.add("М4:ЮЖНЫЙ");
        shops.add("М5:КИРОВА");
        shops.add("М6:КАЛИНОВСКОГО");
        shops.add("М7:СОВЕСТКАЯ(2)");

    }

   /* public NewLabelDistrib(Product product, DistributionDoc distributionDoc) {
        this.product = product;
        this.distributionDoc = distributionDoc;
    }*/

    public byte[] getPDF(Integer count) throws IOException, DocumentException {


        distributionDoc = distributionDocs.get(0);
        FileOutputStream fos = null;
        byte[] myByteFile = new byte[0];
        Rectangle one = new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth());
        Document documentA4 = new Document(one);


        fos = new FileOutputStream("pdf2.pdf");
        PdfWriter writer = PdfWriter.getInstance(documentA4, fos);


        documentA4.setMargins(20.0F, 20.0F, 20F, 20F);

        documentA4.open();
        BaseFont bf = null;

        bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font font = new Font(bf, 10f, Font.NORMAL);
        Font fontBold = new Font(bf, 10f, Font.BOLD);
        Font fontBig = new Font(bf, 20f, Font.NORMAL);
        PdfPCell cell;
        Phrase phrase;

        for (String shop : shops) {
            float[] columnWidths = {1.5F, 4F, 4F, 7F, 2F, 6F, 5F, 3F, 4F, 4F};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);

            int number = 1;
            double sumCount = 0;
            double sumCost = 0;


            cell = new PdfPCell(new Phrase("Спецификация № " + distributionDoc.getDocNumber() +
                    " Дата:  " + distributionDoc.getDateDistribTxt(), fontBig));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(10);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Торговый объект:  " + shop, fontBig));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(10);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(" ", fontBig));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("№п/п", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Артикул", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Штрихкод", font)); // getBarcodesText(product.getId())
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Наименование", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Пол", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Сезон", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Дата поступления", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Количество", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Цена розничная,BYN", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Стоимость, BYN", font));
            table.addCell(cell);


            for (Product product : products) {
                distributionDoc = distributionDocs.get(number - 1);

                cell = new PdfPCell(new Phrase(String.valueOf(number), font));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(product.getArticle(), font));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(product.getBarcode(), font)); // getBarcodesText(product.getId())
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(product.getProductName(), font));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(product.getGender(), font));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(product.getSeason(), font));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(product.getDateArrive(), font));
                table.addCell(cell);

                switch (shop) {
                    case "М1:ГРИН":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM1()), font));
                        sumCount = sumCount + distributionDoc.getCountM1();
                        break;
                    case "М2:СОВЕТСКАЯ":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM2()), font));
                        sumCount = sumCount + distributionDoc.getCountM2();
                        break;
                    case "М3:ПРИВОКЗАЛЬНАЯ":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM3()), font));
                        sumCount = sumCount + distributionDoc.getCountM3();
                        break;
                    case "М4:ЮЖНЫЙ":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM4()), font));
                        sumCount = sumCount + distributionDoc.getCountM4();
                        break;
                    case "М5:КИРОВА":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM5()), font));
                        sumCount = sumCount + distributionDoc.getCountM5();
                        break;
                    case "М6:КАЛИНОВСКОГО":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM6()), font));
                        sumCount = sumCount + distributionDoc.getCountM6();
                        break;
                    case "М7:СОВЕСТКАЯ(2)":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM7()), font));
                        sumCount = sumCount + distributionDoc.getCountM6();
                        break;
                }

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(product.getRetailPrice(), font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                switch (shop) {
                    case "М1:ГРИН":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM1() * product.getDoubleRetailPrice()), font));
                        sumCost = sumCost + (distributionDoc.getCountM1() * product.getDoubleRetailPrice());
                        break;
                    case "М2:СОВЕТСКАЯ":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM2() * product.getDoubleRetailPrice()), font));
                        sumCost = sumCost + (distributionDoc.getCountM2() * product.getDoubleRetailPrice());
                        break;
                    case "М3:ПРИВОКЗАЛЬНАЯ":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM3() * product.getDoubleRetailPrice()), font));
                        sumCost = sumCost + (distributionDoc.getCountM3() * product.getDoubleRetailPrice());
                        break;
                    case "М4:ЮЖНЫЙ":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM4() * product.getDoubleRetailPrice()), font));
                        sumCost = sumCost + (distributionDoc.getCountM4() * product.getDoubleRetailPrice());
                        break;
                    case "М5:КИРОВА":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM5() * product.getDoubleRetailPrice()), font));
                        sumCost = sumCost + (distributionDoc.getCountM5() * product.getDoubleRetailPrice());
                        break;
                    case "М6:КАЛИНОВСКОГО":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM6() * product.getDoubleRetailPrice()), font));
                        sumCost = sumCost + (distributionDoc.getCountM6() * product.getDoubleRetailPrice());
                        break;
                    case "М7:СОВЕСТКАЯ(2)":
                        cell = new PdfPCell(new Phrase(String.valueOf(distributionDoc.getCountM7() * product.getDoubleRetailPrice()), font));
                        sumCost = sumCost + (distributionDoc.getCountM7() * product.getDoubleRetailPrice());
                        break;
                }
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                number++;

            }


            cell = new PdfPCell(new Phrase(" ", fontBold));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(7);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(sumCount), fontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(" ", fontBold));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(sumCost), fontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", fontBig));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Товар принял, ФИО:", font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(3);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("________________________________", font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(7);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Подпись:", font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("________________________________", font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(7);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Дата:", font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("________________________________", font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(7);
            table.addCell(cell);

            documentA4.add(table);
            documentA4.newPage();


        }

        documentA4.close();


        try {
            myByteFile = Files.readAllBytes(Paths.get("pdf2.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myByteFile;

    }

    public static PdfPCell createBarcode(PdfWriter writer, String code) {
        BarcodeEAN barcode = new BarcodeEAN();
        barcode.setCodeType(Barcode.EAN8);
        barcode.setCode(code);
        barcode.setBarHeight(12);

        PdfPCell cell =
                new PdfPCell(barcode.createImageWithBarcode(writer.getDirectContent(),
                        BaseColor.BLACK, BaseColor.BLACK), false);

        return cell;
    }

    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);

        return cell;
    }

    private String getBarcodesText(Long id) {
        String barcodeTxt;
        String code;
        int len;
        int checksum_digit = 0;
        code = String.format("%07d", id);
        len = code.length();
        if (len == 7) {
            String[] str = code.split("");
            int sum1 = Integer.parseInt(str[1]) + Integer.parseInt(str[3]) + Integer.parseInt(str[5]);
            int sum2 = 3 * (Integer.parseInt(str[0]) + Integer.parseInt(str[2]) +
                    Integer.parseInt(str[4]) + Integer.parseInt(str[6]));
            int checksum_value = sum1 + sum2;
            checksum_digit = 10 - (checksum_value % 10);
            if (checksum_digit == 10) {
                checksum_digit = 0;
            }

            barcodeTxt = String.format("%07d", id) + String.valueOf(checksum_digit);
        } else {
            barcodeTxt = "00000000";
            System.out.println("неверный штрихкод");
        }


        return barcodeTxt;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }


    public DistributionDoc getDistributionDoc() {
        return distributionDoc;
    }

    public void setDistributionDoc(DistributionDoc distributionDoc) {
        this.distributionDoc = distributionDoc;
    }
}
