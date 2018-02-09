package cn.easyiot.simpledemo;

import java.io.Serializable;

/**
 * RestInfo.java
 * @author Jason.Zhang
 * @email mail2jason_zhang@163.com
 * @date 2018年2月5日 下午3:29:15
 * @version 1.0
 */
public class RestInfo implements Serializable{
	private static final long serialVersionUID = -4630664970744924964L;

	//接口uri
	private String uri;
	
	//接口名称
	private String name;
	
	//接口请求方式
	private String method;
	
	public RestInfo(String uri, String name, String method) {
		super();
		this.uri = uri;
		this.name = name;
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}
