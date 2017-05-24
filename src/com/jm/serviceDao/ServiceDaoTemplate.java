package com.jm.serviceDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jm.dao.DaoTemplate;
import com.jm.pojo.BasicModel;
import com.jm.query.QueryParams;
import com.jm.util.ListUtil;



public abstract class ServiceDaoTemplate<P extends BasicModel,DAO extends DaoTemplate>{
	@Autowired
	DAO dao;

	public int insert(P pojo) throws Exception {
	 return	dao.save(pojo);
	}

	public int inserts(List<P> pojos) throws Exception {
		 int size=0;
		 for(int i=0;i<pojos.size();i++)
		    size=size+dao.save(pojos.get(i));
		 return size;
		}
	
	public int deleteById(Integer id) throws Exception {
	 return	dao.deleteById(id);
	}
	public int deleteObject(P p) throws Exception {
		return deleteById(p.getId());
		}
   

	public P getBasicById(Integer id) throws Exception {
	 return (P) dao.getBasicById(id);
	}

	
	
	public  int updateAllField(P record)throws Exception{
		 if(record.getId()==null)
			 throw new Exception("对象的id为null");
		return dao.updateAll(record);
	};

	public int updateNotNullField(P record) throws Exception {
		 if(record.getId()==null)
			 throw new Exception("对象的id为null");
		return dao.updateNoNull(record);
	}
	 //更新集合中的所有对象的所有字段含null值字段
	public  int updatesHasNull(List<P> pojos)throws Exception{
        int count=0;
        if(ListUtil.isNotEmpty(pojos)){
        	for(int i=0;i<pojos.size();i++){
        		 if(pojos.get(i).getId()==null)
        			 throw new Exception("对象的id为null");
        		count=count+dao.updateAll(pojos.get(i));
        		}
        }
        return count;
	};

	//更新集合中的所有对象的非null字段
	public  int updatesNotNull(List<P> pojos)throws Exception{
		 int count=0;
		 if(ListUtil.isNotEmpty(pojos)){
	        	for(int i=0;i<pojos.size();i++){
	        		 if(pojos.get(i).getId()==null)
	        			 throw new Exception("对象的id为null");
	        		count=count+dao.updateNoNull(pojos.get(i));
	        		}
	        }
	        return count;
	}
	

	public List<P> getBasic(QueryParams queryParam) throws Exception {
		return dao.getBasic(queryParam);
	}

	public List<P> getDetail(QueryParams queryParam) throws Exception {
		return dao.getDetail(queryParam);
	}
	
	public int deleteByIds(List<Integer> ids)throws Exception{
		return dao.deleteByIds(ids);
	}
	
	public int deleteObjects(List<P> objs)throws Exception{
		return dao.deleteObjects(objs);
	}
}
