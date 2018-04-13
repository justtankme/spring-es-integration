package com.tank.es.core.client;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClientSpringFactory {
	public static int CONNECT_TIMEOUT_MILLIS = 1000;
	public static int SOCKET_TIMEOUT_MILLIS = 30000;
	public static int CONNECTION_REQUEST_TIMEOUT_MILLIS = 500;
	public static int MAX_CONN_PER_ROUTE = 10;
	public static int MAX_CONN_TOTAL = 30;

	private static HttpHost HTTP_HOST;
	private static String USERNAME;
	private static String PASSWORD;
	private RestClientBuilder builder;
	private RestClient restClient;
	private RestHighLevelClient restHighLevelClient;

	private static ESClientSpringFactory esClientSpringFactory = new ESClientSpringFactory();

	private ESClientSpringFactory() {
	}

	public static ESClientSpringFactory build(HttpHost httpHost, String username, String password, Integer maxConnectNum, Integer maxConnectPerRoute, Integer connectTimeOut, Integer socketTimeOut,
			Integer connectionRequestTime) {
		HTTP_HOST = httpHost;
		USERNAME = username;
		PASSWORD = password;
		MAX_CONN_TOTAL = maxConnectNum;
		MAX_CONN_PER_ROUTE = maxConnectPerRoute;
		CONNECT_TIMEOUT_MILLIS = connectTimeOut;
		SOCKET_TIMEOUT_MILLIS = socketTimeOut;
		CONNECTION_REQUEST_TIMEOUT_MILLIS = connectionRequestTime;
		return esClientSpringFactory;
	}

	public void init() {
		builder = RestClient.builder(HTTP_HOST);
		setConnectTimeOutConfig();
		setMutiConnectConfig();
		restClient = builder.build();
		restHighLevelClient = new RestHighLevelClient(builder);
		System.out.println("finished init factory");
	}

	// 配置连接时间延时
	public void setConnectTimeOutConfig() {
		builder.setRequestConfigCallback(new RequestConfigCallback() {
			@Override
			public Builder customizeRequestConfig(Builder requestConfigBuilder) {
				requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
				requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
				requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
				return requestConfigBuilder;
			}
		});
	}

	// 使用异步httpclient时设置并发连接数
	public void setMutiConnectConfig() {
		builder.setHttpClientConfigCallback(new HttpClientConfigCallback() {
			@Override
			public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
				httpClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
				
				//basic authentication
				final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
				credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USERNAME, PASSWORD));
				httpClientBuilder.disableAuthCaching();
				httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
				//Encrypted communication
				//The keystore must contain the client’s signed certificate and the CA certificate
//				KeyStore keystore = KeyStore.getInstance("jks");
//				try (InputStream is = Files.newInputStream(keyStorePath)) {
//				    truststore.load(is, keyStorePass.toCharArray());
//				}
				//The truststore must contain the certificate of the CA that signed the nodes certificates.
//				try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("classpath:jssecacerts")) {
//					KeyStore truststore = KeyStore.getInstance("jks");
//				    truststore.load(is, null);
//					SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null)
////							.loadKeyMaterial(keystore, null)
//							;
//					final SSLContext sslContext = sslBuilder.build();
//					httpClientBuilder.setSSLContext(sslContext);
//				} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | KeyManagementException e) {
//					e.printStackTrace();
//				}
				
				return httpClientBuilder;
			}
		});
	}

	public RestClient getClient() {
		return restClient;
	}

	public RestHighLevelClient getRhlClient() {
		return restHighLevelClient;
	}

	public void close() {
		if (restClient != null) {
			try {
				restClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("close client");
	}

}