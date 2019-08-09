package com.lanit.satonin18.app.dto.cabinet;

import com.lanit.satonin18.app.dto.CommonDefaultVar;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class CabinetDto {
    private Integer maxResult;
    private Integer page;
    private Boolean desc;

    private String orderFieldName;

    private Boolean flagNeedSetFirstPage;

    private List<Integer> idFilterStatus;
    private Boolean showArchive;

    private Boolean flagNeedReplaceStatus;
    private Integer selectedIdNotification4editStatus;
    private Integer selectedNewIdStatus;
}
