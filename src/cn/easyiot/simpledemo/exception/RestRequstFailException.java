package cn.easyiot.simpledemo.exception;
/**
 * 接口请求失败
 * RestRequstFailException.java
 * @author Jason.Zhang
 * @email mail2jason_zhang@163.com
 * @date 2018年2月6日 下午3:30:41
 * @version 1.0
 */
public class RestRequstFailException extends RuntimeException {
	private static final long serialVersionUID = -1517085411108834775L;
	public RestRequstFailException(String message){
		super(message);
	}
}
