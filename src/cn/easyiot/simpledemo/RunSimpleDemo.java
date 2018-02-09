package cn.easyiot.simpledemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * RunTest.java
 * @author Jason.Zhang
 * @email mail2jason_zhang@163.com
 * @date 2018年2月8日 上午9:34:22
 * @version 1.0
 */
public class RunSimpleDemo {

	public static void main(String[] args) {
		try {
			//EasyIOT实例化时登录
			EasyIOT iot = new EasyIOT("请填写用户名", "请填写密码");
			
//			临时变量
			JSONObject paramMap = new JSONObject();
			JSONArray list = new JSONArray();
			
			
//			IoT连接平台查询接口
			iot.common_requests("get-iotservers", paramMap);
			
			
//			IoT平台业务模式查询接口
//			paramMap.put("iotserverId", "请填写IOT连接平台");
//			iot.common_requests("get-iotservicemode", paramMap);
			
			
//			注册设备接口
//			paramMap=new JSONObject();
//			paramMap.put("devSerial", "请填写设备序列号，推荐使用设备IMEI");
//			paramMap.put("name", "请填写设备名称");
//			paramMap.put("deviceType", "请填写设备类型(产品型号)");
//			paramMap.put("connectPointId", "请填写IOT连接平台");
//			paramMap.put("serviceMode", "请填写IoT平台业务模式");
			//其他可选参数这里不演示
//			iot.common_requests("reg-device", paramMap);
			
			
//			批量注册设备接口
//			paramMap=new JSONObject();
//			paramMap.put("devices", list);
//			for (int i = 1; i < 4; i++) {
//				JSONObject device = new JSONObject();
//				device.put("devSerial", "请填写设备序列号");
//				device.put("name", "请填写设备名称");
//				device.put("deviceType", "请填写设备类型(产品型号)");
//				device.put("connectPointId", "请填写IOT连接平台");
//				device.put("serviceMode", "请填写IoT平台业务模式");
//				//其他可选参数这里不演示
//				list.add(device);
//			}
//			iot.common_requests("reg-device-batch", paramMap);
			
			
//			更新设备接口
//			paramMap=new JSONObject();
//			paramMap.put("devSerial", "请填写设备序列号");
//			paramMap.put("name", "新的设备名称");
//			paramMap.put("longitude", "122.23452");		//经度
//			paramMap.put("latitude", "23.23452");		//纬度
//			iot.common_requests("update-device", paramMap);
			
			
//			设备信息查询接口
//			paramMap=new JSONObject();
//			paramMap.put("devSerial", "请填写设备序列号");
//			iot.common_requests("query-device-allinfo", paramMap);
			
			
//			删除设备接口
//			paramMap=new JSONObject();
//			paramMap.put("devSerial", "Test_请填写密码");
//			iot.common_requests("del-device", paramMap);
			
			
//			查询所有可用设备类型(产品型号)接口
//			paramMap=new JSONObject();
//			paramMap.put("serverID", iot.getUserName());
//			iot.common_requests("list-devtypes", paramMap);
			
			
//			查询用户所有设备接口
//			paramMap=new JSONObject();
//			paramMap.put("serverID", iot.getUserName());
//			iot.common_requests("list-devices", paramMap);
			
			
//			查询设备类型(产品型号)信息接口
//			paramMap=new JSONObject();
//			paramMap.put("devType", "请填写设备类型(产品型号)");
//			iot.common_requests("query-devType", paramMap);
			
			
//			设备控制接口
//			paramMap=new JSONObject();
//			paramMap.put("devSerial", "设备序列号");
//			paramMap.put("method", "指令ID");		//具体请看设备所属设备类型(产品型号)的设备指令
//			JSONObject params=new JSONObject();
//			paramMap.put("params", params);		//具体请看设备所属设备类型(产品型号)的设备指令消息参数
//			params.put("消息参数", '消息参数值');
//			...
//			iot.common_requests("urt-command", paramMap);
			
			
//			注册订阅地址接口
//			paramMap=new JSONObject();
//			paramMap.put("callbackUrl", "订阅地址，如：http://www.baidu.com");
//			iot.common_requests("subscribe-service-address", paramMap);

			
//			取消订阅地址接口
//			paramMap=new JSONObject();
//			iot.common_requests("unsubscribe-service-address", paramMap);
			
			
//			查询订阅地址接口
//			paramMap=new JSONObject();
//			iot.common_requests("query-subscribe-service-address", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
