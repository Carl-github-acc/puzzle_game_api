package com.carl.tetris.web.game.models;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model used to hold current game parameters
 */
@Value
public class GameModel {

    private LocalDateTime startTime = LocalDateTime.now();
    private List<String> playerTokens = new ArrayList<>();
    private Map<String, List<BlockModel>> playerGroundMap = new HashMap<>();
    private Map<String, Integer> playerScoreMap = new HashMap<>();
    private Map<String, ShapeModel> currentShapeControlledMap = new HashMap<>();
}
