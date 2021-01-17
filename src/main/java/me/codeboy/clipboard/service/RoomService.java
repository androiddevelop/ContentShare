package me.codeboy.clipboard.service;

import me.codeboy.clipboard.data.DataCenter;
import me.codeboy.clipboard.data.Room;
import me.codeboy.clipboard.util.ImageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * @param name room name
     * @return contents
     */
    public List<String> getContents(String name) {
        List<String> result = new ArrayList<>();
        Room room = DataCenter.getDataCenter().getRoom(name);
        if (room != null) {
            result.addAll(room.getContents());
        }
        return result;
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
        }
        Room room = DataCenter.getDataCenter().getRoom(name);
        if (room != null) {
            room.getContents().remove(content);
            room.getContents().add(0, content);
            return true;
        }
        return false;
    }
}
