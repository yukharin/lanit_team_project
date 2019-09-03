package com.lanit.satonin18.app.entity.enum_type;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

//ActionType.values()[idActionType] и ActionType.valueOf() -считаю криво

//can add abstract class(with generic) ActionTypeStoreMapHelper
class NotificStatusStoreMapHelper {
    private static HashMap<Integer, Status> mapById = new HashMap<>();

    public static HashMap<Integer, Status> getMapById() {
        return mapById;
    }
//-------------------------------------

    public static int size() {
        return mapById.size();
    }

    public static boolean isEmpty() {
        return mapById.isEmpty();
    }

    public static Status get(Object key) {
        return mapById.get(key);
    }

    public static boolean containsKey(Object key) {
        return mapById.containsKey(key);
    }

    public static Status put(Integer key, Status value) {
        return mapById.put(key, value);
    }

    public static void putAll(Map<? extends Integer, ? extends Status> m) {
        mapById.putAll(m);
    }

    public static Status remove(Object key) {
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

    public static Collection<Status> values() {
        return mapById.values();
    }

    public static Set<Map.Entry<Integer, Status>> entrySet() {
        return mapById.entrySet();
    }

    public static Status getOrDefault(Object key, Status defaultValue) {
        return mapById.getOrDefault(key, defaultValue);
    }

    public static Status putIfAbsent(Integer key, Status value) {
        return mapById.putIfAbsent(key, value);
    }

    public static boolean remove(Object key, Object value) {
        return mapById.remove(key, value);
    }

    public static boolean replace(Integer key, Status oldValue, Status newValue) {
        return mapById.replace(key, oldValue, newValue);
    }

    public static Status replace(Integer key, Status value) {
        return mapById.replace(key, value);
    }

    public static Status computeIfAbsent(Integer key, Function<? super Integer, ? extends Status> mappingFunction) {
        return mapById.computeIfAbsent(key, mappingFunction);
    }

    public static Status computeIfPresent(Integer key, BiFunction<? super Integer, ? super Status, ? extends Status> remappingFunction) {
        return mapById.computeIfPresent(key, remappingFunction);
    }

    public static Status compute(Integer key, BiFunction<? super Integer, ? super Status, ? extends Status> remappingFunction) {
        return mapById.compute(key, remappingFunction);
    }

    public static Status merge(Integer key, Status value, BiFunction<? super Status, ? super Status, ? extends Status> remappingFunction) {
        return mapById.merge(key, value, remappingFunction);
    }

    public static void forEach(BiConsumer<? super Integer, ? super Status> action) {
        mapById.forEach(action);
    }

    public static void replaceAll(BiFunction<? super Integer, ? super Status, ? extends Status> function) {
        mapById.replaceAll(function);
    }
}

//todo move f(x) in other class
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    NEW(0, "Новое"),
    IN_WORK(1, "В работе"),
    REJECTED(2, "Отклонено", true),
    OK(3, "Одобрено", true);

    @Getter
    private int id; //todo нужна проверка на уникальность
    @Getter
    private String name;
    @Getter
    private boolean isArchiveStatus = false;

    private Status(int id, String name, boolean isArchiveStatus) {
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

    private Status(int id, String name) {
        this(id, name, false);
    }

//        public static mas[] values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }

    public static void addAllArchiveStatusesInTheList(List<Status> list){
        for (Status itemEnam : Status.values() ) {
            if(itemEnam.isArchiveStatus())  list.add(itemEnam);
        }
    }
    public static List<Integer> getAllId(){
        ArrayList<Integer> ids = new ArrayList<>( Status.values().length );

        for (Status itemEnam : Status.values() ) {
            ids.add(itemEnam.getId() );
        }
        return ids;
    }
    public static List<Integer> getArchiveStatusesId(){
        ArrayList<Integer> ids = new ArrayList<>(/*IdStatus.values().length*/);

        for (Status itemEnam : Status.values() ) {
            if(itemEnam.isArchiveStatus())  ids.add(itemEnam.getId() );
        }
        return ids;
    }

    public static Status getById(int idStatus) throws RuntimeException {
        if( ! NotificStatusStoreMapHelper.containsKey(idStatus))
            throw new RuntimeException("idStatus нет в Enum");

        return NotificStatusStoreMapHelper.get(idStatus);
    }
    public static List<Status> getByIds(List<Integer> ids) throws RuntimeException {
        ArrayList<Status> list = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            list.add( getById(id) );
        }
        return list;
    }
    public static List<Status> getArchiveStatuses() throws RuntimeException {
        ArrayList<Status> list = new ArrayList<>(/*IdStatus.values().length*/);
        for (Status stat: Status.values()) {
            if(stat.isArchiveStatus())
                list.add( stat );
        }
        return list;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isArchiveStatus=" + isArchiveStatus +
                '}';
    }
};

