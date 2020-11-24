package com.czxy.util;

import com.aliyun.oss.OSSClient;

public class AliYunUtil {
    //  accessKeyId
    public static final String accessKeyId = "LTAI4FfFGEjFZFrwNq8BcqT7";
    //  accessKeySecret
    public static final String accessKeySecret = "i37miqG8ZJfWKkiI2l5ZqRuaIopFCN";
    //  短信签名名称
    public static final String signName = "最强妖怪";
    //  短信模板ID
    public static final String templateCode = "SMS_183246112";//SMS_183246116(注册使用)；SMS_183246112(登陆使用)

    //  所在地区
    public static String endpoint = "oss-cn-beijing.aliyuncs.com";
    //  oss应用名称
    public static String bucketName = "in-soul";
    //  oss对象
    public static OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

}
