package classes;

import java.util.List;

public class Product_factory extends Model{
	
	public Product_factory(){
		super("products");		
	}	
	
	public List<Product> getAll() {		
		return this.cast_list(getAllRows(), Product.class);
	}
	
	public List<Product> getByColumn(String columnName, String value) {		
		return this.cast_list(getByColumnAndOp(columnName,value,"="), Product.class);
	}
	
	public Product getById(int id){
		List<Product> product = this.getByColumn("id", Integer.toString(id));
		if (product.isEmpty())
			return null;
		return this.getByColumn("id", Integer.toString(id)).get(0);
	}
}
