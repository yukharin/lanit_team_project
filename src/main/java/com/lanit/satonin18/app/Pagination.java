package com.lanit.satonin18.app;

import lombok.Data;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Pagination<E> {

    protected long totalRecords;
    protected int currentPage;
    protected List<E> list;
    protected int maxResult;
    protected int totalPages;
    protected int maxNavigationPage;
    protected List<Integer> navigationPages;

    //static //can be object //generic is only one //how spring and servlet impemets treadsafe
    public final class EmptyPagination<E> extends Pagination<E> {
        public EmptyPagination(Pagination pagination) {
            super(1, pagination.maxResult, pagination.maxNavigationPage);

            totalRecords = 0;
            currentPage = 1;
            list = Collections.EMPTY_LIST;//new ArrayList();
            maxResult = pagination.maxResult;//getMaxResult();
            totalPages = 1;
            maxNavigationPage = pagination.maxNavigationPage;//getMaxNavigationPage();
            navigationPages = Collections.EMPTY_LIST;//new ArrayList();
        }
    };

    public Pagination(int currentPage, int maxResult, int maxNavigationPage) {
        this.currentPage = currentPage;
        this.maxResult = maxResult;
        this.maxNavigationPage = maxNavigationPage;
    }

    public Pagination<E> initQuery(TypedQuery<E> query , long count) {
        query.setFirstResult((currentPage-1)*maxResult);
        query.setMaxResults(maxResult);

        list = query.getResultList();
        totalRecords = count;
        totalPages = (int) Math.ceil(  ((double)totalRecords)  /  ((double) maxResult)  );

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

}

