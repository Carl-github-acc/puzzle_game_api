package com.carl.tetris.web.players;

import com.carl.tetris.domain.helper.PlayerToken;
import com.carl.tetris.web.game.models.BlockModel;
import com.carl.tetris.web.game.models.GameModel;
import com.carl.tetris.web.game.GameService;
import com.carl.tetris.web.game.models.ShapeModel;
import com.carl.tetris.web.players.models.*;
import com.carl.tetris.web.room.RoomService;
import com.carl.tetris.web.room.models.JoinRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Used to handle commands directed by the player
 */
@Service
public class PlayersService {
    private RoomService roomService;
    private GameService gameService;

    public PlayersService(@Autowired RoomService roomService, @Autowired GameService gameService) {
        this.roomService = roomService;
        this.gameService = gameService;
    }

    public PlayerJoinResponse joinGame(PlayerJoinRequest playerJoinRequest, String ipAddress) {
        String playerToken = PlayerToken.generatePlayerToken(String.valueOf(System.currentTimeMillis()));
        JoinRoomResponse joinResponse = roomService.joinRoom(playerJoinRequest, playerToken);
        return new PlayerJoinResponse(playerJoinRequest.getRoomName(), joinResponse.getStatus().name(), playerToken, joinResponse.getPlayersInRoom(), joinResponse.getReadyToStart());
    }

    public PlayerMoveResponse move(PlayerMoveRequest moveRequest) {
        GameModel game = roomService.getGame(moveRequest.getRoomName());
        ShapeModel movedPosition = gameService.movePlayer(moveRequest.getPlayerToken(), moveRequest.getShape(), game);
        return new PlayerMoveResponse(moveRequest.getPlayerToken(), moveRequest.getRoomName(), moveRequest.getShape(), movedPosition);
    }

    public PlayerAddGroundResponse addGround(PlayerAddGroundRequest addRequest) {
        GameModel game = roomService.getGame(addRequest.getRoomName());
        List<BlockModel> currentGround = gameService.addGround(addRequest.getPlayerToken(), addRequest.getAddBlocks() , game);
        return new PlayerAddGroundResponse(addRequest.getPlayerToken(), addRequest.getRoomName(), addRequest.getAddBlocks(), currentGround);
    }

    public PlayerPositionResponse getCurrentPosition(String roomName, String playerToken) {
        GameModel game = roomService.getGame(roomName);
        return new PlayerPositionResponse(playerToken, roomName, gameService.getPosition(playerToken, game));
    }

    public PlayerGetGroundResponse getGround(String roomName, String playerToken) {
        GameModel game = roomService.getGame(roomName);
        return new PlayerGetGroundResponse(playerToken, roomName, gameService.getGround(playerToken, game));
    }
}
