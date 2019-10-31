package com.nikthedev;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.nikthedev.model.SettingsPerUser;

/**
 * class implementing the API methods needed for saving the settings
 * @author nchantzopoulos
 *
 */

@Path("/settings")
public class Api {
	
	SettingsService dashboardService = new SettingsService(); 
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createSettings(SettingsPerUser criteria) {
		
		SettingsPerUser in = dashboardService.create(criteria);
		
		return Response.ok(in).build();
		
	}
	
	@POST
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateSettings(SettingsPerUser criteria) {
	
		SettingsPerUser updated = dashboardService.update(criteria);
		
		return Response.ok(updated).build();
	}
	
	@POST
	@Path("/find")
	@Consumes("application/json")
	@Produces("application/json")
	public Response findSettings(SettingsPerUser criteria) {
		List<SettingsPerUser> infos = dashboardService.find(criteria);
		return Response.ok(infos).build(); 
	}
	
	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("application/json")
	public Response delete(SettingsPerUser criteria) {
		
		SettingsPerUser deleted = dashboardService.delete(criteria);
		return Response.ok(deleted).build();
		
	}
	

	
}
