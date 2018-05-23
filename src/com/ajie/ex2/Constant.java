package com.ajie.ex2;

import java.io.File;

/**
 * 常量聚集地
 * 
 * @author niezhenjie
 * 
 */
public class Constant {
	/** 项目的抽象路径 */
	public static final String ROOT_PATH = System.getProperty("user.dir") + File.separator
			+ "webapp";
	/** 如果uri等于该常量 则退出 */
	public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

}
