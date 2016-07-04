package com.rx.unop.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rx.unop.util.UnopUtil;
import com.unionpay.acp.sdk.SDKConfig;

public class UnopServlet extends HttpServlet{
	private static final long serialVersionUID = 3630080377987697189L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		request.getRequestDispatcher("/pages/order_add.jsp").forward(request, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SDKConfig config = SDKConfig.getConfig();
		config.loadPropertiesFromSrc();
		Map<String,String> paramMap = UnopUtil.buildParamMap(config);
		String html = UnopUtil.createHtml(config.getFrontRequestUrl(), UnopUtil.signData(paramMap));
		System.out.println(html);
		req.setAttribute("html", html);
		req.getRequestDispatcher("/pages/order_commit.jsp").forward(req, resp);
	}
	
	public static void main(String[] args) {
		String a = "\u5bc6\u7801\u52a0\u5bc6\u8bc1\u4e66\u8def\u5f84";
		System.out.println(a);
	}
	
}
