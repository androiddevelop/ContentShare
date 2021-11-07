package me.codeboy.clipboard.service;

import me.codeboy.clipboard.data.DataCenter;
import me.codeboy.clipboard.data.Room;
import me.codeboy.clipboard.util.EscapeUtil;
import me.codeboy.clipboard.util.ImageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * room service
 * Created by yuedong.li on 2019-07-12
 */
@Service
public class RoomService {
    /**
     * 获取房间内容
     *
     * @param name room id
     * @return contents
     */
    public List<String> getContents(String name) {
        Room room = DataCenter.getDataCenter().getRoom(name);
        if (room == null) {
            room = Room.createRoom(name, null);
            DataCenter.getDataCenter().addRoom(room);
        }
        return room.getContents();
    }

    /**
     * 添加内容
     *
     * @param name    房间
     * @param content 内容
     * @param type    文件类型 0文本 1图片
     * @return 成功失败
     */
    public boolean addContent(String name, String content, int type) {
        if (type == 1) {
            content = ImageUtil.saveBase64ToFile(content);
            if (content == null) {
                return false;
            }
            content = "<a target='_blank' href='" + content + "' title='点击查看'><img class='clip-image' src='" + content + "'></src></a>";
        } else {
            //去除html注入
            content = EscapeUtil.htmlEncode(content);
        }
        Room room = DataCenter.getDataCenter().getRoom(name);
        if (room != null) {
            room.removeContent(content);
            room.addContent(content);
            return true;
        }
        return false;
    }

    /**
     * 获取更新时间戳
     *
     * @param name 名字
     * @return 时间戳
     */
    public long getContentUpdateTimestamp(String name) {
        Room room = DataCenter.getDataCenter().getRoom(name);
        if (room != null) {
            return room.getLatestUpdateTime();
        }
        return -1L;
    }
}
