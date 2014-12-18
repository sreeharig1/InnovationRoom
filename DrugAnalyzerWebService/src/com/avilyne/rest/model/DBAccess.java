package com.avilyne.rest.model;

//import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


/*
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
*/

public  class DBAccess {
		
	   //Query based on Medicine name   
	   public static ResultSet selectStat(String name) throws SQLException, ClassNotFoundException {
		   
		  List<String> l = new ArrayList<String>();
	      String s = null;
	       
	      //DB connection
		  Connection conn = DBConnection.PostgreSqlConn(); 
	      
		  //Create a statement
	       Statement st = conn.createStatement();
	      
	      //Interact with the DB
	      String query = "SELECT c.comp_name, mc.min_content, mc.max_content FROM medicinecomposition as mc INNER JOIN medicines as m on m.med_Id = mc.med_Id INNER JOIN compounds as c ON c.comp_id = mc.comp_id WHERE m.med_name = '" + name + "';";
	      
	      //System.out.println(query);
	      ResultSet rs = st.executeQuery(query);
	      
	      //Verify by printing
	      while (rs.next()) {
	         String comp = rs.getString("comp_name");
	         String min = rs.getString("min_content");
	         String max = rs.getString("max_content");
	         s = comp + "		" + min + "		" + max;
	         //System.out.println(s);
	         l.add(s);
	      	}
	       
	         rs.close();
	         st.close();
	         conn.close();
	         
	         return rs;
	   }
	   
	   public static void selectDrugs() throws ClassNotFoundException, SQLException {
		   
		   Connection conn = DBConnection.PostgreSqlConn(); 
		   
		   Statement st = conn.createStatement();
		    
		   //Interact with the DB
		   String query = "SELECT med_name FROM medicines;"; 
		      
		   //System.out.println(query);
		   ResultSet rs = st.executeQuery(query);
		      
		   while (rs.next()) {
			   String med = rs.getString("med_name");
			   System.out.println(med);
		   }
	   }
	   
	   
	   //Drug analysis
	   public static String analyzeDrug(DrugComposition drug) throws ClassNotFoundException, SQLException, JSONException, IOException
	   {
		   //PropFileVar.PropertyFileReading();
		   JSONArray jsonArray = new JSONArray();
		   
		   //List<String> listRecommendation = new ArrayList<String>();
		   //listRecommendation.add(drug.name);
		   
		   //DB connection
		   Connection conn = DBConnection.PostgreSqlConn(); 
		      
		   //Create a statement
		   Statement st = conn.createStatement();
		      
		   //Interact with the DB
		   String query = "SELECT c.comp_name, mc.min_content, mc.max_content FROM medicinecomposition as mc INNER JOIN medicines as m on m.med_Id = mc.med_Id INNER JOIN compounds as c ON c.comp_id = mc.comp_id WHERE m.med_name = '" + drug.name + "';";
		      
		   //System.out.println(query);
		   ResultSet rs = st.executeQuery(query);
		      
		   //Verify by printing
		   while (rs.next()) 
		   {
			   /*String comp = rs.getString("comp_name");
			   String min = rs.getString("min_content");
			   String max = rs.getString("max_content");
			   String s = comp + "		" + min + "		" + max;
			   System.out.println(s);*/
		       for(int num=0; num<drug.compounds.size(); num++)//drug.compounds.size()
		       	{
		    	   if(drug.compounds.get(num).name.equals(rs.getString("comp_name")))
		        	 {
		    		   if((drug.compounds.get(num).weightage < rs.getInt("min_content")) || (drug.compounds.get(num).weightage > rs.getInt("max_content")))
		    		   {
		    			   jsonArray.put(drug.compounds.get(num).name + " - Harmful");
		    		   }
		    		   else
		    		   {
		    			   jsonArray.put(drug.compounds.get(num).name + " - Harmless");
		    		   }
		        	 }
		        }
		         
		    }
		    
		    JSONObject jsonObj = new JSONObject();
		    /*for(int i = 0; i < listRecommendation.size(); i++)
		    	jsonObj.append("S"+i, listRecommendation.get(i));
		    System.out.println(jsonObj.length());*/
		    jsonObj.put("Drug", drug.name);
		    
		    //for(int i = 0; i < listRecommendation.size(); i++)
		    	//jsonArray.put(listRecommendation.get(i).toString());
		    jsonObj.put("Messages", jsonArray);
		    
		    //String jsonRecommendation = jsonObj.toString();
		    //System.out.println(jsonObj.get("Drug") + "		" + jsonObj.get("Messages"));
		    
		    
		    rs.close();
		    st.close();
		    conn.close();
		    
			return jsonObj.toString();
		} 
}

