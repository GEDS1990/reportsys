package com.midnight.reportsys.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义异常解析处理类
 * 这是DispatcherServlet的统一异常处理器，会最终处理controller中抛出的异常
 * @author Midnight
 * @create 2016-08-22 20:13
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception ex) {
        ex.printStackTrace();
        PrintWriter pw = null;
        try {
			 pw  = httpServletResponse.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        CustomException customException = null;
        String message = null;
        //如果是自定义异常，就可以直接拿出异常信息
        if(ex instanceof CustomException){
            customException = (CustomException)ex;
            //得到异常信息
           message = customException.getMessage();
        }else if (ex instanceof  MaxUploadSizeExceededException) {
        	 message = "文件过大";
        	 pw.write("oversize");
        	
		}
        else {
            //如果不是自定义异常，就说明是未知的异常
            customException = new CustomException("发生未知错误");
        }
       
        //将异常信息放在request域
        httpServletRequest.setAttribute("exceptionMessage",message);
        try{
        	 httpServletRequest.getRequestDispatcher("/page/fail.jsp").forward(httpServletRequest,httpServletResponse);       
        	 
        }catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ModelAndView();

    }
}
