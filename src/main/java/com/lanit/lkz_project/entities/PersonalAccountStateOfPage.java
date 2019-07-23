package com.lanit.lkz_project.entities;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class PersonalAccountStateOfPage<T> {

    private boolean isFiltered;

    private EnumSet<NotificationStatus> activeFiltersByStatus;

    private List<T> pageData;

    private long total;



    public PersonalAccountStateOfPage() {
        activeFiltersByStatus = EnumSet.noneOf(NotificationStatus.class);
    }

    public void addFilters(NotificationStatus... statuses) {
        activeFiltersByStatus.addAll(Arrays.asList(statuses));
    }

    public boolean isFiltered() {
        return isFiltered;
    }

    public void setFiltered(boolean filtered) {
        this.isFiltered = filtered;
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


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
