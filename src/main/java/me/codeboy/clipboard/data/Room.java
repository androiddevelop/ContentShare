package me.codeboy.clipboard.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 房间数据
 * Created by yuedong.li on 2019-07-12
 */
public class Room {
    public String name;
    public String password;
    public List<String> contents = new ArrayList<>();
    public final static int MAX_SIZE = 100;

    public Room() {
        contents.add("欢迎使用，您可以粘贴文本或者图片，进而共享给其他的设备");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 添加内容
     *
     * @param content 内容
     */
    public synchronized void addContent(String content) {
        if (content == null || content.length() == 0) {
            return;
        }
        if (contents.size() == MAX_SIZE) {
            contents.remove(contents.size() - 1);
        }
        contents.add(0, content);
    }

    /**
     * 快速创建一个房间
     *
     * @return 房间
     */
    public static Room createRoom(String name, String password) {
        Room room = new Room();
        room.name = name;
        room.password = password;
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
