package cn.omsfuk.library.web.filter;

import cn.omsfuk.library.web.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");
        if (url.startsWith("/reader") || url.startsWith("reader")) {
            if (user == null || !user.hasRole("reader")) {
                response.sendRedirect("/login");
                return false;
            }
            return true;
        }
        /**
        if (url.startsWith("/admin") || url.startsWith("admin")) {
            if (user == null || !user.hasRole("admin")) {
                response.sendRedirect("/login");
                return false;
            }
            return true;
        }
         */
        return true;
    }

}
