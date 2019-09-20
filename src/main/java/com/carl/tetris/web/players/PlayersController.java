package com.carl.tetris.web.players;

import com.carl.tetris.domain.helper.PlayerToken;
import com.carl.tetris.web.players.models.*;
import com.carl.tetris.web.room.models.RoomStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("player")
public class PlayersController {

    @Autowired
    PlayersService playersService;

    @PostMapping(value = "/join",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerJoinResponse join(@RequestBody PlayerJoinRequest joinRequest, HttpServletRequest request) {
        PlayerJoinResponse response = playersService.joinGame(joinRequest, request.getRemoteAddr());
        return response;
    }

    @PostMapping(value = "/move",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerMoveResponse move(@RequestBody PlayerMoveRequest moveRequest, HttpServletRequest request) {
        PlayerMoveResponse response = playersService.move(moveRequest);
        return response;
    }

    @PostMapping(value = "/addGround",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerAddGroundResponse addGround(@RequestBody PlayerAddGroundRequest addRequest, HttpServletRequest request) {
        PlayerAddGroundResponse response = playersService.addGround(addRequest);
        return response;
    }

    @GetMapping(value = "/position",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerPositionResponse getPosition(@RequestParam("roomName") String roomName, @RequestParam("playerToken") String playerToken) {
        return playersService.getCurrentPosition(roomName, playerToken);
    }

    @GetMapping(value = "/ground",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerGetGroundResponse roomStatus(@RequestParam("roomName") String roomName, @RequestParam("playerToken") String playerToken) {
        return playersService.getGround(roomName, playerToken);
    }
}
