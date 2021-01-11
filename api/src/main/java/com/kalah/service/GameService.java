package com.kalah.service;

import com.kalah.model.Game;

/**
 * @author Ankit
 * @Description Interface so implementation can be provided as per requirement.
 *
 */
public interface GameService {

	public Game initializeGame(Integer initialStoneCount);

	public Game playGame(Integer pitIndex);
}
