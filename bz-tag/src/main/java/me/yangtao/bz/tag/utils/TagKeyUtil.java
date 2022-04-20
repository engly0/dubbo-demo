package me.yangtao.bz.tag.utils;

public class TagKeyUtil {
    public static final String TAG_KEY_PREFIX = "bzTag_";

    public static String getTagKey(String tagName){
        return TAG_KEY_PREFIX+tagName;
    }
}
