package com.carl.tetris.web.players.models;

import com.carl.tetris.web.game.models.BlockModel;
import lombok.Data;

import java.util.List;

@Data
public class PlayerAddGroundRequest {
    private String playerToken;
    private String roomName;
    private List<BlockModel> addBlocks;

    public PlayerAddGroundRequest() {}
}
