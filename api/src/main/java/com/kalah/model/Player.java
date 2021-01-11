package com.kalah.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ankit
 * @Descrition This class is model class for Player it contains index and name
 *             of the player.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	public static final Integer PLAYER_ONE_INDEX = 1;
	public static final Integer PLAYER_TWO_INDEX = 2;

	private Integer playerIndex;

	private String playerName;

}
