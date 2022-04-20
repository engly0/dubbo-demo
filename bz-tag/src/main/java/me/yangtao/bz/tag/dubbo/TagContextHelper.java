package me.yangtao.bz.tag.dubbo;

import me.yangtao.bz.tag.utils.TagKeyUtil;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Map;

public class TagContextHelper {
    public static void put(String tag,String tagValue){
        String tagKey = TagKeyUtil.getTagKey(tag);
        RpcContext.getContext().setAttachment(tagKey, tagValue);
        TagThreadContext.putTagInfo(tagKey, tagValue);
    }

    public static String get(String tag){
        String tagKey = TagKeyUtil.getTagKey(tag);
        return TagThreadContext.getTagValue(tagKey);
    }

    public static Map<String, String> getAll(){
        return TagThreadContext.getAll();
    }
}
