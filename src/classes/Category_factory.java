package classes;

import java.util.List;

public class Category_factory extends Model{
	
	public Category_factory(){
		super("category");		
	}	
	
	public List<Category> getAll() {		
		return this.cast_list(getAllRows(), Category.class);
	}
	
	public List<Category> getByColumn(String columnName, String value) {		
		return this.cast_list(getByColumnAndOp(columnName,value,"="), Category.class);
	}
	
	public Category getById(int id){
		List<Category> category = this.getByColumn("id", Integer.toString(id));
		if (category.isEmpty())
			return null;
		return this.getByColumn("id", Integer.toString(id)).get(0);
	}
}
