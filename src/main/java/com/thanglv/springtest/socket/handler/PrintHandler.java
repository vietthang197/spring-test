package com.thanglv.springtest.socket.handler;

import com.thanglv.springtest.obj.GhtkCommand;
import com.thanglv.springtest.services.DetectCommandService;
import com.thanglv.springtest.services.PrintProvider;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrintQuality;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.*;
import java.net.Socket;

public class PrintHandler extends Thread {
    private Socket client;
    private BufferedReader inFromClient;
    private boolean isRun;
    private String bitmapHeader = "BITMAP 5,0,48,384,0,";

    public PrintHandler(Socket client) {
        this.client = client;
        isRun = true;
    }

    @Override
    public void run() {
        InputStream inp = null;
        BufferedInputStream reader = null;
        try {
            inp = client.getInputStream();
            reader = new BufferedInputStream(inp);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (isRun && client.isConnected()) {
            try {
                if (inp.available() > 0) {
                    int nRead;
                    byte[] data = new byte[1024];
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    while ((nRead = reader.read(data, 0, data.length)) != -1) {
                        outputStream.write(data, 0, nRead);
                    }
                    // lấy command mà client gửi để detect khổ giấy, ...
                    byte[] arrData = outputStream.toByteArray();
                    String command = new String(arrData);
                    GhtkCommand ghtkCommand = DetectCommandService.detect(command);
                    if (ghtkCommand == null) {
                        System.out.println("Error while detect command ghtk!");
                        return;
                    }

                    PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
                    attrs.add(PrintQuality.HIGH);
                    attrs.add(MediaSizeName.ISO_A8);
                    attrs.add(getDefaultPrintableArea(getPageFormat(), ghtkCommand.getWidthPaper(), ghtkCommand.getHeightPaper()));
                    Doc myDoc = new SimpleDoc(arrData, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
                    DocPrintJob job = PrintProvider.getPrinterByName("OneNote for Windows 10").createPrintJob();
                    job.print(myDoc, attrs);
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isRun = false;
                this.interrupt();
            }
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PageFormat getPageFormat() {
        PageFormat pageFormat = new PageFormat();
        Paper a8Paper = new Paper();
        double paperWidth = 2.0; // size a6 inches
        double paperHeight = 2.9; // size a6 inches
        a8Paper.setSize(paperWidth * 72.0, paperHeight * 72.0);
        pageFormat.setPaper(a8Paper);
        return pageFormat;
    }

    protected static MediaPrintableArea getDefaultPrintableArea(PageFormat page,
                                                         double w, double h) {
        double DPI = 25.4;
        double ix, iw, iy, ih;
        if (w >= 72.0 * 6.0) {
            ix = 72.0;
            iw = w - 2 * 72.0;
        } else {
            ix = w / 6.0;
            iw = w * 0.75;
        }
        if (h >= 72.0 * 6.0) {
            iy = 72.0;
            ih = h - 2 * 72.0;
        } else {
            iy = h / 6.0;
            ih = h * 0.75;
        }

        return new MediaPrintableArea(0, 0,
                (float) (iw / DPI), (float) (ih / DPI), MediaPrintableArea.INCH);
    }
}
