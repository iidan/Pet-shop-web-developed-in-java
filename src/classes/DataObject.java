package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jsp.Mysql;

public class DataObject {
	Map<String, String> data = new HashMap<String, String>();
	String table_name;
	
	public DataObject(String table_name){
		this.table_name = table_name;
	}
	
	public void add_data(String name, String value){
		this.data.put(name, value);
	}
	
	public String get_data(String name){
		return this.data.get(name);
	}
	
	public void update(){
		// update db
		String sql = "Update " + this.table_name + " set ";
		for ( Entry<String, String> entry : data.entrySet() ){
			String key = entry.getKey();
			String value = entry.getValue();
			
			sql += key + " = " + value + ",";
		}
		dbCon.query(sql);
	}
	
	public void delete(){
		// delete from db
		String sql = "Delete from" + this.table_name + " where id = " + this.data.get("id");
		dbCon.query(sql);
	} 
	
	public void insert(){
		// insert to db
		String sql = "INSERT INTO " + this.table_name + " ";
		String values = "";
		String keys = "";
		for ( Entry<String, String> entry : data.entrySet() ){
			keys += "`" + entry.getKey() + "`,";
			values += "'" + entry.getValue() + "',";
		}
		keys = keys.replaceAll(",$", "");
		values = values.replaceAll(",$", "");
		sql += "(" + keys + ")" + " VALUES " + "(" + values + ");";
		dbCon.query(sql);
	}
}