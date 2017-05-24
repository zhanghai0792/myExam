package com.jm.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jm.pojo.BasicModel;
import com.jm.query.QueryParams;
import com.jm.serviceDao.ServiceDaoTemplate;
import com.jm.controller.util.JsonUtil;
import com.jm.dao.DaoTemplate;
import com.jm.json.jsonResult;
import com.jm.util.AppConfig;
import com.jm.util.FileUtil;
import com.jm.util.ListUtil;
import com.jm.util.StringUtil;

public class actionTemplate<P extends BasicModel,S extends ServiceDaoTemplate,Q extends QueryParams> {
	protected Logger logger;
	protected Class pojoClass;
	protected Class queryParamClass;

	public actionTemplate() {
		super();
		if (logger == null) {
			logger = Logger.getLogger(this.getClass());
		}
    		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			this.queryParamClass = (Class<Q>) pt.getActualTypeArguments()[2];
			this.pojoClass= (Class<P>) pt.getActualTypeArguments()[0];
	
	}

	@Autowired
	protected S serviceDao;

	protected boolean isInsertBeforeDeal(){return false;}//是否添加前要进行insert前的处理
	protected boolean isInsertAfterDeal(){return false;}//是否添加后要进行insert前的处理
	protected boolean isUpdateBeforeDeal(){return false;}//是否添加前要进行update前的处理
	protected boolean isUpdateAfterDeal(){return false;}//是否添加后要进行update前的处理
	protected boolean isDeleteBeforeDeal(){return false;}//是否添加前要进行Delete前的处理
	protected boolean isDeleteAfterDeal(){return false;}//是否添加后要进行Delete前的处理
	
	protected Object insertBeforeDeal(Object obj){return null;}//insert的处理，子类中要覆盖
	protected Object insertAfterDeal(Object obj){return null;}//insert的处理，子类中要覆盖
	
	protected Object updateBeforeDeal(Object obj){return null;}//insert的处理，子类中要覆盖
	protected Object updateAfterDeal(Object obj){return null;}//insert的处理，子类中要覆盖
	
	protected Object deleteBeforeDeal(Object obj){return null;}//delete的处理，子类中要覆盖
	protected Object deleteAfterDeal(Object obj){return null;}//delete的处理，子类中要覆盖
	
