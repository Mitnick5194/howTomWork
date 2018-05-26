package com.ajie.ex2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author niezhenjie
 * 
 */
public class PrimitiveServlet implements Servlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException,
			IOException {
		System.out.println("进入了service方法 哈哈哈哈哈");
		PrintWriter writer = response.getWriter();
		writer.write("来自servlet的回应");
		writer.write("第二句回复  这句不会打印到浏览器 因为上面执行完已经被写到浏览器了");
	}

	@Override
	public void destroy() {
		System.out.println("serevlet destroy方法正在被执行");
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("serevlet init方法正在被执行");
	}

}
