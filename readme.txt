2015-01-12
本DEMO是银联最新的文档编写
com.unionpay.acp.demo01是银联原来的包
com.rx.unop是我自己写的包。
通过详细研究银联的文档，终于实现了跳转到银联支付页面
未实现支付成功后的代码编写，请自己根据需求业务和银联文档或demo进行编写

注意：
1.要想运行项目，请先输入com.rx.unop.util.UnopUtil类下的merId 商户号码
否则运行不会跳转
2.加密证书我放在key包下，请复制到E\:\\unop\\700000000000001.pfx目录下
因为我在acp_sdk.properties配置文件里已经写了此路径。

