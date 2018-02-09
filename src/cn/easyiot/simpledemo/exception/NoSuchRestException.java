package cn.easyiot.simpledemo.exception;
/**
 * 找不到指定的Rest接口
 * NoSuchRestException.java
 * @author Jason.Zhang
 * @email mail2jason_zhang@163.com
 * @date 2018年2月6日 下午3:30:41
 * @version 1.0
 */
public class NoSuchRestException extends RuntimeException {
	private static final long serialVersionUID = -1517085411108834775L;
	public NoSuchRestException(String message){
		super(message);
	}
}
