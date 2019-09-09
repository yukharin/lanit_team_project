package com.lanit.satonin18.app.objects.output;

import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.property_in_future.DEFAULT_CABINET_VARS;
import com.lanit.satonin18.app.objects.state4session.CabinetSessionState;
import com.lanit.satonin18.app.objects.value_object.cabinet.ColumnCabinetTable;
import org.springframework.data.domain.PageImpl;


import java.util.Arrays;
import java.util.List;

public class Cabinet4renderHtml {
    private User user;
    private final List<Integer> selectShowListMaxResult = COMMON_DEFAULT_VARS.selectShowListMaxResult;
//        model.addAttribute("listFastFilter", DEFAULT_CABINET_VARS.list4FastFilter);

    private final ColumnCabinetTable[] columnTable = ColumnCabinetTable.values();
    //------------------------------------------------------------------------------------------
    private final List<Status> statuses4selectFilter = Arrays.asList(Status.values());
    private final List<Status> listArchiveStatus = Status.getArchiveStatuses();

    public List<Status> getStatuses4selectFilter() {
        return statuses4selectFilter;
    }

    public List<Status> getListArchiveStatus() {
        return listArchiveStatus;
    }
    //--------------------------------------------------------------------
    private PageImpl<Notification> pageImpl ;//= COMMON_DEFAULT_VARS.EMPTY_PAGE_IMPL_NOTIFICATION; //
    private List<Status> checkedMainListNotificStatuses = Arrays.asList(Status.values());
    private List<Integer> newCheckedMainListNotificStatusesId;
    private CabinetSessionState state;

    //todo delete
    public Cabinet4renderHtml(CabinetSessionState state) {
        this.state = state;
    }
    public Cabinet4renderHtml(CabinetSessionState state, User user) {
        this.state = state;
        this.user = user;
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

    public CabinetSessionState getState() {
        return state;
    }

    public void setState(CabinetSessionState state) {
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


    public List<Integer> getNewCheckedMainListNotificStatusesId() {
        return newCheckedMainListNotificStatusesId;
    }

    public void setNewCheckedMainListNotificStatusesId(List<Integer> newCheckedMainListNotificStatusesId) {
        this.newCheckedMainListNotificStatusesId = newCheckedMainListNotificStatusesId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getSelectShowListMaxResult() {
        return selectShowListMaxResult;
    }

    public ColumnCabinetTable[] getColumnTable() {
        return columnTable;
    }
}