package com.satya.restful.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.satya.restful.messenger.models.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage message = new ErrorMessage(exception.getMessage(), 404, "http://www.google.com");
		
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}
