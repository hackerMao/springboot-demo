package com.hacker.utils;

import com.hacker.core.constants.AliMsgTemplateConstant;
import com.hacker.core.constants.RedisKeyConstant;
import com.hacker.core.enumeration.CaptchaType;
import com.hacker.exception.ParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@Slf4j
@Component
public class CaptchaUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static CaptchaUtil captchaUtil;

    @PostConstruct
    public void init() {
        captchaUtil = this;
        captchaUtil.stringRedisTemplate = this.stringRedisTemplate;
    }

    /**
     * 判断是否存验证码发送记录
     *
     * @param phoneNumber 手机号
     * @param t           验证码类型
     * @return bool
     * @throws ParameterException
     */
    public static Boolean hasFlag(String phoneNumber, CaptchaType t) throws ParameterException {
        try {
            String flag = String.format(RedisKeyConstant.CAPTCHA_FLAG_KEY, t.getText(), phoneNumber);
            return captchaUtil.stringRedisTemplate.opsForValue().get(flag) != null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(phoneNumber + ": 判断验证码标记异常-->" + e);
            return true;
        }
    }

    /**
     * 获取短信模板
     *
     * @param captchaType 验证码类型
     * @return
     */
    public static String getTemplateCode(CaptchaType captchaType) {
        String tmpCode = null;
        switch (captchaType) {
            case LOGIN:
                tmpCode = AliMsgTemplateConstant.LOGIN;
                break;
            case REGISTER:
                tmpCode = AliMsgTemplateConstant.REGISTER;
                break;
            case RESET_PWD:
                tmpCode = AliMsgTemplateConstant.RESET_PASSWORD;
                break;
            case MODIFY_PWD:
                tmpCode = AliMsgTemplateConstant.MODIFY_PASSWORD;
                break;
            default:
                tmpCode = AliMsgTemplateConstant.VERIFY_CODE;
                break;
        }
        return tmpCode;
    }

    /**
     * 发送短信验证码
     *
     * @param phoneNumber 手机号
     * @param captchaType 验证码类型
     * @return bool
     */
    public static Boolean send(String phoneNumber, CaptchaType captchaType) {
        // 生成验证码
        Integer captcha = new Random().nextInt(999999) % (999999 - 100000 + 1) + 100000;
        // 发送短信验证码
        try {
            String jsonParams = "{\"code\":" + "\"" + captcha + "\"}";
            String flagKey = String.format(RedisKeyConstant.CAPTCHA_FLAG_KEY, captchaType.getText(), phoneNumber);
            String key = String.format(RedisKeyConstant.CAPTCHA_KEY, captchaType.getText(), phoneNumber);

            captchaUtil.stringRedisTemplate.opsForValue().set(flagKey, "1", 60, TimeUnit.SECONDS);
            captchaUtil.stringRedisTemplate.opsForValue().set(key, captcha.toString(), 5 * 60, TimeUnit.SECONDS);

            if (captchaUtil.stringRedisTemplate.opsForValue().get(key) != null) {
                String tmpCode = getTemplateCode(captchaType);
                return AliMessageUtil.sendMsg(tmpCode, phoneNumber, jsonParams);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(phoneNumber + ": 发送验证码异常-->" + e);
            return false;
        }

    }

    /**
     * 验证码校验
     *
     * @param phoneNumber 手机号
     * @param captchaType 验证码类型
     * @param captcha     验证码
     * @return
     */
    public static Boolean checkCaptcha(String phoneNumber, CaptchaType captchaType, String captcha) {
        try {
            String key = captchaType.getText() + "_" + phoneNumber;
            log.info("key: " + key);
            String flag = "flag_" + key;
            Object c = captchaUtil.stringRedisTemplate.opsForValue().get(key);
            log.info("captcha: " + c);
            if (c == null) {
                return false;
            }
            boolean yes = captcha.equals(c.toString());
            log.info("验证结果：" + yes);
            if (yes) {
                captchaUtil.stringRedisTemplate.delete(key);
            }
            captchaUtil.stringRedisTemplate.delete(flag);
            return yes;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(phoneNumber + ": 验证码校验异常-->" + e);
            return false;
        }
    }
}
