package com.rongqi.manage.interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

//    @Autowired
//    private IUserService userService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String uri = request.getRequestURI();
//        if (uri.indexOf("/login") >= 0) {
//            return true;
//        }
//        //获取session
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("USER_SESSION");
//        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
//        if (user != null) {
//            User loginUser = userService.login(user.getAccount(), user.getPassword());
//            if (loginUser != null){
//
//                return true;
//            }else {
//                //不符合条件的给出提示信息，并转发到登录页面
//                request.setAttribute("msg", "登入的用户名或密码错误");
//                request.getRequestDispatcher("login.html").forward(request, response);
//                return false;
//            }
//        }
//        //不符合条件的给出提示信息，并转发到登录页面
//        request.setAttribute("msg", "您还没有登录，请先登录！");
//        request.getRequestDispatcher("login.html").forward(request, response);
//        return false;
//    }
}
