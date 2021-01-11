package com.kalah.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.kalah.model.Board;

class ServiceImplTest {

	ServiceImpl serviceImpl = new ServiceImpl();

	@Test
	void initializeGameTest() {
		serviceImpl.initializeGame(Board.INITIAL_STONE_ON_PIT);
		assertNotNull("Game Instance is Created", serviceImpl.initializeGame(Board.INITIAL_STONE_ON_PIT));
		assertEquals("INITIALIZE", serviceImpl.initializeGame(Board.INITIAL_STONE_ON_PIT).getStatus().toString());
	}

}
