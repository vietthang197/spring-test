package com.thanglv.springtest.services;

import javax.print.PrintService;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Sp46Printer implements Printable {
    private PrintService printService;

    public Sp46Printer(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }
}
