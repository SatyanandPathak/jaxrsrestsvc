package com.satya.restful.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.satya.restful.messenger.models.ErrorMessage;


// This is the Generic Exception Mapper by default as the type is Throwable. 
// Comment this to handle specific exception


//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		
		ErrorMessage message = new ErrorMessage(exception.getMessage(), 500, "http://www.google.com");
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}
	

}
