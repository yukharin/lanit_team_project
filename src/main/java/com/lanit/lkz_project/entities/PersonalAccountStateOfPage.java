package com.lanit.lkz_project.entities;

import org.springframework.data.domain.Page;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class PersonalAccountStateOfPage<T> {

    private boolean filtration;

    private EnumSet<NotificationStatus> activeFiltersByStatus;

    private List<T> pageData;

    private Page<T> page;

    private int total;

    public PersonalAccountStateOfPage() {
        activeFiltersByStatus = EnumSet.noneOf(NotificationStatus.class);
    }

    public void addFilters(NotificationStatus... statuses) {
        for (NotificationStatus status : statuses) {
            activeFiltersByStatus.add(status);
        }
    }

    public boolean getFiltration() {
        return filtration;
    }

    public void setFiltration(boolean filtration) {
        this.filtration = filtration;
    }

    public Set<NotificationStatus> getActiveFiltersByStatus() {
        return activeFiltersByStatus;
    }


    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
