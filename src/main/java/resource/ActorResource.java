package resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

@Path("actors")
public class ActorResource {

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces({"application/xml", "application/json"})
    public Response getAllOrders() {
        return Response.ok().entity("Working just fine!").build();
    }
}
