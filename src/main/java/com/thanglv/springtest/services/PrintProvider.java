package com.thanglv.springtest.services;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrintProvider {
    public static PrintService getPrinterByName(String name) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().equals(name)) {
                return printService;
            }
        }
        return null;
    }
}
