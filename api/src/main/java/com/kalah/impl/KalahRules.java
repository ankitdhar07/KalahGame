package com.kalah.impl;

import org.springframework.stereotype.Component;

import com.kalah.exception.KalahException;
import com.kalah.model.Board;
import com.kalah.model.Game;
import com.kalah.model.Pit;
import com.kalah.model.Player;
import com.kalah.model.Status;

import lombok.extern.java.Log;

/**
 * @author Ankit
 * @Descrition This class is the rule book for the Game
 *
 */
@Log
@Component
public class KalahRules {

	/**
	 * @Description This method checks for the Player turn and sets the Status
	 *              accordingly. And throws exception if Player tries to distribute
	 *              other Player's Stone.
	 * @param game
	 * @param startPit
	 */
	public void checkPlayerTurnRule(Game game, Pit startPit) {

		if (game.getStatus().equals(Status.INITIALIZE)) {
			Status status = startPit.getPlayerIndex().equals(Player.PLAYER_ONE_INDEX) ? Status.PLAYER_ONE_TURN
					: Status.PLAYER_TWO_TURN;
			game.setStatus(status);
		}

		if ((game.getStatus().equals(Status.PLAYER_ONE_TURN) && startPit.getPitIndex() >= Board.PLAYER_ONE_HOUSE)
				|| (game.getStatus().equals(Status.PLAYER_TWO_TURN)
						&& startPit.getPitIndex() <= Board.PLAYER_ONE_HOUSE)) {

			throw new KalahException(" I saw you, you just played..");
		}
	}

	/**
	 * @Description This method checks if the start pit is empty or not
	 * 
	 * @param startPit
	 */
	public void checkEmptyStartRULE(Pit startPit) {

		if (startPit.getStoneCount() == 0) {
			throw new KalahException("Please have a look mate, there is nothing to distribute.");
		}
	}

	/**
	 * @Description This method will check if the last pit is not house and the
	 *              stone count is one for current player. If yes then it will put
	 *              all the stone from the opponent to the current player house.
	 * @param game
	 * @param endPit
	 */
	public void lastEmptyPitRule(Game game, Pit endPit) {

		if (Boolean.FALSE.equals(endPit.isHouse()) && Boolean.TRUE.equals(endPit.isPlayerPit(game.getStatus()))
				&& endPit.getStoneCount().equals(1)) {
			Pit oppositePit = game.getBoard().getOppositePit(endPit);
			if (oppositePit.getStoneCount() > 0) {
				Pit house = game.getBoard().getPlayerHouse(endPit.getPlayerIndex());
				house.setStoneCount((house.getStoneCount() + oppositePit.getStoneCount()) + endPit.getStoneCount());
				oppositePit.setStoneCount(0);
				endPit.setStoneCount(0);
			}
		}
	}

	/**
	 * @Description This method checks for the player turn based on the last sowed
	 *              stone in the pit
	 * @param game
	 * @param endPit
	 */
	public void nextPlayerTurnRule(Game game, Pit endPit) {

		if (Boolean.TRUE.equals(endPit.isPlayerOneHouse()) && game.getStatus().equals(Status.PLAYER_ONE_TURN)) {
			game.setStatus(Status.PLAYER_ONE_TURN);
		} else if (Boolean.TRUE.equals(endPit.isPlayerTwoHouse()) && game.getStatus().equals(Status.PLAYER_TWO_TURN)) {
			game.setStatus(Status.PLAYER_TWO_TURN);
		} else {
			Status changeStage = game.getStatus() == Status.PLAYER_ONE_TURN ? Status.PLAYER_TWO_TURN
					: Status.PLAYER_ONE_TURN;
			game.setStatus(changeStage);
		}

	}

	/**
	 * @Description This method checks for the Stone count in both the players
	 *              house. And calls the winner method to check for the winner if
	 *              game is finished.
	 * @param game
	 * 
	 */
	public void gameResult(Game game) {
		Integer playerOneStoneCount = game.getBoard().getPlayerOnePitStoneCount();
		Integer playerTwoStoneCount = game.getBoard().getPlayerTwoPitStoneCount();

		if (playerOneStoneCount == 0 || playerTwoStoneCount == 0) {

			game.setStatus(Status.FINISHED);

			Pit house1 = game.getBoard().getPits().get(Board.PLAYER_ONE_HOUSE);
			house1.setStoneCount(house1.getStoneCount() + playerOneStoneCount);

			Pit house2 = game.getBoard().getPits().get(Board.PLAYER_TWO_HOUSE);
			house2.setStoneCount(house2.getStoneCount() + playerTwoStoneCount);

			winner(game, house1.getStoneCount(), house2.getStoneCount());
		}
	}

	/**
	 * @Description This method checks for the winner if the Game status is
	 *              finished. Throws exception for winner (work around as Service
	 *              was broken).
	 * @param game
	 * @param houseOneStoneCount
	 * @param houseTwoStoneCount
	 */
	public void winner(Game game, Integer houseOneStoneCount, Integer houseTwoStoneCount) {
		if (houseOneStoneCount > houseTwoStoneCount) {
			game.setWinner(game.getPlayerOne());
			throw new KalahException("Congratulations ! Player One, you have won. Your Score : " + houseOneStoneCount);
		} else if (houseOneStoneCount < houseTwoStoneCount) {
			game.setWinner(game.getPlayerTwo());
			throw new KalahException("Congratulations ! Player Two, you have won. Your Score : " + houseTwoStoneCount);
		} else {
			game.setWinner(null);
		}
	}

	/**
	 * @Description This method distributes the stone to players board.
	 * @param game
	 * @param currentPit
	 */
	public void sowStone(Game game, Pit currentPit) {
		Integer stoneToDistribute = currentPit.getStoneCount();
		currentPit.setStoneCount(0);

		for (int i = 0; i < stoneToDistribute; i++) {
			currentPit = game.getBoard().getNextPit(currentPit);
			log.info("next pit " + currentPit);
			if (Boolean.TRUE.equals(currentPit.isDistributable(game.getStatus()))) {
				currentPit.setStoneCount(currentPit.getStoneCount() + 1);
			} else {
				i--;
			}
		}
	}
}
