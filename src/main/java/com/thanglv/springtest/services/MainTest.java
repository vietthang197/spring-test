package com.thanglv.springtest.services;

import javax.print.PrintService;
import java.awt.print.*;
import java.io.FileInputStream;
import java.io.IOException;

public class MainTest {
    private static final double DPI = 72.0d;
    public static void main(String[] args) throws PrinterException, IOException {
        PrintService sp46Printer = PrintProvider.getPrinterByName("Microsoft Print to PDF");
        if (sp46Printer != null)
            System.out.println("DETECTED SP46 printer");
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(sp46Printer);
        Book book = new Book();
        PageFormat pageFormat = new PageFormat();

        // set paper a6
        Paper a6Paper = new Paper();
        double paperWidth = 4.1; // size a6 inches
        double paperHeight = 5.8; // size a6 inches
        a6Paper.setSize(paperWidth * DPI, paperHeight * DPI);
        a6Paper.setImageableArea(0, 0, paperWidth * DPI, paperHeight * DPI);
        pageFormat.setPaper(a6Paper);
        book.append(new ImagePrinter(new FileInputStream("D:/test2.PNG")), pageFormat);
        printerJob.setPageable(book);
        printerJob.print();

    }

    public static double mmToInch(double mm) {
        return mm / 25.4;
    }
}
