package com.satya.client;

import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.satya.restful.messenger.models.Message;

public class ElegantJerseyRestClient {
	
	public static void main(String args[]){
		
		Client client = ClientBuilder.newClient();

		WebTarget baseURI = client.target("http://localhost:8080/restmessengersvc/webapi");
		WebTarget messagesURI = baseURI.path("messages");
		WebTarget singleMessageTarget = messagesURI.path("{messageId}");
		
		
		getMessage("1", singleMessageTarget);
		postMessage(messagesURI);
		
		
		
		
	}

	private static void getMessage(String messageId, WebTarget target) {
		Client client = ClientBuilder.newClient();

//		WebTarget baseURI = client.target("http://localhost:8080/restmessengersvc/webapi");
//		WebTarget messagesURI = baseURI.path("messages");
//		WebTarget singleMessageTarget = messagesURI.path("{messageId}");

		Message message = target
				.resolveTemplate("messageId", messageId)
				.request(MediaType.APPLICATION_JSON)
		    .get(Message.class);

		System.out.println("message is==" + message);

	}
	
	
	private static void postMessage(WebTarget messageTarget){
		
		Calendar cal = Calendar.getInstance();
		Message message = new Message(5, cal.getTime() , "Create by Jersey Rest Java client", "Satyanand Pathak client addedd");
		
		Response createdMessage = messageTarget
		.request(MediaType.APPLICATION_JSON)
		.post(Entity.json(message));
		
		System.out.println("new message=="+createdMessage.readEntity(Message.class));
	
	}

}
