package com.satya.restful.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.satya.restful.messenger.database.DatabaseClass;
import com.satya.restful.messenger.models.Profile;

public class ProfileService {
	
	DatabaseClass bd = new DatabaseClass();
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public List<Profile> getFrofiles(){
		return new ArrayList<Profile>(profiles.values());
		
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
		
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
		
	}

}
