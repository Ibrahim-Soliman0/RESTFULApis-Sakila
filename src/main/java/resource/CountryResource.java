package resource;

import dto.CountryDto;
import entity.Country;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import service.CountryService;

import java.util.List;
import java.util.Optional;

@Path("countries")
public class CountryResource {

    private final CountryService countryService = new CountryService();

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllCountries(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<CountryDto> countryDtos = countryService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(countryDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getCountryById(@PathParam("id") Integer id) {
        Optional<CountryDto> countryDto = countryService.getByIdDto(id);
        if (countryDto.isPresent()) {
            return Response.ok(countryDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createCountry(CountryDto countryDto) {
        CountryDto savedDto = countryService.saveDto(countryDto);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateCountry(@PathParam("id") Integer id, CountryDto countryDto) {
        Optional<CountryDto> updatedDto = countryService.updateDto(id, countryDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCountry(@PathParam("id") Integer id) {
        Optional<Country> country = countryService.getById(id);
        if (country.isPresent()) {
            countryService.delete(country.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}