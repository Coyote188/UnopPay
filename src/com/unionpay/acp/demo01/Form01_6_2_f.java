package com.unionpay.acp.demo01;

import java.util.HashMap;
import java.util.Map;

import com.unionpay.acp.sdk.SDKConfig;

/**
 * 名称： 第1部分 互联网支付跳转支付 ——网关支付产品<br>
 * 功能： 6.2　消费类交易<br>
 * 前台类交易<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 作者： 中国银联ACP团队<br>
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class Form01_6_2_f extends DemoBase {

	public static void main(String[] args) {

		/**
		 * 初始化证书
		 */
	/**
		 * 参数初始化
		 * 在java main 方式运行时必须每次都执行加载
		 * 如果是在web应用开发里,这个方写在可使用监听的方式写入缓存,无须在这出现
		 */
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件

		/**
		 * 交易请求url 从配置文件读取
		 */
		  String requestFrontUrl = SDKConfig.getConfig()
				.getFrontRequestUrl();
		


		/**
		 * 组装请求报文
		 */
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", "5.0.0");
		// 字符集编码 默认"UTF-8"
		String encoding = "UTF-8";
		data.put("encoding", encoding);
		// 证书ID 调用 SDK 可自动获取并赋值
		// data.put("certId", "31692114440333550101559775639582427889");
		// 签名 调用 SDK 可自动运算签名并赋值
		// data.put("signature", "");
		// 签名方法 01 RSA
		data.put("signMethod", "01");
		// 交易类型 01-消费
		data.put("txnType", "01");
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", "01");
		// 业务类型 000201 B2C网关支付
		data.put("bizType", "000201");
		// 渠道类型 07-互联网渠道
		data.put("channelType", "07");
		// 商户/收单前台接收地址 选送
		//后台服务对应的写法参照 FrontRcvResponse.java
		data.put("frontUrl", "http://localhost:8080/ACPTest/acp_front_url.do");
		// 商户/收单后台接收地址 必送
		//后台服务对应的写法参照 BackRcvResponse.java
		data.put("backUrl", "http://localhost:8080/ACPTest/acp_back_url.do");
		// 接入类型:商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", "0");
		// 商户号码
		data.put("merId", "105550149170027");
		// 订单号 商户根据自己规则定义生成，每订单日期内不重复
		data.put("orderId", "123231412332132");
		// 订单发送时间 格式： YYYYMMDDhhmmss 商户发送交易时间，根据自己系统或平台生成
		data.put("txnTime", "20140901125959");

		/**
		 * 以下报文域 只有 accessType=1 收单接入时需要上送
		 */
		// 收单机构代码 ，直连商户不需要填，收单机构需上送
		// data.put("acqInsCode", "");
		// 商户类别 mcc码
		// data.put("merCatCode", "");
		// 商户名称 直连商户不需要填，收单机构需上送
		// data.put("merName", "");
		// 商户简称 直连商户不需要填，收单机构需上送
		// data.put("merAbbr", "");

		/**
		 * 以下报文域 只有 accessType=2 平台商户接入时需要上送
		 */
		// 二级商户代码 直连商户不需要填，平台商户需上送
		// data.put("subMerId", "");
		// 二级商户名称 直连商户不需要填，平台商户需上送
		// data.put("subMerName", "");
		// 二级商户简称 直连商户不需要填，平台商户需上送
		// data.put("subMerAbbr", "");

		// 账号类型 01
		// data.put("accType", "");
		// 账号 不加密 622188123456789
		// data.put("accNo", "");
		// 账号加密
		// data.put("accNo",MpiUtil.encrptPan("622188123456789", encoding));

		// 交易金额 分
		data.put("txnAmt", "1");
		// 交易币种
		data.put("currencyCode", "156");

		// 银行卡验证信息及身份信息 根据需求选送 参考接口规范
		// data.put("customerInfo", getCustomer(encoding));

		// 订单超时时间 orderTimeoutInterval 根据需求选送 参考接口规范
		// data.put("orderTimeout", "12");

		// 订单支付超时时间 payTimeoutTime 根据需求选送 参考接口规范
		// data.put("payTimeout", "20120815135900");

		// // 终端号 根据需求选送 参考接口规范
		// data.put("termId", "");

		// 商户保留域 根据需求选送 参考接口规范
		// data.put("reqReserved", "");

		// 保留域 根据需求选送 参考接口规范
		// data.put("reserved", "");

		// 风险信息域 根据需求选送 参考接口规范
		// data.put("riskRateInfo", "");

		// 加密证书ID 根据需求选送 参考接口规范 有报文域加密时，该字段必须上送
		// data.put("encryptCertId", "");

		// 失败交易前台跳转地址 根据需求选送 参考接口规范
		// data.put("frontFailUrl", "");

		// 分期付款信息域 根据需求选送 参考接口规范 txnSubType =03 分期付款交易必须上送
		// data.put("instalTransInfo", "");

		// 默认支付方式 根据需求选送 参考接口规范
		// data.put("defaultPayType", "");

		// 发卡机构代码 根据需求选送 参考接口规范 当需要网银标志前移时，上送银行简称代码 如 工行 ICBC
		data.put("issInsCode", "ICBC");

		// 支持支付方式 根据需求选送 参考接口规范
		// data.put("supPayType", "");

		// 终端信息域 根据需求选送 参考接口规范
		// data.put("userMac", "");

		// 持卡人ip 根据需求选送 参考接口规范 防钓鱼用
		data.put("customerIp", "127.0.0.1");

		// 绑定标识号 根据需求选送 参考接口规范
		// data.put("bindId", "");

		// 支付卡类型 根据需求选送 参考接口规范
		// data.put("payCardType", "");

		// 安全类型 根据需求选送 参考接口规范
		// data.put("securityType", "");

		// 有卡交易信息域 根据需求选送 参考接口规范
		// data.put("cardTransData", "");

		// VPC交易信息域 根据需求选送 参考接口规范
		// data.put("vpcTransData", "");

		// 订单描述 根据需求选送 参考接口规范
		// data.put("orderDesc", "");

		// 若报文中的数据元标识的key对应的value为空，不上送该报文域
	
		
		/**
		 * 创建表单
		 */
		String html = createHtml(requestFrontUrl, signData(data));
		System.out.println(html);
	}

}
