package infrastructure.lifecycle;

import domain.Election;
import infrastructure.repositories.RedisElectionRepository;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
public class Subscribe {
    private static final Logger LOGGER = Logger.getLogger(Subscribe.class);

    public Subscribe(ReactiveRedisDataSource reactiveRedisDataSource,
                     RedisElectionRepository repository) {
        LOGGER.info("Startup: Subscribe");

        Multi<String> sub = reactiveRedisDataSource.pubsub(String.class)
                                                   .subscribe("elections");

        sub.emitOn(Infrastructure.getDefaultWorkerPool())
           .subscribe()
           .with(id -> {
               LOGGER.info("Election " + id + " received from subscription");
               Election election = repository.findById(id);
               LOGGER.info("Election " + election.id() + " starting");
           });
    }
}
