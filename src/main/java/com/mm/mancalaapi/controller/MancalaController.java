package com.mm.mancalaapi.controller;

import com.mm.mancalaapi.model.Game;
import com.mm.mancalaapi.service.Impl.MancalaServiceImpl;
import com.mm.mancalaapi.service.MancalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mancala/api/game")
@CrossOrigin(origins = "*")
public class MancalaController {
    private final MancalaService mancalaService;

    @Autowired
    public MancalaController(MancalaServiceImpl mancalaService) {
        this.mancalaService = mancalaService;
    }

    @PostMapping("/create")
    public ResponseEntity<Game> createGame() {
        try {
            Game game = mancalaService.createGame();
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<Game> getGame() {
        try {
            Game game = mancalaService.getGame();
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/move")
    public ResponseEntity<Void> moveStones(@RequestParam int playerIndex, @RequestParam int pitIndex) {
        try {
            mancalaService.moveStones(playerIndex, pitIndex);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request for invalid input
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
