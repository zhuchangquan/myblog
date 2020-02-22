package com.mind.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mind.utils.R;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	    @ExceptionHandler(value = Exception.class)
	    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

	        log.error("------------------>捕捉到全局异常", e);

	        ModelAndView mav = new ModelAndView();
	        mav.addObject("msg", e.getMessage());
	        mav.addObject("url", req.getRequestURL());
	        mav.setViewName("error/500");
	        return mav;
	    }

	    @ExceptionHandler(value = MyException.class)
	    @ResponseBody
	    public R jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {

	        //TODO 错误日志处理

	        return R.fail(e.getMessage(), "some data");
	    }
	    
	    @ExceptionHandler(value = IllegalArgumentException.class)
	    public ModelAndView assertExceptionHandler(HttpServletRequest req, IllegalArgumentException e) throws Exception {

	        log.error("------------------>捕捉到assert异常", e);

	        ModelAndView mav = new ModelAndView();
	        mav.addObject("msg", e.getMessage());
	        mav.addObject("url", req.getRequestURL());
	        mav.setViewName("error/500");
	        return mav;
	    }
}
