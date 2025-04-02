package cn.itbeien.admin.service.system.impl;

import cn.itbeien.admin.service.system.IpWhiteListService;
import cn.itbeien.common.vo.IpWhiteListQryPar;
import cn.itbeien.common.entity.IpWhiteList;
import cn.itbeien.common.mapper.IpWhiteListMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpWhiteListServiceImpl implements IpWhiteListService {
	
	
	@Autowired
	private IpWhiteListMapper ipWhiteListMapper;
	
	@Override
	public boolean addIpWhiteList(IpWhiteList param) throws DataAccessException {
		int result = ipWhiteListMapper.insert(param);
		
		return result>0;
	}

	@Override
	public boolean delIpWhiteList(String id) throws DataAccessException {
		int result = ipWhiteListMapper.deleteByPrimaryKey(id);
		
		return result>0;
	}

	@Override
	public boolean uptIpWhiteList(IpWhiteList param) throws DataAccessException{
		int result = ipWhiteListMapper.updateByPrimaryKeySelective(param);
		return result>0;
	}

	@Override
	public List<IpWhiteList> qryIpWhiteListsByPage(IpWhiteListQryPar param) throws DataAccessException {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<IpWhiteList> list = this.qryIpWhiteLists(param);
		return list;
	}

	@Override
	public List<IpWhiteList> qryIpWhiteLists(IpWhiteListQryPar param) throws DataAccessException {
		return ipWhiteListMapper.selectIpWhiteLists(param);
	}

	@Override
	public IpWhiteList qryIpWhiteListById(String id) throws DataAccessException {
		return ipWhiteListMapper.selectByPrimaryKey(id);
	}

}
