package cn.omsfuk.library.web.config;

import cn.omsfuk.library.web.dao.UserDAO;
import cn.omsfuk.library.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    private Map<String, GrantedAuthority> authorityMap = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        cn.omsfuk.library.web.model.User user = userDAO.selectUserByUsername(s);
        SessionUtil.setAttribute("user", user);
        return new User(user.getUsername(), user.getPassword(), resolveAuthoirity(user.getRoles()));
    }

    private List<GrantedAuthority> resolveAuthoirity(String roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(2);
        for (String role : roles.split(",")) {
            if (role != null && role.length() != 0) {
                GrantedAuthority authority = authorityMap.get(role);
                if (authority == null) {
                    authority = new SimpleGrantedAuthority(role);
                    authorityMap.put(role, authority);
                }
                authorities.add(authority);
            }
        }
        return authorities;
    }
}
