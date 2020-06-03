package com.carl.puzzlegame.web.players;

import com.carl.puzzlegame.web.players.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller used to handle aspects of the player
 */
@RestController
@RequestMapping("player")
public class PlayersController {

    PlayersService playersService;

    public PlayersController(@Autowired PlayersService playersService) {
        this.playersService = playersService;
    }

    /**
     * Controller used by players to join a game
     * @param joinRequest - Join request
     * @param request - HTTP request
     * @return A response indicating whether the join request succeeded or not
     */
    @PostMapping(value = "/join",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerJoinResponse join(@RequestBody PlayerJoinRequest joinRequest, HttpServletRequest request) {
        PlayerJoinResponse response = playersService.joinGame(joinRequest, request.getRemoteAddr());
        return response;
    }

    /**
     * Controller to move a player controlled shape
     * @param moveRequest - Request to move the puzzle game shape
     * @return The new position the shape is moved to
     */
    @PostMapping(value = "/move",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerMoveResponse move(@RequestBody PlayerMoveRequest moveRequest) {
        PlayerMoveResponse response = playersService.move(moveRequest);
        return response;
    }

    /**
     * Add ground blocks to the player
     * @param addRequest - Add ground request
     * @return The new ground block
     */
    @PostMapping(value = "/addGround",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerAddGroundResponse addGround(@RequestBody PlayerAddGroundRequest addRequest) {
        PlayerAddGroundResponse response = playersService.addGround(addRequest);
        return response;
    }

    /**
     * Get the current position of the player
     * @param roomName - room name
     * @param playerToken - player token
     * @return Current position of the shape controlled by the player
     */
    @GetMapping(value = "/position",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerPositionResponse getPosition(@RequestParam("roomName") String roomName, @RequestParam("playerToken") String playerToken) {
        return playersService.getCurrentPosition(roomName, playerToken);
    }

    /**
     * Get the current ground blocks
     * @param roomName - Room name
     * @param playerToken - Player token
     * @return The current ground blocks
     */
    @GetMapping(value = "/ground",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerGetGroundResponse roomStatus(@RequestParam("roomName") String roomName, @RequestParam("playerToken") String playerToken) {
        return playersService.getGround(roomName, playerToken);
    }
}
