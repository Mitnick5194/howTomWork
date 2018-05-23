package com.ajie.ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private Request request;
	OutputStream output;

	public Response(OutputStream ou) {
		output = ou;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Request getRequest() {
		return request;
	}

	/**
	 * 发送静态资源到客户端
	 */
	public void sendStaticRes() {
		try {
			File file = new File(HttpServer.ROOT_PATH + request.getUri());
			FileInputStream fis = null;
			if (file.exists()) {
				fis = new FileInputStream(file);
				StringBuffer sb = new StringBuffer();
				sb.append("HTTP/1.1 200\r\n");// 先吧头部放进去
				sb.append("Content-Type: text/x-html\r\n");
				byte[] buf = new byte[2048];
				int n = 0;
				do {
					n = fis.read(buf);
					sb.append(new String(buf));
				} while (n != -1);
				output.write(sb.toString().getBytes());
				output.flush();
			} else {
				// 文件不存在 404吧
				String errorMes = "HTTP/1.1 404 file not found\r\n <h1>文件不存在</h1>";
				output.write(errorMes.getBytes());
				output.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
