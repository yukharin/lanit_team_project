package com.lanit.satonin18.app.objects.the_notification;

public enum ColumnTheNotificationTable {
    actionType("Тип действия ", "actionType"),
    content("Содержание", "content"),
    date("Дата действия", "date"),
    userByIdImplementorLastName("Исполнитель", "userByIdImplementor.lastName"),
    organization("Подразделение", "userByIdImplementor.organization.name"),
    statusAfterProcessing("Статус после изменения", "statusAfterProcessing");

    private String description;
    private String varName;

    ColumnTheNotificationTable(String description, String varName){
        this.description = description;
        this.varName = varName;
    }

    public String getDescription() {
        return description;
    }

    public String getVarName() {
        return varName;
    }
}
