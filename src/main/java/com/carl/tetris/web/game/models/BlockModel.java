package com.carl.tetris.web.game.models;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Value
public class BlockModel {
    private int y;
    private int x;
    private String blockType;
}
