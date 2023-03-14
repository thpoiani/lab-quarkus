package infrastructure;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

@QuarkusMain
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String... args) {
        LOGGER.info("Running main method");
        Quarkus.run(args);
    }
}