package com.satya.restful.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.satya.restful.messenger.database.DatabaseClass;
import com.satya.restful.messenger.models.Comment;
import com.satya.restful.messenger.models.ErrorMessage;
import com.satya.restful.messenger.models.Message;

public class CommentService {
	
	Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getComments(long messageId){
		
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
				
		
		// Not good implementation as the error content rendering is in business class
		Response errorResponse = errorResponseBuilder(404, "****Not Found*****", Status.NOT_FOUND);
		
		
		Message message = messages.get(messageId);
		if (message == null){
			throw new NotFoundException(errorResponse);
		}
		
		Map<Long, Comment> comments = message.getComments();
		Comment comment= comments.get(commentId);
		
		if (comment == null){
			throw new WebApplicationException(errorResponse);
		}
		
		return comments.get(commentId);
	}
	
	public Comment updateComment(long id, Comment comment){
		Map<Long, Comment> comments = messages.get(id).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		
		return comment;
	}
	
	public Comment addComment(long id, Comment comment){
		
		Map<Long, Comment> comments = messages.get(id).getComments();
		
		if (comment.getId() <= 0){
			return null;
		}
		comments.put(comment.getId(), comment);
		
		return comment;
	}
	
	public void deleteComment(long id, long commentId){
		Map<Long, Comment> comments = messages.get(id).getComments();
		comments.remove(commentId);
		
	}
	
	private Response errorResponseBuilder(int errorCode, String errorMessage, Status statusCode){
		ErrorMessage message = new ErrorMessage(errorMessage, errorCode, "http://www.google.com");
		
		return Response.status(statusCode).entity(message).build();
	}
	
	
	
	

}
