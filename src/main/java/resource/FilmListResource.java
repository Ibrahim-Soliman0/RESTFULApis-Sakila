package resource;

import dto.FilmListDto;
import entity.FilmList;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.FilmListMapper;
import service.FilmListService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("film-lists")
public class FilmListResource {

    private final FilmListService filmListService = new FilmListService();
    private final FilmListMapper filmListMapper = FilmListMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllFilmLists(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<FilmList> filmLists = filmListService.getAll(page, size);
        List<FilmListDto> filmListDtos = filmLists.stream()
                .map(filmListMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(filmListDtos) {
        }).build();
    }

    @GET
    @Path("/{fid}")
    @Produces({ "application/xml", "application/json" })
    public Response getFilmListById(@PathParam("fid") Integer fid) {
        Optional<FilmList> filmList = filmListService.getById(fid);
        if (filmList.isPresent()) {
            FilmListDto filmListDto = filmListMapper.toDto(filmList.get());
            return Response.ok(filmListDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createFilmList(FilmListDto filmListDto) {
        // FilmList is immutable, creation not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @PUT
    @Path("/{fid}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateFilmList(@PathParam("fid") Integer fid, FilmListDto filmListDto) {
        // FilmList is immutable, update not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("/{fid}")
    public Response deleteFilmList(@PathParam("fid") Integer fid) {
        // FilmList is immutable, delete not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}