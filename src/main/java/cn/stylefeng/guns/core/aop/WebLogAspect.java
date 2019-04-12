package cn.stylefeng.guns.core.aop;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.common.HttpUtils;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 全局请求日志类
 */
@Aspect
@Component
public class WebLogAspect {

    @Autowired
    private IUserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * cn.stylefeng.guns.modular..controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        // 记录下请求内容
        LOG.info("请求地址 : " + request.getRequestURL().toString());
        LOG.info("HTTP METHOD : " + request.getMethod());
        LOG.info("IP : " + request.getRemoteAddr());
        LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        LOG.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
        int id = 0;
        if(ShiroKit.getUser() != null) {
            id = ShiroKit.getUser().getId();
            User u = userService.selectById(id);
            if(u.getStatus() == GlobalNumber.INT_FIX_2) {
                request.setAttribute("tips", "账户已冻结");
                ShiroKit.getSubject().logout();
                response.sendRedirect("/login");
            }
        }
        LOG.info("请求用户ID : " + id);
        String prasm = "";
        if(joinPoint.getArgs().length == 0) {
            prasm = "";
        } else {
            prasm = Arrays.toString(joinPoint.getArgs());
        }

        String sendUrl = "http://127.0.0.1:8773/log/save?";
        sendUrl += "userId=" + id +
                "&address=" + request.getRequestURL().toString()
                +"&addr=" + request.getRemoteAddr()
                +"&params=" + prasm
                +"&class_method=" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // HttpUtils.sendHttp(sendUrl);
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        LOG.info("返回值 : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        LOG.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }

}
