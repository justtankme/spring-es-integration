# spring-es-integration
目标ES添加了SG插件，因此需要进行以下操作添加认证文件：
- 使用java InstallCert localhost:9200获取jssecacerts文件（详见 http://www.cnblogs.com/wanghaixing/p/5630070.html ）
- 将jssecacerts文件copy到%JAVA_HOME%/jre/lib/security，如/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/security
- 配置HttpAsyncClientBuilder使用CredentialsProvider，详见ESClientSpringFactory.setMutiConnectConfig()
