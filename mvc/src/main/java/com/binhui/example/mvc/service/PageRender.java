package com.binhui.example.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

    private String url;
    private Page<T> page;
    private int totalPages;
    private int perPage;
    private int actualPage;
    private List<PageItem> pages;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        perPage = 10;
        totalPages = page.getTotalPages();
        actualPage = page.getNumber() + 1;

        int from, to;
        if (totalPages <= perPage) {
            from = 1;
            to = totalPages;
        } else {
            if (actualPage <= perPage / 2) {
                from = 1;
                to = perPage;
            } else if (actualPage >= totalPages - perPage / 2) {
                from = totalPages - perPage + 1;
                to = perPage;
            } else {
                from = actualPage - perPage / 2;
                to = perPage;
            }
        }

        for (int i = 0; i < to; i++) {
            pages.add(new PageItem(from + i, actualPage == from + i));
        }

    }

    public String getUrl() {
        return url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }

}
