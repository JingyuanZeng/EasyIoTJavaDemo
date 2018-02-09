package cn.easyiot.simpledemo;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.easyiot.simpledemo.exception.HttpStatusCodeErrorException;
import cn.easyiot.simpledemo.exception.NoSuchRestException;
import cn.easyiot.simpledemo.exception.RestRequstFailException;
import cn.easyiot.simpledemo.exception.RestUriIsNullException;

/**
 * 示例主类
 * EasyIOT.java
 * @author Jason.Zhang
 * @email mail2jason_zhang@163.com
 * @date 2018年1月30日 上午11:53:55
 * @version 1.0
 */
public class EasyIOT {
	//接口信息集合
	private Map<String,RestInfo> restInfo;
	//接口请求地址前缀
	private static final String PREFIX_URL="https://www.easy-iot.cn/idev/3rdcap/";
	//编码
	private static final String CHARSET="utf-8";
	//Content-Type
	private static final String CONTENT_TYPE="application/json";
	private String userName;
	private String password;
	//登录后的授权码
	private String accessToken;
	//httpclient
	private CloseableHttpClient httpClient;
	private String restName;
	/**
	 * @param userName 用户名
	 * @param password 密码
	 */
	public EasyIOT(String userName,String password){
		this.userName=userName;
		this.password=password;
		
		//创建httpclient
		this.httpClient = HttpClients.createDefault();
		
		init();
		
		//登录
		this.login();
	}
	
	//初始化所有接口信息
	private void init(){

		restInfo=new HashMap<>();
		restInfo.put("login", new RestInfo("server/login", "登录鉴权接口", "post"));
		restInfo.put("get-iotservers", new RestInfo("service-config/get-iotservers", "IoT连接平台查询接口", "get"));
		restInfo.put("get-iotservicemode", new RestInfo("service-config/get-iotservicemode", "IoT平台业务模式查询接口", "get"));
		restInfo.put("reg-device", new RestInfo("devices/reg-device", "注册设备接口", "post"));
		restInfo.put("reg-device-batch", new RestInfo("devices/reg-device-batch", "批量注册设备接口", "POST"));
		restInfo.put("update-device", new RestInfo("devices/update-device", "更新设备接口", "put"));
		restInfo.put("query-device-allinfo", new RestInfo("devices/query-device-allinfo", "设备信息查询接口", "get"));
		restInfo.put("del-device", new RestInfo("devices/del-device", "删除设备接口", "delete"));
		restInfo.put("list-devtypes", new RestInfo("devices/del-device", "查询所有可用设备类型接口", "get"));
		restInfo.put("list-devices", new RestInfo("devices/list-devices", "查询用户所有设备接口", "get"));
		restInfo.put("query-devType", new RestInfo("dev-manage/query-devType", "查询设备类型信息接口", "get"));
		restInfo.put("urt-command", new RestInfo("dev-control/urt-command", "设备控制接口", "post"));
		restInfo.put("subscribe-service-address", new RestInfo("subscribe-service-address", "注册订阅地址接口", "post"));
		restInfo.put("unsubscribe-service-address", new RestInfo("unsubscribe-service-address", "取消订阅地址接口", "delete"));
		restInfo.put("query-subscribe-service-address", new RestInfo("query-subscribe-service-address", "查询订阅地址接口", "get"));
	}
	
	/**
	 * 统一发送HTTP请求方法
	 * @param rest 接口uri
	 * @param paramMap 接口参数，每个接口参数详细请看平台文档中心
	 * @return
	 * @throws Exception
	 */
	public JSONObject common_requests(String rest, JSONObject paramMap) throws Exception{
		if (rest.isEmpty()) {
			throw new RestUriIsNullException("请求的Rest接口uri为空");
		}
		if (!restInfo.containsKey(rest)) {
			throw new NoSuchRestException("找不到指定的Rest接口, rest = "+rest);
		}
		RestInfo info = restInfo.get(rest);
		String method=info.getMethod();
		rest=info.getUri();
		this.restName=info.getName();
		if (paramMap==null) {
			paramMap=new JSONObject();
		}
		method=method.toUpperCase();
		HttpUriRequest httpRequest=null;
		if ("POST".equals(method) || "PUT".equals(method)) {
			HttpEntity httpEntity = new StringEntity(paramMap.toString(),CHARSET);
			httpRequest=RequestBuilder.create(method).setUri(PREFIX_URL.concat(rest))
					.setEntity(httpEntity).build();
		}else if ("GET".equals(method) || "DELETE".equals(method)) {
			Set<Entry<String, Object>> entrySet = paramMap.entrySet();
			BasicNameValuePair[] params = new BasicNameValuePair[entrySet.size()];
			int index=0;
			String value=null;
			for (Entry<String, Object> entry : entrySet) {
				
				if (entry.getValue() instanceof JSON) {
					value=((JSONObject)entry.getValue()).toString();
				}else{
					value=entry.getValue().toString();
				}
				params[index]=new BasicNameValuePair(entry.getKey(), value);
				index++;
			}
			
			httpRequest=RequestBuilder.create(method.toUpperCase()).setUri(PREFIX_URL.concat(rest))
										.setCharset(Charset.forName(CHARSET)).addParameters(params).build();
		}
		httpRequest.addHeader("Content-type", CONTENT_TYPE);
		if (this.accessToken!=null) {
			httpRequest.setHeader("serverID", this.userName);
			httpRequest.setHeader("accessToken", accessToken);
		}
		
		CloseableHttpResponse resp = httpClient.execute(httpRequest);
		HttpEntity entity = resp.getEntity();
		
		if (resp.getStatusLine().getStatusCode() != 200) {
			throw new HttpStatusCodeErrorException(this.restName+"请求失败，状态码期望值为200，实际为 "+resp.getStatusLine().getStatusCode());
		}
		
		String responseData = EntityUtils.toString(entity);
		JSONObject respJson = JSONObject.parseObject(responseData);
		
		String optResult = respJson.getString("optResult");
		if (!"0".equals(optResult)) {
			throw new RestRequstFailException(this.restName+"请求失败，返回码="+optResult+", "+Constants.resultCodeExplain.get(optResult));
		}
		
		if (respJson.getString("accessToken")!=null) {
			this.accessToken=respJson.getString("accessToken");
		}
		System.out.println(this.restName+"请求成功，服务器返回："+respJson.toString());
		return respJson;
	}
	/**
	 * 登录鉴权接口
	 * @return
	 */
	public JSONObject login(){
		try {
			JSONObject json = new JSONObject();
			json.put("serverId", this.userName);
			json.put("password", this.password);
			return this.common_requests("login", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, RestInfo> getRestInfo() {
		return restInfo;
	}

	public void setRestInfo(Map<String, RestInfo> restInfo) {
		this.restInfo = restInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
