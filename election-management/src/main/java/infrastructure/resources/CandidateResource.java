package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.CreateCandidate;
import api.dto.in.UpdateCandidate;
import api.dto.out.Candidate;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/candidates")
public class CandidateResource {
    private final CandidateApi api;

    public CandidateResource(CandidateApi api) {
        this.api = api;
    }

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Transactional
    public void create(CreateCandidate dto) {
        api.create(dto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Candidate update(@PathParam("id") String id, UpdateCandidate dto) {
        return api.update(id, dto);
    }

    @GET
    public List<Candidate> list() {
        return api.list();
    }
}
