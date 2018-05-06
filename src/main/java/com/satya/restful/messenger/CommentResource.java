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

import com.satya.restful.messenger.models.Comment;
import com.satya.restful.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	CommentService commentSvc = new CommentService();
	
	@GET
	public List<Comment> getComments(@PathParam("messageId") long messageId){
		return commentSvc.getComments(messageId);
	}
	
	
	@Path("{commentId}")
	@GET
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") int commentId){
		
		return commentSvc.getComment(messageId, commentId);
		
	}
	
	@POST
	public Comment adddComment(@PathParam("messageId") long messageId, Comment comment){
		
		
		return commentSvc.addComment(messageId, comment);
		
	}
	
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment){
		return commentSvc.updateComment(messageId, comment);
	}
	
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
		commentSvc.deleteComment(messageId, commentId);
	}

}
