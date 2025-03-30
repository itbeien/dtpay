package cn.itbeien.payment.service.risk;

import cn.itbeien.common.enums.MercStatusEnum;
import cn.itbeien.common.mapper.IpWhiteListMapper;
import cn.itbeien.common.mapper.MerchantAccessRightMapper;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RiskManageService {
	
	@Autowired
	private IpWhiteListMapper ipWhiteListMapper;

	@Autowired
	private MerchantAccessRightMapper merchantAccessRightMapper;
	
	/**
	 * 校验请求接口是否
	 * @param map
	 * @return
	 */
	public void validateInterfaceCode(Map<String,Object> map){
		
		List<Map<String,Object>> maplist = merchantAccessRightMapper.getMapByMercNoAndInterface(map);
		if(maplist==null||maplist.size()==0){
			log.info("商户号{}对应接口{}",map.get("mercNo"),map.get("interfaceCode")+"未获授权");
			throw new TradeException(RespEnum.E50017.getCode(), map.get("interfaceCode")+RespEnum.E50017.getDesc());
		}
		
		log.info("商户号{}对应接口{}",map.get("mercNo"),map.get("interfaceCode")+(MercStatusEnum.ENABLED.getCode().equals(maplist.get(0).get("status"))?"已获授权":"未获授权"));
		if(!MercStatusEnum.ENABLED.getCode().equals(maplist.get(0).get("status"))){
			throw new TradeException(RespEnum.E50017.getCode(), map.get("interfaceCode")+RespEnum.E50017.getDesc());
		}
	}
	

	/**
	 * 校验请求IP地址白名单
	 * @param map
	 * @return
	 */
	public boolean validateIpFrom(Map<String,Object> map){
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String ipFrom = this.getIpAddr(request);
		map.put("ip", ipFrom);
		log.info("请求商户号为{}，请求IP[{}]", (String)map.get("mercNo"),ipFrom);
		
		List<Map<String,Object>> mapList = ipWhiteListMapper.getIpWhiteListByMap(map.get("mercNo")==null?"":(String)map.get("mercNo"));
		
		if(mapList==null||mapList.size()==0){
			log.error("请求IP为[{}]，在系统中暂未设置任何白名单", ipFrom);
			return false;
		}
		
		for(Map<String,Object> temp:mapList){
			String ips = (String)temp.get("ipAddress");
			List<String> ipList = Arrays.asList(ips.split("/"));
			if(ipList.contains(ipFrom) //ip地址完全匹配或者商户设置为0.0.0.0则表示在IP白名单中
					||(((String)map.get("mercNo")).equals((String)temp.get("mercNo"))&&"0.0.0.0".equals((String)temp.get("ipAddress")))){
				log.info("请求IP[{}]在商户或系统{}白名单中", ipFrom,(String)temp.get("mercNo"));
				return true;
			}
		}
		log.info("请求IP[{}]系统白名单中",ipFrom);
		return false;
	}
	
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */
    public String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();  
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ipAddress= inet.getHostAddress();  
            }  
        }  
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }  
        return ipAddress;
    }
    
    public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("127.0.0.2");
		list.add("127.0.0.12");
		list.add("222.0.0.12");
		System.out.println(list.contains("127.0.0.1"));
	}
}
