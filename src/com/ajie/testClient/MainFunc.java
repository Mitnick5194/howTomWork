package com.ajie.testClient;

import java.io.File;

import com.ajie.common.XmlUtil;

/**
 * @author niezhenjie
 * 
 */
public class MainFunc {
	public final static int BUFFER_SIZE = 1024;
	/** 文件分隔符 */
	public static final String File_SEPARATOR = File.separator;

	public static void main(String[] args) {/*
											ServerSocket ss = null;
											try {
											ss = new ServerSocket(9999);
											while (true) {
											Socket socket = ss.accept();
											InputStream inputStream = socket.getInputStream();
											OutputStream outputStream = socket.getOutputStream();
											StringBuffer sb = new StringBuffer();
											byte[] buf = new byte[BUFFER_SIZE];
											int n = 0;
											do {
											n = inputStream.read(buf, 0, BUFFER_SIZE);
											sb.append(new String(buf));
											} while (n > BUFFER_SIZE);
											System.out.println(sb.toString());
											String errorMessage = "HTTP/1.1 404 File Not Found\r\n" 
											+ "\r\n<h1>File Not Found hahahahahahahahah</h1>";
											outputStream.write(errorMessage.getBytes());
											sb = null;
											socket.close();
											}
											} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											} finally {
											try {
											ss.close();
											} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											}
											}

											*/

	/*	String path = System.getProperty("user.dir") + File_SEPARATOR + "webapp" + File_SEPARATOR
				+ "WEB-INF" + File_SEPARATOR + "servlet.xml";*/
	/*	System.out.println(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
			int n = 0;
			byte[] buf = new byte[1024];
			StringBuffer sb = new StringBuffer();
			do {
				n = fis.read(buf);
				sb.append(new String(buf));
			} while (n > 1024);
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/

		String servletPath = XmlUtil.getAttribute("primitiveServlet");
		String path = XmlUtil.getClassPackagePath(servletPath);
		System.out.println(path);
		/*System.out.println(servletPath);
		String newStr = servletPath.replace(".", "/");
		System.out.println(newStr);*/
		
	}
}
