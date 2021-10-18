package com.thanglv.springtest.services;

import javax.print.PrintService;
import java.awt.print.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainTest {
    public static void main(String[] args) throws PrinterException, FileNotFoundException {
        PrintService sp46Printer = PrintProvider.getPrinterByName("Microsoft Print to PDF");
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(sp46Printer);
        Book book = new Book();
        PageFormat pageFormat = new PageFormat();

        // set paper a6
        Paper a6Paper = new Paper();
        double paperWidth = 4.1;
        double paperHeight = 5.8;
        a6Paper.setSize( paperWidth * 72.0, paperHeight * 72.0 );
        //
        pageFormat.setPaper(a6Paper);
        book.append(new ImagePrinter(new FileInputStream("D:/test.png")), pageFormat);
        printerJob.setPageable(book);
        printerJob.print();

    }
}
