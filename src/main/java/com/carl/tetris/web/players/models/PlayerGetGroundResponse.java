package com.carl.tetris.web.players.models;

import com.carl.tetris.web.game.models.BlockModel;
import lombok.Data;
import lombok.Value;

import java.util.List;

/**
 * Response model for getting current ground
 */
@Value
public class PlayerGetGroundResponse {
    private String playerToken;
    private String roomName;
    private List<BlockModel> currentBlocks;
}
