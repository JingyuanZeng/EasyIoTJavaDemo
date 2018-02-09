package cn.easyiot.simpledemo.exception;
/**
 * http状态码错误，不是200
 * HttpStatusCodeErrorException.java
 * @author Jason.Zhang
 * @email mail2jason_zhang@163.com
 * @date 2018年2月6日 下午3:30:41
 * @version 1.0
 */
public class HttpStatusCodeErrorException extends RuntimeException {
	private static final long serialVersionUID = -1517085411108834775L;
	public HttpStatusCodeErrorException(String message){
		super(message);
	}
}
