package com.lanit.satonin18.app.objects.cabinet;

import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;

public class Cabinet4renderHtml {
    private static final List<Status> statuses4selectFilter = Arrays.asList(Status.values());
    private static final List<Status> listArchiveStatus = Status.getArchiveStatuses();

    public static List<Status> getStatuses4selectFilter() {
        return statuses4selectFilter;
    }

    public static List<Status> getListArchiveStatus() {
        return listArchiveStatus;
    }
    //--------------------------------------------------------------------
    private PageImpl<Notification> pageImpl ;//= COMMON_DEFAULT_VARS.EMPTY_PAGE_IMPL_NOTIFICATION; //
    private List<Status> checkedMainListNotificStatuses = Arrays.asList(Status.values());
    private CabinetState state;

    public Cabinet4renderHtml(CabinetState state) {
        this.state = state;
    }

    public PageImpl<Notification> getPageImpl() {
        return pageImpl;
    }

    public void setPageImpl(PageImpl<Notification> pageImpl) {
        this.pageImpl = pageImpl;
    }

    public List<Status> getCheckedMainListNotificStatuses() {
        return checkedMainListNotificStatuses;
    }

    public void setCheckedMainListNotificStatuses(List<Status> checkedMainListNotificStatuses) {
        this.checkedMainListNotificStatuses = checkedMainListNotificStatuses;
    }

    public CabinetState getState() {
        return state;
    }

    public void setState(CabinetState state) {
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
