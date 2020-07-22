package com.example.sweater.export;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.Receipt;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exports {
    private static final String FILE_NAME = "products.xlsx";
    private static final String FILE_NAME_RECEIPTS = "receipts.xlsx";

    public void createXlsx(Iterable<Product> products) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Products");
        String[] headers = {
                "Наименование товара",
                "Пол",
                "Размер",
                "Торговая Марка",
                "Импортер",
                "Производитель",
                "Артикул",
                "Внешний Код",
                "Состав",
                "Сезон",
                "Штрихкод",
                "Примечание",
                "Дата Прихода",
                "Количество",
                "Цена Ввоза",
                "Коэффициент",
                "Цена Розничная",
                "Страна Ввоза",
                "Валюта",
                "Курс",
                "Стоимость ввоза",
                "Стоимость розничная",
                "Магазин",
                "Коробка"
        };


        int rowNum = 0;
        int colNum = 0;
        double arrCost;
        double retCost;
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("mm/dd/yyyy"));
       /* cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
        */


        Cell cell;
        Row row;
        System.out.println("Creating excel");
        row = sheet.createRow(rowNum++);
        for (String header : headers) {

            cell = row.createCell(colNum++);
            cell.setCellValue(header);

        }

        for (Product product : products) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getProductName());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getGender());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getSize());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getTrademark());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getImporter());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getManufacturer());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getArticle());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getCode());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getComposition());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getSeason());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getBarcode());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getNote());
            cell = row.createCell(colNum++);

            //cell.setCellValue( product.getDateArrive());

            String sDate1 = product.getDateArrive().substring(8, 10)
                    + "." + product.getDateArrive().substring(5, 7) + "." + product.getDateArrive().substring(0, 4);
            Date date1 = null;
            try {
                date1 = new SimpleDateFormat("dd.MM.yyyy").parse(sDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cell.setCellValue(date1);
            cell.setCellStyle(cellStyle);
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getQuantity().replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getImportPrice().replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getCoefficient().replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getRetailPrice().replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getCountryOfEntry());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getCurrency());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getCourse().replace(".", ","));

            arrCost = product.getDoubleQuantity() * product.getDoubleImportPrice();
            retCost = product.getDoubleQuantity() * product.getDoubleRetailPrice();
            cell = row.createCell(colNum++);
            cell.setCellValue(String.valueOf(arrCost).replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(String.valueOf(retCost).replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getAuthor());
            cell = row.createCell(colNum++);
            cell.setCellValue(product.getBoxNumber());


        }
        /*
        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);

            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }*/

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }


    public void createXlsxReceipts(Iterable<Receipt> receipts) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Receipts");
        String[] headers = {
                "id",
                "Штрихкод",
                "Сумма всего",
                "Количество",
                "Скидка",
                "Пол",
                "Наименование товара",
                "Номер чека",
                "Цена",
                "Автор",
                "Магазин",
                "Номер коробки",
                "Дата поступления",
                "Дата продажи"

        };


        int rowNum = 0;
        int colNum = 0;
        double arrCost;
        double retCost;
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("mm/dd/yyyy"));
       /* cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
        */


        Cell cell;
        Row row;
        System.out.println("Creating excel");
        row = sheet.createRow(rowNum++);
        for (String header : headers) {

            cell = row.createCell(colNum++);
            cell.setCellValue(header);

        }

        for (Receipt receipt : receipts) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getId());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getBarcode());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getCost().replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getCount());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getDiscount().replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getGender());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getProductName());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getReceiptNumber());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getRetailPrice().replace(".", ","));
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getAuthor());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getStoreName());
            cell = row.createCell(colNum++);
            cell.setCellValue(receipt.getBoxNumber());
            cell = row.createCell(colNum++);

            //cell.setCellValue( product.getDateArrive());

            String sDate1 = receipt.getDateArrive().substring(8, 10)
                    + "." + receipt.getDateArrive().substring(5, 7) + "." + receipt.getDateArrive().substring(0, 4);
            Date date1 = null;
            try {
                date1 = new SimpleDateFormat("dd.MM.yyyy").parse(sDate1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cell.setCellValue(date1);
            cell.setCellStyle(cellStyle);
            cell = row.createCell(colNum++);

            String sDate2 = receipt.getSaleDate().toString().substring(8, 10)
                    + "." + receipt.getSaleDate().toString().substring(5, 7) + "." + receipt.getSaleDate().toString().substring(0, 4);
            Date date2 = null;
            try {
                date2 = new SimpleDateFormat("dd.MM.yyyy").parse(sDate2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cell.setCellValue(date2);
            cell.setCellStyle(cellStyle);
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME_RECEIPTS);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    public byte[] getXLS() throws IOException {

        byte[] myByteFile = new byte[0];
        try {
            myByteFile = Files.readAllBytes(Paths.get("products.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myByteFile;

    }

    public byte[] getXLSReceipts() throws IOException {

        byte[] myByteFile = new byte[0];
        try {
            myByteFile = Files.readAllBytes(Paths.get("receipts.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myByteFile;

    }
}
