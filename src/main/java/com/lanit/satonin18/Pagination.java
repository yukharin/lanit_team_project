package com.lanit.satonin18;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;

public class Pagination<E> {

    private int totalRecords;
    private int currentPage;
    private List<E> list;
    private int maxResult;
    private int totalPages;
    private int maxNavigationPage;

    private int temp_page;
    private int temp_maxResult;
    private int temp_maxNavigationPage;

    private List<Integer> navigationPages;

    public Pagination(int temp_page, int temp_maxResult, int temp_maxNavigationPage) {
        this.temp_page = temp_page;
        this.temp_maxResult = temp_maxResult;
        this.temp_maxNavigationPage = temp_maxNavigationPage;
    }
    // @temp_page: 1, 2, ..
    public Pagination<E> initQuery(Query<E> query ) {
        final int pageIndex = temp_page - 1 < 0 ? 0 : temp_page - 1;

        int fromRecordIndex = pageIndex * temp_maxResult;
        int maxRecordIndex = fromRecordIndex + temp_maxResult;

        ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE  );

        List<E> results = new ArrayList<E>();
        boolean hasResult = resultScroll.first();
        if (hasResult) {
            // Scroll to position:
            hasResult = resultScroll.scroll(fromRecordIndex);
            if (hasResult) {
                do {
                    E record = (E) resultScroll.get(0);
                    results.add(record);
                } while (resultScroll.next()//
                        && resultScroll.getRowNumber() >= fromRecordIndex
                        && resultScroll.getRowNumber() < maxRecordIndex);
            }
            // Go to Last record.
            resultScroll.last();
        }
        // Total Records
        this.totalRecords = resultScroll.getRowNumber() + 1;
        this.currentPage = pageIndex + 1;
        this.list = results;
        this.maxResult = temp_maxResult;

        if (this.totalRecords % this.maxResult == 0) {
            this.totalPages = this.totalRecords / this.maxResult;
        } else {
            this.totalPages = (this.totalRecords / this.maxResult) + 1;
        }
        this.maxNavigationPage = temp_maxNavigationPage;

        if (maxNavigationPage < totalPages) {
            this.maxNavigationPage = temp_maxNavigationPage;
        }
        resultScroll.close();

        this.calcNavigationPages();
        return this;
    }

    private void calcNavigationPages() {

        this.navigationPages = new ArrayList<Integer>();

        int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;

        int begin = current - this.maxNavigationPage / 2;
        int end = current + this.maxNavigationPage / 2;

        // The first temp_page
        navigationPages.add(1);
        if (begin > 2) {
            // Using for '...'
            navigationPages.add(-1);
        }
        for (int i = begin; i < end; i++) {
            if (i > 1 && i < this.totalPages) {
                navigationPages.add(i);
            }
        }
        if (end < this.totalPages - 2) {
            // Using for '...'
            navigationPages.add(-1);
        }
        // The last temp_page.
        navigationPages.add(this.totalPages);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<E> getList() {
        return list;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public List<Integer> getNavigationPages() {
        return navigationPages;
    }

}