package com.satya.restful.messenger;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.satya.restful.messenger.models.Profile;
import com.satya.restful.messenger.service.ProfileService;

@Path("/profiles")
public class ProfilesResource {
	
	ProfileService svc = new ProfileService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles(){
		
		return svc.getFrofiles();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{profileName}")
	public Profile getProfile(@PathParam(value="profileName") String profileName){
		return svc.getProfile(profileName);
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile profile){
		return svc.addProfile(profile);
	}
	
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam(value="profileName") String profileName, Profile profile){
		
		profile.setProfileName(profileName);
		return svc.updateProfile(profile);
		
	}
	
	@DELETE
	@Path("{profileName}")
	public void deleteProfile(String profileName){
		svc.removeProfile(profileName);
		
	}
	
	

}
