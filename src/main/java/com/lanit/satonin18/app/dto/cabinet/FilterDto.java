package com.lanit.satonin18.app.dto.cabinet;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class FilterDto {
    private List<Integer> idFilterStatus;
    private Boolean showArchive;

}
