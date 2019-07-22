package com.lanit.satonin18.mvc.controller.cabinet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//can be store in type NotificationStatusES
public enum IdStatus {
    NEW(1),
    IN_WORK(2),
    REJECTED(3, true),
    OK(4, true);

    private int id;
    private boolean isArchiveStatus;

    private IdStatus(int id) {
        this.id = id;
        this.isArchiveStatus = false;
    }
    private IdStatus(int id, boolean isArchiveStatus) {
        this.id = id;
        this.isArchiveStatus = isArchiveStatus;
    }

    public int getId() {return id;}

    public boolean isArchiveStatus() {
        return isArchiveStatus;
    }
//        public static mas[] values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }

    public static void addAllInTheList(List<IdStatus> list){
        list.addAll(Arrays.asList(IdStatus.values()));
    }
    public static void addAllArchiveStatusesInTheList(List<IdStatus> list){
//            list.add(IdStatus.REJECTED);
//            list.add(IdStatus.OK);
        for (IdStatus itemEnam : IdStatus.values() ) {
            if(itemEnam.isArchiveStatus())  list.add(itemEnam);
        }
    }
    public static List<Integer> getAllId(){
        ArrayList<Integer> ids = new ArrayList<>(IdStatus.values().length);

        int i=0;
        for (IdStatus itemEnam : IdStatus.values() ) {
            ids.add(itemEnam.getId() );
            i++;
        }
        return ids;
    }
    public static List<Integer> getArchiveStatusesId(){
        ArrayList<Integer> ids = new ArrayList<>(/*IdStatus.values().length*/);

        int i=0;
        for (IdStatus itemEnam : IdStatus.values() ) {
            if(itemEnam.isArchiveStatus())  ids.add(itemEnam.getId() );
            i++;
        }
        return ids;
    }
};