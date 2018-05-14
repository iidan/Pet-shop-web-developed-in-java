package classes;

import java.util.List;

public class User_factory extends Model{
	
	public User_factory(){
		super("users");		
	}	
	
	public List<User> getAll() {		
		return this.cast_list(getAllRows(), User.class);
	}
	
	public List<User> getByColumn(String columnName, String value) {		
		return this.cast_list(getByColumnAndOp(columnName,value,"="), User.class);
	}
	
	public User getById(int id){
		List<User> users = this.getByColumn("id", Integer.toString(id));
		if (users.isEmpty())
			return null;
		return this.getByColumn("id", Integer.toString(id)).get(0);
	}
}
