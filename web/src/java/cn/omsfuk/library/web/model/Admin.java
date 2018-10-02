package cn.omsfuk.library.web.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Admin {

    private Integer id;

    private String username;

    private String password;

    private Date reg_date;
}
