package com.ajie.ex2;

/**
 * 处理静态请求
 * 
 * @author niezhenjie
 * 
 */
public class StaticResourceProcessor {

	public void process(Request request, Response response) {
		response.sendStaticResource();
	}

}
