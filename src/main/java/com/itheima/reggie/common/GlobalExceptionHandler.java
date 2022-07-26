package com.itheima.reggie.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {Controller.class, RestController.class})
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
    /**
     *  数据异常处理
     * @param
     * @param exception
     * @return
     */

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> getSQLIntegrityViolationException(SQLIntegrityConstraintViolationException exception) {
        String message = exception.getMessage();
        log.error(message);

        if(message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String msg = "用户名"+split[2] + "已存在";
            return  R.error(msg);
        }
        return  R.error("未知错误！！！！！");
    }

    /**
     * 处理自定义的异常
     * @param exception
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public R<String> getCustomException(CustomException exception) {
        String message = exception.getMessage();
        log.error(message);
        return  R.error(message);
    }

    @ExceptionHandler(IOException.class)
    public R<String> getFileSizeLimitException(IOException exception) {
        String message = exception.getMessage();
        log.error(message);
        return  R.error("图片过大请重新上传。");
    }
}
