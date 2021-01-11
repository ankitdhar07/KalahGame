package com.kalah.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ankit
 * @Descrition This class is Pit class and the Board contains 14 such pits.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pit {
	private Integer pitIndex;

	private Integer stoneCount;

	private Integer playerIndex;

	/**
	 * @Descrition The methods checks weather the stone in the Game can be
	 *             distributed or not.
	 * @param gameStatus
	 * @return
	 */
	public Boolean isDistributable(Status gameStatus) {

		return (!gameStatus.equals(Status.PLAYER_ONE_TURN) || !this.pitIndex.equals(Board.PLAYER_TWO_HOUSE))
				&& (!gameStatus.equals(Status.PLAYER_TWO_TURN) || !this.pitIndex.equals(Board.PLAYER_ONE_HOUSE));
	}

	/**
	 * @Descrition This method checks whether the player and game Status are in
	 *             sync.
	 * @param gameStatus
	 * @return
	 */
	public Boolean isPlayerPit(Status gameStatus) {

		return ((gameStatus.equals(Status.PLAYER_ONE_TURN) && this.playerIndex.equals(Player.PLAYER_ONE_INDEX))
				|| (gameStatus.equals(Status.PLAYER_TWO_TURN) && this.playerIndex.equals(Player.PLAYER_TWO_INDEX)));

	}

	/**
	 * @Description This method checks if the pit selected is house or not.
	 * @return
	 */
	public Boolean isHouse() {
		return this.pitIndex.equals(Board.PLAYER_ONE_HOUSE) || this.pitIndex.equals(Board.PLAYER_TWO_HOUSE);
	}

	/**
	 * @Description Gives the Index of the Next pit
	 * @return
	 */
	public Integer nextPitIndex() {
		Integer index = this.pitIndex + 1;
		if (index > Board.PLAYER_TWO_HOUSE) {
			index = 1;
		}
		return index;
	}

	/**
	 * @Description if it is the player one house
	 * @return
	 */
	public Boolean isPlayerOneHouse() {
		return this.playerIndex.equals(Player.PLAYER_ONE_INDEX) && this.pitIndex.equals(Board.PLAYER_ONE_HOUSE);

	}

	/**
	 * @Description if it is the player Two house
	 * @return
	 */
	public Boolean isPlayerTwoHouse() {
		return this.playerIndex.equals(Player.PLAYER_TWO_INDEX) && this.pitIndex.equals(Board.PLAYER_TWO_HOUSE);
	}

	/**
	 * @Description This methods gives the opposite Pit index.
	 * @return
	 */
	public Integer getOppositePitIndex() {
		return (Board.PIT_START_INDEX + Board.PIT_END_INDEX - 1) - this.getPitIndex();
	}
}
