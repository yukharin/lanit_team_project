package com.lanit.satonin18.app.dto.notification_app;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Action;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationAppState {

//    @Setter
//    @Getter
//    private static /*final*/ List<Status> statuses4selectFilter; /*= new ArrayList<Status>( static_statusService.listByIds( IdStatus.getAllId() ))*/
//    @Setter
//    @Getter
//    private static /*final*/ List<Status> listArchiveStatus; /*= new ArrayList<Status>( static_statusService.listByIds( IdStatus.getArchiveStatusesId() ))*/
//--------------------------------------------------------------------
    //for web-page Cabinet/list  ---------------------------------------
//    private List<Status> checkedMainListNotificStatuses ;//= statuses4selectFilter

    private Pagination<Action> pagination = Default_NotificationApp_var.pagination;
    private List<Action> showListActions;

    private NotificationAppModel model = new NotificationAppModel();

    private Action latestAction;

//    private boolean showArchive = Default_NotificationApp_var.SHOW_ARCHIVE; //=  is NOTIFICATION_STATUS = PROCESSED
//    private FastFilter currentFastFilter; //=Mock



}
