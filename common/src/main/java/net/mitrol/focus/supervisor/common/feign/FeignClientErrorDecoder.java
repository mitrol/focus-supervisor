package net.mitrol.focus.supervisor.common.feign;

import com.google.gson.Gson;
import feign.Response;
import feign.codec.ErrorDecoder;
import net.mitrol.focus.supervisor.common.error.ErrorDescription;
import net.mitrol.utils.log.MitrolLogger;
import net.mitrol.utils.log.MitrolLoggerImpl;

/**
 * Error decoder to catch the error from the server.
 * 
 * @author ladassus.
 */
public class FeignClientErrorDecoder implements ErrorDecoder {

    private static final MitrolLogger log = MitrolLoggerImpl.getLogger(FeignClientErrorDecoder.class);
    private Gson gson;

    public FeignClientErrorDecoder() {
        this.gson = new Gson();
    }

    @Override
    public Exception decode(String s, Response response) {
        try {
            ErrorDescription description;
            description = gson.fromJson(response.body().asReader(), ErrorDescription.class);
            return new FeignClientError("Error calling client " + s, description);
        } catch (Exception e) {
            return new FeignClientError(e);
        }
    }
}

