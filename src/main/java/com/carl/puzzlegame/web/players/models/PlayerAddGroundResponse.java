package com.carl.puzzlegame.web.players.models;

import com.carl.puzzlegame.web.game.models.BlockModel;
import lombok.Value;

import java.util.List;

/**
 * Response model for adding new ground
 */
@Value
public class PlayerAddGroundResponse {
    private String playerToken;
    private String roomName;
    private List<BlockModel> addBlocks;
    private List<BlockModel> currentBlocks;
}
