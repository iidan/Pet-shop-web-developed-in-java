package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Model {
	
	private String tableName;
	
	public Model(String tableName){
		this.tableName = tableName;
	}
	
	protected <T extends DataObject> List<T> cast_list(List<DataObject> list, Class<T> type){
		List<T> ret = new ArrayList<T>();
		for(DataObject obj: list){
			try {
				T t = type.newInstance();
				t.data = obj.data;
				t.table_name = obj.table_name;
				ret.add(t);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return ret;
	}
	
	private List<DataObject> createObjects(ResultSet res){		
		
		ArrayList<DataObject> return_objects = new ArrayList<DataObject>();
		if (res == null)
			return return_objects;
		try {			
			java.sql.ResultSetMetaData meta = res.getMetaData();
			int column_count = meta.getColumnCount(); 
			
			while (res.next()){
				DataObject obj = new DataObject(meta.getTableName(1));
				for (int i = 1; i <= column_count; i++){					
					String column_data = res.getString(i);
					String column_name = meta.getColumnName(i);
					obj.add_data(column_name, column_data);
				}
				return_objects.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return return_objects;
	}
	
	public List<DataObject> getByColumnAndOp(String columnName, String value, String operator){
		Connection con = dbCon.getcon();
		String sql = "Select * from "+this.tableName+" where `"+columnName+"` "+operator+" '"+value+"'";
		Statement st = null;
		try {
			
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.createObjects(rs);
	}
	
	public List<DataObject> getAllRows(){
		Connection con = dbCon.getcon();
		String sql = "Select * from "+this.tableName;
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.createObjects(rs);
	}
	
}
