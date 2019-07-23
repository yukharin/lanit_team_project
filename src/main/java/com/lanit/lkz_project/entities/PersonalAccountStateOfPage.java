package com.lanit.lkz_project.entities;


import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class PersonalAccountStateOfPage<T> {

    private EnumSet<NotificationStatus> activeFiltersByStatus;

    private List<T> pageData;

    private long total;

    private TimeFilter timeFilter;

    public TimeFilter getTimeFilter() {
        return timeFilter;
    }

    public PersonalAccountStateOfPage() {
        activeFiltersByStatus = EnumSet.noneOf(NotificationStatus.class);
    }

    public void addFilters(NotificationStatus... statuses) {
        activeFiltersByStatus.addAll(Arrays.asList(statuses));
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

    public void setTimeFilter(TimeFilter timeFilter) {
        this.timeFilter = timeFilter;
    }

    public enum TimeFilter {
        First(3), Second(10), Third(30), Off;

        private int days;

        TimeFilter() {
        }

        TimeFilter(int days) {
            this.days = days;
        }

        public int days() {
            return days;
        }

    }
}
