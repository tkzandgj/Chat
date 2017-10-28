package cn.sinjinsong.chat.server.util;

import cn.sinjinsong.common.domain.Request;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/5/26.
 */
public class RequestParser {

    /**
     * 解析url中的参数
     * https://www.baidu.com/request ? username=XXXX & password=XXXX
     * @param rawURL
     * @return
     */
    public static Request parse(String rawURL){
        String[] slices = rawURL.split("\\?");
        Map<String,String> params = null;
        if(slices.length > 1){
            params = new HashMap<>();
            String [] paramsStr = slices[1].split("&");
            for(String param: paramsStr){
                String key = param.substring(0,param.indexOf("="));
                String value = param.substring(param.indexOf("=")+1);
                if(StringUtils.isEmpty(key) || StringUtils.isEmpty(value)){
                    continue;
                }
                params.put(key,value);
            }
        }
        return new Request(slices[0],params);
    }


    public static void main(String[] args){
        String str = "https://www.baidu.com/request ? username=XXXX & password=XXXX";
        Request request = RequestParser.parse(str);
        Map<String, String> params = request.getParams();
        for (Map.Entry<String, String> map : params.entrySet()){
            System.out.println(map.getKey() + " = " + map.getValue());
        }
    }
}
