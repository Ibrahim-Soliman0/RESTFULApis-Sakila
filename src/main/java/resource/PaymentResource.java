package resource;

import dto.PaymentDto;
import entity.Payment;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import service.PaymentService;

import java.util.List;
import java.util.Optional;

@Path("payments")
public class PaymentResource {

    private final PaymentService paymentService = new PaymentService();

    @GET
    @Produces({ "application/xml", "application/json" })
    public Response getAllPayments(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<PaymentDto> paymentDtos = paymentService.getAllDtos(page, size);
        return Response.ok(new GenericEntity<>(paymentDtos) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/xml", "application/json" })
    public Response getPaymentById(@PathParam("id") Integer id) {
        Optional<PaymentDto> paymentDto = paymentService.getByIdDto(id);
        if (paymentDto.isPresent()) {
            return Response.ok(paymentDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response createPayment(PaymentDto paymentDto) {
        PaymentDto savedDto = paymentService.saveDto(paymentDto);
        return Response.status(Response.Status.CREATED).entity(savedDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    public Response updatePayment(@PathParam("id") Integer id, PaymentDto paymentDto) {
        Optional<PaymentDto> updatedDto = paymentService.updateDto(id, paymentDto);
        if (updatedDto.isPresent()) {
            return Response.ok(updatedDto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePayment(@PathParam("id") Integer id) {
        Optional<Payment> payment = paymentService.getById(id);
        if (payment.isPresent()) {
            paymentService.delete(payment.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}