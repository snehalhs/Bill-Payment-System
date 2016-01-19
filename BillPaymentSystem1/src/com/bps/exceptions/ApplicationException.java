package com.bps.exceptions;


//TODO: Auto-generated Javadoc
/**
* This class used to handle the ApplicationException.
*/
public class ApplicationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * String object containing the exception error code.
	 */
	private String errorCode;

	/**
	 * Default Constructor for ApplicationException.
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * Constructs an ApplicationException with the specified error code.
	 * 
	 * @param errCode -
	 *            error code for the error description.
	 */
	public ApplicationException(final String  errCode) {
		this.errorCode = errCode;
	}
	/**
	 * Constructs an ApplicationException with the specified error code.
	 *
	 * @param errCode the err code
	 * @param cause the cause
	 */
	public ApplicationException(final String errCode,final  Throwable cause) {
		super(errCode, cause);
	}
	/**
	 * Constructs an ApplicationException with the specified error code.
	 *
	 * @param errCode the err code
	 * @param cause the cause
	 */
	public ApplicationException(final  Throwable cause) {
		super(cause);
	}
	/**
	 * Gets the error code of the exception.
	 * 
	 * @return <b> String </b> error code of the exception.
	 */
	public String getErrorCode() {
		return errorCode;
	}
}
