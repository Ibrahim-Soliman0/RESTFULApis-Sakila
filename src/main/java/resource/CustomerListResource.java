package resource;

import dto.CustomerListDto;
import entity.CustomerList;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import mapper.CustomerListMapper;
import service.CustomerListService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("customer-lists")
public class CustomerListResource {

    private final CustomerListService customerListService = new CustomerListService();
    private final CustomerListMapper customerListMapper = CustomerListMapper.INSTANCE;

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllCustomerLists(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<CustomerList> customerLists = customerListService.getAll(page, size);
        List<CustomerListDto> customerListDtos = customerLists.stream()
                .map(customerListMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(new GenericEntity<>(customerListDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getCustomerListById(@PathParam("id") Integer id) {
        Optional<CustomerList> customerList = customerListService.getById(id);
        if (customerList.isPresent()) {
            CustomerListDto customerListDto = customerListMapper.toDto(customerList.get());
            return Response.ok(customerListDto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createCustomerList(CustomerListDto customerListDto) {
        // CustomerList is immutable, creation not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updateCustomerList(@PathParam("id") Integer id, CustomerListDto customerListDto) {
        // CustomerList is immutable, update not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomerList(@PathParam("id") Integer id) {
        // CustomerList is immutable, delete not supported
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}