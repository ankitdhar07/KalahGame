package com.kalah.model;

import static com.kalah.model.Player.PLAYER_ONE_INDEX;
import static com.kalah.model.Player.PLAYER_TWO_INDEX;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ankit
 * @Descrition This class is responsible to to store Game Object, It is created
 *             once the Game is initialized.
 */

@Data
@NoArgsConstructor
public class Game {

	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Player winner;
	private Status status;

	/**
	 * @Descrition Game Constructor to initialize the game.
	 * @param initialStoneCount
	 */
	public Game(Integer initialStoneCount) {
		this.playerOne = new Player(PLAYER_ONE_INDEX, "Player One");
		this.playerTwo = new Player(PLAYER_TWO_INDEX, "Player Two");
		this.board = new Board(initialStoneCount, playerOne, playerTwo);
		this.status = Status.INITIALIZE;
	}
}
