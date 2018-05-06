package com.satya.restful.messenger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.satya.restful.messenger.beans.MessageFilterBean;
import com.satya.restful.messenger.exception.DataNotFoundException;
import com.satya.restful.messenger.models.Message;
import com.satya.restful.messenger.service.MessageService;

/**
 * Root resource (exposed at "messages" path)
 */
@Path("/messages")
@Consumes(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MessageResource {
	
	MessageService svc = new MessageService();

  @GET    
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) throws Exception {
    	
    	if (messageFilterBean.getYear() > 0){
    		return svc.getMessagesForYear(messageFilterBean.getYear());
    	}
    	
    	if (messageFilterBean.getStart() > -1 && messageFilterBean.getSize() > 0){
    		return svc.getMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
    		
    	}
    	return svc.getMessages();
    }
    
    
    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam(value = "messageId") long messageId, @Context UriInfo uriInfo){
    	Message message = svc.getMessage(messageId);
    	
    	if (message == null){
    		throw new DataNotFoundException("****Message not found****");
    	}
    	
    	String url = getUriForSelf(messageId, uriInfo);
    	message.addLink(url, "self");
    	
    	message.addLink(getUriForProfile(message, uriInfo), "profiles");
    	message.addLink(getUriForComment(message, uriInfo), "comments");
    			
    	return message;
    	
    }


		
    
    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException{
    	Message newMessage = svc.addMessage(message);
    	/*return Response.status(Status.CREATED)
    			.entity(newMessage)
    			//.cookie(new Cookie("myCookie", "adding cookie"))
    			.build();*/
    	
//    	String uri = String.format("%s/%d", "/restmessengersvc/webapi/messages", newMessage.getId());
//    	
//    	return Response
//    			.created(new URI(uri))
//    			.entity(newMessage)
//    			.build();
    	
    	
    String newId = String.valueOf(newMessage.getId());
    URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    return Response.created(uri).entity(newMessage).build();
    			
    	
    }
    
    @PUT
    @Path("{messageId}")
    public Response updateMessage(@PathParam(value="messageId") long id, Message message){
    	message.setId(id);
    	return Response.status(Status.ACCEPTED).entity(message).build();
    	
    }
    
    @DELETE
    @Path("{messageId}")
    public Message deleteMessage(@PathParam(value="messageId") long id){
    	return svc.removeMessage(id);
    	
    }
    
    
    
    // Not good to have comments related stuff here though it is a sub resource of message
//    @GET
//    @Path("/{id}/comments")
//    public String getComments(@PathParam("id") String messageId){
//    	
//    	return "test::"+messageId;
//    	
//    }
    
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
    	return new CommentResource();
    }
    
    private String getUriForSelf(long messageId, UriInfo uriInfo) {
			String url = uriInfo.getBaseUriBuilder()
    			.path(MessageResource.class)
    			.path(Long.toString(messageId))
    			.build()
    			.toString();
			return url;
		}
    
    
private String getUriForComment(Message message, UriInfo uriInfo){
    	
    	return uriInfo.getBaseUriBuilder()
    			.path(MessageResource.class)
    			.path(MessageResource.class, "getCommentResource")
    			.resolveTemplate("messageId", message.getId())
    			.path(CommentResource.class)
    			.build()
    			.toString();
    	
     }
    
    private String getUriForProfile(Message message, UriInfo uriInfo){
    	
    	return uriInfo.getAbsolutePathBuilder()
    	.path(ProfilesResource.class)
    	.path(message.getAuthor())
    	.build()
    	.toString();
    	
     }
    

}