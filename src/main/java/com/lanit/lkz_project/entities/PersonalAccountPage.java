package com.lanit.lkz_project.entities;


import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PersonalAccountPage<T> {

    private Page<T> page;

    private TimeFilter timeFilter = TimeFilter.Off;

    private boolean newFilter = false;

    private boolean inProcessingFilter = false;

    private boolean approvedFilter = false;

    private boolean rejectedFilter = false;


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
