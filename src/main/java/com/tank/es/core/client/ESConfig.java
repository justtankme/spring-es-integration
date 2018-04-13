package com.tank.es.core.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//使用javaConfig方式配置
@Configuration
@ComponentScan(basePackageClasses = ESClientSpringFactory.class)
public class ESConfig {

	@Value("${es.host}")
	private String host;

	@Value("${es.port}")
	private int port;
	
	@Value("${es.username}")
	private String username;
	
	@Value("${es.password}")
	private String password;

	@Value("${es.schema}")
	private String schema;

	@Value("${es.maxConnectNum}")
	private Integer connectNum;
	
	@Value("${es.maxConnectPerRoute}")
	private Integer connectPerRoute;

	@Value("${es.connectTimeOut}")
	private Integer connectTimeOut;
	
	@Value("${es.socketTimeOut}")
	private Integer socketTimeOut;
	
	@Value("${es.connectionRequestTime}")
	private Integer connectionRequestTime;

	@Bean
	public HttpHost httpHost() {
		return new HttpHost(host, port, schema);
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public ESClientSpringFactory getFactory() {
		return ESClientSpringFactory.build(httpHost(), username, password, connectNum, connectPerRoute, connectTimeOut, socketTimeOut, connectionRequestTime);
	}

	@Bean
	@Scope("singleton")
	public RestClient getRestClient() {
		return getFactory().getClient();
	}

	@Bean
	@Scope("singleton")
	public RestHighLevelClient getRHLClient() {
		return getFactory().getRhlClient();
	}
}