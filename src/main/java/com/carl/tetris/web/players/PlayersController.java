package com.carl.tetris.web.players;

import com.carl.tetris.web.players.models.*;
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
     * @param moveRequest - Request to move the tetris shape
     * @param request - HTTP request
     * @return The new position the shape is moved to
     */
    @PostMapping(value = "/move",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerMoveResponse move(@RequestBody PlayerMoveRequest moveRequest, HttpServletRequest request) {
        PlayerMoveResponse response = playersService.move(moveRequest);
        return response;
    }

    /**
     *
     * @param addRequest
     * @param request
     * @return
     */
    @PostMapping(value = "/addGround",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerAddGroundResponse addGround(@RequestBody PlayerAddGroundRequest addRequest, HttpServletRequest request) {
        PlayerAddGroundResponse response = playersService.addGround(addRequest);
        return response;
    }

    /**
     *
     * @param roomName
     * @param playerToken
     * @return
     */
    @GetMapping(value = "/position",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerPositionResponse getPosition(@RequestParam("roomName") String roomName, @RequestParam("playerToken") String playerToken) {
        return playersService.getCurrentPosition(roomName, playerToken);
    }

    /**
     *
     * @param roomName
     * @param playerToken
     * @return
     */
    @GetMapping(value = "/ground",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerGetGroundResponse roomStatus(@RequestParam("roomName") String roomName, @RequestParam("playerToken") String playerToken) {
        return playersService.getGround(roomName, playerToken);
    }
}
