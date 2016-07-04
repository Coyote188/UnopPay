package com.unionpay.acp.demo01;

import java.util.HashMap;
import java.util.Map;


import com.unionpay.acp.sdk.SDKConfig;

/**
 * 名称： 第1部分 互联网支付跳转支付——网关支付产品<br>
 * 功能： 6.7.1　预授权<br>
 * 后台类交易<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 作者： 中国银联ACP团队<br>
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class Form01_6_7_1_b extends DemoBase {

	/**
	 * 6.7.1　预授权 表单填写
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
		//
		// //通过MPI插件获取
		// contentData.put("certId", certId);//M
		//
		// //填写对报文摘要的签名
		// contentData.put("signature", signature);//M

		// 01RSA02 MD5 (暂不支持)
		contentData.put("signMethod", "01");// M

		// 取值：02
		contentData.put("txnType", "02");// M

		// 01：预授权03：担保消费通过地址区分前台与后台交易
		contentData.put("txnSubType", "01");// M

		contentData.put("bizType", "00");// M

		contentData.put("channelType", "07");// M

		// 后台返回商户结果时使用，如上送，则发送商户后台交易结果通知
		contentData.put("backUrl", backUrl);// M

		// 0：普通商户直连接入2：平台类商户接入
		contentData.put("accessType", "0");// M

		// 　
		contentData.put("merId", merId);// M

		// //商户类型为平台类商户接入时必须上送
		// contentData.put("subMerId", subMerId);//C
		//
		// //商户类型为平台类商户接入时必须上送
		// contentData.put("subMerName", subMerName);//C
		//
		// //商户类型为平台类商户接入时必须上送
		// contentData.put("subMerAbbr", subMerAbbr);//C

		// 预授权的订单号，由商户生成
		contentData.put("orderId", orderId);// M

		// 　
		contentData.put("txnTime", txnTime);// M

		// 后台类交易且卡号上送；跨行收单且收单机构收集银行卡信息时上送01：银行卡02：存折03：C卡默认取值：01取值“03”表示以IC终端发起的IC卡交易，IC作为普通银行卡进行支付时，此域填写为“01”initArgs----------------end
		//
		// contentData.put("accType", accType);//C
		//
		// //1、 后台类消费交易时上送全卡号或卡号后4位 2、 跨行收单且收单机构收集银行卡信息时上送、
		// 3、前台类交易可通过配置后返回，卡号可选上送
		// contentData.put("accNo", accNo);//C

		contentData.put("txnAmt", "1");// M

		// 默认为156交易 参考公参
		contentData.put("currencyCode", "156");// M
		//
		// //1、后台类消费交易时上送2、跨行收单且收单机构收集银行卡信息时上送3、认证支付2.0，后台交易时可选Key=value格式（具体填写参考数据元说明）
		contentData.put("customerInfo", getCustomer(encoding));//C
		//
		// //　
		// contentData.put("termId", termId);//O
		//
		// //商户自定义保留域，交易应答时会原样返回
		// contentData.put("reqReserved", reqReserved);//O
		//
		// //格式如下：{子域名1=值&子域名2=值&子域名3=值} 移动支付参考消费
		// contentData.put("reserved", reserved);//O
		//
		// //格式如下：{子域名1=值&子域名2=值&子域名3=值}
		// contentData.put("riskRateInfo", riskRateInfo);//O
		//
		// //当使用银联公钥加密密码等信息时，需上送加密证书的CertID；说明一下？目前商户、机构、页面统一套
		// contentData.put("encryptCertId", encryptCertId);//C
		//
		// //分期付款交易，商户端选择分期信息时，需上送 组合域，填法见数据元说明
		// contentData.put("instalTransInfo", instalTransInfo);//C
		//
		// //C当帐号类型为02-存折时需填写在前台类交易时填写默认银行代码，支持直接跳转到网银商户发卡银行控制系统应答返回
		// contentData.put("issInsCode", issInsCode);//O
		//
		// //移动支付业务需要上送
		// contentData.put("userMac", userMac);//O
		//
		// //有卡交易必填有卡交易信息域
		// contentData.put("cardTransData", cardTransData);//C
		//
		// //渠道类型为语音支付时使用用法见VPC交易信息组合域子域用法
		// contentData.put("vpcTransData", vpcTransData);//C
		//
		// //移动支付上送
		// contentData.put("orderDesc", orderDesc);//C
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
	}

}