	/*获得对应所有pojo类的基本信息*/
	@RequestMapping("/get")
	@ResponseBody
	public jsonResult getBasic(Q queryParam) throws Exception {
		try {
			List<P> datas = serviceDao.getBasic(queryParam);
			jsonResult json = new jsonResult("查询成功");
			if (datas != null && datas.size() > 0)
				json.add(datas);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/*获得对应所有pojo类的所有信息*/
	@RequestMapping("/getDetails")
	@ResponseBody
	public jsonResult getDetails(Q queryParam) throws Exception {
		try {
			List<P> datas = serviceDao.getDetail(queryParam);
			jsonResult json = new jsonResult("查询成功");
			if (datas != null && datas.size() > 0)
				json.add(datas);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/*删除指定ids的pojo实例*/
	@RequestMapping("/delete")
	@ResponseBody
	public jsonResult deleteIdsByStringJson(@RequestBody P p) throws Exception {
		Object extendDeal=null;
		 if(isDeleteBeforeDeal())
			 extendDeal=deleteBeforeDeal(p);
		
		 int size=serviceDao.deleteObject(p);
		 if(isDeleteAfterDeal())
			 deleteAfterDeal(extendDeal);
		  if(size>0){
		 return new jsonResult( "删除["+1+"]条记录",p);
		 }else{
			 return new jsonResult(false,"删除失败");
		 }
		
	}
	//删除业务
	protected jsonResult deleteDeal(List<Integer> ids)throws Exception{
		try {
			int size = -1;
			 Object deleteExtendDealResult=null;//删除时额外处理时的业务
			  if(isDeleteBeforeDeal()){
				  deleteExtendDealResult=deleteBeforeDeal(ids);//删除前的业务
			  }
	
				size = serviceDao.deleteByIds(ids);
                if(isDeleteAfterDeal())
				   deleteAfterDeal(deleteExtendDealResult);//删除后的补充业务
				
				if (size > 0)
					return new jsonResult(true, "删除["+size+"]条记录");
				else
					return new jsonResult(false, "删除失败");
			}
        catch (Exception e) {
			logger.error(e.getMessage());
			return new jsonResult(false, e.getMessage());
		}
	}





	
	@RequestMapping("/deletes")
	@ResponseBody
	public jsonResult delete(@RequestBody List<P> pojos) throws Exception {
		try {
			int size = -1;
			if (ListUtil.isNotEmpty(pojos)) {
				Object extendDeal=null;
				 if(isDeleteBeforeDeal())
					 extendDeal=deleteBeforeDeal(pojos);
				
				
				size = serviceDao.deleteObjects(pojos);


				 if(isDeleteAfterDeal())
					 deleteAfterDeal(extendDeal);
				
				if (size > 0)
					return createMessageJsonResult(pojos, "删除");
			}
			return new jsonResult(false, "删除失败");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

/*	@RequestMapping("/deleteByIds")
	@ResponseBody
	public jsonResult deleteByIds(@RequestBody List<Integer> ids) throws Exception {
		if (ListUtil.isEmpty(ids)) {
			throw new Exception("没有ids集合");
	}
			return deleteDeal(ids);		
	}*/
	
	/*添加pojo实例*/
	@RequestMapping("/add")
	@ResponseBody
	public jsonResult save(@RequestBody P p) throws Exception {
		Object extendDeal=null;
		 if(isInsertBeforeDeal())
			 extendDeal=insertBeforeDeal(p);

		int size=serviceDao.insert(p);
		
		if(isInsertAfterDeal())
			 insertAfterDeal(extendDeal);
		
		if(size>0){
			return new jsonResult("添加成功", p);
		}else{
			return new jsonResult(false, "添加失败");
		}
	}
	
	@RequestMapping("/adds")
	@ResponseBody
	public jsonResult adds(@RequestBody List<P> pojos) throws Exception {
		 if(ListUtil.isEmpty(pojos))
			   throw new Exception("添加的对象为空");
		return insertDeal(pojos);
	}
	
	@RequestMapping("/addOne")
	@ResponseBody
	public jsonResult adds(@RequestBody P p) throws Exception {
		 List<P> pojos=new ArrayList<P>(0);
		  pojos.add(p);
		return insertDeal(pojos);
	}
	
	//add添加业务
	public jsonResult insertDeal(List<P> pojos) throws Exception {
		try {
			int size = -1;
			 Object insertExtendDealResult=null;//添加时的额外处理时的业务
			 if(isInsertBeforeDeal())
			     insertBeforeDeal(pojos);
			
				size = serviceDao.inserts(pojos);//正常添加
				
			  if(isInsertAfterDeal())
				insertAfterDeal(insertExtendDealResult);//数据库记录添加后的其他业务处理处理
				
			if (size > 0)
				return createMessageJsonResult(pojos, "添加");
			return new jsonResult(false, "添加失败");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new jsonResult(false,e.getMessage());
		}
	}
	
	
	
	/*添加pojo实例*/
	@RequestMapping("/update")
	@ResponseBody
	public jsonResult updateStringJson(@RequestBody P p) throws Exception {
		
		Object extendDeal=null;
		 if(isUpdateBeforeDeal())
			 extendDeal=updateBeforeDeal(p);

		  int size=serviceDao.updateAllField(p);
		  
			if(isUpdateAfterDeal())
				 updateAfterDeal(extendDeal);
		  
		 if(size>0){
				return new jsonResult("更新成功", p);
			}else{
				return new jsonResult(false, "更新失败");
			}
	}
	
	
    /*全部更新，如果该对象属性也为null，也更新为null*/
	@RequestMapping("/updateNull")
	@ResponseBody
	public jsonResult updateNull(@RequestBody P p) throws Exception {
		if(p.getId()==null)
			throw new Exception("对象没有id值");
		List<P> pojos=new ArrayList<P>(0);
		  pojos.add(p);
		  return updateDeal(pojos, 0);
	}
	/*更新pojo对象的非null属性*/
	@RequestMapping("/updateNoNull")
	@ResponseBody
	public jsonResult updateNoNull(@RequestBody P p) throws Exception {
		if(p.getId()==null)
			throw new Exception("对象没有id值");
		List<P> pojos=new ArrayList<P>(0);
		  pojos.add(p);
		return updateDeal(pojos, 1);
	}

	
	// type=0把所有字段都更新，不管是否为空;type=1，更新非空的属性
	public jsonResult updateDeal(List<P> pojos, int type) throws Exception {
		try {
			int size = -1;
			 Object updateExtendDealResult=null;//添加时的额外处理时的业务
			 if(isUpdateBeforeDeal())
				 updateExtendDealResult=updateBeforeDeal(pojos);//更新前的处理
			
			if (type == 0) {
				size = serviceDao.updatesHasNull(pojos);//更新所有字段(含null)
			} else {
				size = serviceDao.updatesNotNull(pojos);//(如果该对象的字段为null,则不更新)
			}
			
			if(isUpdateAfterDeal())
			  updateAfterDeal(updateExtendDealResult);
			
			if (size > 0)
				return createMessageJsonResult(pojos, "更新");
			return new jsonResult(false, "更新失败");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new jsonResult(false,e.getMessage());
		}
	}
	
	
	
	//删除相对路径的文件或者目录
	protected boolean deleteFile(String relativeRootPath){
		File file=new File(AppConfig.RootPath+"/"+relativeRootPath);
		return FileUtil.delete(file);
	}
	
	//在指定目录（绝对路径）下以新的文件名保存文件
	protected boolean save(String foldAbsolutePath, String newName, MultipartFile mFile) throws Exception {
		try {
			if ((mFile != null) && (mFile.getInputStream() != null)) {
				FileUtil.saveFile(foldAbsolutePath, newName, mFile.getName(), mFile.getInputStream());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return false;
	}

	//把多个pojo对象添加到json中
	jsonResult createMessageJsonResult(List<P> pojos, String msg) {
		jsonResult json;
		if (pojos != null && pojos.size() > 0) {
			json = new jsonResult(msg + "成功");
			json.add(pojos);
		} else {
			json = new jsonResult(false, msg + "失败");
		}
		return json;

	}
    //添加一个pojo对象到json中
	jsonResult createMessageJsonResult(P pojo, String msg) {
		jsonResult json;
		if (pojo != null) {
			json = new jsonResult(msg + "成功");
			json.getDatas().add(pojo);
		} else {
			json = new jsonResult(false, msg + "失败");
		}
		return json;

	}

	
	


	
	
	
	public S getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(S serviceDao) {
		this.serviceDao = serviceDao;
	}



}
