package com.lanit.satonin18.app.objects.value_object.cabinet;

public enum ColumnCabinetTable {
    notificationType("Вид уведомления", "notificationType"),
    organization("Заказчик", "organization.name"),
    dateResponse("Срок предоставления ответа", "dateResponse"),
    status("Статус обработки уведомления", "status"),
    id("Номер уведомления", "id"),
    dateReceived("Дата получения уведомления", "dateReceived"),
    letterNumber("Номер письма", "letterNumber");

    private String description;
    private String varName;

    ColumnCabinetTable(String description, String varName){
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
