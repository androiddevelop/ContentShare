# ContentShare
内容共享，快速在多设备上实现文字、图片共享。

### 支持

- 多设备间可以访问，共享文本和图片。
- 支持房间，可以针对特定的共享有单独的区域。
- 支持设置访问密码。(暂未支持)
- PC端直接粘贴即可，提升效率。
- 自动清理信息，清理24小时内没有任何访问的房间。

### 部署

1. 下载[ContentShare-1.0.0.jar](https://github.com/androiddevelop/Files/raw/main/ContentShare-1.0.0.jar)
2. 执行以下命令即可
```
java -jar ContentShare-1.0.0.jar
```

> 默认80端口，如果需要修改，可以 `application.properties` 中端口号即可。

### 二次开发

本项目基于 `SpringBoot` + `thymeleaf` 开发，可以在此基础上进一步改造。
