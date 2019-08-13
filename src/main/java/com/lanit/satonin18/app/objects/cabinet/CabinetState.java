package com.lanit.satonin18.app.objects.cabinet;

import com.lanit.satonin18.app.dto.FilterDto;
import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;
import lombok.Data;

@Data
public class CabinetState {
    FilterDto filterDto;
    PaginationDto paginationDto;
    OrderByDto orderByDto;

//    PaginationDto
//    private Integer maxResult;
//    private Integer page;

//    OrderByDto
//    private Boolean desc;
//    private String orderFieldName;

//    FilterDto
//    private List<Integer> idFilterStatus;
//    private Boolean showArchive;

//    private Boolean flagNeedSetFirstPage;


//    private Boolean flagNeedReplaceStatus;
//    private Integer selectedIdNotification4editStatus;
//    private Integer selectedNewIdStatus;
}
