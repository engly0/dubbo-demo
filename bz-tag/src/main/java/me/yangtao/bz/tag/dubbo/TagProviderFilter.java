package me.yangtao.bz.tag.dubbo;

import me.yangtao.bz.tag.spring.TagsConfig;
import me.yangtao.bz.tag.utils.TagKeyUtil;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(
        group = {"provider"},
        order = 0
)
public class TagProviderFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TagProviderFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result;
        try {
            System.out.println("<<<TagProviderFilter>>>begin");
            for (String tag : TagsConfig.getTagsFromStatic()) {
                String tagKey = TagKeyUtil.getTagKey(tag);
                String tagValue = RpcContext.getContext().getAttachment(tagKey);
                TagThreadContext.putTagInfo(tagKey, tagValue);
            }

            result = invoker.invoke(invocation);
        } finally {
            //为避免污染后续dubbo生产者线程数据，清除RpcContext
            RpcContext.getContext().clearAttachments();
            //清除线程缓存
            TagThreadContext.clear();
        }

        return result;
    }
}
