package com.thanglv.springtest.services;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrintProvider {
    public static PrintService getSP46Printer() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().equals("SP46")) {
                return printService;
            }
        }
        return null;
    }
}
