package com.mm.mancalaapi;

import com.mm.mancalaapi.model.Game;
import com.mm.mancalaapi.service.Impl.MancalaServiceImpl;
import com.mm.mancalaapi.service.MancalaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class MancalaServiceTest {
    private MancalaService mancalaService;

    @BeforeEach
    public void setup() {
        mancalaService = new MancalaServiceImpl();
    }

    @Test
    public void testCreateGame() {
        Game game = mancalaService.createGame();

        assertNotNull(game);
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertEquals("Player 1", game.getCurrentPlayerName());

        List<Integer> player1Pits = game.getPlayer1().getPits();
        List<Integer> player2Pits = game.getPlayer2().getPits();

        assertEquals(14, player1Pits.size());
        assertEquals(14, player2Pits.size());
        assertEquals(0, player1Pits.get(6));
        assertEquals(0, player2Pits.get(13));
    }

    @Test
    public void testMoveStones() {
        Game game = mancalaService.createGame();

        assertEquals("Player 1", game.getCurrentPlayerName());

        mancalaService.moveStones(1, 0);

        List<Integer> player1Pits = game.getPlayer1().getPits();
        List<Integer> player2Pits = game.getPlayer2().getPits();

        assertEquals(0, player1Pits.get(0));
        assertEquals(7, player1Pits.get(1));
        assertEquals(6, player2Pits.get(7));
        assertEquals(6, player2Pits.get(8));

        assertEquals("Player 2", game.getCurrentPlayerName());
    }

    @Test
    public void testGetOppositeIndex() {
        int oppositeIndex = mancalaService.getOppositeIndex(0);
        assertEquals(12, oppositeIndex);

        oppositeIndex = mancalaService.getOppositeIndex(5);
        assertEquals(7, oppositeIndex);
    }

    @Test
    public void testGetCurrentPlayer() {
        Game game = mancalaService.createGame();

        assertEquals(game.getPlayer1(), mancalaService.getCurrentPlayer(1));
        assertEquals(game.getPlayer2(), mancalaService.getCurrentPlayer(2));
    }
}
