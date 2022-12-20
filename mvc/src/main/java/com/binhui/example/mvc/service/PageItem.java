package com.binhui.example.mvc.service;

public class PageItem {

    private int num;
    private boolean actual;

    public PageItem(int num, boolean actual) {
        this.num = num;
        this.actual = actual;
    }

    public int getNum() {
        return num;
    }

    public boolean isActual() {
        return actual;
    }

}

