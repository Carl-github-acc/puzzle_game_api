package com.carl.puzzlegame.web.game.models;

import lombok.Value;

/**
 * Model used to hold properties about the current status of the shape
 */
@Value
public class ShapeModel {
    private int y;
    private int x;
    private String shape;
    private int rotation;
}
