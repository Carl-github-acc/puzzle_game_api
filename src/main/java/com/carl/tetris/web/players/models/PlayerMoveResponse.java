package com.carl.tetris.web.players.models;

import com.carl.tetris.web.game.models.BlockModel;
import com.carl.tetris.web.game.models.ShapeModel;
import lombok.Data;
import lombok.Value;

@Value
public class PlayerMoveResponse {
    private String playerToken;
    private String roomName;
    private ShapeModel requestShape;
    private ShapeModel currentShape;
}
