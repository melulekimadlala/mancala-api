package com.mm.mancalaapi.service.Impl;

import com.mm.mancalaapi.service.IMancalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MancalaServiceImpl implements IMancalaService {
    private Map<String, List<Integer>> games = new HashMap<>();

    public Map<String, List<Integer>> getGames() {
        return games;
    }

    @Override
    public String createGame() {
        // This gameId needs to have a distinction for different players
        String gameId = UUID.randomUUID().toString();
        //index 6 is player 1's big pit
        //index 13 is player 2's big pit
        List<Integer> pits = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);
        games.put(gameId, pits);
        return gameId;
    }

    @Override
    public ResponseEntity<List<Integer>> move(String gameId, int pitIndex) {
        if (!games.containsKey(gameId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<Integer> pits = games.get(gameId);

        if (pitIndex < 0 || pitIndex >= 14 || pits.get(pitIndex) == 0) {
            return ResponseEntity.badRequest().body(pits);
        }

        int stones = pits.get(pitIndex);
        pits.set(pitIndex, 0);

        int currentIndex = pitIndex + 1;
        while (stones > 0) {
            if (currentIndex == 14) {
                currentIndex = 0;
            }

            if (currentIndex == 6 && !gameId.startsWith("player1")) {
                currentIndex++;
                continue;
            }

            if (currentIndex == 13 && !gameId.startsWith("player2")) {
                currentIndex++;
                continue;
            }

            pits.set(currentIndex, pits.get(currentIndex) + 1);
            stones--;
            currentIndex++;
        }

        // Add missing code here
//        if (pits.get(currentIndex - 1) == 1 && pits.get(getOppositeIndex(currentIndex - 1)) > 0) {
//            pits.set(6, pits.get(6) + pits.get(getOppositeIndex(currentIndex - 1)) + 1);
//            pits.set(getOppositeIndex(currentIndex - 1), 0);
//            pits.set(currentIndex - 1, 0);
//        }

        games.put(gameId, pits);
        return ResponseEntity.ok(pits);
    }

//    private int getOppositeIndex(int index) {
//        return 12 - index;
//    }
}
