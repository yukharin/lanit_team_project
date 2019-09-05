package com.lanit.satonin18.app.objects.value_object.the_notification;

public enum ColumnTheNotificationTable {
    actionType("Тип действия", "actionType"),
    content("Содержание", "content"),
    date("Дата действия", "date"),
    userByIdImplementorLastName("Исполнитель", "userByIdImplementor.lastName"),
    organization("Подразделение", "userByIdImplementor.organization.name"),
//    todo REPLACY (вдруг этот чувак уже поменял работу)
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
