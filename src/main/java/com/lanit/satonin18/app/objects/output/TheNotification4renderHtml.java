package com.lanit.satonin18.app.objects.output;

import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.enum_type.ActionType;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.state4session.TheNotificationSessionState;
import com.lanit.satonin18.app.objects.value_object.the_notification.ColumnTheNotificationTable;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;

public class TheNotification4renderHtml {
    private User user;
    private Notification currentNotification;
    private final List<Integer> selectShowListMaxResult = COMMON_DEFAULT_VARS.selectShowListMaxResult;
    private final List<ActionType> listActionType = Arrays.asList(ActionType.values());
    private final List<Status> listStatus = Arrays.asList(Status.values());
    private final ColumnTheNotificationTable[] columnTable = ColumnTheNotificationTable.values();
    //-------------------------------------
    private PageImpl<Action> pageImpl = COMMON_DEFAULT_VARS.EMPTY_PAGE_IMPL_ACTION;
    private List<Action> showListActions; // todo check
    private Action latestAction;

    private TheNotificationSessionState state;

    public TheNotification4renderHtml(TheNotificationSessionState state, User currentUser, Notification currentNotification) {
        this.state = state;
        this.user = currentUser;
        this.currentNotification = currentNotification;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getCurrentNotification() {
        return currentNotification;
    }

    public void setCurrentNotification(Notification currentNotification) {
        this.currentNotification = currentNotification;
    }

    public List<Integer> getSelectShowListMaxResult() {
        return selectShowListMaxResult;
    }

    public List<ActionType> getListActionType() {
        return listActionType;
    }

    public List<Status> getListStatus() {
        return listStatus;
    }

    public ColumnTheNotificationTable[] getColumnTable() {
        return columnTable;
    }
}
