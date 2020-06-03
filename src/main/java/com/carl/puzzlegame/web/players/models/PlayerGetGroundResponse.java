package com.carl.puzzlegame.web.players.models;

import com.carl.puzzlegame.web.game.models.BlockModel;
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
