package me.yangtao.bz.tag.spring;

import me.yangtao.bz.tag.dubbo.TagContextHelper;
import me.yangtao.bz.tag.dubbo.TagThreadContext;
import me.yangtao.bz.tag.utils.CookieUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class RestAspect {
    private static final Logger log = LoggerFactory.getLogger(RestAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestPointCut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postPointCut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getPointCut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deletePointCut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patchPointCut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putPointCut() {
    }

    @Autowired
    private TagsConfig tagsConfig;

    private AtomicLong warnSkipCnt = new AtomicLong(0L);

    @Before("requestPointCut() || postPointCut() || getPointCut() || deletePointCut() || patchPointCut() || putPointCut()")
    public void getTagInfo(JoinPoint point) {
        if (request != null) {
            for (String tag : tagsConfig.getTags()) {
                String tagVal = getTagValue(request, tag);
                //放入Context
                TagContextHelper.put(tag, tagVal);
                System.out.println("tag." + tag + ":" + tagVal);
            }

            //TODO 以下For test，待删除
            Enumeration<String> headerName = request.getHeaderNames();
            while (headerName.hasMoreElements()) {
                String header = headerName.nextElement();
                System.out.println("header:" + header + ">" + request.getHeader(header));
            }
        }else{
            //控制日志打印频率为1%
            if(warnSkipCnt.addAndGet(1) % 100 == 0) {
                log.warn("RestAspect can not get HttpServletRequest object.");
            }
        }
        System.out.println(">>>>>>>>>>>>>getTagInfo done");
    }

    @After("requestPointCut() || postPointCut() || getPointCut() || deletePointCut() || patchPointCut() || putPointCut()")
    public void clearTagCache(JoinPoint point) {
        //为避免线程复用时，污染后续的请求任务，这里在Rest请求任务结束后清除线程缓存
        TagThreadContext.clear();
        System.out.println(">>>>>>>>>>>>>clearTagCache done");
    }

    private String getTagValue(HttpServletRequest request, String tag) {
        //优先从header中获取
        String tagVal = request.getHeader(tag);
        if (tagVal == null || tagVal.trim().length() == 0) {
            tagVal = CookieUtil.getCookieValue(request, tag, true);
        }
        return tagVal;
    }


}
