package com.ajie.ex2;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.ajie.common.XmlUtil;

/**
 * 处理Servlet的请求
 * 
 * @author niezhenjie
 * 
 */
public class ServletProcessor {

	/**
	 * 这个方法的主要逻辑是通过uri路径 使用类加载机制找到对应的二进制文件 将它load进来
	 * 
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException
	 */
	public void process(Request request, Response response) throws ClassNotFoundException {
		URL[] urls = new URL[1];
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		try {
			// 获取servlete的相对路径
			String servletPath = XmlUtil.getAttribute(servletName);
			// 第三个参数是文件的位置 注意 要以/结束
			urls[0] = new URL("file", null, Constant.CLASS_PATH);
			loader = new URLClassLoader(urls);
			@SuppressWarnings("unchecked")
			Class<Servlet> clazz = (Class<Servlet>) loader.loadClass(servletPath);
			Servlet instace = clazz.newInstance();
			instace.service(request, response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != loader)
				try {
					loader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
