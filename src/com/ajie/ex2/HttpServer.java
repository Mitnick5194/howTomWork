package com.ajie.ex2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 入口
 * 
 * @author niezhenjie
 * 
 */
public class HttpServer {
	/** 是否退出 */
	private boolean shutdown = false;

	public void await() throws ClassNotFoundException {
		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket(9999, 1, InetAddress.getByName("192.168.0.115"));
			while (!shutdown) {
				socket = ss.accept();
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				Request request = new Request(in);
				request.parse();
				String uri = request.getUri();
				Response response = new Response(out);
				response.setRequest(request);
				if (null != uri && uri.startsWith("/servlet/")) {
					ServletProcessor servletProcessor = new ServletProcessor();
					servletProcessor.process(request, response);
				} else {
					StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
					staticResourceProcessor.process(request, response);
				}
				in.close();
				out.close();
				socket.close();
				shutdown = Constant.SHUTDOWN_COMMAND.equals(uri);
			}
		} catch (IOException e) {
			shutdown = true;
			e.printStackTrace();
		} finally {
			try {
				ss.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		try {
			httpServer.await();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
