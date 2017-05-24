package com.jm.pojo;
import java.io.Serializable;
public class BasicModel implements Serializable{
 protected Integer id;
public Integer getId() {
return id;
}
public void setId(Integer id) {
this.id = id;

}public boolean equals(Object obj) {
 if(this.getClass().getName().equals(obj.getClass().getName())){
return this.hashCode()==obj.hashCode();
}
return false;
}
public int hashCode() {
int basic=this.getClass().hashCode()*1000;
if(id==null){
return -basic;
}else{
return basic+id;
}
}
}