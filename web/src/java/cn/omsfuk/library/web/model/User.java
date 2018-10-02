package cn.omsfuk.library.web.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public class User {

    @Id
    private Integer id;

    private String username;

    private String password;

    private int max_borrow;

    private String email;

    private String phone;

    private String qq;

    private Date reg_date;

    private String roles;

    private ArrayList<String> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMax_borrow() {
        return max_borrow;
    }

    public void setMax_borrow(int max_borrow) {
        this.max_borrow = max_borrow;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean hasRole(String role) {
        if (roleList == null) {
            roleList = new ArrayList<String>(2);
            if (roles == null) return false;
            for (String role0 : roles.split(",")) {
                if (role0 != null && !role0.equals("")) {
                    roleList.add(role0);
                }
            }
        }
        return roleList.contains(role);
    }
}
