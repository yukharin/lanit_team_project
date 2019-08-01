package com.lanit.satonin18.app.entity.no_db;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

//ActionType.values()[idActionType] и ActionType.valueOf() -считаю криво

//can add abstract class(with generic) ActionTypeStoreMapHelper
class NotificStatusStoreMapHelper {
    private static HashMap<Integer, NotificationStatus> mapById = new HashMap<>();

    public static HashMap<Integer, NotificationStatus> getMapById() {
        return mapById;
    }
//-------------------------------------

    public static int size() {
        return mapById.size();
    }

    public static boolean isEmpty() {
        return mapById.isEmpty();
    }

    public static NotificationStatus get(Object key) {
        return mapById.get(key);
    }

    public static boolean containsKey(Object key) {
        return mapById.containsKey(key);
    }

    public static NotificationStatus put(Integer key, NotificationStatus value) {
        return mapById.put(key, value);
    }

    public static void putAll(Map<? extends Integer, ? extends NotificationStatus> m) {
        mapById.putAll(m);
    }

    public static NotificationStatus remove(Object key) {
        return mapById.remove(key);
    }

    public static void clear() {
        mapById.clear();
    }

    public static boolean containsValue(Object value) {
        return mapById.containsValue(value);
    }

    public static Set<Integer> keySet() {
        return mapById.keySet();
    }

    public static Collection<NotificationStatus> values() {
        return mapById.values();
    }

    public static Set<Map.Entry<Integer, NotificationStatus>> entrySet() {
        return mapById.entrySet();
    }

    public static NotificationStatus getOrDefault(Object key, NotificationStatus defaultValue) {
        return mapById.getOrDefault(key, defaultValue);
    }

    public static NotificationStatus putIfAbsent(Integer key, NotificationStatus value) {
        return mapById.putIfAbsent(key, value);
    }

    public static boolean remove(Object key, Object value) {
        return mapById.remove(key, value);
    }

    public static boolean replace(Integer key, NotificationStatus oldValue, NotificationStatus newValue) {
        return mapById.replace(key, oldValue, newValue);
    }

    public static NotificationStatus replace(Integer key, NotificationStatus value) {
        return mapById.replace(key, value);
    }

    public static NotificationStatus computeIfAbsent(Integer key, Function<? super Integer, ? extends NotificationStatus> mappingFunction) {
        return mapById.computeIfAbsent(key, mappingFunction);
    }

    public static NotificationStatus computeIfPresent(Integer key, BiFunction<? super Integer, ? super NotificationStatus, ? extends NotificationStatus> remappingFunction) {
        return mapById.computeIfPresent(key, remappingFunction);
    }

    public static NotificationStatus compute(Integer key, BiFunction<? super Integer, ? super NotificationStatus, ? extends NotificationStatus> remappingFunction) {
        return mapById.compute(key, remappingFunction);
    }

    public static NotificationStatus merge(Integer key, NotificationStatus value, BiFunction<? super NotificationStatus, ? super NotificationStatus, ? extends NotificationStatus> remappingFunction) {
        return mapById.merge(key, value, remappingFunction);
    }

    public static void forEach(BiConsumer<? super Integer, ? super NotificationStatus> action) {
        mapById.forEach(action);
    }

    public static void replaceAll(BiFunction<? super Integer, ? super NotificationStatus, ? extends NotificationStatus> function) {
        mapById.replaceAll(function);
    }
}

//todo move f(x) in other class
public enum NotificationStatus {
    NEW(1, "Новое"),
    IN_WORK(2, "В работе"),
    REJECTED(3, "Отклонено", true),
    OK(4, "Одобрено", true);

    @Getter
    private int id; //todo нужна проверка на уникальность
    @Getter
    private String name;
    @Getter
    private boolean isArchiveStatus = false;

    private NotificationStatus(int id, String name, boolean isArchiveStatus) {
//        super(name, id);
//        mapById.put(id, this);

        // в идеале это проверка должна быть не во время исполнения программы //хотя это статик вар
        if(NotificStatusStoreMapHelper.containsKey(id))
            throw new RuntimeException("повторяющиеся id в ENUM");

        NotificStatusStoreMapHelper.getMapById().put(id, this);

        this.id = id;
        this.name = name;
        this.isArchiveStatus = isArchiveStatus;
    }

    private NotificationStatus(int id, String name) {
        this(id, name, false);
    }

//        public static mas[] values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }

    public static void addAllArchiveStatusesInTheList(List<NotificationStatus> list){
        for (NotificationStatus itemEnam : NotificationStatus.values() ) {
            if(itemEnam.isArchiveStatus())  list.add(itemEnam);
        }
    }
    public static List<Integer> getAllId(){
        ArrayList<Integer> ids = new ArrayList<>( NotificationStatus.values().length );

        for (NotificationStatus itemEnam : NotificationStatus.values() ) {
            ids.add(itemEnam.getId() );
        }
        return ids;
    }
    public static List<Integer> getArchiveStatusesId(){
        ArrayList<Integer> ids = new ArrayList<>(/*IdStatus.values().length*/);

        for (NotificationStatus itemEnam : NotificationStatus.values() ) {
            if(itemEnam.isArchiveStatus())  ids.add(itemEnam.getId() );
        }
        return ids;
    }

    public static NotificationStatus getById(int idStatus) throws RuntimeException {
        if( ! NotificStatusStoreMapHelper.containsKey(idStatus))
            throw new RuntimeException("idStatus нет в Enum");

        return NotificStatusStoreMapHelper.get(idStatus);
    }
    public static List<NotificationStatus> getByIds(List<Integer> ids) throws RuntimeException {
        ArrayList<NotificationStatus> list = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            list.add( getById(id) );
        }
        return list;
    }

    @Override
    public String toString() {
        return "NotificationStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isArchiveStatus=" + isArchiveStatus +
                '}';
    }
};

