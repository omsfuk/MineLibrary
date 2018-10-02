package cn.omsfuk.library.web.service;

import cn.omsfuk.library.web.model.User;

public interface UserService {

    boolean login(String username, String password);

    void logout();

    boolean register(User user);

    boolean updateUser(User user);


}
