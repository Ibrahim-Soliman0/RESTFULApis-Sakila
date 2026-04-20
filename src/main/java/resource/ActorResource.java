package resource;

import dto.ActorDto;
import entity.Actor;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import service.ActorService;

import java.util.List;
import java.util.Optional;

@Path("actors")
public class ActorResource {

    private final ActorService actorService = new ActorService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllActors(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<ActorDto> actorDtos = actorService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(actorDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getActorById(@PathParam("id") Integer id) {
        Optional<ActorDto> actorDto = actorService.getByIdDto(id);
        if (actorDto.isPresent()) {
            return Response.ok(actorDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createActor(ActorDto actorDto) {
        ActorDto savedActorDto = actorService.saveDto(actorDto);
        return Response.status(Response.Status.CREATED).entity(savedActorDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateActor(@PathParam("id") Integer id, ActorDto actorDto) {
        Optional<ActorDto> updatedDto = actorService.updateDto(id, actorDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteActor(@PathParam("id") Integer id) {
        Optional<Actor> actor = actorService.getById(id);
        if (actor.isPresent()) {
            actorService.delete(actor.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
