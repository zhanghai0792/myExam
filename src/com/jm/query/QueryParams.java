package com.jm.query;
import java.util.List;
public abstract class QueryParams<T extends com.jm.pojo.BasicModel>{
protected Integer page;
protected Integer pageSize;
protected Integer recordIndex;
protected String orderBy;
protected List<T> datas;
protected List<T> pojos;
protected String condition;
public Integer getPage() {
	return page;
}
public Integer getPageSize() {
	return pageSize;
}
public Integer getRecordIndex() {
	return recordIndex;
}
public String getOrderBy() {
	return orderBy;
}
public List<T> getDatas() {
	return datas;
}
public List<T> getPojos() {
	return pojos;
}
public String getCondition() {
	return condition;
}
public void setPage(Integer page) {
	this.page = page;
}
public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}
public void setRecordIndex(Integer recordIndex) {
	this.recordIndex = recordIndex;
}
public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
}
public void setDatas(List<T> datas) {
	this.datas = datas;
}
public void setPojos(List<T> pojos) {
	this.pojos = pojos;
}
public void setCondition(String condition) {
	this.condition = condition;
}


}