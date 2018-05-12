package com.satya.client.invocation;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.satya.restful.messenger.models.Message;


public class InvocationDemo {

	public static void main(String[] args) {
		InvocationDemo demo = new InvocationDemo();
		Invocation invocation = demo.prepareRequestForMessageByYear(2018);
		List<Message> messages = invocation.invoke(new GenericType<List<Message>>(){});
		System.out.println("response messes ****==="+messages);

	}

	private Invocation prepareRequestForMessageByYear(long year) {
		Client client = ClientBuilder.newClient();
		return client
		.target("http://localhost:8080/restmessengersvc/webapi")
		.path("messages")
		.queryParam("year", year)
		.request(MediaType.APPLICATION_JSON)
		.buildGet();
		
	}

}
