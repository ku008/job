package com.czxy.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.stereotype.Service;

import static com.czxy.util.AliYunUtil.*;


@Service
public class AliYunMessageService {
    /**
     * 发送短信验证码
     *
     * @param telephoneNumber 用户填写的手机号码
     * @return validateCode     该用户此次操作的短信验证码
     */
    public BaseResult sendMessage(String telephoneNumber) {

        //  随机生成 6位 验证码
        String validateCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //  请求方式  POST（默认）
        request.setMethod(MethodType.POST);
        //  阿里云短信服务器（默认）
        request.setDomain("dysmsapi.aliyuncs.com");
        //  版本号（默认）
        request.setVersion("2017-05-25");
        //  执行动作  发送短信 （默认）
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":" + validateCode + "}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("验证码："+validateCode);
            return new BaseResult(CommomUtil.SUCCESS,"发送验证码成功："+response.getData(),validateCode);
        } catch (ServerException e) {
            e.printStackTrace();
            return new BaseResult(CommomUtil.FAIL,"抱歉，您的当前获取次数已达上限，请于下一个时段或明天再试！");
        } catch (ClientException e) {
            e.printStackTrace();
            return new BaseResult(CommomUtil.FAIL,"系统异常，请重新获取！");
        }
    }

}
