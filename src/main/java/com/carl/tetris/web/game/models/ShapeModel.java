package com.carl.tetris.web.game.models;

import lombok.Value;

@Value
public class ShapeModel {
    private int y;
    private int x;
    private String shape;
    private int rotation;
}
