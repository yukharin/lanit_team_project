package com.lanit.satonin18.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterDto {
    private List<Integer> idFilterStatus;
    private Boolean showArchive;
}
