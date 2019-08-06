package com.carl.tetris.web.players.models;

import com.carl.tetris.web.game.models.BlockModel;
import com.carl.tetris.web.game.models.ShapeModel;
import lombok.Value;

import java.util.List;

@Value
public class PlayerPositionResponse {
    private String playerToken;
    private String roomName;
    private ShapeModel shape;
}
