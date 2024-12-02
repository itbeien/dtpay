package cn.itbeien.business.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Slf4j
public class HttpClient {

	private static Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

	public static final String BOUNDARYSTR = "aifudao7816510d1hq";
	public static final String BOUNDARY = "--" + BOUNDARYSTR + "\r\n";
	public static final int SMALL_TIME = 5000;

	private static SSLConnectionSocketFactory sslsf = null;
	private static PoolingHttpClientConnectionManager cm = null;
	private static SSLContextBuilder builder = null;
	private static final String HTTP = "http";
	private static final String HTTPS = "https";

	static {
		try {
			builder = new SSLContextBuilder();
			// 全部信任,不做身份鉴定.
			builder.loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(), new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(200);// max connection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 法用途: multipart/form-data格式URL请求 包含图片数据
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendMuiltiPartFile(String url,File file,String fileName,Map<String,String> params){
		String result = null;
		try{
			// 创建HttpClient实例
			CloseableHttpClient httpClient = HttpClients.createDefault();

			// 创建HttpPost实例，指定请求的URL
			HttpPost httpPost = new HttpPost(url);
			// 设置请求的multipart/form-data
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addBinaryBody(fileName, file, ContentType.MULTIPART_FORM_DATA, file.getName());

			for(Map.Entry<String,String> entry: params.entrySet()) {
				// 添加其他表单字段
				builder.addTextBody(entry.getKey(), entry.getValue());
			}

			// 构建HttpEntity实例
			HttpEntity multipartEntity = builder.build();

			// 设置请求实体
			httpPost.setEntity(multipartEntity);

			// 执行请求并获取响应
			HttpResponse response = httpClient.execute(httpPost);

			// 打印响应状态
			log.info("响应状态{}",response.getStatusLine().toString());

			// 打印响应内容
			result = EntityUtils.toString(response.getEntity());
			log.info("响应数据:{}",result);
		}catch(Exception e){
			log.error("系统异常:{}",e);
		}
		return  result;
	}

	/**
	 * 
	 * 方法用途: multipart/form-data格式URL请求
	 * 实现步骤: <br>
	 * @param postUrl
	 * @param mPostMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String sendMuiltiPart(String postUrl, Map mPostMap) {

		BufferedOutputStream out = null;

		HttpURLConnection conn = null;

		try {
			URL url = new URL(postUrl);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(1000 * 10);
			conn.setReadTimeout(1000 * 10);
			conn.setRequestMethod("POST");

			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-type", "multipart/form-data;boundary=" + BOUNDARYSTR);
			conn.connect();

			out = new BufferedOutputStream(conn.getOutputStream());

			StringBuilder sb = new StringBuilder();

			Iterator<String> it = mPostMap.keySet().iterator();
			while (it.hasNext()) {
				String str = it.next();
				sb.append(BOUNDARY);
				sb.append("Content-Disposition:form-data;name=\"");
				sb.append(str);
				sb.append("\"\r\n\r\n");
				sb.append(mPostMap.get(str));
				sb.append("\r\n");
			}

			// post the string data.
			out.write(sb.toString().getBytes());
			out.write(("--" + BOUNDARYSTR + "--\r\n").getBytes());
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer responseSb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseSb.append(line.trim());
			}

			return responseSb.toString().trim();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("发送请求[" + postUrl + "]失败，" + e.getMessage());
			return null;
		} finally {
			conn.disconnect();

			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 功能：POST application/json
	 * 
	 * @param postURL
	 * @param requestBody
	 * @param sendCharset
	 * @param readCharset
	 * @param timeOut(请求、读取) 秒
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public static String send(String postURL, String requestBody, String sendCharset, String readCharset,int timeOut)
			throws Exception {
		if(timeOut<5){
			timeOut = 5;
		}
		// Post请求的url，与get不同的是不需要带参数
		HttpURLConnection httpConn = null;

		StringBuffer responseSb = new StringBuffer();

		try {

			//SSLContext sc = SSLContext.getInstance("SSL");
			//sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL postUrl = new URL(postURL);
			// 打开连接
			httpConn = (HttpURLConnection) postUrl.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("POST");
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			//httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + sendCharset);
			httpConn.setRequestProperty("Content-TYpe","application/json");
			httpConn.setConnectTimeout(1000 * timeOut);//连接超时时间
			httpConn.setReadTimeout(1000 * timeOut);//读取数据超时时间
			// 连接，从postUrl.openConnection()至此的配置必须要在 connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行 connect。
			httpConn.connect();
			DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
			out.write(requestBody.getBytes(sendCharset));
			out.flush();
			out.close();
			int status = httpConn.getResponseCode();
			
			if (status != HttpURLConnection.HTTP_OK) {
				LOGGER.info("发送请求失败，状态码：[" + status + "] 返回信息：" + httpConn.getResponseMessage());
				return null;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), readCharset));
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseSb.append(line.trim());
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.info("发送请求[" + postURL + "]失败，" + ex.getMessage());
			throw ex;
		} finally {
			httpConn.disconnect();
		}
		return responseSb.toString().trim();
	}


	/**
	 * 发送https请求
	 *
	 * @param url           url
	 * @param requestMethod 请求方式
	 * @param param         请求参数
	 * @return 返回值
	 */
	public static String sendHttpsRequest(String url, String requestMethod, String param) {
		StringBuilder result = new StringBuilder();
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[]{
					new X509TrustManager() {
						@Override
						public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
						}

						@Override
						public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
						}

						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return new X509Certificate[0];
						}
					}
			}, new SecureRandom());
			URL console = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
			// GET/POST
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if (null != param) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(param.getBytes("UTF-8"));
				outputStream.close();
			}

			// 设置证书忽略相关操作
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String s, SSLSession sslSession) {
					return true;
				}
			});
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String ret = "";
			while ((ret = br.readLine()) != null) {
				if (ret != null && !ret.trim().equals("")) {
					result.append(new String(ret.getBytes("utf-8"), "utf-8"));
				}
			}
			conn.disconnect();
			br.close();
		} catch (NoSuchAlgorithmException | KeyManagementException | MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return result.toString();
	}


	/**
	 * postBody
	 *
	 * @param url
	 * @param header
	 * @param Content_type
	 * @return
	 * @throws IOException
	 */
	public static String postBody(String url, Map<String, String> header, String Content_type) throws IOException {
		String result = null;
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).setConnectionManagerShared(true).build();
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(SMALL_TIME).setConnectTimeout(SMALL_TIME).setSocketTimeout(SMALL_TIME).build();
			httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.addHeader("Content-type",Content_type);
			// 请求头
			List<NameValuePair> params = new ArrayList();
			for (Map.Entry<String, String> entry : header.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// 请求体
			UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params,"UTF-8");
			encodedFormEntity.setContentType("application/json;charset=UTF-8");
			httpPost.setEntity(encodedFormEntity);
			httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = httpResponse.getEntity();
				result = EntityUtils.toString(resEntity);
			}
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (httpPost != null) {
					httpPost.releaseConnection();
				}
				if (httpResponse != null) {
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static class TrustAnyTrustManager implements X509TrustManager
	{
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
		{
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
		{
		}

		@Override
		public X509Certificate[] getAcceptedIssuers()
		{
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier
	{
		@Override
		public boolean verify(String hostname, SSLSession session)
		{
			return true;
		}
	}

}
