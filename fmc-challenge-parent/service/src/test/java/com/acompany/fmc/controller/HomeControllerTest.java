package com.acompany.fmc.controller;

import static junit.framework.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.framework.model.Model;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	@InjectMocks
	private HomeController controller = new HomeController();
	
	@Test
	@Ignore
	public void test(){
		Model model = controller.execute(null);
		assertEquals(model.getAttribute("view"), "home.welcome");
	}

}
