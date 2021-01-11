package com.kalah.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class GameTest {

	@Test
	public void initializeGameTest() {
		Game game = new Game(Board.INITIAL_STONE_ON_PIT);
		assertEquals(Player.PLAYER_ONE_INDEX, game.getPlayerOne().getPlayerIndex());
		assertEquals(Player.PLAYER_TWO_INDEX, game.getPlayerTwo().getPlayerIndex());
		assertEquals(6, game.getBoard().getPits().get(1).getStoneCount());
		assertEquals(14, game.getBoard().getPits().size());
		assertEquals(1, game.getBoard().getPits().get(1).getPlayerIndex());
		assertEquals(Status.INITIALIZE, game.getStatus());

	}

}
