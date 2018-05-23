package com.ajie.ex2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应
 * 
 * @author niezhenjie
 * 
 */
public class Response implements HttpServletResponse {
	private OutputStream out;
	private Request request;

	public Response(OutputStream out) {
		this.out = out;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * 发送静态资源
	 */
	public void sendStaticResource() {
		String path = Constant.ROOT_PATH + request.getUri();
		File file = new File(path);
		FileInputStream in = null;
		StringBuffer sb = new StringBuffer();
		try {
			in = new FileInputStream(file);
			byte[] buf = new byte[Request.BUFFER_SIZE];

			sb.append("HTTP/1.1 200\r\n");
			sb.append("Content-Type: text/html\r\n");
			int n = 0;
			do {
				try {
					n = in.read(buf, 0, Request.BUFFER_SIZE);
					sb.append(new String(buf));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (n > -1);
			sb.append("\r\n");
			out.write(sb.toString().getBytes());
			out.flush();
		} catch (FileNotFoundException e) {
			String errorMes = "HTTP/1.1 404 file not found\r\n <h1>文件不存在</h1>";
			try {
				out.write(errorMes.getBytes());
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sb = null;
			try {
				if (null != in)
					in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*try {
			File file = new File(Constant.ROOT_PATH + request.getUri());
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
				out.write(sb.toString().getBytes());
				out.flush();
			} else {
				// 文件不存在 404吧
				String errorMes = "HTTP/1.1 404 file not found\r\n <h1>文件不存在</h1>";
				out.write(errorMes.getBytes());
				out.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

	}

	@Override
	public void flushBuffer() throws IOException {

	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		PrintWriter pw = new PrintWriter(out);
		return pw;
	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {

	}

	@Override
	public void resetBuffer() {

	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCookie(Cookie arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDateHeader(String arg0, long arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean containsHeader(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String encodeRedirectURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendError(int arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendError(int arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendRedirect(String arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDateHeader(String arg0, long arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}
