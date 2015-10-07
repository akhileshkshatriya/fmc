package com.acompany.fmc.view.util;

import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.framework.frontcontroller.FrontController;
import com.acompany.fmc.framework.model.Model;

@RunWith(MockitoJUnitRunner.class)
public class RequestSubmitterTest {
	
	@InjectMocks
	private RequestSubmitter submitter;
	
	@Mock
	private FrontController frontController;
	
	private static final String REQUEST = "request";
	private static final Model model = new Model();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSubmitRequest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//FrontController controller = new FrontController();
		//FrontController spy = Mockito.spy(controller);
		//doNothing().when(spy).dispatchRequest(REQUEST, model);
		//RequestSubmitter submitter = new RequestSubmitter(spy);
		RequestSubmitter.submitRequest(REQUEST, model);
	}
	
	@Test
	public void testSubmitRequestThrowException() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Mockito.doThrow(new IllegalArgumentException()).when(frontController).dispatchRequest(REQUEST, model);
		RequestSubmitter.submitRequest(REQUEST, model);
	}

}
