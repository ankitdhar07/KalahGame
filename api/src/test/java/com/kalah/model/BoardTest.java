package com.kalah.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.kalah.exception.KalahException;

import lombok.extern.java.Log;

@Log
class BoardTest {
	Player playerOne = new Player(Player.PLAYER_ONE_INDEX, "playerOne");
	Player playerTwo = new Player(Player.PLAYER_TWO_INDEX, "playerTwo");
	Board board = new Board(Board.INITIAL_STONE_ON_PIT, playerOne, playerTwo);

	@Test
	void initializeBoardTest() {
		assertEquals(14, board.getPits().size());
	}

	@Test
	void getPlayerHouseTest() {
		assertEquals(1, board.getPlayerHouse(1).getPlayerIndex());
		assertEquals(2, board.getPlayerHouse(2).getPlayerIndex());
	}

	@Test
	void getPlayerHouseExceptionTest() {
		try {
			assertEquals(3, board.getPlayerHouse(3).getPlayerIndex());
		} catch (KalahException e) {
			log.info("Incorrect Player Index");
		}
	}
}
