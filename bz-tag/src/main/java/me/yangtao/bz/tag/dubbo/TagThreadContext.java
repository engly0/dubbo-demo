package me.yangtao.bz.tag.dubbo;

import java.util.HashMap;
import java.util.Map;

public class TagThreadContext {
    static ThreadLocal<Map<String,String>> tagsThreadCache = new ThreadLocal<>();

    /**
     * 清空线程缓存
     * 注意：务必在每次新线程任务开始时或结束时清除，避免线程复用时污染到后续的线程任务
     */
    public static void clear(){
        tagsThreadCache.remove();
    }

    /**
     * 向当前线程放入tag信息。
     * 注意：tagKey必须经过TagKeyUtil包装，避免撞车
     * @param tagKey
     * @param tagValue
     */
    public static void putTagInfo(String tagKey,String tagValue){
        if(tagValue == null){
            return;
        }
        Map<String, String> tagMap = tagsThreadCache.get();
        if(tagMap == null){
            tagMap = new HashMap<>();
            tagsThreadCache.set(tagMap);
        }
        tagMap.put(tagKey,tagValue);
    }

    /**
     * 从线程缓存中获取tag信息。
     * 注意：tagKey必须经过TagKeyUtil包装，避免撞车
     * @param tagKey
     * @return
     */
    public static String getTagValue(String tagKey){
        Map<String, String> tagMap = tagsThreadCache.get();
        if(tagMap != null){
            return tagMap.get(tagKey);
        }
        return null;
    }

    public static Map<String,String> getAll(){
        return tagsThreadCache.get();
    }
}
