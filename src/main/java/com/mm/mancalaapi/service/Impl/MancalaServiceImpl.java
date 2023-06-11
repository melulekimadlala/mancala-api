package com.mm.mancalaapi.service.Impl;

import com.mm.mancalaapi.model.Game;
import com.mm.mancalaapi.model.Player;
import com.mm.mancalaapi.service.MancalaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MancalaServiceImpl implements MancalaService {
    private Game game;

    @Override
    public Game createGame() {
        System.out.println("Creating a new game...");
        game = new Game();

        List<Integer> pits = Arrays.asList(6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);

        Player player1 = new Player();
        player1.setPits(pits);
        player1.setName("Player 1");

        Player player2 = new Player();
        player2.setPits(pits);
        player2.setName("Player 2");

        game.setPlayer1(player1);
        game.setPlayer2(player2);
        game.setCurrentPlayerName(player1.getName());

        return game;
    }

    public Game getGame() {
        return game;
    }

    public void moveStones(int playerIndex, int pitIndex) {
        Player currentPlayer = getCurrentPlayer(playerIndex);
        List<Integer> pits = currentPlayer.getPits();
        int stones = pits.get(pitIndex);
        pits.set(pitIndex, 0);

        if (playerIndex == 1) {
            game.setCurrentPlayerName(game.getPlayer2().getName());
        } else {
            game.setCurrentPlayerName(game.getPlayer1().getName());
        }

        int currentIndex = pitIndex + 1;
        while (stones > 0) {
            if (currentIndex == 14) {
                currentIndex = 0;
            }

            if (currentIndex == 6 && !currentPlayer.getName().equals("Player 1")) {
                currentIndex++; //Skip if Player 2 is the current player
                continue;
            }

            if (currentIndex == 13 && !currentPlayer.getName().equals("Player 2")) {
                currentIndex = 0; //Skip if Player 1 is the current player
                continue;
            }

            pits.set(currentIndex, pits.get(currentIndex) + 1);
            stones--;
            currentIndex++;
        }

        if (pits.get(currentIndex - 1) == 1 && pits.get(getOppositeIndex(currentIndex - 1)) > 0) {
            if (currentPlayer.getName().equals("Player 1")) {
                pits.set(6, pits.get(6) + pits.get(getOppositeIndex(currentIndex - 1)) + 1);
            } else {
                pits.set(13, pits.get(13) + pits.get(getOppositeIndex(currentIndex - 1)) + 1);
            }

            pits.set(getOppositeIndex(currentIndex - 1), 0);
            pits.set(currentIndex - 1, 0);
        }

        if (isGameOver(game)) {
            game.setCurrentPlayerName("Game Over!");
        }
    }

    public int getOppositeIndex(int index) {
        return 12 - index;
    }

    public Player getCurrentPlayer(int playerIndex) {
        if (playerIndex == 1) {
            return game.getPlayer1();
        } else if (playerIndex == 2) {
            return game.getPlayer2();
        } else {
            throw new IllegalArgumentException("Invalid player index");
        }
    }

    private boolean isGameOver(Game game) {
        int count = 0;
        if (game.getCurrentPlayerName().equals("Player 1")) {
            for(int i = 0; i < 6; i ++) {
                if (game.getPlayer1().getPits().get(i) == 0) {
                    count++;
                }
            }
        } else {
            for(int i = 7; i < 13; i ++) {
                if (game.getPlayer2().getPits().get(i) == 0) {
                    count++;
                }
            }
        }
       return count == 6;
    }
}
