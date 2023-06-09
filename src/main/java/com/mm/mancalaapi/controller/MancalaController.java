package com.mm.mancalaapi.controller;

import com.mm.mancalaapi.service.Impl.MancalaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MancalaController {
    private final MancalaServiceImpl mancalaService;

    @Autowired
    public MancalaController(MancalaServiceImpl mancalaService) {
        this.mancalaService = mancalaService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGame() {
        return ResponseEntity.ok(mancalaService.createGame());
    }

    @PostMapping("/{gameId}/move/{pitIndex}")
    public ResponseEntity<List<Integer>> makeMove(@PathVariable String gameId, @PathVariable int pitIndex) {
        return mancalaService.move(gameId, pitIndex);
    }
}
