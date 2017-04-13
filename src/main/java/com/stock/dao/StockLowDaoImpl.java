package com.stock.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Repository;
@SuppressWarnings("deprecation")
@Repository("stocklowdataDao")
public class StockLowDaoImpl implements StockLowDao{

	public List<Map<String, Object>> getAllStockPriceLow(String companyname) throws SQLException {
		SparkSession ssc = SparkSession.builder().master("local").appName("test").enableHiveSupport().getOrCreate();
   	 
			
			  Dataset<Row> sql = ssc.sql("select stock_price_low from kiran.stockmarketdata where stockmarketdata.company=\'"+companyname+"\' limit 10");
			
			  List<Map<String, Object>> stockList = new ArrayList<>();

			 
			  Row[] dataRows = (Row[]) sql.collect();
			 
			  String[] columns = sql.columns();
			 
			    for (Row row : dataRows) {
			    		    		
			    	Map<String, Object> object= new HashMap<String, Object>();
			          
			        	   for(String column : columns)
			        	   {
			        		 
			        		   object.put(column, row.getAs(column));
			        		  
			        
			                 }
			        	   stockList.add(object);
			        	   
			      //     }
			    }
			    
		
			return stockList;
			
				 
			  
		}

		
	}


