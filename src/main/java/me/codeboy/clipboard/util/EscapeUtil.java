package me.codeboy.clipboard.util;

/**
 * 转义
 * Created by yuedong.li on 2019-07-23
 */
public class EscapeUtil {
    /**
     * 转义
     *
     * @param message 源码
     * @return 转移后
     */
    public static String htmlEncode(String message) {
        if (message == null)
            return "";

        char[] content = message.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char c : content) {
            switch (c) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
//                case '&':
//                    result.append("&amp;");
//                    break;
//                case '"':
//                    result.append("&quot;");
//                    break;
                default:
                    result.append(c);
            }
        }
        return (result.toString());
    }
}
