package com.acompany.fmc.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	@InjectMocks
	private GameController controller = new GameController();
	
	@Test
	@Ignore
	public void test(){
		//Model model = controller.execute(null);
		//assertEquals(model.getAttribute("view"), "home.welcome");
	}

}
