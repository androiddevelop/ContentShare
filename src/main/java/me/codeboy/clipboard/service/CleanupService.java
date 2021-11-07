package me.codeboy.clipboard.service;

import me.codeboy.clipboard.data.DataCenter;
import me.codeboy.clipboard.data.Room;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * 清理服务
 * Created by yuedong.li on 2019-07-23
 */
@Configuration
public class CleanupService {
    /**
     * 指定时间内没有访问进行数据删除
     */
    private final static long CLEANUP_TIME = 360000;

    @Scheduled(initialDelay = 60000, fixedDelay = CLEANUP_TIME)
    public void autoCleanup() {
        try {
            Set<String> roomNames = DataCenter.getDataCenter().getAllData().keySet();
            for (String room : roomNames) {
                Room tmp = DataCenter.getDataCenter().getAllData().get(room);
                if (tmp != null && tmp.needDelete()) {
                    DataCenter.getDataCenter().getAllData().remove(room);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
