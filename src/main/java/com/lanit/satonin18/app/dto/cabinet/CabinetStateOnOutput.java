package com.lanit.satonin18.app.dto.cabinet;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dto.Common_Default_var;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import lombok.*;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class CabinetStateOnOutput {
    @Getter private static final List<Status> statuses4selectFilter = Arrays.asList(Status.values());
    @Getter private static final List<Status> listArchiveStatus = Status.getArchiveStatuses();
    //--------------------------------------------------------------------
    private PageImpl<Notification> pageImpl = Common_Default_var.EMPTY_PAGE_IMPL_NOTIFICATION; //

    private List<Status> checkedMainListNotificStatuses = Arrays.asList(Status.values());

    private CabinetDtoOnInput dto;

    public CabinetStateOnOutput(CabinetDtoOnInput dto) {
        this.dto = dto;
    }

    public List<Integer> navigationPages;

    public void calcNavigationPages() {
        navigationPages = new ArrayList<Integer>();
        int totalPages = pageImpl.getTotalPages();
        int currentPage = pageImpl.getPageable().getPageNumber();
        int maxNavigationPages = Common_Default_var.MAX_NAVIGATION_PAGES;

        int current = currentPage > totalPages ? totalPages : currentPage;
        int begin = current - maxNavigationPages / 2;
        int end = current + maxNavigationPages / 2;

        // The first temp_page
        navigationPages.add(1);
        if (begin > 2) {
            // Using for '...'
            navigationPages.add(-1);
        }
        for (int i = begin; i < end; i++) {
            if (i > 1 && i < totalPages) {
                navigationPages.add(i);
            }
        }
        if (end < totalPages - 2) {
            // Using for '...'
            navigationPages.add(-1);
        }
        // The last temp_page.
        navigationPages.add(totalPages);
    }
}
