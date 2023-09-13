package com.poo.MartReports.Exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
