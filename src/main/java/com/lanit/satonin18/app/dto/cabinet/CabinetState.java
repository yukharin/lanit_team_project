package com.lanit.satonin18.app.dto.cabinet;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.no_db.NotificationStatus;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabinetState {

    @Setter
    @Getter
    private static /*final*/ List<NotificationStatus> statuses4selectFilter; /*= new ArrayList<NotificationStatus>( static_statusService.listByIds( IdStatus.getAllId() ))*/
    @Setter
    @Getter
    private static /*final*/ List<NotificationStatus> listArchiveStatus; /*= new ArrayList<NotificationStatus>( static_statusService.listByIds( IdStatus.getArchiveStatusesId() ))*/
//--------------------------------------------------------------------
    //for web-page Cabinet/list  ---------------------------------------
    private List<NotificationStatus> checkedMainListNotificStatuses ;//= statuses4selectFilter

    private Pagination<Notification> pagination = Default_Cabinet_var.pagination;

    private List<Notification> showListNotifications;

    private CabinetModel model = new CabinetModel();

}
