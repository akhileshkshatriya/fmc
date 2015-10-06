package com.acompany.fmc.framework.frontcontroller;

import static org.mockito.Mockito.verify;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.framework.model.Model;

@RunWith(MockitoJUnitRunner.class)
public class FrontControllerTest {

	private static final String REQUEST = "request";
	@InjectMocks
	private FrontController frontController = new FrontController();

	@Mock
	private Dispatcher dispatcher;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDispatchRequest()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Model model = new Model();
		frontController.dispatchRequest(REQUEST, model);
		verify(dispatcher).dispatch("request", model);
	}

}
