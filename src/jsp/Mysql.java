package jsp;
import java.sql.*;

import classes.dbCon;

public class Mysql {
	public static Connection connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection("jdbc:mysql://localhost/shop","toli","1234");
		}
		catch (Exception e){return null;}
		
	}
	
	public static boolean close(Connection c){
		try {
			c.close();
			return true;
		}catch (Exception e) {return false;}
		
	}
	
	public static ResultSet getCategoriesBySubCategory(int subCategory){
		Connection con = Mysql.connect();
		Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = ("SELECT * FROM category where parentCatId="+subCategory+";");
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
}
