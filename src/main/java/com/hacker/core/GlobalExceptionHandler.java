package com.hacker.core;

import com.hacker.core.config.ExceptionCodeConfiguration;
import com.hacker.exception.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

/**
 * @Description 全局异常处理类
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;

    /**
     * 最基本异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public UnifyErrorResponse handleException(HttpServletRequest request, Exception e) {
        String url = request.getRequestURI();
        String method = request.getMethod();

        return new UnifyErrorResponse(9999, "服务器粗错啦~~",
                method + " " + url, System.currentTimeMillis());
    }

    /**
     * 自定义异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpException.class)
    public UnifyErrorResponse handleHttpException(HttpServletRequest request, HttpException e) {
        String url = request.getRequestURI();
        String method = request.getMethod();

        String message = codeConfiguration.getMessage(e.getCode());
        return new UnifyErrorResponse(e.getCode(), message,
                method + " " + url, System.currentTimeMillis());
    }

    /**
     * 参数类型不匹配异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public UnifyErrorResponse handleMethodArgumentTypeException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String message = this.codeConfiguration.getMessage(10001);

        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * 参数无效异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public UnifyErrorResponse handleBeanValidation(HttpServletRequest request, MethodArgumentNotValidException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append(';')
        );
        String message = errorMsg.toString();

        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * validator抛出异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public UnifyErrorResponse handleConstraintException(HttpServletRequest request, ConstraintViolationException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String message = e.getMessage();

        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * 重复键值异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public UnifyErrorResponse handleDuplicateKeyException(HttpServletRequest req, DuplicateKeyException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = String.valueOf(e.getCause().getMessage());
        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * 参数缺失异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public UnifyErrorResponse handleMethodArgumentTypeException(HttpServletRequest req, MissingServletRequestParameterException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = e.getMessage();
        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * 反序列化异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public UnifyErrorResponse handleMethodArgumentTypeException(HttpServletRequest req, HttpMessageNotReadableException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = e.getMessage();
        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * validator异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public UnifyErrorResponse handleValidationException(HttpServletRequest req, ValidationException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = this.codeConfiguration.getMessage(10001);
        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * 数据违反完整性异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public UnifyErrorResponse handleDataIntegrityViolationException(HttpServletRequest req, DataIntegrityViolationException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        // String message = e.getMessage();
        String message = e.getCause().getMessage();
        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }

    /**
     * 请求头缺失异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public UnifyErrorResponse handleMissingRequestHeaderException(HttpServletRequest req, MissingRequestHeaderException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = e.getCause().getMessage();
        return new UnifyErrorResponse(10001, message,
                method + " " + requestUrl, System.currentTimeMillis());
    }
}
