package com.thanglv.springtest.services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class ImagePrinter implements Printable {
    private double          x, y, width;

    private int             orientation;

    private BufferedImage   image;

    public ImagePrinter(PrinterJob printJob, BufferedImage image) {
        PageFormat pageFormat = printJob.defaultPage();
        this.x = 0;
        this.y = 0;
        this.width = pageFormat.getImageableWidth();
        this.orientation = pageFormat.getOrientation();
        this.image = image;
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex == 0) {

            System.out.println("pW:" +  pageFormat.getPaper().getImageableWidth() + ", pH:" + pageFormat.getPaper().getImageableHeight());
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }
}
