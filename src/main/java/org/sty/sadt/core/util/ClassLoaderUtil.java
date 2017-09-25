package org.sty.sadt.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 *<li>功能描述：用来加载类，classpath下的资源文件，属性文件等。
 *getExtendResource(StringrelativePath)方法，可以使用../符号来加载classpath外部的资源。
 *@author 高俊
 */
public class ClassLoaderUtil {
	public static final Logger log = LoggerFactory.getLogger(ClassLoaderUtil.class);
    /**
     *Thread.currentThread().getContextClassLoader().getResource("")
     */
    
    /**
     *加载Java类。 使用全限定类名
     *@paramclassName
     *@return
     */
    public static Class loadClass(String className) {
        try {
          return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
          throw new RuntimeException("class not found '"+className+"'", e);
        }
     }
     /**
       *得到类加载器
       *@return
       */
     public static ClassLoader getClassLoader() {
     
        return ClassLoaderUtil.class.getClassLoader();
     }
     
     /**
      *提供相对于classpath的资源路径，返回文件的输入流
      *@paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
      *@return 文件输入流
      *@throwsIOException
      *@throwsMalformedURLException
      */
     public static InputStream getStream(String relativePath) throws MalformedURLException, IOException {
         if(!relativePath.contains("../")){
             return getClassLoader().getResourceAsStream(relativePath);
             
         }else{
             return getStreamByExtendResource(relativePath);
         }
        
     }
     /**
       *根据url返回文件的输入流
       *@param url
       *@return
       *@throws IOException
       */
     public static InputStream getStream(URL url) throws IOException{
         if(url!=null){
             
                return url.openStream();
            
             
         }else{
             return null;
         }
     }
     /**
       *
       *@paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
       *@return
       *@throwsMalformedURLException
       *@throwsIOException
       */
     private static InputStream getStreamByExtendResource(String relativePath) throws MalformedURLException, IOException{
        return ClassLoaderUtil.getStream(ClassLoaderUtil.getExtendResource(relativePath));
         
         
     }
     
      /**
       *提供相对于classpath的资源路径，返回属性对象，它是一个散列表
       *@param resource 相对于classpath的路径，如果要查找classpath外面的文件，请使用../来查找
       *@return
       */
     public static Properties getProperties(String resource) {
        Properties properties = new Properties();
        try {
          properties.load(getStream(resource));
        } catch (IOException e) {
          throw new RuntimeException("couldn't load properties file '"+resource+"'", e);
        }
        return properties;
     }
     /**
       *得到本Class所在的ClassLoader的Classpat的绝对路径。
       *URL形式的
       *@return
       */
     public static String getAbsolutePathOfClassLoaderClassPath(){
         
         URL abPath = ClassLoaderUtil.getClassLoader().getResource("");
         if(abPath == null){//说明是在jar包中
        	 String jarFilePath = ClassLoaderUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        	 // URL Decoding
        	 try {
				jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        	 return "file:"+jarFilePath;

         }else{
        	 ClassLoaderUtil.log.info(ClassLoaderUtil.getClassLoader().getResource("").toString());
        	 return ClassLoaderUtil.getClassLoader().getResource("").toString();
         }
         
     }
     /**
       *
       *@param relativePath 必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
       *@return 资源的绝对URL
     *@throws MalformedURLException
       */
     public static URL getExtendResource(String relativePath) throws MalformedURLException{
     
         ClassLoaderUtil.log.info("传入的相对路径："+relativePath) ;
         //ClassLoaderUtil.log.info(Integer.valueOf(relativePath.indexOf("../"))) ;
         if(!relativePath.contains("../")){
             return ClassLoaderUtil.getResource(relativePath);
             
         }
         String classPathAbsolutePath=ClassLoaderUtil.getAbsolutePathOfClassLoaderClassPath();
         if(relativePath.substring(0, 1).equals("/")){
             relativePath=relativePath.substring(1);
         }
        
         String wildcardString=relativePath.substring(0,relativePath.lastIndexOf("../")+3);
         relativePath=relativePath.substring(relativePath.lastIndexOf("../")+3);
         int containSum=ClassLoaderUtil.containSum(wildcardString, "../");
         classPathAbsolutePath= ClassLoaderUtil.cutLastString(classPathAbsolutePath, "/", containSum);
         String resourceAbsolutePath=classPathAbsolutePath+relativePath;
         ClassLoaderUtil.log.info("绝对路径："+resourceAbsolutePath) ;
         URL resourceAbsoluteURL=new URL(resourceAbsolutePath);
         return resourceAbsoluteURL;
     }
     /**
      *处理字符串中有../的情况，判断路径中一共有几个../
       *@param source 路径
       *@param dest 需要判断出现次数的字符串
       *@return dest字符串出来的次数
       */
     private static int containSum(String source,String dest){
         int containSum=0;
         int destLength=dest.length();
         while(source.contains(dest)){
             containSum=containSum+1;
             source=source.substring(destLength);
             
         }
         
         
         return containSum;
     }
     /**
       *
       *@param source 路径
       *@param dest "../"
       *@param num source路径中出来"../"的次数
       *@return 去掉../之后的路径
       */
     private static String cutLastString(String source,String dest,int num){
         // String cutSource=null;
         for(int i=0;i<num;i++){
             source=source.substring(0, source.lastIndexOf(dest, source.length()-2)+1);
         }
         return source;
     }
     /**
       *
       *@param resource
       *@return
       */
      public static URL getResource(String resource){
    	  ClassLoaderUtil.log.info("传入的相对于classpath的路径："+resource) ;
          return ClassLoaderUtil.getClassLoader().getResource(resource);
      }
     
 
     
 
    /**
     *@param args
     *@throwsMalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        
        //ClassLoaderUtil.getExtendResource("../spring/dao.xml");
        //ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
        ClassLoaderUtil.getExtendResource("log4j.properties");
        
        System.out.println(ClassLoaderUtil.getClassLoader().getResource("log4j.properties").toString());
 
    }
 
}
