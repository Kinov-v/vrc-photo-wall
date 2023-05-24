package tech.vrcat.vrc.photo.wall.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vrcat.vrc.photo.wall.entity.User;
import tech.vrcat.vrc.photo.wall.mapper.UserMapper;

@RestController
public class UserController {
    @Autowired
    UserMapper mapper;

    @RequestMapping("/login")
    public int login(User user, String rem, HttpServletResponse response, HttpSession session) {
        User u = mapper.selectByUsername(user.getUsername());
        if (u != null) {
            if (u.getPassword().equals(user.getPassword())) {
                if (rem != null) {
                    //创建Cookie用户名和密码添加到里面 并下发给客户端
                    Cookie c1 = new Cookie("username", u.getUsername());
                    Cookie c2 = new Cookie("password", u.getPassword());
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
                //把当前登录成功的用户对象保存
                session.setAttribute("u",u);
                return 1;//登录成功!
            }
            return 3;//密码错误
        }
        return 2;//用户名不存在
    }

    @RequestMapping("/currentUser")
    public User currentUser(HttpSession session) {
        return (User) session.getAttribute("u");
    }

    @RequestMapping("/logout")
    public void logut(HttpSession session) {
        session.removeAttribute("u");
    }
}
