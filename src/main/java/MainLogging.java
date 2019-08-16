
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainLogging {

    private static Logger logger = LogManager.getLogger(MainLogging.class);

    public static void main(String[] args) {
        logger.info("testing    ");
        Level level = logger.getLevel();
    }
}
