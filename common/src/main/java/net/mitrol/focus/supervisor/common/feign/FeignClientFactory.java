package net.mitrol.focus.supervisor.common.feign;

import feign.Feign;
import feign.gson.GsonDecoder;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.env.Environment;

import static java.lang.String.format;

/**
 * Factory that creates clients instances based on Netflix's Feign.
 * 
 * @author ladassus.
 */
public class FeignClientFactory<T> implements FactoryBean<T> {

    /** The log. */
    private static final MitrolLogger log = MitrolLoggerImpl.getLogger(FeignClientFactory.class);
    /** URL Key. */
    private static final String CLIENT_URL_KEY = "mitrol.http.client.%s.url";

    private Class<T> clazz;
    private String name;
    private Environment environment;

    /**
     * Creates a new factory associated to type.
     * 
     * */
    public FeignClientFactory(String name, Class<T> clazz, Environment environment) {
        this.clazz = clazz;
        this.name = name;
        this.environment = environment;
    }

    @Override
    public T getObject() throws Exception {
        String url = environment.getRequiredProperty(format(CLIENT_URL_KEY, name));
        log.info("Creating client {} pointing to {}", name, url);
        return Feign.builder().decoder(new GsonDecoder()).errorDecoder(new FeignClientErrorDecoder()).target(clazz, url);
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
