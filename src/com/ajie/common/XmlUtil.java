package com.ajie.common;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * dom4j工具的封装
 * 
 * @author niezhenjie
 * 
 */
public class XmlUtil {
	public static final String FILE_SEPARATOR = File.separator;
	// 项目的磁盘抽象路径
	public static final String ROOT_PATH = System.getProperty("user.dir");

	public static Document getDocument() {
		SAXReader reader = new SAXReader();
		String xmlpath = ROOT_PATH + FILE_SEPARATOR + "webapp" + FILE_SEPARATOR + "WEB-INF"
				+ FILE_SEPARATOR + "servlet.xml";
		try {
			Document doc = reader.read(new File(xmlpath));
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 指定xml节点名字 返回包的路径 需要将xxx.xxx.xxx转换成xxx/xxx/xxx
	 * 
	 * @param attributeName
	 * @return
	 */
	public static String getAttribute(String attributeName) {
		Document doc = getDocument();
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements("servlet");
		for (Element e : elements) {
			String name = e.attributeValue("name");
			if (attributeName.equals(name)) {
				String value = e.attributeValue("class");
				if (null != value && value.indexOf(".") > -1) {
					//规则是以类名小写字母开头做名字
					return value;
				}
			}
		}
		return null;
	}

	/**
	 * 提取xxx/xxxx/xxxx/xxx的上层目录（其实就是获取Java文件的包路径）
	 * 
	 * @param classpath
	 * @return
	 */
	public static String getClassPackagePath(String classpath) {
		int idx = classpath.lastIndexOf("/");
		if (idx == -1) // 没有包名 classpath只是单个的文件名
			return "";
		return classpath.substring(0, idx);
	}

}
