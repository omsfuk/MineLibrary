package cn.omsfuk.library.web.util;

import cn.omsfuk.library.web.model.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtil {

    public static void setAttribute(String name, Object obj) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, obj, RequestAttributes.SCOPE_SESSION);
    }

    public static void removeAttribute(String name) {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static void getAttribute(String name) {
        RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static User getCurrentUser() {
        return (User) RequestContextHolder.getRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
    }
}
