package com.commerce.interceptor;

import com.commerce.util.SessionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        // 세션이 없는 경우 새로 생성하지 않음
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionUtils.LOGIN_SESSION) == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
