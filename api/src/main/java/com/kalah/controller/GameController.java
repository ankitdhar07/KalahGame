package com.kalah.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kalah.exception.KalahException;
import com.kalah.model.Board;
import com.kalah.model.Game;
import com.kalah.service.GameService;

import lombok.extern.java.Log;

/**
 * @author Ankit This is controller class, Request to application will be routed
 *         from this class.
 *
 */
@CrossOrigin(allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.DELETE }, origins = { "*" })
@Log
@RestController
@RequestMapping("/kalah")
public class GameController {

	private GameService service;

	public GameController(GameService service) {
		this.service = service;
	}

	/**
	 * @Description This method will initialize the Game with the Default value
	 *              provided in the request parameter.
	 * @param initialStoneCount
	 * @return Game instance
	 */
	@GetMapping(value = "/game")
	public ResponseEntity<Game> initializeBoard(
			@RequestParam(name = "stone", defaultValue = "3", required = false) Integer initialStoneCount) {
		log.info("Initialize default values");
		return ResponseEntity.status(HttpStatus.CREATED).body(service.initializeGame(initialStoneCount));
	}

	/**
	 * @Description This method will take the Pit Index and start the game play
	 *              based on the rules.
	 * @param pitIndex
	 * @return Game instance
	 */
	@PutMapping("/game/pits/{pitIndex}")
	public ResponseEntity<Game> gamePlay(@PathVariable Integer pitIndex) {

		log.info("player is moving stone from pit index  " + pitIndex);
		if (pitIndex > Board.PIT_END_INDEX || pitIndex < Board.PIT_START_INDEX
				|| pitIndex.equals(Board.PLAYER_ONE_HOUSE) || pitIndex.equals(Board.PLAYER_TWO_HOUSE)) {
			throw new KalahException(
					"Incorrect pit index or house pit , value should be in range of 1 to 14 except pit 7 and 14 ");
		}
		return ResponseEntity.ok().body(service.playGame(pitIndex));
	}

}
