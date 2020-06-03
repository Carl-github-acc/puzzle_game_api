package com.carl.puzzlegame.web.room;

import com.carl.puzzlegame.domain.code.RoomStatus;
import com.carl.puzzlegame.web.game.models.GameModel;
import com.carl.puzzlegame.web.game.GameService;
import com.carl.puzzlegame.web.players.models.PlayerJoinRequest;
import com.carl.puzzlegame.web.room.models.JoinRoomResponse;
import com.carl.puzzlegame.web.room.models.RoomStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.carl.puzzlegame.domain.code.JoinRoomStatus.*;

/**
 * Service class handling requests that control aspects of the room
 */
@Service
public class RoomService {
    @Autowired
    private GameService gameService;

    private static int MAX_ROOM_SIZE = 100;
    /**
     * Temporary limit on the maximum number of players that can be joined into the game
     * this number will be customizable by the user in the future
     */
    private static int MAX_NUM_PLAYERS = 2;

    /**
     * Used to automatically delete game rooms when it reachs the maximum allotted room number
     */
    private Map<String, GameModel> roomNameGameMap = new LinkedHashMap<String, GameModel>() {
        protected boolean removeEldestEntry(Map.Entry<String, GameModel> eldest)
        {
            return size() > MAX_ROOM_SIZE;
        }
    };

    /**
     * Join a room
     *
     * @param playerJoinRequest - Player join room rquest
     * @param playerToken - Player token
     * @return Status indicating whether joining the room succeeded or not
     */
    public JoinRoomResponse joinRoom(PlayerJoinRequest playerJoinRequest, String playerToken) {
        if (roomNameGameMap.containsKey(playerJoinRequest.getRoomName())) {
            if (roomNameGameMap.get(playerJoinRequest.getRoomName()).getPlayerTokens().size() >= MAX_NUM_PLAYERS) {
                return new JoinRoomResponse(MAX_PLAYERS_REACHED, roomNameGameMap.get(playerJoinRequest.getRoomName()).getPlayerTokens(), false);
            }
            gameService.createNewPlayer(playerToken, roomNameGameMap.get(playerJoinRequest.getRoomName()));
            List<String> currentPlayers = roomNameGameMap.get(playerJoinRequest.getRoomName()).getPlayerTokens();
            return new JoinRoomResponse(JOINED, currentPlayers, currentPlayers.size() >= MAX_NUM_PLAYERS);
        } else {
            roomNameGameMap.put(playerJoinRequest.getRoomName(), gameService.createNewGame(playerToken));
            return new JoinRoomResponse(NEW_ROOM_CREATED, roomNameGameMap.get(playerJoinRequest.getRoomName()).getPlayerTokens(), false);
        }
    }

    /**
     * Gets the current room status
     * @param roomName - room name
     * @return The current room status
     */
     RoomStatusResponse getRoomStatus(String roomName) {
        if (roomNameGameMap.containsKey(roomName)) {
            if (roomNameGameMap.get(roomName).getPlayerTokens().size() >= MAX_NUM_PLAYERS) {
                return new RoomStatusResponse(RoomStatus.READY.name(), roomNameGameMap.get(roomName).getPlayerTokens(), true);
            }
            return new RoomStatusResponse(RoomStatus.NEED_MORE_PLAYERS.name(), new ArrayList<>(), false);
        } else {
            return new RoomStatusResponse(RoomStatus.EMPTY_ROOM.name(), new ArrayList<>(), false);
        }
    }

    /**
     * Gets the game that's associated with the room
     * @param roomName
     * @return The game that's associated with the room
     */
    public GameModel getGame(String roomName) {
        return roomNameGameMap.get(roomName);
    }

    /**
     * Automatic scheduler used to remove old game rooms
     */
    @Scheduled(fixedRate = 1800000)
    private void roomOldGames() {
        List<String> removeOldGames = new ArrayList<>();
        for (String roomName: roomNameGameMap.keySet()) {
            if (LocalDateTime.now().isAfter(roomNameGameMap.get(roomName).getStartTime().plusMinutes(30))) {
                removeOldGames.add(roomName);
            }
        }
        roomNameGameMap.keySet().removeIf(removeOldGames::contains);
    }
}
