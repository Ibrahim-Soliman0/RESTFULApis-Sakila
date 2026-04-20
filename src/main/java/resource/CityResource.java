package resource;

import dto.CityDto;
import entity.City;
import entity.Country;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import service.CityService;
import service.CountryService;

import java.util.List;
import java.util.Optional;

@Path("cities")
public class CityResource {

    private final CityService cityService = new CityService();
    private final CountryService countryService = new CountryService();

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllCities(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<CityDto> cityDtos = cityService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(cityDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getCityById(@PathParam("id") Integer id) {
        Optional<CityDto> cityDto = cityService.getByIdDto(id);
        if (cityDto.isPresent()) {
            return Response.ok(cityDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createCity(CityDto cityDto) {
        Optional<Country> country = countryService.getById(cityDto.getCountryId());
        if (country.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid countryId").build();
        }
        CityDto savedCityDto = cityService.saveDto(cityDto);
        return Response.status(Response.Status.CREATED).entity(savedCityDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateCity(@PathParam("id") Integer id, CityDto cityDto) {
        Optional<Country> country = countryService.getById(cityDto.getCountryId());
        if (country.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid countryId").build();
        }
        Optional<CityDto> updatedDto = cityService.updateDto(id, cityDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCity(@PathParam("id") Integer id) {
        Optional<City> city = cityService.getById(id);
        if (city.isPresent()) {
            cityService.delete(city.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}