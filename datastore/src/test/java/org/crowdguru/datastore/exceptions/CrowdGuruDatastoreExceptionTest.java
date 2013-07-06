package org.crowdguru.datastore.exceptions;

import java.util.Arrays;
import junit.framework.TestCase;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CrowdGuruDatastoreExceptionTest extends TestCase{
	
	private static final String MESSAGE_1 = "MESSAGE_1"; 
	
	public void testMessage(){
		CrowdGuruDatastoreException ex = new CrowdGuruDatastoreException(MESSAGE_1);
		
		try{
			throw ex;
		}catch(CrowdGuruDatastoreException e){
			assertThat(e.getMessage(), is(equalTo(MESSAGE_1)));
		}
	}
	
	public void testThrowable(){
		Throwable cause = new Throwable();
		CrowdGuruDatastoreException ex = new CrowdGuruDatastoreException(cause);
		
		try{
			throw ex;
		}catch(CrowdGuruDatastoreException e){
			assertThat(e.getCause(), is(sameInstance(cause)));
		}
	}
	
	public void testThrowableAndMessage(){
		Throwable cause = new Throwable(MESSAGE_1);
		CrowdGuruDatastoreException ex = new CrowdGuruDatastoreException(MESSAGE_1, cause);
		
		try{
			throw ex;
		}catch(CrowdGuruDatastoreException e){
			assertThat(e.getMessage(), is(equalTo(MESSAGE_1)));
			assertThat(e.getCause(), is(sameInstance(cause)));
		}
	}
	
	public void testSuppressionEnabled(){
		Throwable cause = new Throwable();
		CrowdGuruDatastoreException ex = new CrowdGuruDatastoreException(
				MESSAGE_1, cause, true, true);
		
		Throwable suppressed = new Throwable();
		ex.addSuppressed(suppressed);
		
		try{
			throw ex;
		}catch(CrowdGuruDatastoreException e){
			assertThat(e.getCause(), is(sameInstance(cause)));
			assertThat(e.getMessage(), is(equalTo(MESSAGE_1)));
			assertThat(Arrays.asList(e.getSuppressed()), contains(suppressed));
			assertThat(Arrays.asList(e.getSuppressed()), not(contains(cause)));
		}
	}
	
	public void testSuppressionDisabled(){
		Throwable cause = new Throwable();
		CrowdGuruDatastoreException ex = new CrowdGuruDatastoreException(
				MESSAGE_1, cause, false, true);
		
		Throwable suppressed = new Throwable();
		ex.addSuppressed(suppressed);
		
		try{
			throw ex;
		}catch(CrowdGuruDatastoreException e){
			assertThat(e.getCause(), is(sameInstance(cause)));
			assertThat(e.getMessage(), is(equalTo(MESSAGE_1)));
			assertThat(e.getSuppressed(), is(emptyArray()));
		}
	}
	
	public void testWritableStackTraceDisabled(){
		Throwable cause = new Throwable();
		CrowdGuruDatastoreException ex = new CrowdGuruDatastoreException(
				MESSAGE_1, cause, false, true);
		
		try{
			throw ex;
		}catch(CrowdGuruDatastoreException e){
			assertThat(ex.getStackTrace(), is(not(emptyArray())));
		}
	}
	
	
	public void testWritableStackTraceEnabled(){
		Throwable cause = new Throwable();
		CrowdGuruDatastoreException ex = new CrowdGuruDatastoreException(
				MESSAGE_1, cause, false, false);
		
		try{
			throw ex;
		}catch(CrowdGuruDatastoreException e){
			assertThat(ex.getStackTrace(), is(emptyArray()));
		}
	}
	
}
