package com.fwxm.scientific.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ServletUtils {

    /**
     * 此forward方法执行完毕之后不会输出内容到浏览器，而是把输出到字节流，最后以字符串的形式返回
     * @param request
     * @param response
     * @param src
     * @return
     */
    public static String forward(HttpServletRequest request, HttpServletResponse response, String src) {
        try{

            /*  ↓↓↓↓↓重新构造response，修改response中的输出流对象，使其输出到字节数组↓↓↓↓↓  */
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            
            final ServletOutputStream servletOuputStream = new ServletOutputStream() {
                @Override
                public void write(int b) throws IOException {
                    byteArrayOutputStream.write(b);
                }
            };

            final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF-8"));

            HttpServletResponse responses = new HttpServletResponseWrapper(response) {
                public ServletOutputStream getOutputStream() {
                    return servletOuputStream;
                }
                public PrintWriter getWriter() {
                    return printWriter;
                }
            };
             /*  ↑↑↑↑↑↑重新构造response，修改response中的输出流对象，使其输出到字节数组↑↑↑↑↑↑ */

            //执行forward操作
            request.getRequestDispatcher(src).forward(request,responses);

            //把字节流中的内容太转为字符串
            return new String(byteArrayOutputStream.toByteArray(),"utf-8");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
