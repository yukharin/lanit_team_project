package com.lanit.lkz_project.entities.data_transfer_objects;


import lombok.Data;


@Data
public class PersonalAccountPageDto<Notification> {

    private TimeFilter timeFilter;

    private boolean newFilter;

    private boolean inProcessingFilter;

    private boolean approvedFilter;

    private boolean rejectedFilter;

    private JsonPageImpl<Notification> page;

    private SortParameter sortParameter;

    private boolean reversedOrder;

}
