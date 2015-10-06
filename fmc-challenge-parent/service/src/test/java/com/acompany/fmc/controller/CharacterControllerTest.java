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
import com.acompany.fmc.service.dto.Character;

@RunWith(MockitoJUnitRunner.class)
public class CharacterControllerTest {
	
	@InjectMocks
	private CharacterController controller = new CharacterController();
	
	@Mock
	private CharacterService characterService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldRedirectToPoliceViewOnSuccessfullyCreatingHero() {
		when(characterService.createHero(getDummyHero())).thenReturn(getDummyHero().getName());

		Model model = controller.createCharacter(getDummyModelWithCharacterAsHeroSetup());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		assertEquals("goto.police.options", model.getRedirectTo());
	}
	
	
	
	private Model getDummyModelWithCharacterAsHeroSetup(){
		Model model = new Model();
		Character hero = getDummyHero();
		model.addAttribute("hero", hero);
		return model;
	}

	protected Character getDummyHero() {
		Character hero = new Character();
		hero.setName("test_hero");
		hero.setGender(Character.GENDER.MALE);
		return hero;
	}
}
