package com.sinosoft.one.ams.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sinosoft.one.ams.model.Company;
import com.sinosoft.one.ams.model.UserPower;
import com.sinosoft.one.ams.repositories.CompanyDao;
import com.sinosoft.one.ams.repositories.GeRmsUserPowerRepository;
import com.sinosoft.one.ams.service.facade.CompanyService;
import com.sinosoft.one.uiutil.NodeEntity;
import com.sinosoft.one.uiutil.Treeable;

@Component
public class CompanyServiceImpl implements CompanyService{
	
	@Resource(name="companyDao")
	private CompanyDao companyDao;
	@Resource(name="geRmsUserPowerRepository")
	private GeRmsUserPowerRepository geRmsUserPowerRepository;

	//根据Uppercomcode查询出comCode集合
	public List<Company> findCompanyByUpperComCode(String uppercomcode) {
		
		List<Company> company = new ArrayList<Company>();
		
		//父机构ID为空，查询全部机构
		if(uppercomcode == null){
			company = (List<Company>) companyDao.findAll();
		}else{
			//父机构ID不为空，查询辅机构的子机构ID
			List<String> childComCode = companyDao.findComCodeByUppercomcode(uppercomcode);
			if(!childComCode.isEmpty()){
				company = (List<Company>) companyDao.findAll(childComCode);
			}
			
		}
		return company;
	}
	
	//根据机构ID查询机构对象
	public Company findCompanyByComCode(String comCode) {
		return companyDao.findOne(comCode);
	}

	/**
	 * 构建功能树 topCompany父节点 filter所有节点
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  Treeable<NodeEntity> creatCompanyTreeAble(List<Company> topCompany,Map<String,Company> filter){
		List<NodeEntity> nodeEntitys=new ArrayList<NodeEntity>();
		nodeEntitys=creatSubNode(topCompany, filter);
		Treeable<NodeEntity> treeable =new Treeable.Builder(nodeEntitys,"id", "title", "children", "state").classField("classField").urlField("urlField").builder();
		return treeable;
	}
	
	//创建机构树
	List<NodeEntity> creatSubNode(List<Company> topCompany,Map<String,Company> filter){
		ArrayList<NodeEntity> nodeEntitys=new ArrayList<NodeEntity>();
		for (Company company : topCompany) {
			if(!filter.containsKey(company.getComCode()))
                continue;
			NodeEntity nodeEntity = new NodeEntity();
			nodeEntity.setId(company.getComCode());
			nodeEntity.setTitle(company.getComCName());
			
			//根据机构ID查询子机构
			List<Company> childCompany = findCompanyByUpperComCode(company.getComCode());
			if(!childCompany.isEmpty()){
				nodeEntity.setChildren(creatSubNode(childCompany,filter));
				
			}
				nodeEntitys.add(nodeEntity);
			}
		return nodeEntitys;
	}

	//查询出全部机构
	public List<Company> findAll() {
		return (List<Company>) companyDao.findAll();
	}

	public List<Company> findAllNextComBySupper(String uppercomcode) {
		List<Company> companies=new ArrayList<Company>();
		iteratorComapny(companies, uppercomcode);
		return companies;
	}

	//迭代机构
	void iteratorComapny(List<Company> campanys,String SupercomCode){
		
		//根据父机构ID查询出子机构ID
		List<String> comCodes=companyDao.findComCodeByUppercomcode(SupercomCode);
		if(comCodes.size()>0){
			List<Company> coms=(List<Company>) companyDao.findAll(comCodes);
			campanys.addAll(coms);
			for (String comCode : comCodes) {
				iteratorComapny(campanys, comCode);
			}
		}
		
	}

	//根据userCode查询出用户已被引入的机构
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Treeable<NodeEntity> findCompanyByUserCode(String userCode) {
		List<String> userPowerIds = geRmsUserPowerRepository.findUserPowerIdByUserCode(userCode);
		List<NodeEntity> nodeEntitys=new ArrayList<NodeEntity>();
		Treeable<NodeEntity> treeable =new Treeable.Builder(nodeEntitys,"id", "title", "children", "state").builder();
		
		//如果用户权限ID不为空，则取得用户权限的对象
		if(!userPowerIds.isEmpty()){
			List<UserPower> userPowers = (List<UserPower>) geRmsUserPowerRepository.findAll(userPowerIds);
			
			List<String> comCodes = new ArrayList<String>();
			for(UserPower userPower : userPowers){
				comCodes.add(userPower.getComCode());
			}
			List<Company> companies = (List<Company>) companyDao.findAll(comCodes);
			
			for(Company company : companies){
				NodeEntity nodeEntity = new NodeEntity();
				nodeEntity.setId(company.getComCode());
				nodeEntity.setTitle(company.getComCName());
				nodeEntitys.add(nodeEntity);
			}
			
			
		}
		return treeable;
	}

	//根据userCode查询用户已引入机构
	public List<Company> findComsByUserCode(String userCode) {
		List<String> comCodes = geRmsUserPowerRepository.findComCodeByUserCode(userCode);
		List<Company> coms = new ArrayList<Company>();
		if(!comCodes.isEmpty()){
			coms = (List<Company>) companyDao.findAll(comCodes);
		}
		
		return coms;
	}

}
