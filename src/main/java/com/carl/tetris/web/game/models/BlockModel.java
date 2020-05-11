package com.carl.tetris.web.game.models;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model to hold the properties of each block
 */
@Value
public class BlockModel {
    private int y;
    private int x;
    private String shape;
}
