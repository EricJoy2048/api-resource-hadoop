package org.sty.sadt.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <li>功能描述：资源加载类
 * @author 高俊
 *
 */
public class PropertiesTools {
	private Properties properties = null;
	
	public PropertiesTools(String filePath) {
		if(FileTool.fileIsExit(filePath)){
			properties = new Properties();
			try {
				properties.load(new FileInputStream(new File(filePath)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public PropertiesTools(Properties properties) {
		this.properties = properties;
	}
	
	/**
	 * 获取配置项
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		if(properties == null){
			return null;
		}
		return properties.getProperty(key);
	}
	

}
