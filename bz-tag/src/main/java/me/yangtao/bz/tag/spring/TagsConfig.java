package me.yangtao.bz.tag.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TagsConfig {
    @Value("${bz-tag.tags: app,bz_channel,user-agent}")
    private String[] tags = new String[]{"app","bz_channel"};
    private static TagsConfig cachedConfig;

    public TagsConfig(){
        cachedConfig = this;
    }

    public String[] getTags() {
        return tags;
    }

    public static String[] getTagsFromStatic(){
        if(cachedConfig == null){
            new TagsConfig();
        }
        return cachedConfig.tags;
    }
}
