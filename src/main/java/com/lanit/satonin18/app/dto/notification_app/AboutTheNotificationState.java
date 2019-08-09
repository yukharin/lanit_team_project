package com.lanit.satonin18.app.dto.notification_app;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.dto.CommonDefaultVar;
import com.lanit.satonin18.app.entity.Action;
import lombok.*;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class AboutTheNotificationState {

    private PageImpl<Action> pageImpl = CommonDefaultVar.EMPTY_PAGE_IMPL_ACTION;
    private List<Action> showListActions;
    private Action latestAction;

    private AboutTheNotificationDto dto;

    public AboutTheNotificationState(AboutTheNotificationDto dto) {
        this.dto = dto;
    }

    public List<Integer> navigationPages;

    public void calcNavigationPages() {
        navigationPages = new ArrayList<Integer>();
        int totalPages = pageImpl.getTotalPages();
        int currentPage = pageImpl.getPageable().getPageNumber();
        int maxNavigationPages = CommonDefaultVar.MAX_NAVIGATION_PAGES;

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
