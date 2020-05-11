package com.carl.tetris.web.game;

import com.carl.tetris.web.game.models.BlockModel;
import com.carl.tetris.web.game.models.GameModel;
import com.carl.tetris.web.game.models.ShapeModel;
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
     * @param playerToken
     * @param game
     * @return
     */
    public GameModel createNewPlayer(String playerToken, GameModel game) {
        game.getPlayerTokens().add(playerToken);
        game.getPlayerGroundMap().put(playerToken, new ArrayList<>());
        game.getPlayerScoreMap().put(playerToken, 0);
        return game;
    }

    public GameModel createNewGame(String playerToken) {
        GameModel game = new GameModel();
        return createNewPlayer(playerToken, game);
    }

    public ShapeModel movePlayer(String playerToken, ShapeModel movePosition, GameModel game) {
        game.getCurrentShapeControlledMap().put(playerToken, movePosition);
        return game.getCurrentShapeControlledMap().get(playerToken);
    }

    public List<BlockModel> addGround(String playerToken, List<BlockModel> addBlocks, GameModel game) {
        if (game.getPlayerGroundMap().containsKey(playerToken)) {
            game.getPlayerGroundMap().get(playerToken).addAll(addBlocks);
        } else {
            game.getPlayerGroundMap().put(playerToken, addBlocks);
        }
        return game.getPlayerGroundMap().get(playerToken);
    }

    public ShapeModel getPosition(String playerToken, GameModel game) {
        return game.getCurrentShapeControlledMap().get(playerToken);
    }

    public List<BlockModel> getGround(String playerToken, GameModel game) {
        return game.getPlayerGroundMap().get(playerToken);
    }
}
