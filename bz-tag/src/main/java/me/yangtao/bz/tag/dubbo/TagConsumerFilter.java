package me.yangtao.bz.tag.dubbo;

import me.yangtao.bz.tag.spring.TagsConfig;
import me.yangtao.bz.tag.utils.TagKeyUtil;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(
        group = {"consumer"},
        order = 0
)
public class TagConsumerFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TagConsumerFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result;

        try {
            System.out.println("<<<TagConsumerFilter>>>begin");
            //每次调用前，将上下文缓存的数据放入RpcContext
            for (String tag : TagsConfig.getTagsFromStatic()) {
                String tagKey = TagKeyUtil.getTagKey(tag);
                String tagValue = TagThreadContext.getTagValue(tagKey);
                RpcContext.getContext().setAttachment(tagKey, tagValue);
            }

            result = invoker.invoke(invocation);
        } finally {
            //为避免污染后续dubbo生产者线程数据，清除RpcContext
            RpcContext.getContext().clearAttachments();
        }

        return result;
    }
}
