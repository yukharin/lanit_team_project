package com.lanit.satonin18.app.objects.value_object.cabinet;

//MOCK
public enum FastFilter {
    NEW_NOTIFICATION (1, "Новые уведомления"),
    RUN_OUT_3_DAY_DATA_RESPONSE (2, "Истекает срок предоставления ответа (за 3 дня)"),
    DIR_LETTER(3, "(Mock)По направленному письму (для выбранной записи)"),
    MULTIPLE_RETURNS(4, "Множественные возвраты");
//--------------------------------------------------------------------
    private int id;
    private String description;

    private FastFilter(int id, String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//        public static mas[] values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }
};