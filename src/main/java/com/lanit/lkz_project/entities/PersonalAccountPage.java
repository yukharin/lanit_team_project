package com.lanit.lkz_project.entities;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.EnumSet;

@Data
public class PersonalAccountPage<T> {

    private EnumSet<NotificationStatus> activeFiltersByStatus;

    private Page<T> page;

    private TimeFilter timeFilter = TimeFilter.Off;

    public TimeFilter getTimeFilter() {
        return timeFilter;
    }

    public PersonalAccountPage() {
        activeFiltersByStatus = EnumSet.noneOf(NotificationStatus.class);
    }

    public void addFilters(NotificationStatus... statuses) {
        activeFiltersByStatus.addAll(Arrays.asList(statuses));
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
