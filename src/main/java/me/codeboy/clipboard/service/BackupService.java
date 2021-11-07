package me.codeboy.clipboard.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import me.codeboy.clipboard.data.DataCenter;
import me.codeboy.clipboard.data.Room;
import me.codeboy.common.base.io.util.CBFileUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * 同步配置
 * Created by yuedong.li on 2019-07-12
 */
@Service
public class BackupService implements ApplicationRunner {
    private final static String FILE = "room_data";
    /**
     * 指定时间内没有重复提交备份时进行数据备份
     */
    private final static long BACKUP_TIME = 60000;
    /**
     * 上一次备份时间
     */
    private long lastBackupTaskBackupTime = System.currentTimeMillis();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File file = new File(FILE);
        if (file.exists()) {
            String content = CBFileUtil.getFileContent(FILE);
            Map<String, Room> rooms = JSON.parseObject(content, new TypeReference<Map<String, Room>>() {
            }.getType());

            DataCenter.getDataCenter().restoreData(rooms);
        }
    }

    /**
     * 数据备份
     */
    public void backup() {
        try {
            long now = System.currentTimeMillis();
            if (now - lastBackupTaskBackupTime < BACKUP_TIME) {
                return;
            }
            lastBackupTaskBackupTime = now;
            String content = JSON.toJSONString(DataCenter.getDataCenter().getAllData());
            CBFileUtil.saveContentToFile(content, FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(initialDelay = 10000, fixedDelay = BACKUP_TIME)
    public void autoBackup() {
        try {
            long now = System.currentTimeMillis();
            if (now - lastBackupTaskBackupTime > BACKUP_TIME) {
                lastBackupTaskBackupTime = now;
                String content = JSON.toJSONString(DataCenter.getDataCenter().getAllData());
                CBFileUtil.saveContentToFile(content, FILE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
