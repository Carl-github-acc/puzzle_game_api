package com.carl.tetris.domain.code;

/**
 * Statuses that can occur when trying to join a room
 */
public enum JoinRoomStatus {
    JOINED,
    NEW_ROOM_CREATED,
    MAX_PLAYERS_REACHED
}
