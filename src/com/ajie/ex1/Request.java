package com.ajie.ex1;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	private InputStream input;
	private String uri;

	public Request(InputStream in) {
		input = in;
	}

	/**
	 * 处理请求信息
	 */
	public void parse() {
		try {
			byte[] buf = new byte[2048];
			// 这里只能一次读入 循环读的话 因为浏览器那么的socket并没有调用socket.shutdown 所以永远都不会输出-1
			// 就会在read的时候阻塞
			input.read(buf);
			String requestString = new String(buf);
			System.out.println(requestString);
			uri = getUri(requestString);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据请求的信息字符串提取uri部分 分割的依据是http协议头的规则
	 * 
	 * http响应头部规则如下：<br>
	 * GET / HTTP/1.1 Host: 127.0.0.1:9999 Connection: keep-alive Cache-Control:
	 * max-age=0
	 * 
	 * @param requestString
	 * @return
	 */
	public String getUri(String requestString) {
		int index1, index2;
		index1 = requestString.indexOf(" ");// 第一个空格分割的位置就是GET xxx（路径）
		if (index1 != -1) {
			// 从xxx路径后面开始分割直到下一个空格位置
			index2 = requestString.indexOf(" ", index1 + 1);
			if (index1 != -1)
				uri = requestString.substring(index1 + 1, index2);
		}
		return uri;
	}

	public String getUri() {
		return uri;
	}

}
