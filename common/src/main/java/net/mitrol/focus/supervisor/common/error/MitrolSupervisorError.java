package net.mitrol.focus.supervisor.common.error;

/**
 * Base error class in the Supervisor
 * @author ladassus
 */
public class MitrolSupervisorError extends RuntimeException {

    private final int errorCode;

    public MitrolSupervisorError() {
        super();
        this.errorCode = DefaultErrorCodes.GENERIC_ERROR;
    }

    public MitrolSupervisorError(String message) {
        super(message);
        this.errorCode = DefaultErrorCodes.GENERIC_ERROR;
    }

    public MitrolSupervisorError(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = DefaultErrorCodes.GENERIC_ERROR;
    }

    public MitrolSupervisorError(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MitrolSupervisorError(int errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    /**
     * Retrieves the error code.
     */
    public int getErrorCode() {
        return errorCode;
    }
}
