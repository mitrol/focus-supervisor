import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author ladassus.
 */
@SpringBootApplication
public class MitAcdConnectorMessageGeneratorApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MitAcdConnectorMessageGeneratorApplication.class).run(args);
    }
}
