package com.carl.tetris.domain.helper;

import com.google.common.hash.Hashing;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;

/**
 * Utility class to handle player tokens
 */
@UtilityClass
public class PlayerToken {
    /**
     * Generate a player token based on a given ip address
     * @param ipAddress
     * @return player token
     */
    public static String generatePlayerToken(String ipAddress) {
        return Hashing.sha256()
                .hashString(ipAddress, StandardCharsets.UTF_8)
                .toString();
    }
}
