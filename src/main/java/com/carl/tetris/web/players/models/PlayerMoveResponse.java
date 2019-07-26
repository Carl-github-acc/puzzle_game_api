package com.carl.tetris.web.players.models;

import com.carl.tetris.web.game.models.BlockModel;
import lombok.Data;
import lombok.Value;

@Value
public class PlayerMoveResponse {
    private String playerToken;
    private String roomName;
    private BlockModel moveToPosition;
    private BlockModel currentPosition;
}
