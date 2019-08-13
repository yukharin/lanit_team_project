package com.lanit.satonin18.app.dto;

import lombok.Data;

@Data
public class OrderByDto {
    private Boolean desc;
    private String orderFieldName;

//    private Boolean flagChangedStateAndNeedSetFirstPage;
}
