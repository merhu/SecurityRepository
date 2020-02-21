package com.object;

import org.json.JSONObject;

public class CustomerFactory {
	
	public static Customer getCustomer(String json_string) throws Exception{
		
        // parsing file "JSONExample.json" 
        JSONObject jobj = new org.json.JSONObject(json_string); 
          
        // getting firstName and lastName 
        int id = (int) jobj.get("id");
        String name = (String) jobj.get("name"); 
        String email = (String) jobj.get("email"); 
        String password = (String) jobj.get("password"); 
		
		// create customer object
		Customer cust = new Customer();
		cust.setName(name);
		cust.setId(id);
		cust.setEmail(email);
		cust.setPass(password);
		return cust;
	}
	
	public static String getCustomerAsJSONString(Customer customer) throws Exception {
        JSONObject jo = new JSONObject(); 
        
        // putting data to JSONObject 
        jo.put("name", customer.getName()); 
        jo.put("email", customer.getEmail());
        jo.put("password", customer.getPass());
        jo.put("id", customer.getId());
        
        String out = jo.toString();
        return out;
	}

}