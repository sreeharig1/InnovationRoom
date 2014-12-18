package com.avilyne.rest.resource;
 
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.avilyne.rest.model.DBAccess;
import com.avilyne.rest.model.DrugComposition;
import com.avilyne.rest.model.DrugCompound;
/*
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Request;
*/

@Path("/drugservice")
public class WebserviceCall {
      
    // The @Context annotation allows us to have certain contextual objectsinjected into this class.UriInfo object allows us to get URI information (no kidding).
    //@Context
    //UriInfo uriInfo;
 
    // Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
    //@Context
    //Request request;
     
    // Basic "is the service running" test
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() throws ClassNotFoundException, SQLException {
    	//DBAccess.selectDrugs();
        return "Demo";
    }
    
    @GET
    @Path("/druganalysis")
    @Produces(MediaType.APPLICATION_JSON)
    public String drugInfo(@QueryParam("drugname") String drugname) throws ClassNotFoundException, SQLException, JSONException, IOException
    {
    	JSONObject jsonObj = new JSONObject(drugname);
    	DrugComposition drug = new DrugComposition();
    	drug.name = jsonObj.getString("Drug");
    	
    	for(int num=1; num<jsonObj.length(); num++)
    	{
    		DrugCompound dc = new DrugCompound();
    		JSONObject obj = jsonObj.getJSONObject("Compound"+num);
    		dc.name=obj.getString("CompName");
    		dc.weightage=obj.getInt("Weightage");
    		drug.compounds.add(dc);
    	}
    	
    	return DBAccess.analyzeDrug(drug);
    	
    	/*DrugCompound dc = new DrugCompound();
		dc.name="Comp1";
		dc.weightage=30;
		
		DrugCompound dc1 = new DrugCompound();
		dc1.name="Comp2";
		dc1.weightage=5;
		
		DrugCompound dc2 = new DrugCompound();
		dc2.name="Comp3";
		dc2.weightage=45;
		
		DrugCompound dc3 = new DrugCompound();
		dc3.name="Comp3";
		dc3.weightage=50;
		
		DrugCompound dc4 = new DrugCompound();
		dc4.name="Comp3";
		dc4.weightage=50;
		
		DrugComposition d = new DrugComposition();
		d.name = "Med1";
		d.compounds.add(dc);
		d.compounds.add(dc1);
		d.compounds.add(dc2);
		System.out.println(drugname);*/
    }
    
    /*
    @GET
    @Path("select1")
    @Produces(MediaType.TEXT_PLAIN)
    public String drugAnalysis() throws ClassNotFoundException, SQLException {
    	//DBAccess.analyzeDrug();
        return "Demo";
    }
    */
    
}