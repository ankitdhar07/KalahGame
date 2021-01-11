package com.kalah.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalah.model.Game;
import com.kalah.model.Pit;
import com.kalah.service.GameService;

@Service
public class ServiceImpl implements GameService {
	@Autowired
	private KalahRules kalahRules;
	private Map<String, Game> gameMap = new HashMap<>();
	private String firstGame = "gameOne";

	@Override
	public Game initializeGame(Integer initialStoneCount) {
		Game game = new Game(initialStoneCount);
		if (!(gameMap.isEmpty()) && gameMap.containsKey(firstGame)) {
			gameMap.remove(firstGame);
		}
		gameMap.put(firstGame, game);

		return gameMap.get(firstGame);
	}

	@Override
	public Game playGame(Integer pitIndex) {
		Game game = gameMap.get(firstGame);
		Pit pit = game.getBoard().getPitByPitIndex(pitIndex);
		kalahRules.checkPlayerTurnRule(game, pit);
		kalahRules.checkEmptyStartRULE(pit);
		kalahRules.sowStone(game, pit);
		kalahRules.lastEmptyPitRule(game, pit);
		kalahRules.nextPlayerTurnRule(game, pit);
		kalahRules.gameResult(game);
		return game;

	}

}
