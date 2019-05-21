package com.macro.mall.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @ClassName BankImageUtils
 * @Description
 * @Author shuang669539827@163.com
 * @Date2019/5/21 9:23
 * @Version v1.0
 **/
public class BankImageUtils {

    public static void main(String[] args) {
        JSONArray images = getImages("9000824886");
        System.out.println(images);
        String s = HttpUtil.get("https://gh.aku.pub/U/Icbccrawler/icbcgetimg?proid=9000824886&_=1558365768695");
        System.out.println(s);
    }

    public static JSONArray getImages(String proid){
        if (proid != null) {
            String url ="https://gh.aku.pub/U/Icbccrawler/icbcgetimg";
            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            HashMap<String, Object> paramMap = new HashMap<>(16);
            paramMap.put("proid",proid);
            paramMap.put("_", DateUtil.currentSeconds());
            String json= HttpUtil.get(url, paramMap);
            if (json != null) {
                JSONObject jsonObject = JSON.parseObject(json);
                if (jsonObject != null && jsonObject.getIntValue("code")>0) {
                    return  jsonObject.getJSONArray("datas");
                }

            }
        }
      return null;
    }

}
