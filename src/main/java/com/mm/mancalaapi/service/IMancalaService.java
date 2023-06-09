package com.mm.mancalaapi.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMancalaService {
     String createGame();
     ResponseEntity<List<Integer>> move(String gameId, int pitIndex);
}
