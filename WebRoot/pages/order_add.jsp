<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ChinaPay ��������֧��</title>
</head>
<body>
<form name="createOrder" action="./doCreateOrder" method="POST" target="_blank">
	<table>
		<tr>
			<td>
				<font color=red>*</font>�������
			</td>
			<td>
                 <input type="text" name="TransAmt" value="�Զ�����" maxlength="12"> &nbsp;
            </td>
		</tr>
		<tr>
			<td>
				<font color=red>*</font>�������
			</td>
			<td>
                 <input type="text" name="TransAmt" value="1" maxlength="12"> &nbsp;(12λ���֣�����Ĭ�Ͻ��Ϊ1��)
            </td>
		</tr>
	</table>	
	<input type='button' name='v_action' value='�ύ����' onClick='document.createOrder.submit()'>
</form>
</body>
</html>