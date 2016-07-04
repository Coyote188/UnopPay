package com.unionpay.acp.demo01;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.unionpay.acp.sdk.SDKConfig;

/**
 * 名称：第1部分 互联网支付跳转支付——网关支付产品<br>
 * 功能： 6.4　退货类交易<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 作者： 中国银联ACP团队<br>
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class Form01_6_4 extends DemoBase {

	/**
	 * 6.4　退货类交易 表单填写
	 * 
	 * @return
	 */
	public static Map<String, Object> setFormDate() {

		Map<String, Object> contentData = new HashMap<String, Object>();
		String merId = "802290049000180";
		String txnTime = "20140820145625";// --订单发送时间
		String orderId = "201408201508395217";// --商户订单号
		// 固定填写
		contentData.put("version", version);// M
		// 默认取值：UTF-8
		contentData.put("encoding", encoding);// M
		// //通过MPI插件获取
		// contentData.put("certId", certId);//M
		// 填写对报文摘要的签名
		// contentData.put("signature", signature);//M
		// 01RSA02 MD5 (暂不支持)
		contentData.put("signMethod", 01);// M
		// 交易类型 00
		contentData.put("txnType", 00);// M
		// 默认00
		contentData.put("txnSubType", 00);// M
		// 默认:000000
		contentData.put("bizType", 000000);// M
		// 0：普通商户直连接入2：平台类商户接入
		contentData.put("accessType", 0);// M
		// 　
		contentData.put("merId", merId);// M
		// 被查询交易的交易时间
		contentData.put("txnTime", txnTime);// M
		// 被查询交易的订单号
		contentData.put("orderId", orderId);// M
		// 待查询交易的流水号
		contentData.put("queryId", "");// C
		// 格式如下：{子域名1=值&子域名2=值&子域名3=值} 子域： origTxnType N2原交易类型余额查询时必送
		contentData.put("reserved", "");// O
		return contentData;
	}

	public static void main(String[] args) throws InterruptedException {

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
		String respCode = resmap.get("respCode");
		if (StringUtils.isNotBlank(respCode)) {

			if (!"00".equals(respCode)) {// 如果服务器返回不成功，结束流程，返回错误信息
				System.out.println("处理不成功");
			} else {

				Map<String, Object> contentData = new HashMap<String, Object>();
				// 固定填写
				contentData.put("version", version);// M
				// 默认取值：UTF-8
				contentData.put("encoding", encoding);// M
				// //通过MPI插件获取
				// contentData.put("certId", certId);//M
				// 填写对报文摘要的签名
				// contentData.put("signature", signature);//M
				// 01RSA02 MD5 (暂不支持)
				contentData.put("signMethod", "01");// M
				// 交易类型 00
				contentData.put("txnType", "00");// M
				// 默认00
				contentData.put("txnSubType", "00");// M
				// 默认:000000
				contentData.put("bizType", "000000");// M
				// 0：普通商户直连接入2：平台类商户接入
				contentData.put("accessType", "0");// M
				// 　
				contentData.put("merId", resmap.get("merId"));// M
				// 被查询交易的交易时间
				contentData.put("txnTime", resmap.get("txnTime"));// M
				// 被查询交易的订单号
				contentData.put("orderId", resmap.get("orderId"));// M

				// 待查询交易的流水号
				// contentData.put("queryId", resmap.get("origQryId"));//C
				// 格式如下：{子域名1=值&子域名2=值&子域名3=值} 子域： origTxnType N2原交易类型余额查询时必送
				// contentData.put("reserved", "");//O
				Map<String, String> queryResmap = submitDate(contentData,requestBackUrl);

				String origRespcode = queryResmap.get("origRespCode");
				System.out.println("第==1==次查询返回 ： OrigRespcode:" + origRespcode
						+ ",RespCode:" + queryResmap.get("RespCode"));
				// 如果响应码05,交易已受理，请稍后查询交易结果
				// 如果响应码为05（发送报文失败），则重新发送 5次发送没有结果,返回失败,不去再查
				if (StringUtils.isNotBlank(origRespcode)
						&& origRespcode.substring(0, 2).equals("05")) {
					int queryTimes = 5;
					for (int i = 1; i < queryTimes; i++) {
						Integer sleepTime = (int) Math.pow(2, i);

						// Thread.sleep(sleepTime * 1000*2);
						Thread.sleep(sleepTime * 1000);
						// 发起交易状态查询交易
						System.out.println("发起第===" + (i + 1) + "===交易状查询");

						queryResmap = submitDate(contentData,requestBackUrl);

						origRespcode = queryResmap.get("origRespCode");
						System.out.println("origRespcode:" + origRespcode);
						if (!("05".equals(origRespcode))) {
							System.out.println("查询成功,退出查询");
							break;
						}

					}
				}

				System.out.println("退货结果为");
				System.out.println(queryResmap.toString());
			}

		} else {
			System.out.println("服务器返回错误");
		}

	}

}
