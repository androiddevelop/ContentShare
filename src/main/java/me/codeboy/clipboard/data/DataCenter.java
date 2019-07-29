package me.codeboy.clipboard.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据中心
 * Created by yuedong.li on 2019-07-12
 */
public class DataCenter {
    private static DataCenter dataCenter = new DataCenter();
    private Map<String, Room> rooms = new ConcurrentHashMap<>(); //房间

    private DataCenter() {
        this.rooms.put("share", new Room());
    }

    public static DataCenter getDataCenter() {
        return dataCenter;
    }

    /**
     * 添加房间
     *
     * @param room 房间
     * @return 创建结果
     */
    public boolean addRoom(Room room) {
        if (room == null) {
            return false;
        }
        if (rooms.containsKey(room.name)) {
            return false;
        }
        rooms.put(room.name, room);
        return true;
    }

    /**
     * 获取room信息
     *
     * @param name name
     * @return 房间信息，房间不存在则返回空
     */
    public Room getRoom(String name) {
        if (name != null && rooms.containsKey(name)) {
            return rooms.get(name);
        }
        return null;
    }

    /**
     * 恢复数据
     *
     * @param rooms 数据
     */
    public void restoreData(Map<String, Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * 获取所有数据
     *
     * @return 所有数据
     */
    public Map<String, Room> getAllData() {
        return rooms;
    }
}
