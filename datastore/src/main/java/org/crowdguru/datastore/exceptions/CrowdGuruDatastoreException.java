package org.crowdguru.datastore.exceptions;

@SuppressWarnings("serial")
public class CrowdGuruDatastoreException extends Exception {

	public CrowdGuruDatastoreException(String message) {
		super(message);
	}

	public CrowdGuruDatastoreException(Throwable cause) {
		super(cause);
	}

	public CrowdGuruDatastoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public CrowdGuruDatastoreException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
