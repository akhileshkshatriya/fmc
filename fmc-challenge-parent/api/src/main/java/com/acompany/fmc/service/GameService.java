package com.acompany.fmc.service;

import com.acompany.fmc.service.dto.Statistics;

public interface GameService {

	Statistics resumeGame();

	boolean saveGame();

	boolean isThereAnySavedGame();

}
