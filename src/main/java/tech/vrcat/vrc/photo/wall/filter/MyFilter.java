package tech.vrcat.vrc.photo.wall.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tech.vrcat.vrc.photo.wall.entity.User;

import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = {"/insert.html", "/product/delete", "/admin.html"})
public class MyFilter implements Filter {
    /*init初始化方法*/
    public void init(FilterConfig config) throws ServletException {
    }

    /*destory销毁过滤器时执行的方法*/
    public void destroy() {
    }

    //此方法当客户端向服务器发出请求,并且这个请求被过滤器处理的话此方法执行
    //此方法中的doFilter方法执行 代表允许访问资源,
    //  不执行则代表禁止访问资源
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //将父类类型转成子类类型
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //获取Session对象
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("u");
        if (u != null) {//登录过
            chain.doFilter(request, response);//放行
        } else {//未登录
            //重定向:  让客户端向指定的地址再次发送请求
            resp.sendRedirect("/login.html");
        }
        chain.doFilter(request, response);
    }
}
