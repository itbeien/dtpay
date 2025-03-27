package cn.itbeien.common.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @author beien
 * @date 2024-03-05 0:54
 * Copyright© 2024 beien
 */
public class HttpClient {

	private static Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

	public static final String BOUNDARYSTR = "aifudao7816510d1hq";
	public static final String BOUNDARY = "--" + BOUNDARYSTR + "\r\n";

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
}
