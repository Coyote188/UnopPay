<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ChinaPay 银联电子支付</title>
</head>
<body>
<form name="createOrder" action="./doCreateOrder" method="POST" target="_blank">
	<table>
		<tr>
			<td>
				<font color=red>*</font>订单编号
			</td>
			<td>
                 <input type="text" name="TransAmt" value="自动生成" maxlength="12"> &nbsp;
            </td>
		</tr>
		<tr>
			<td>
				<font color=red>*</font>订单金额
			</td>
			<td>
                 <input type="text" name="TransAmt" value="1" maxlength="12"> &nbsp;(12位数字，不填默认金额为1分)
            </td>
		</tr>
	</table>	
	<input type='button' name='v_action' value='提交订单' onClick='document.createOrder.submit()'>
</form>
</body>
</html>