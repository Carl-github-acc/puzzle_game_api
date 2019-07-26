package com.carl.tetris.web.game.models;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Value
public class GameModel {

    private List<String> playerTokens = new ArrayList<>();
    private Map<String, List<BlockModel>> playerGroundMap = new HashMap<>();
    private Map<String, Integer> playerScoreMap = new HashMap<>();
    private Map<String, BlockModel> currentShapeControlledMap = new HashMap<>();
}
