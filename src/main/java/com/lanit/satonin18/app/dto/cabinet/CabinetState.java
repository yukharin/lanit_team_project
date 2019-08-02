package com.lanit.satonin18.app.dto.cabinet;

import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.no_db.Status;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class CabinetState {
    @Getter private static final List<Status> statuses4selectFilter = Arrays.asList(Status.values());
    @Getter private static final List<Status> listArchiveStatus = Status.getArchiveStatuses();
//--------------------------------------------------------------------
    private Pagination<Notification> pagination = Default_Cabinet_var.pagination;

    private List<Status> checkedMainListNotificStatuses = Arrays.asList(Status.values());
    private List<Notification> showListNotifications;
    private CabinetModel model;

    public CabinetState(CabinetModel model) {
        this.model = model;
    }
}
