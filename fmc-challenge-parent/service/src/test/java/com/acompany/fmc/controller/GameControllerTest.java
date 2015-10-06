package com.acompany.fmc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	@InjectMocks
	private GameController controller = new GameController();

	@Mock
	private CharacterService characterService;

	@Mock
	private GameService gameService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRedirectToWelcomeViewTest() {

		when(gameService.isThereAnySavedGame()).thenReturn(true);

		Model model = controller.welcome(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		assertEquals("welcome.home", model.getRedirectTo());
	}

	@Test
	public void givenNoHeroCreatedShouldRedirectToCharacterView() {
		when(characterService.isHeroCreated()).thenReturn(false);

		Model model = controller.goToPolice(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		assertEquals("create.character", model.getRedirectTo());
	}

	@Test
	public void givenHeroAlreadyCreatedShouldRedirectToPoliceView() {

		when(characterService.isHeroCreated()).thenReturn(true);
		Model model = controller.goToPolice(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		assertEquals("goto.police.options", model.getRedirectTo());
	}

}
