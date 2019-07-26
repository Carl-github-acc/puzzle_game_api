package com.carl.tetris.web.players;

import com.carl.tetris.domain.helper.PlayerToken;
import com.carl.tetris.web.game.models.BlockModel;
import com.carl.tetris.web.game.models.GameModel;
import com.carl.tetris.web.game.models.GameService;
import com.carl.tetris.web.players.models.*;
import com.carl.tetris.web.room.RoomService;
import com.carl.tetris.web.room.models.JoinRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayersService {
    @Autowired
    private RoomService roomService;

    @Autowired
    private GameService gameService;

    private Map<String, GameModel> roomNameGameMap = new HashMap<>();

    public PlayerJoinResponse joinGame(PlayerJoinRequest playerJoinRequest, String ipAddress) {
        String playerToken = PlayerToken.generatePlayerToken(ipAddress);
        JoinRoomResponse joinResponse = roomService.joinRoom(playerJoinRequest, playerToken);
        return new PlayerJoinResponse(joinResponse.getStatus().name(), playerToken, joinResponse.getPlayersInRoom(), joinResponse.getReadyToStart());
    }

    public PlayerMoveResponse move(PlayerMoveRequest moveRequest) {
        GameModel game = roomService.getGame(moveRequest.getRoomName());
        BlockModel movedPosition = gameService.movePlayer(moveRequest.getPlayerToken(), moveRequest.getMoveToPosition(), game);
        return new PlayerMoveResponse(moveRequest.getPlayerToken(), moveRequest.getRoomName(), moveRequest.getMoveToPosition(), movedPosition);
    }

    public PlayerAddGroundResponse addGround(PlayerAddGroundRequest addRequest) {
        GameModel game = roomService.getGame(addRequest.getRoomName());
        List<BlockModel> currentGround = gameService.addGround(addRequest.getPlayerToken(), addRequest.getAddBlocks() , game);
        return new PlayerAddGroundResponse(addRequest.getPlayerToken(), addRequest.getRoomName(), addRequest.getAddBlocks(), currentGround);
    }

    public PlayerPositionResponse getCurrentPosition(String playerToken, String roomName) {
        GameModel game = roomService.getGame(roomName);
        return new PlayerPositionResponse(playerToken, roomName, gameService.getPosition(playerToken, game));
    }

    public PlayerGetGroundResponse getGround(String playerToken, String roomName) {
        GameModel game = roomService.getGame(roomName);
        return new PlayerGetGroundResponse(playerToken, roomName, gameService.getGround(playerToken, game));
    }
}
