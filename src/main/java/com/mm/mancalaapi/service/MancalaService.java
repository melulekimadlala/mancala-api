package com.mm.mancalaapi.service;

import com.mm.mancalaapi.model.Game;
import com.mm.mancalaapi.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MancalaService {
     Game createGame();
     Game getGame();
     void moveStones(int playerIndex, int pitIndex);
     int getOppositeIndex(int index);
     Player getCurrentPlayer(int playerIndex);
}
