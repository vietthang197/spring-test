package com.thanglv.springtest.services;

import javax.imageio.ImageIO;
import javax.print.PrintException;
import java.awt.print.*;
import java.io.FileInputStream;
import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException, PrintException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pf = job.defaultPage();
        Paper paper = pf.getPaper();
        paper.setSize(375, 555);
        paper.setImageableArea(0.5 * 72, 0.0 * 72, 7.5 * 72, 10.5 * 72);
        pf.setPaper(paper);
        Book book = new Book();//java.awt.print.Book
        book.append(new ImagePrinter(job, ImageIO.read(new FileInputStream("D:/test.png"))), pf);
        job.setPageable(book);
        job.print();
    }
}
