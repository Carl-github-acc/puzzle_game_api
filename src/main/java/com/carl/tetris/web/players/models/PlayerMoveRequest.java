package com.carl.tetris.web.players.models;

import com.carl.tetris.web.game.models.BlockModel;
import lombok.Data;

@Data
public class PlayerMoveRequest {
    private String playerToken;
    private String roomName;
    private BlockModel moveToPosition;

    public PlayerMoveRequest() {}
}
