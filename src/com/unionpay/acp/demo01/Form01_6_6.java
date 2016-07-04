package com.unionpay.acp.demo01;

import java.util.HashMap;
import java.util.Map;

import com.unionpay.acp.sdk.SDKConfig;

/**
 * 名称： 第1部分 互联网支付跳转支付——网关支付产品<br>
 * 功能： 6.6　文件传输类交易<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 作者： 中国银联ACP团队<br>
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class Form01_6_6 extends DemoBase {

	/**
	 * 6.6　文件传输类交易 表单填写
	 * 
	 * @return
	 */
	public static Map<String, Object> setFormDate() {

		Map<String, Object> contentData = new HashMap<String, Object>();

		String merId = "012345678901234";
		//String merId = "012345678901235";
		String txnTime = "20140616112211";// --订单发送时间

		String settleDate = "0616";

		String fileType = "89";

		// 固定填写
		contentData.put("version", version);// M

		// 默认取值：UTF-8
		contentData.put("encoding", encoding);// M

		// //通过SDK插件获取
		// contentData.put("certId", certId);//M
		//
		// //填写对报文摘要的签名
		// contentData.put("signature", signature);//M

		// 01RSA02 MD5 (暂不支持)
		contentData.put("signMethod", "01");// M

		// 取值:76
		contentData.put("txnType", "76");// M

		// 01：对账文件下载
		contentData.put("txnSubType", "01");// M

		// 默认:000000
		contentData.put("bizType", "000000");// M

		// 0：普通商户直连接入1. 收单机构接入
		contentData.put("accessType", "0");// M

		//商户类型为收单接入时必须上送
	// contentData.put("acqInsCode", "12345670");//C

		// 　商户类型为商户接入时必须上送
		contentData.put("merId", merId);// C

		// 　
		contentData.put("settleDate", settleDate);// M

		// 　
		contentData.put("txnTime", txnTime);// M

		// 依据实际业务情况定义参考附录：商户索取的文件类型约定
		contentData.put("fileType", fileType);// M

		// //商户自定义保留域，交易应答时会原样返回
		// contentData.put("reqReserved", reqReserved);//O

		return contentData;
	}

	public static void main(String[] args) {

	/**
		 * 参数初始化
		 * 在java main 方式运行时必须每次都执行加载
		 * 如果是在web应用开发里,这个方写在可使用监听的方式写入缓存,无须在这出现
		 */
		SDKConfig.getConfig().loadPropertiesFromSrc();

		/**
		 * 交易请求url 从配置文件读取
		 */
		  String requestBackUrl = SDKConfig.getConfig()
				.getBackRequestUrl();

		Map<String, String> resmap = submitDate(setFormDate(),requestBackUrl);

		System.out.println(resmap.toString());

		// 解析返回报文的文件流
		deCodeFileContent(resmap);
	}

}
