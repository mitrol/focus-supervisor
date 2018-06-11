package net.mitrol.focus.supervisor.common.error;

/**
 * Base error codes inside the Focus Supervisor.
 * @author ladassus.
 */
public class DefaultErrorCodes {

	/** Generic error code used when there is no defined error code. */
	public static final int GENERIC_ERROR = 1000;

	/** A precondition failed validating function arguments. */
	public static final int INVALID_ARGUMENTS = 1050;

	/** The entity was not found into any database. */
	public static final int ENTITY_NOT_FOUND = 2000;

	/** An action is not allowed to be executed. */
    public static final int NOT_ALLOWED_ERROR = 1100;

	/** There is a constraint violation. */
	public static final int CONSTRAINT_VIOLATION = 2010;
}
