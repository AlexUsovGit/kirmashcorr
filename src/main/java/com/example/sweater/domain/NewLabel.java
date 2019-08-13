package com.example.sweater.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class NewLabel {
    private String str;
    private Product product;
    //public static final String FONT = "c:\\Windows\\Fonts\\times.ttf";
    //public static final String FONT = "times.ttf";
    public static final String FONT = "arial.ttf";


    public NewLabel() {
    }


    public NewLabel(String str) {
        this.str = str;
    }

    public NewLabel(Product product) {
        this.product = product;
    }

    public void createPdf() throws DocumentException, IOException {

        Rectangle one = new Rectangle(50.0F, 145.0F, 90);
        DateFormat df = new SimpleDateFormat("HH-mm-ss");
        Document document = new Document(one);
        PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
        document.setMargins(10, 10, 10, 10);

        document.open();

        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        // Font font = FontFactory.getFont(String.valueOf(Font.FontFamily.TIMES_ROMAN), "Cp1250", true);
        Font font = new Font(/*Font.FontFamily.HELVETICA*/ bf, 5, Font.NORMAL);
        // Chunk chunk = new Chunk("Hello World" + product.getBarcode(), font);
        PdfPCell cell;

        float[] columnWidths = {1.9F, 4.1F};


        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(90);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        // the cell object

        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Импортер в Беларусь", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getImporter(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Производитель", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getManufacturer(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Тоговая марка", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getTrademark(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Наименование", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getProductName() + " " + product.getGender(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Артикул", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getArticle(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Состав материала", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getComposition(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Дата выпуска", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getDateArrive(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Символы ухода", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("символы", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Цена", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getRetailPrice(), font));
        table.addCell(cell);


        document.add(table);

        //document.add(chunk);
        document.close();
    }

    public byte[] getPDF(Integer count) throws IOException, DocumentException {


        FileOutputStream fos = null;
        byte[] myByteFile = new byte[0];
        Rectangle one = new Rectangle(110.0F, 159.0F);

        // Rectangle one = new Rectangle( 111.0F,159.0F);
        DateFormat df = new SimpleDateFormat("HH-mm-ss");
        Document document = new Document(one);


        fos = new FileOutputStream("pdf.pdf");
        PdfWriter writer = PdfWriter.getInstance(document, fos);


        document.setMargins(3.0F,1.0F, 1.5F, 1.0F);

        document.open();
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Font font = FontFactory.getFont(String.valueOf(Font.FontFamily.TIMES_ROMAN), "Cp1250", true);
        Font font = new Font(/*Font.FontFamily.HELVETICA*/ bf, 4.1f, Font.NORMAL);
        Font font10 = new Font(/*Font.FontFamily.HELVETICA*/ bf, 10.0f, Font.BOLD);
        PdfPCell cell;

        float[] columnWidths = {1.9F, 5.1F};


        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);

        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        // the cell object

        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Импортер в Беларусь", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getImporter(), font));
        cell.setFixedHeight(18);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Производитель", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getManufacturer(), font));
        cell.setFixedHeight(20);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Торговая марка", font));
        cell.setFixedHeight(13);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getTrademark(), font));

        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Наименование", font));
        cell.setFixedHeight(13);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getProductName() + " " + product.getGender(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Артикул", font));
        cell.setFixedHeight(8);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getArticle(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Состав материала", font));
        cell.setFixedHeight(13);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getComposition(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Размер", font));
        cell.setFixedHeight(10);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", font));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Дата выпуска", font));
        cell.setFixedHeight(12);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getStringDate(), font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Символы ухода", font));
        cell.setFixedHeight(12);
        table.addCell(cell);
        cell = createImageCell("symbols.png");
        cell.setPaddingLeft(4);
        cell.setPaddingTop(2);
        cell.setPaddingBottom(2);
        cell.setPaddingRight(4);
        cell.setFixedHeight(10);
        table.addCell(cell);
        cell = createImageCell("eac.jpg");

        cell.setPaddingLeft(8);
        cell.setPaddingTop(3);
        cell.setPaddingBottom(3);
        cell.setPaddingRight(3);
        cell.setFixedHeight(20);
        table.addCell(cell);
        cell = createBarcode(writer, getBarcodesText(product.getId()));
        cell.setPaddingLeft(4);
        cell.setPaddingTop(1);
        cell.setPaddingRight(1);
        cell.setFixedHeight(20);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Цена", font10));
        cell.setFixedHeight(12);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getRetailPrice() + " BYN", font10));
        table.addCell(cell);

        // cell = new PdfPCell(new Phrase("Штрихкод",font));
        // table.addCell(cell);
        // table.addCell(createImageCell("eac.jpg"));
        // cell = createBarcode(writer, String.format("%08d", product.getId()));
        // cell = new PdfPCell(new Phrase("Bar",font));



        try {
            for (Integer i = 0; i < count; i++) {
                document.newPage();
                document.add(table);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //document.add(chunk);


        document.close();


        try {
            myByteFile = Files.readAllBytes(Paths.get("pdf.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //---------------------------------------------------------------

       /* Exports exports = new Exports();
        exports.createXlsx();*/
        return myByteFile;

    }

    public static PdfPCell createBarcode(PdfWriter writer, String code) throws DocumentException, IOException {
        BarcodeEAN barcode = new BarcodeEAN();
        barcode.setCodeType(Barcode.EAN8);
        barcode.setCode(code);
        barcode.setBarHeight(12);

        // PdfPCell cell = new PdfPCell(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY), true);
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
}