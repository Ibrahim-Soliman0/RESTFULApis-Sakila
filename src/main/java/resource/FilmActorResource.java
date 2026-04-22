package resource;

import dto.FilmActorDto;
import entity.FilmActor;
import entity.FilmActorId;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.FilmActorMapper;
import service.FilmActorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("film-actors")
public class FilmActorResource {

    private final FilmActorService filmActorService = new FilmActorService();
    private final FilmActorMapper filmActorMapper = FilmActorMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllFilmActors(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<FilmActor> filmActors = filmActorService.getAll(page, size);
        List<FilmActorDto> filmActorDtos = filmActors.stream()
                .map(filmActorMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(filmActorDtos) {
        }).build();
    }

    @GET
    @Path("/{actorId}/{filmId}")
    @Produces({ "application/xml", "application/json" })
    public Response getFilmActorById(@PathParam("actorId") Integer actorId, @PathParam("filmId") Integer filmId) {
        FilmActorId id = new FilmActorId();
        id.setActorId(actorId);
        id.setFilmId(filmId);
        Optional<FilmActor> filmActor = filmActorService.getById(id);
        if (filmActor.isPresent()) {
            FilmActorDto filmActorDto = filmActorMapper.toDto(filmActor.get());
            return Response.ok(filmActorDto).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createFilmActor(FilmActorDto filmActorDto) {
        FilmActor filmActor = filmActorMapper.toEntity(filmActorDto);
        FilmActor savedFilmActor = filmActorService.save(filmActor);
        FilmActorDto savedDto = filmActorMapper.toDto(savedFilmActor);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @DELETE
    @Path("/{actorId}/{filmId}")
    public Response deleteFilmActor(@PathParam("actorId") Integer actorId, @PathParam("filmId") Integer filmId) {
        FilmActorId id = new FilmActorId();
        id.setActorId(actorId);
        id.setFilmId(filmId);
        Optional<FilmActor> filmActor = filmActorService.getById(id);
        if (filmActor.isPresent()) {
            filmActorService.delete(filmActor.get());
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}