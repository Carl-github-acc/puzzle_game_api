package com.carl.puzzlegame.web.players;

import com.carl.puzzlegame.domain.helper.PlayerToken;
import com.carl.puzzlegame.web.game.models.BlockModel;
import com.carl.puzzlegame.web.game.models.GameModel;
import com.carl.puzzlegame.web.game.GameService;
import com.carl.puzzlegame.web.game.models.ShapeModel;
import com.carl.puzzlegame.web.players.models.*;
import com.carl.puzzlegame.web.room.RoomService;
import com.carl.puzzlegame.web.room.models.JoinRoomResponse;
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

    /**
     * Add a player to a current game
     *
     * TODO: use ip address to create player token (not currently used as it is difficult to test)
     * @param playerJoinRequest - Join request
     * @param ipAddress - Ip address
     * @return A response indicating whether the join request succeeded or not
     */
    PlayerJoinResponse joinGame(PlayerJoinRequest playerJoinRequest, String ipAddress) {
        String playerToken = PlayerToken.generatePlayerToken(String.valueOf(System.currentTimeMillis()));
        JoinRoomResponse joinResponse = roomService.joinRoom(playerJoinRequest, playerToken);
        return new PlayerJoinResponse(playerJoinRequest.getRoomName(), joinResponse.getStatus().name(), playerToken, joinResponse.getPlayersInRoom(), joinResponse.getReadyToStart());
    }

    /**
     * Move the shape controlled by the player to a new position
     * @param moveRequest - Move request
     * @return New shape position
     */
    PlayerMoveResponse move(PlayerMoveRequest moveRequest) {
        GameModel game = roomService.getGame(moveRequest.getRoomName());
        ShapeModel movedPosition = gameService.movePlayer(moveRequest.getPlayerToken(), moveRequest.getShape(), game);
        return new PlayerMoveResponse(moveRequest.getPlayerToken(), moveRequest.getRoomName(), moveRequest.getShape(), movedPosition);
    }

    /**
     * Add ground blocks to the player
     *
     * TODO: Make the server add ground blocks automatically
     *
     * @param addRequest - Add ground blocks request
     * @return
     */
    PlayerAddGroundResponse addGround(PlayerAddGroundRequest addRequest) {
        GameModel game = roomService.getGame(addRequest.getRoomName());
        List<BlockModel> currentGround = gameService.addGround(addRequest.getPlayerToken(), addRequest.getAddBlocks() , game);
        return new PlayerAddGroundResponse(addRequest.getPlayerToken(), addRequest.getRoomName(), addRequest.getAddBlocks(), currentGround);
    }

    /**
     * Get current shape position
     *
     * @param roomName - Room name
     * @param playerToken - Player token
     * @return The player's shape's current position
     */
    PlayerPositionResponse getCurrentPosition(String roomName, String playerToken) {
        GameModel game = roomService.getGame(roomName);
        return new PlayerPositionResponse(playerToken, roomName, gameService.getPosition(playerToken, game));
    }

    /**
     * Get ground blocks currently held by the player
     *
     * @param roomName - Room name
     * @param playerToken - Player token
     * @return Response containing the new ground blocks
     */
    PlayerGetGroundResponse getGround(String roomName, String playerToken) {
        GameModel game = roomService.getGame(roomName);
        return new PlayerGetGroundResponse(playerToken, roomName, gameService.getGround(playerToken, game));
    }
}
