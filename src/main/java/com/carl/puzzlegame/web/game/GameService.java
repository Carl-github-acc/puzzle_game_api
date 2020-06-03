package com.carl.puzzlegame.web.game;

import com.carl.puzzlegame.web.game.models.BlockModel;
import com.carl.puzzlegame.web.game.models.GameModel;
import com.carl.puzzlegame.web.game.models.ShapeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage current game parameters (blocks in place, shapes been controlled etc.)
 */
@Service
public class GameService {
    /**
     * Create a new player for the game
     * @param playerToken - Player token
     * @param game - Game associated with the player
     * @return The current game
     */
    public GameModel createNewPlayer(String playerToken, GameModel game) {
        game.getPlayerTokens().add(playerToken);
        game.getPlayerGroundMap().put(playerToken, new ArrayList<>());
        game.getPlayerScoreMap().put(playerToken, 0);
        return game;
    }

    /**
     * Create a new game
     * @param playerToken - Player token
     * @return The new game
     */
    public GameModel createNewGame(String playerToken) {
        GameModel game = new GameModel();
        return createNewPlayer(playerToken, game);
    }

    /**
     * Move the shape the player is controlling in the game
     * @param playerToken - Player token
     * @param movePosition - New shape position
     * @param game - Game associated with the player
     * @return The new shape position
     */
    public ShapeModel movePlayer(String playerToken, ShapeModel movePosition, GameModel game) {
        game.getCurrentShapeControlledMap().put(playerToken, movePosition);
        return game.getCurrentShapeControlledMap().get(playerToken);
    }

    /**
     * Add new blocks to the ground
     * @param playerToken - Player token
     * @param addBlocks - Blocks to be added
     * @param game - Game associated with the player
     * @return The new ground
     */
    public List<BlockModel> addGround(String playerToken, List<BlockModel> addBlocks, GameModel game) {
        if (game.getPlayerGroundMap().containsKey(playerToken)) {
            game.getPlayerGroundMap().get(playerToken).addAll(addBlocks);
        } else {
            game.getPlayerGroundMap().put(playerToken, addBlocks);
        }
        return game.getPlayerGroundMap().get(playerToken);
    }

    /**
     * Get current position of the block based on the player
     * @param playerToken - Player token
     * @param game - Game associated with the player
     * @return The current position of the shape that's been controlled by the player
     */
    public ShapeModel getPosition(String playerToken, GameModel game) {
        return game.getCurrentShapeControlledMap().get(playerToken);
    }

    /**
     * Fetch the current ground
     * @param playerToken - Player token
     * @param game - Game associated with the player
     * @return Current ground
     */
    public List<BlockModel> getGround(String playerToken, GameModel game) {
        return game.getPlayerGroundMap().get(playerToken);
    }
}
