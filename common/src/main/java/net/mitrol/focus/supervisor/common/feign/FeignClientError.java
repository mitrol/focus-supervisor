package net.mitrol.focus.supervisor.common.feign;

/**
 * Client thrown by http clients.
 * 
 * @author ladassus.
 */
public class FeignClientError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** Description of the error returned by the server. */
    private ErrorDescription description;

    public FeignClientError() {
        super();
    }

    public FeignClientError(String msg, ErrorDescription description) {
        super(msg);
        this.description = description;
    }

    public FeignClientError(Throwable th) {
        super(th.getMessage(), th);
    }

    public ErrorDescription getDescription() {
        return description;
    }

}