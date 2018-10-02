package cn.omsfuk.library.web.service.impl;

import cn.omsfuk.library.web.dao.UserDAO;
import cn.omsfuk.library.web.model.User;
import cn.omsfuk.library.web.service.UserService;
import cn.omsfuk.library.web.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSerivceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean login(String username, String password) {
        User user = userDAO.selectUserByUsername(username);
        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            SessionUtil.setAttribute("user", user);
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        SessionUtil.removeAttribute("user");
    }

    @Override
    public boolean register(User user) {
        return userDAO.insertUser(user) == 1;
    }

    @Override
    public boolean updateUser(User user) {
        user.setId(SessionUtil.getCurrentUser().getId());
        if (userDAO.updateUser(user) == 1) {
            return true;
        }
        return false;
    }


}
