package com.acompany.fmc.framework.frontcontroller;

import java.lang.reflect.InvocationTargetException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import com.acompany.fmc.framework.ApplicationContext;
import com.acompany.fmc.framework.model.Model;

@RunWith(JUnit4ClassRunner.class)
//@PrepareForTest(ApplicationContext.class)
public class DispatcherTest {

	private static final String REQUEST = "request";
	
//	@InjectMocks
//	private Dispatcher dispatcher;
//
//	@Mock
//	private HandlerMapping handler;
//
//	@Mock
//	private ViewResolver resolver;
//	
//	@Mock
//	private ApplicationContext context; 
//
//	@Before
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}

//	@Test
//	@Ignore
//	public void testDispatch() {
//
//		PowerMockito.mockStatic(ApplicationContext.class);
//		BDDMockito.given(ApplicationContext.getApplicationContext()).willReturn(context);
//		
//		Method method = Mockito.mock(Method.class);
//		final Class<?> controllerClass = Class.class;
//		when(method.getDeclaringClass()).thenAnswer(new Answer<Object>() {
//            @Override
//            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
//                return controllerClass;
//            }
//        });
//		when(context.getBean(controllerClass)).thenReturn(controllerClass);
//		
//		Class<?> object = Mockito.mock(Class.class);
//		when(handler.get(REQUEST)).thenReturn(method);
//		
//		//when(method.getDeclaringClass()).thenReturn(object);
//		//when(context.getBean(dummyHero,CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero.getName());
//		
//	}
	
	@Test
	public void testDispatch2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		ApplicationContext context  = ApplicationContext.getApplicationContext();
		Dispatcher dispatcher = (Dispatcher)context.getBean(Dispatcher.class);
		dispatcher.dispatch("test.controller.mapping", new Model());
		
	}
}
