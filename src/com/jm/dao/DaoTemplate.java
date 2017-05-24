package com.jm.dao;
import java.util.List;
import com.jm.pojo.BasicModel;
import com.jm.query.QueryParams;
public interface DaoTemplate <T extends BasicModel,Query extends QueryParams>{
int deleteById(Integer id) throws Exception;
    int deleteByIds(List<Integer> ids)throws Exception;
    int deleteObjects(List<T> objs)throws Exception;
    int saveNoNull(T record)throws Exception;
    int save(T record)throws Exception;
 T getBasicById(Integer id)throws Exception;
    T getDetailById(Integer id)throws Exception;
    int updateAll(T record)throws Exception;
    int updateNoNull(T record)throws Exception;
    List<T> getBasic(Query query)throws Exception;
  List<T> getDetail(Query query)throws Exception;
    long count(Query query)throws Exception;
}