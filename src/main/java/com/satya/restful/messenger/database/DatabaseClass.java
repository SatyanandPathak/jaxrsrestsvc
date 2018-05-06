package com.satya.restful.messenger.database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.satya.restful.messenger.models.Message;
import com.satya.restful.messenger.models.Profile;

public class DatabaseClass {
	
	public static Map<Long, Message> messages = new HashMap<>();
	public static Map<String, Profile> profiles = new HashMap<>();
	
	public DatabaseClass(){
		Message m1 = new Message(1L, new Date(), "Hello World", "Satyanand");
		Message m2 = new Message(2L, new Date(), "Hello Jersey", "Sunita");
		Message m3 = new Message(3L, new Date(), "Hello JAX-RS", "Saksham");
		messages.put(m1.getId(), m1);
		messages.put(m2.getId(), m2);
		messages.put(m3.getId(), m3);
		
		
		Profile p1 = new Profile(1, "Satyanand1984", "Satyanand", "Pathak");
		Profile p2 = new Profile(2, "Sunita2985", "Sunita Kumari", "Pathak");
		Profile p3 = new Profile(3, "Saksham2913", "Saksham", "Pathak");
		profiles.put("Satyanand1984", p1);
		profiles.put("Sunita2985", p2);
		profiles.put("Saksham2913", p3);
		
	}
	
	
	public static Map<Long, Message> getMessages(){

		return messages;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}

}
