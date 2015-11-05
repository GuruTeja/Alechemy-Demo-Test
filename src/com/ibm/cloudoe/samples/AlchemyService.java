package com.ibm.cloudoe.samples;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/delete")
public class AlchemyService {
	@Path("{id}")
	public Response getMsg(@PathParam("id") String id) {
		return null;
		
	}
}
