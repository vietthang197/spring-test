package com.thanglv.springtest.services;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.Arrays;

public class PrintProvider {
    public static PrintService getPrinterByName(String name) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("========================");
        Arrays.stream(printServices).forEach(item -> System.out.println(item.getName()));
        System.out.println("========================");
        for (PrintService printService : printServices) {
            if (printService.getName().equals(name)) {
                return printService;
            }
        }
        throw new RuntimeException("Error not found print service with name =" + name);
    }
}
