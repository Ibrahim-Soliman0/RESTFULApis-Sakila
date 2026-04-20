package resource;

import dto.ActorInfoDto;
import entity.ActorInfo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.ActorInfoMapper;
import service.ActorInfoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("actor-infos")
public class ActorInfoResource {

    private final ActorInfoService actorInfoService = new ActorInfoService();
    private final ActorInfoMapper actorInfoMapper = ActorInfoMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllActorInfos(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<ActorInfo> actorInfos = actorInfoService.getAll(page, size);
        List<ActorInfoDto> actorInfoDtos = actorInfos.stream()
                .map(actorInfoMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(actorInfoDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getActorInfoById(@PathParam("id") Integer id) {
        Optional<ActorInfo> actorInfo = actorInfoService.getById(id);
        if (actorInfo.isPresent()) {
            ActorInfoDto actorInfoDto = actorInfoMapper.toDto(actorInfo.get());
            return Response.ok(actorInfoDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createActorInfo(ActorInfoDto actorInfoDto) {
        // ActorInfo is immutable, creation not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateActorInfo(@PathParam("id") Integer id, ActorInfoDto actorInfoDto) {
        // ActorInfo is immutable, update not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteActorInfo(@PathParam("id") Integer id) {
        // ActorInfo is immutable, delete not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}