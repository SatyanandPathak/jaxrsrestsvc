package com.satya.restful.messenger;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@Path("annotations")
	@GET
	public String getParamsUsingAnnotations(@MatrixParam(value="matrixParam") String matrixParam,
																					@HeaderParam(value="tokenId") String token,
																					@CookieParam(value="myCookie") String myCookie){
		
		/*Matrix Param applies to the resource. But Query Param applies to the whole URL.
		 * So in case of Matrix Param we use like database/accounts:type=DEDICATED&lob=US
		 * 
		 * But in case of Query Param the pattern is like 
		 * /database/accounts?account_type=DEDICATED&account_lob=US
		 
		 */
		
		
		
		return String.format("MatrixParam: %s  HeaderParam:%s   CookieParam: %s", matrixParam, token, myCookie);
		
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		
		
		String values = String.format("Absolute Path:%s  /n HTTP Headers: %s", uriInfo.getAbsolutePath(),
		    headers.getRequestHeader("tokenId"));
		
		
		return values;
	}
	
}
