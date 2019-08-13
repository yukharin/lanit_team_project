package com.lanit.satonin18.app.objects.the_notification;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.entity.Action;
import lombok.*;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class TheNotification4renderHtml {

    private PageImpl<Action> pageImpl = COMMON_DEFAULT_VARS.EMPTY_PAGE_IMPL_ACTION;
    private List<Action> showListActions;
    private Action latestAction;

    private TheNotificationState state;

    public TheNotification4renderHtml(TheNotificationState state) {
        this.state = state;
    }

//    public List<Integer> navigationPages;
//
//    public void calcNavigationPages() {
//        navigationPages = new ArrayList<Integer>();
//        int totalPages = pageImpl.getTotalPages();
//        int currentPage = pageImpl.getPageable().getPageNumber();
//        int maxNavigationPages = COMMON_DEFAULT_VARS.MAX_NAVIGATION_PAGES;
//
//        int current = currentPage > totalPages ? totalPages : currentPage;
//        int begin = current - maxNavigationPages / 2;
//        int end = current + maxNavigationPages / 2;
//
//        // The first temp_page
//        navigationPages.add(1);
//        if (begin > 2) {
//            // Using for '...'
//            navigationPages.add(-1);
//        }
//        for (int i = begin; i < end; i++) {
//            if (i > 1 && i < totalPages) {
//                navigationPages.add(i);
//            }
//        }
//        if (end < totalPages - 2) {
//            // Using for '...'
//            navigationPages.add(-1);
//        }
//        // The last temp_page.
//        navigationPages.add(totalPages);
//    }
}
