

package cn.itbeien.merchant.controller.download;
import cn.itbeien.common.controller.BaseController;
import cn.itbeien.common.entity.merchant.MerchantAccountInfo;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.merchant.service.download.MerchantAccountInfoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
@Slf4j
public class DownLoadController extends BaseController {
	
	private final MerchantAccountInfoService merchantAccountInfoService;

	@RequestMapping(value = "/downloadFiles/{tcLwIds}")
	public String downloadFiles(@PathVariable("tcLwIds") String tcLwIds, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(StringUtils.isEmpty(tcLwIds)) {
			return null;
		}
		String[] fileArr = null;
		if(tcLwIds.contains("_")) {
			fileArr = tcLwIds.split("_");
		} else {
			fileArr = new String[]{tcLwIds};
		}
		List<File> files = new ArrayList<File>();
		String path = request.getSession().getServletContext().getRealPath("") + "/doc/";
		File file = null;
		for(String fileNo : fileArr) {
			file = new File(path + this.getFileNameByNo(fileNo));
			System.out.println("文档：" + path + this.getFileNameByNo(fileNo));
			files.add(file);
		}
		// 下载后的压缩文件
		String fileName =  new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".zip";
		// 在服务器端创建打包下载的临时文件
		String outFilePath = request.getSession().getServletContext().getRealPath("") + "/doc/";

		File fileZip = new File(outFilePath + fileName);
		// 文件输出流
		FileOutputStream outStream = new FileOutputStream(fileZip);
		// 压缩流
		ZipOutputStream toClient = new ZipOutputStream(outStream);
		zipFile(files, toClient);
		toClient.close();
		outStream.close();
		this.downloadFile(fileZip, response, true);
		return null;
	}
	
	/**
	 * accountList:(对账文件列表)
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("/accountList")
	public TableDataInfo accountList(String startDate, String endDate) {
		List<MerchantAccountInfo> list = merchantAccountInfoService.qryMerchantAccountByPage(null);
		return getDataTable(list);
	}
	
	/**
	 * downloadAccount:(对账单文件下载).
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/downloadAccount/{id}")    
    public ResponseEntity<byte[]> downloadAccount(@PathVariable("id") String id) throws IOException {
    	MerchantAccountInfo accountInfo = merchantAccountInfoService.selectByPrimaryKey(id);
        String path = accountInfo.getFilePath();  
        File file = new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        // 为了解决中文名称乱码问题  
        String fileName = new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);    
    }    

	/**
	 * zipFile:(压缩文件)
	 * @param files
	 * @param outputStream
	 * @throws IOException
	 * @throws ServletException
	 */
	private static void zipFile(List<File> files, ZipOutputStream outputStream) throws IOException, ServletException {
		try {
			int size = files.size();
			// 压缩列表中的文件
			for (int i = 0; i < size; i++) {
				File file = (File) files.get(i);
				zipFile(file, outputStream);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * zipFile:(文件压缩)
	 * @param inputFile
	 * @param outputstream
	 * @throws IOException
	 * @throws ServletException
	 */
	private static void zipFile(File inputFile, ZipOutputStream outputstream) throws IOException, ServletException {
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					FileInputStream inStream = new FileInputStream(inputFile);
					BufferedInputStream bInStream = new BufferedInputStream(inStream);
					ZipEntry entry = new ZipEntry(inputFile.getName());
					outputstream.putNextEntry(entry);

					final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
					long streamTotal = 0; // 接受流的容量
					int streamNum = 0; // 流需要分开的数量
					int leaveByte = 0; // 文件剩下的字符数
					byte[] inOutbyte; // byte数组接受文件的数据

					streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
					streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
					leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量

					if (streamNum > 0) {
						for (int j = 0; j < streamNum; ++j) {
							inOutbyte = new byte[MAX_BYTE];
							// 读入流,保存在byte数组
							bInStream.read(inOutbyte, 0, MAX_BYTE);
							outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
						}
					}
					// 写出剩下的流数据
					inOutbyte = new byte[leaveByte];
					bInStream.read(inOutbyte, 0, leaveByte);
					outputstream.write(inOutbyte);
					outputstream.closeEntry(); // Closes the current ZIP entry
					// and positions the stream for
					// writing the next entry
					bInStream.close(); // 关闭
					inStream.close();
				}
			} else {
				throw new ServletException("文件不存在！");
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * @author itbeien
	 * 项目网站：https://www.itbeien.cn
	 * 公众号：贝恩聊架构
	 * 全网同名，欢迎小伙伴们关注
	 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
	 * downloadFile:(文件下载)
	 * Copyright© 2025 itbeien
	 */
	private void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
		try {
			// 以流的形式下载文件。
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			if (isDelete) {
				file.delete(); // 是否将生成的服务器端文件删除
			}
		} catch (IOException ex) {
			log.error("下载文件失败", ex);
		}
	}

	/**
	 * getFileNameByNo:(根据文件编号 得到文件名)
	 * @param fileNo
	 * @return
	 */
	private String getFileNameByNo(String fileNo) {
		String fileName = null;
		switch (fileNo) {
			case "1":
				fileName = "MercInterfaceDoc_1.0.0.doc";
				break;
			case "2":
				fileName = "";
				break;
			case "3":
				fileName = "dtpay-demo.rar";
				break;
			default:
				fileName = "none";
				break;
		}
		return fileName;
	}
}
