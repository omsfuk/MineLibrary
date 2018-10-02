package cn.omsfuk.library.web.model;

import lombok.Data;

import java.util.Date;

@Data
public class Reader {

    private int id;

    private String username;

    private String password;

    private int max_borrow;

    private String email;

    private String phone;

    private String qq;

    private Date reg_date;

}
