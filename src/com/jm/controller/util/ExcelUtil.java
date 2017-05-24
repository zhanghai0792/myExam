package com.jm.controller.util;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.springframework.stereotype.Component;

import com.jm.util.StringUtil;





@Component
public class ExcelUtil {

	  public  List<Map> analysisExcel(InputStream file,String[] excelTemp) throws Exception{
			HashMap<String, String> map=null;
			Workbook workbook=null;
			List<Map> datas=new ArrayList<Map>(0);
		 try{   workbook = WorkbookFactory.create(file);// ---统一实现
		 		Sheet sheet = workbook.getSheetAt(0);// 获得第一个工作簿对象
		 		Row row = null;
		 	
		 	
		 		Row rowField1 = sheet.getRow(0);
		               if(!excelTemp[excelTemp.length-1].equals(getValue(rowField1.getCell(0)))){
		            		System.out.println("导入excel的模板与数据不匹配");   
		            	   return null;
		            		   }
		              // System.out.println("开始读取excel的数据");  
		 		Row rowField = sheet.getRow(2);// 获得第二行
		 		for (int i = 2; i <= sheet.getLastRowNum(); i++) {// 循环单元格
		 			row = sheet.getRow(i);
		 			int k = 0;
		 			if (row != null) {
		 				map = new HashMap<String, String>(0);
		 				for (int j = 0; j < rowField.getLastCellNum(); j++) {// 循环列
		 					if (rowField.getCell(j) != null) {// 此列的字段值不为空
		 						String val = getValue(row.getCell(j));
		 						if (val == null || "".equals(val))
		 							k++;
		 						map.put(excelTemp[j], val);
		 					}

		 				}
		 			}
		 		   datas.add(map);
		 		}
		 		}catch(Exception e){
		 			throw e;}
		  finally{
		 			if(workbook!=null)
		 		        workbook.close();
		 		file.close();
		 		}
		 		return datas; 
		   
		   
	   }
	  
	  
	   
	   public String getValue(Cell cell) {
			String value = "";
			if (null == cell) {
				return null;
			}
			switch (cell.getCellType()) {
			// 数值型
			case Cell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是date类型则 ，获取该cell的date值
					Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					value = StringUtil.DateToString(date);
				} else {// 纯数字
					BigDecimal big = new BigDecimal(cell.getNumericCellValue());
					value = big.toString();
					if (null != value && !"".equals(value.trim())) {
						String[] item = value.split("[.]");
						if (1 < item.length && item[1].trim().length() > 3) {// 如果小数点后面大于三位，则保留三位小数
							value = item[0] + "." + item[1].trim().substring(0, 3);
						}
					}
				}
				break;
			// 字符串类型
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue().toString();
				break;
			// 公式类型
			case Cell.CELL_TYPE_FORMULA:
				// 读公式计算值
				try {
					value = String.valueOf(cell.getNumericCellValue());
				} catch (Exception e) {
					// e.printStackTrace();
					value = cell.getStringCellValue().toString();
				}

				if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
					value = cell.getStringCellValue().toString();
				}
				break;
			// 布尔类型
			case Cell.CELL_TYPE_BOOLEAN:
				value = " " + cell.getBooleanCellValue();
				break;
			// 空值
			case Cell.CELL_TYPE_BLANK:
				value = null;
				// LogUtil.getLogger().error("excel出现空值");
				break;
			// 故障
			case Cell.CELL_TYPE_ERROR:
				value = null;
				// LogUtil.getLogger().error("excel出现故障");
				break;
			default:
				value = cell.getStringCellValue().toString();
			}
			if (value != null && "null".endsWith(value.trim())) {
				value = null;
			}
			return value;
		} 
	
}
