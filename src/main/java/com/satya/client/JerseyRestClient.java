package com.satya.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.satya.restful.messenger.models.Message;
import com.sun.media.jfxmedia.Media;

public class JerseyRestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		Response response = client.target("http://localhost:8080/restmessengersvc/webapi/messages/1")
		.request(MediaType.APPLICATION_JSON).get();
		
		// or
		
		Message responseMessage = client.target("http://localhost:8080/restmessengersvc/webapi/messages/1")
				.request(MediaType.APPLICATION_JSON).get(Message.class);
		
		
		// or
		

		String responseString = client.target("http://localhost:8080/restmessengersvc/webapi/messages/1")
				.request(MediaType.APPLICATION_JSON).get(String.class);
		
		System.out.println("Message directly=="+responseMessage);
		System.out.println("String response=="+responseString);
		
		Message message = response.readEntity(Message.class);
		System.out.println("Message is=="+message);
		
		System.out.println(message.getLinks());
		
		
		Response response1 = client.target("http://localhost:8080/restmessengersvc/webapi/messages/")
				.request().get();
		
		List<Message> messages = response1.readEntity(List.class);
		
		System.out.println("messages=="+messages);
		
	}

}
