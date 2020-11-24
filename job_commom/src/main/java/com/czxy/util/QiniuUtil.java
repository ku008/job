package com.czxy.util;

import com.qiniu.util.Auth;

public class QiniuUtil {
    private static String accessKey = "bc07FsQ-wEJkgsbVPSM_EbwB2eYFFOFZkKf5zEMP";
    private static String secretKey = "7p9TXx9dfzi5ECSavNz-b7YWD0T_Je7FAqzo-1Cq";
    public static String bucket = "my-qi";
    public static Auth auth = Auth.create(accessKey, secretKey);
//    public static String uptoken = auth.uploadToken(bucket);
}
