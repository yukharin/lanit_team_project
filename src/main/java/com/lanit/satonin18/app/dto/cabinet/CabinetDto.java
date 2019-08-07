package com.lanit.satonin18.app.dto.cabinet;

import com.lanit.satonin18.app.dto.Common_Default_var;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class CabinetDto {
    private int maxResult = Common_Default_var.MAX_RESULT;
    private int page = Common_Default_var.PAGE;
    private boolean desc = Common_Default_var.DESC;

    private String orderFieldName = Default_Cabinet_var.ORDER_FIELD_NAME;
    private boolean showArchive = Default_Cabinet_var.SHOW_ARCHIVE;//если параметр не пришел, то false, если пришел( то приходит только true)

    private boolean flagNeedSetFirstPage = false;

    private List<Integer> idFilterStatus = Collections.EMPTY_LIST;//если ничего передали, значит пусто

    private boolean flagNeedReplaceStatus = false;
    private Integer selectedIdNotification4editStatus = null;
    private Integer selectedNewIdStatus = null;
}
