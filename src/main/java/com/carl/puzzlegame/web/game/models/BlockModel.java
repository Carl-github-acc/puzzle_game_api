package com.carl.puzzlegame.web.game.models;

import lombok.Value;

/**
 * Model to hold the properties of each block
 */
@Value
public class BlockModel {
    private int y;
    private int x;
    private String shape;
}
