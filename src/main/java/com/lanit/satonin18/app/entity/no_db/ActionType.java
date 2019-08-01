package com.lanit.satonin18.app.entity.no_db;

import lombok.Getter;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

//ActionType.values()[idActionType] и ActionType.valueOf() -считаю криво

//can add abstract class(with generic) NotificationStatusStoreHelper
class ActionTypeStoreMapHelper {
    private static HashMap<Integer, ActionType> mapById = new HashMap<>();

    public static HashMap<Integer, ActionType> getMapById() {
        return mapById;
    }
//-------------------------------------

    public static int size() {
        return mapById.size();
    }

    public static boolean isEmpty() {
        return mapById.isEmpty();
    }

    public static ActionType get(Object key) {
        return mapById.get(key);
    }

    public static boolean containsKey(Object key) {
        return mapById.containsKey(key);
    }

    public static ActionType put(Integer key, ActionType value) {
        return mapById.put(key, value);
    }

    public static void putAll(Map<? extends Integer, ? extends ActionType> m) {
        mapById.putAll(m);
    }

    public static ActionType remove(Object key) {
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

    public static Collection<ActionType> values() {
        return mapById.values();
    }

    public static Set<Map.Entry<Integer, ActionType>> entrySet() {
        return mapById.entrySet();
    }

    public static ActionType getOrDefault(Object key, ActionType defaultValue) {
        return mapById.getOrDefault(key, defaultValue);
    }

    public static ActionType putIfAbsent(Integer key, ActionType value) {
        return mapById.putIfAbsent(key, value);
    }

    public static boolean remove(Object key, Object value) {
        return mapById.remove(key, value);
    }

    public static boolean replace(Integer key, ActionType oldValue, ActionType newValue) {
        return mapById.replace(key, oldValue, newValue);
    }

    public static ActionType replace(Integer key, ActionType value) {
        return mapById.replace(key, value);
    }

    public static ActionType computeIfAbsent(Integer key, Function<? super Integer, ? extends ActionType> mappingFunction) {
        return mapById.computeIfAbsent(key, mappingFunction);
    }

    public static ActionType computeIfPresent(Integer key, BiFunction<? super Integer, ? super ActionType, ? extends ActionType> remappingFunction) {
        return mapById.computeIfPresent(key, remappingFunction);
    }

    public static ActionType compute(Integer key, BiFunction<? super Integer, ? super ActionType, ? extends ActionType> remappingFunction) {
        return mapById.compute(key, remappingFunction);
    }

    public static ActionType merge(Integer key, ActionType value, BiFunction<? super ActionType, ? super ActionType, ? extends ActionType> remappingFunction) {
        return mapById.merge(key, value, remappingFunction);
    }

    public static void forEach(BiConsumer<? super Integer, ? super ActionType> action) {
        mapById.forEach(action);
    }

    public static void replaceAll(BiFunction<? super Integer, ? super ActionType, ? extends ActionType> function) {
        mapById.replaceAll(function);
    }
}

//todo move f(x) in other class
public enum ActionType{
    //i don't init var here and add in constructor HashMap<Integer, ActionType>.put(id,this)
    SENDING_ANSWER(1,"Направление ответа"),
    CONFORM_ANSWER(2,"Согласовать ответ"),
    REJECT_ANSWER(3, "Отклонить ответ");

    private static int count=0;

    @Getter
    private int id; //todo нужна проверка на уникальность
    @Getter
    private String name;

    private ActionType(int id, String name) throws RuntimeException {
//        super(name, id);
//        mapById.put(id, this);

        // в идеале это проверка должна быть не во время исполнения программы //хотя это статик вар
        if(ActionTypeStoreMapHelper.containsKey(id))
            throw new RuntimeException("повторяющиеся id в ENUM");

        ActionTypeStoreMapHelper.getMapById().put(id, this);

        this.id = id;
        this.name = name;
    }

//    public static ActionType[] values(){
//        return ALL_STATIC FINAL_OBJECT;
//    }

    public static List<Integer> getAllId(){ //can be add return SET
        ArrayList<Integer> ids = new ArrayList<>(ActionType.values().length);
        for (ActionType itemEnam : ActionType.values() ) {
            ids.add(itemEnam.getId() );
        }
        return ids;
    }

    public static ActionType getById(int idActionType) throws RuntimeException {
        if( ! ActionTypeStoreMapHelper.containsKey(idActionType))
            throw new RuntimeException("idActionType нет в Enum");

        return ActionTypeStoreMapHelper.get(idActionType);
    }

    @Override
    public String toString() {
        return "ActionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

