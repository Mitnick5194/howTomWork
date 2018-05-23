package com.ajie.ex1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 注意 本服务器不能使用chrom调试 因为chrome在地址栏里输入请求地址 会请求两次或三次的请求 我也不知道为什么
 * 
 * @author ajie
 *
 */
public class HttpServer {
	/** System.getProperty("user.dir")获取当前项目的根目录在磁盘的位置 */
	public static final String ROOT_PATH = System.getProperty("user.dir")
			+ File.separator + "webapp";
	/** uri路径如果是/SHUTDOWN则关闭服务器 */
	private static final String SHUTDOWN = "/SHUTDOWN";
	private boolean isShutdown = false;

	/**
	 * 等待客户端连接进来的方法
	 */
	public void await() {
		// 在本机的9999端口监听
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9999);
			while (!isShutdown) {
				// 监听客户端连接
				Socket socket = ss.accept();
				// 获取socket的输入流
				InputStream is = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				// 根据输入流构造请求
				Request request = new Request(is);
				request.parse();
				// 构造响应
				Response response = new Response(out);
				response.setRequest(request);
				response.sendStaticRes();
				isShutdown = SHUTDOWN.equals(request.getUri());
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		HttpServer httpService = new HttpServer();
		httpService.await();
	}

}
