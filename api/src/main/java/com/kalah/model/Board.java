package com.kalah.model;

import java.util.Map;

import com.kalah.exception.KalahException;

import lombok.Data;

/**
 * @author Ankit
 * @Description This class represents the board of the game and it contains all
 *              the pits.
 */
@Data
public class Board {

	public static final Integer PIT_START_INDEX = 1;
	public static final Integer PIT_END_INDEX = 14;
	public static final Integer PLAYER_ONE_HOUSE = 7;
	public static final Integer PLAYER_TWO_HOUSE = 14;
	public static final Integer INITIAL_STONE_ON_PIT = 6;
	public static final Integer INITIAL_STONE_ON_HOUSE = 0;
	private Map<Integer, Pit> pits;

	public Board(Integer initialStoneCount, Player playerOne, Player playerTwo) {
		this.pits = initializePit(initialStoneCount, playerOne, playerTwo);
	}

	/**
	 * @Description This method will initialize all the pits with the default value
	 *              provided in the request at the start of the game.
	 * @param initialStoneCount
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	private Map<Integer, Pit> initializePit(Integer initialStoneCount, Player playerOne, Player playerTwo) {

		

		for (int i = Board.PIT_START_INDEX; i < Board.PLAYER_ONE_HOUSE; i++) {
			Pit pit = new Pit(i, initialStoneCount, playerOne.getPlayerIndex());
			pits.put(i, pit);
		}

		Pit houseOne = new Pit(Board.PLAYER_ONE_HOUSE, Board.INITIAL_STONE_ON_HOUSE, playerOne.getPlayerIndex());
		pits.put(Board.PLAYER_ONE_HOUSE, houseOne);

		for (int i = Board.PLAYER_ONE_HOUSE + 1; i < Board.PLAYER_TWO_HOUSE; i++) {
			Pit pit = new Pit(i, initialStoneCount, playerTwo.getPlayerIndex());
			pits.put(i, pit);
		}
		Pit houseTwo = new Pit(Board.PLAYER_TWO_HOUSE, Board.INITIAL_STONE_ON_HOUSE, playerTwo.getPlayerIndex());
		pits.put(Board.PLAYER_TWO_HOUSE, houseTwo);

		return pits;
	}

	/**
	 * @Description This method gives the stone count by pit index
	 * @param pitIndex
	 * @return stone count
	 */
	public Integer getStoneCountByPitIndex(Integer pitIndex) {
		return getPitByPitIndex(pitIndex).getStoneCount();
	}

	/**
	 * @Description This method checks for the players house based on the Player
	 *              index
	 * @param playerIndex
	 * @return
	 */
	public Pit getPlayerHouse(Integer playerIndex) {
		if (playerIndex.equals(Player.PLAYER_ONE_INDEX)) {
			return pits.get(Board.PLAYER_ONE_HOUSE);
		} else if (playerIndex.equals(Player.PLAYER_TWO_INDEX)) {
			return pits.get(Board.PLAYER_TWO_HOUSE);
		}
		throw new KalahException("playerIndex is not correct");
	}

	/**
	 * @Description This method gets the Pit
	 * @param pitIndex
	 * @return
	 */
	public Pit getPitByPitIndex(Integer pitIndex) {
		return pits.get(pitIndex);
	}

	/**
	 * @Description This method gives the next pit Index
	 * @param pit
	 * @return
	 */
	public Pit getNextPit(Pit pit) {
		return pits.get(pit.nextPitIndex());
	}

	/**
	 * @Description This method returns the opposite pit
	 * @param pit
	 * @return
	 */
	public Pit getOppositePit(Pit pit) {
		return pits.get(pit.getOppositePitIndex());
	}

	/**
	 * @Description This method will sum up all the stone count for player One
	 * @return stone count for player one
	 */
	public Integer getPlayerOnePitStoneCount() {
		Integer playerOnePitStoneCount = 0;
		for (int i = Board.PIT_START_INDEX; i < Board.PLAYER_ONE_HOUSE; i++) {
			playerOnePitStoneCount += this.getPits().get(i).getStoneCount();
		}
		return playerOnePitStoneCount;
	}

	/**
	 * @Description This method will sum up all the stone count for player Two
	 * @return stone count for player Two2
	 */
	public Integer getPlayerTwoPitStoneCount() {
		Integer playerTwoPitStoneCount = 0;
		for (int i = Board.PLAYER_ONE_HOUSE + 1; i < Board.PLAYER_TWO_HOUSE; i++) {
			playerTwoPitStoneCount += this.getPits().get(i).getStoneCount();
		}
		return playerTwoPitStoneCount;
	}

}