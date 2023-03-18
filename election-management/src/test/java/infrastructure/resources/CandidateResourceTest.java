package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.CreateCandidate;
import api.dto.out.Candidate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestHTTPEndpoint(CandidateResource.class)
class CandidateResourceTest {
    @InjectMock
    CandidateApi api;

    @Test
    void create() {
        var in = Instancio.create(CreateCandidate.class);

        given().contentType(MediaType.APPLICATION_JSON).body(in)
               .when().post()
               .then().statusCode(RestResponse.StatusCode.CREATED);

        verify(api).create(in);
        verifyNoMoreInteractions(api);
    }

    @Test
    void list() {
        var out = Instancio.stream(Candidate.class).limit(4).toList();

        when(api.list()).thenReturn(out);

        var response = given()
                .when().get()
                .then().statusCode(RestResponse.StatusCode.OK).extract().as(Candidate[].class);

        verify(api).list();
        verifyNoMoreInteractions(api);

        assertEquals(out, Arrays.stream(response).toList());
    }
}