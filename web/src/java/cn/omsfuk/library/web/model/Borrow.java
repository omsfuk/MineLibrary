package cn.omsfuk.library.web.model;

import lombok.Data;

import java.util.Date;

@Data
public class Borrow {

    private Integer bookId;

    private Integer userId;

    private String title;

    private Date startDate;

    private Date endDate;

    private Boolean returned;

    private Integer renewTimes;
}
