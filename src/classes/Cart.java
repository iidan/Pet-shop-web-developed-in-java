package classes;

import java.util.HashMap;
import java.util.Map;

public class Cart { 
	public Map<Product, Integer> products = new HashMap<Product, Integer>();
	public float total_price = 0;
	
	public Cart(){
	/*
	public Cart(Map<Integer,Integer> data){		
		if (data != null){
			for (Map.Entry<Integer, Integer> entry : data.entrySet()){
			    Product prod = prod_factory.getById(entry.getKey());
			    if (prod != null){
			    	products.put(prod, entry.getValue());
			    }
			}
		}
		calculate_cart_price();
		*/
	}
	
	public void calculate_cart_price() {
		total_price = 0;
		if (products != null){
			for (Map.Entry<Product, Integer> entry : products.entrySet()){
			    Product prod = entry.getKey();
				String price = prod.get_data("price");
			     if (price != null)
			    	 total_price += (Float.parseFloat(price) * entry.getValue());
			}
		}
	}
	
	public void update_quantity(int prod_id, int quantity){		
		for (Map.Entry<Product, Integer> entry : products.entrySet()){
			Product prod = entry.getKey();
			Integer id = Integer.parseInt(prod.get_data("id"));
		    if (id == prod_id){
		    	if (quantity == 0){
		    		products.remove(entry.getKey());
		    	}
		    	else{
		    		entry.setValue(quantity);
		    	}
		    	break;		    	
		    }
		}
		calculate_cart_price();
	}
	
	public void add_to_cart(int prod_id, int quantity){
		Product_factory prod_factory = new Product_factory();
		Product prod = prod_factory.getById(prod_id);
	    if (prod != null){
	    	products.put(prod, quantity);
	    }
	    calculate_cart_price();
	}
	
	public void delete_from_cart(int prod_id){
		update_quantity(prod_id, 0);
	}
	
	public int get_cart_count(){
		return products.size();
	}
}
