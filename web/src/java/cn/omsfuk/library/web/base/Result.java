package cn.omsfuk.library.web.base;

import lombok.Data;

@Data
public class Result {

    private static final int OK = 200;

    private static final int FAILURE = 300;

    private static final int UNAUTHORIZED = 401;

    private static final int FORBIDDEN = 403;

    private static final int NOT_FOUND = 404;

    private Integer status;

    private String message;

    private Object data;

    public Result(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Result ok(String data) {
        return new Result(OK, "ok", data);
    }

    public static Result failure(String message) {
        return new Result(FAILURE, message, null);
    }

}
