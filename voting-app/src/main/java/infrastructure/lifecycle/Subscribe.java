package infrastructure.lifecycle;

import infrastructure.repositories.RedisElectionRepository;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
public class Subscribe {
    private static final Logger LOGGER = Logger.getLogger(Subscribe.class);

    public Subscribe(ReactiveRedisDataSource dataSource,
                     RedisElectionRepository repository) {
        LOGGER.info("Startup: Subscribe");

        dataSource.pubsub(String.class)
                  .subscribe("elections")
                  .emitOn(Infrastructure.getDefaultWorkerPool())
                  .subscribe()
                  .with(id -> {
                      LOGGER.info("Election " + id + " received from subscription");
                      LOGGER.info("Election " + repository.findById(id) + " starting");
                  });
    }
}
