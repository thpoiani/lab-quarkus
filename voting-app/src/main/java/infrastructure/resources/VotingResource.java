package infrastructure.resources;

import api.ElectionApi;
import api.dto.out.Election;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/voting")
public class VotingResource {
    private final ElectionApi api;

    public VotingResource(ElectionApi api) {
        this.api = api;
    }

    @GET
    public List<Election> elections() {
        return api.findAll();
    }

    @POST
    @Path("/elections/{electionId}/candidates/{candidateId}")
    @ResponseStatus(RestResponse.StatusCode.ACCEPTED)
    @Transactional
    public void vote(@PathParam("electionId") String electionId, @PathParam("candidateId") String candidateId) {
        api.vote(electionId, candidateId);
    }
}
