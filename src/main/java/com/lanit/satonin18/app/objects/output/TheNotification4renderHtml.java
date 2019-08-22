package com.lanit.satonin18.app.objects.output;

import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.state4session.TheNotificationSessionState;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class TheNotification4renderHtml {

    private PageImpl<Action> pageImpl = COMMON_DEFAULT_VARS.EMPTY_PAGE_IMPL_ACTION;
    private List<Action> showListActions;
    private Action latestAction;

    private TheNotificationSessionState state;

    public TheNotification4renderHtml(TheNotificationSessionState state) {
        this.state = state;
    }

    public PageImpl<Action> getPageImpl() {
        return pageImpl;
    }

    public void setPageImpl(PageImpl<Action> pageImpl) {
        this.pageImpl = pageImpl;
    }

    public List<Action> getShowListActions() {
        return showListActions;
    }

    public void setShowListActions(List<Action> showListActions) {
        this.showListActions = showListActions;
    }

    public Action getLatestAction() {
        return latestAction;
    }

    public void setLatestAction(Action latestAction) {
        this.latestAction = latestAction;
    }

    public TheNotificationSessionState getState() {
        return state;
    }

    public void setState(TheNotificationSessionState state) {
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
