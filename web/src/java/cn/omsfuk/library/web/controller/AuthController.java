package cn.omsfuk.library.web.controller;

import cn.omsfuk.library.web.model.User;
import cn.omsfuk.library.web.service.UserService;
import cn.omsfuk.library.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(String username, String password) {
        if (username == null || password == null) return "reader/login";
        if (userService.login(username, password)) {
            if (SessionUtil.getCurrentUser().hasRole("admin")) {
                return "redirect:/admin/main";
            } else if (SessionUtil.getCurrentUser().hasRole("reader")) {
                return "redirect:/reader/main";
            } else {
                return "failure";
            }
        } else {
            return "reader/login";
        }

    }

    @RequestMapping("register")
    public String register(User user, Model model) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return "reader/register";
        }
        userService.register(user);
        model.addAttribute("back_url", "/reader/login");
        return "common/success";
    }

    @RequestMapping("logout")
    public String logout() {
        userService.logout();
        return "redirect:/reader/login";
    }


}
