package com.thanglv.springtest.services;

public class GhtkService implements PrinterSupport {
    private byte[] bytes;

    public GhtkService (byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void print() {

    }
}
