package resource;

import dto.NicerButSlowerFilmListDto;
import entity.NicerButSlowerFilmList;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.NicerButSlowerFilmListMapper;
import service.NicerButSlowerFilmListService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("nicer-but-slower-film-lists")
public class NicerButSlowerFilmListResource {

    private final NicerButSlowerFilmListService nicerButSlowerFilmListService = new NicerButSlowerFilmListService();
    private final NicerButSlowerFilmListMapper nicerButSlowerFilmListMapper = NicerButSlowerFilmListMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllNicerButSlowerFilmLists(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<NicerButSlowerFilmList> nicerButSlowerFilmLists = nicerButSlowerFilmListService.getAll(page, size);
        List<NicerButSlowerFilmListDto> nicerButSlowerFilmListDtos = nicerButSlowerFilmLists.stream()
                .map(nicerButSlowerFilmListMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(nicerButSlowerFilmListDtos) {
        }).build();
    }

    @GET
    @Path("/{fid}")
    @Produces({ "application/xml", "application/json" })
    public Response getNicerButSlowerFilmListById(@PathParam("fid") Integer fid) {
        Optional<NicerButSlowerFilmList> nicerButSlowerFilmList = nicerButSlowerFilmListService.getById(fid);
        if (nicerButSlowerFilmList.isPresent()) {
            NicerButSlowerFilmListDto nicerButSlowerFilmListDto = nicerButSlowerFilmListMapper
                    .toDto(nicerButSlowerFilmList.get());
            return Response.ok(nicerButSlowerFilmListDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createNicerButSlowerFilmList(NicerButSlowerFilmListDto nicerButSlowerFilmListDto) {
        // NicerButSlowerFilmList is immutable, creation not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @PUT
    @Path("/{fid}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateNicerButSlowerFilmList(@PathParam("fid") Integer fid,
            NicerButSlowerFilmListDto nicerButSlowerFilmListDto) {
        // NicerButSlowerFilmList is immutable, update not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("/{fid}")
    public Response deleteNicerButSlowerFilmList(@PathParam("fid") Integer fid) {
        // NicerButSlowerFilmList is immutable, delete not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}