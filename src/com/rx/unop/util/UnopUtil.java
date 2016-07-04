package com.rx.unop.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.unionpay.acp.sdk.HttpClient;
import com.unionpay.acp.sdk.SDKConfig;
import com.unionpay.acp.sdk.SDKUtil;

public class UnopUtil {
	public static String encoding = "GBK";
	public static String merId = "自己定义";//商户号码
	/**
	 * 5.0.0
	 */
	public static String version = "5.0.0";
	/**
	 * http://localhost:8080/ACPTest/acp_front_url.do
	 */
	//后台服务对应的写法参照 FrontRcvResponse.java
	public static String frontUrl = "http://localhost:8080/ACPTest/acp_front_url.do";
	
	public static Map<String,String> buildParamMap(SDKConfig config){
		Map<String,String> paramMap = new HashMap<String, String>();
		// 版本号
		paramMap.put("version", version);
		// 字符集编码 默认"UTF-8"
		paramMap.put("encoding", encoding);
		// 证书ID 调用 SDK 可自动获取并赋值
		// data.put("certId", "31692114440333550101559775639582427889");
		// 签名 调用 SDK 可自动运算签名并赋值
		// data.put("signature", "");
		// 签名方法 01 RSA
		paramMap.put("signMethod", "01");
		// 交易类型 01-消费
		paramMap.put("txnType", "01");
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		paramMap.put("txnSubType", "01");
		// 业务类型 000201 B2C网关支付
		paramMap.put("bizType", "000201");
		// 渠道类型 07-互联网渠道
		paramMap.put("channelType", "07");
		// 商户/收单后台接收地址 必送
		//后台服务对应的写法参照 BackRcvResponse.java
		paramMap.put("backUrl", "http://localhost:8080/UnopPay");
		paramMap.put("frontUrl", "http://localhost:8080/UnopPay");
		// 接入类型:商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		paramMap.put("accessType", "0");
		// 商户号码
		paramMap.put("merId", merId);
		// 订单号 商户根据自己规则定义生成，每订单日期内不重复
		paramMap.put("orderId", System.currentTimeMillis()+"");
		// 订单发送时间 格式： YYYYMMDDhhmmss 商户发送交易时间，根据自己系统或平台生成
		paramMap.put("txnTime", DateUtil.getCurrentTimes());

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
		paramMap.put("txnAmt", "1");
		// 交易币种
		paramMap.put("currencyCode", "156");

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
		paramMap.put("encryptCertId", "");

		// 失败交易前台跳转地址 根据需求选送 参考接口规范
		// data.put("frontFailUrl", "");

		// 分期付款信息域 根据需求选送 参考接口规范 txnSubType =03 分期付款交易必须上送
		// data.put("instalTransInfo", "");

		// 默认支付方式 根据需求选送 参考接口规范
		// data.put("defaultPayType", "");

		// 发卡机构代码 根据需求选送 参考接口规范 当需要网银标志前移时，上送银行简称代码 如 工行 ICBC
//		paramMap.put("issInsCode", "ICBC");

		// 支持支付方式 根据需求选送 参考接口规范
		// data.put("supPayType", "");

		// 终端信息域 根据需求选送 参考接口规范
		// data.put("userMac", "");

		// 持卡人ip 根据需求选送 参考接口规范 防钓鱼用
		paramMap.put("customerIp", "127.0.0.1");

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
		return paramMap;
//		return submitDate(paramMap, config.getBackRequestUrl());
	}
	
	/**
	 * java main方法 数据提交　 数据组装进行提交 包含签名
	 * 
	 * @param contentData
	 * @return 返回报文 map
	 */
	public static Map<String, String> submitDate(Map<String, ?> contentData,String requestUrl) {
		Map<String, String> submitFromData = (Map<String, String>) signData(contentData);
		return submitUrl(submitFromData,requestUrl);
	}
	
	/**
	 * java main方法 数据提交 提交到后台
	 * 
	 * @param contentData
	 * @return 返回报文 map
	 */
	public static Map<String, String> submitUrl(Map<String, String> submitFromData,String requestUrl) {
		String resultString = "";
		System.out.println("requestUrl====" + requestUrl);
		System.out.println("submitFromData====" + submitFromData.toString());
		/**
		 * 发送
		 */
		HttpClient hc = new HttpClient(requestUrl, 30000, 30000);
		try {
			int status = hc.send(submitFromData, encoding);
			if (200 == status) {
				resultString = hc.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> resData = new HashMap<String, String>();
		/**
		 * 验证签名
		 */
		if (null != resultString && !"".equals(resultString)) {
			// 将返回结果转换为map
			resData = SDKUtil.convertResultStringToMap(resultString);
			if (SDKUtil.validate(resData, encoding)) {
				System.out.println("验证签名成功");
			} else {
				System.out.println("验证签名失败");
			}
			// 打印返回报文
			System.out.println("打印返回报文：" + resultString);
		}
		return resData;
	}
	/**
	 * java main方法 数据提交 　　 对数据进行签名
	 * 
	 * @param contentData
	 * @return　签名后的map对象
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> signData(Map<String, ?> contentData) {
		Entry<String, String> obj = null;
		Map<String, String> submitFromData = new HashMap<String, String>();
		for (Iterator<?> it = contentData.entrySet().iterator(); it.hasNext();) {
			obj = (Entry<String, String>) it.next();
			String value = obj.getValue();
			if (StringUtils.isNotBlank(value)) {
				// 对value值进行去除前后空处理
				submitFromData.put(obj.getKey(), value.trim());
				System.out.println(obj.getKey() + "-->" + String.valueOf(value));
			}
		}
		/**
		 * 签名
		 */
		SDKUtil.sign(submitFromData, encoding);

		return submitFromData;
	}
	
	
	/**
	 * 构造HTTP POST交易表单的方法示例
	 * 
	 * @param action
	 *            表单提交地址
	 * @param hiddens
	 *            以MAP形式存储的表单键值
	 * @return 构造好的HTTP POST交易表单
	 */
	public static String createHtml(String action, Map<String, String> hiddens) {
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"/></head><body>");
		sf.append("<form id=\"pay_form\" name=\"pay_form\" action=\"" + action +"\" method=\"post\">");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\"" + key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("<input type=\"submit\" value=\"确认\" style=\"display:none;\">");
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.forms['pay_form'].submit();");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}
}
