package org.zhenhaochen.shortlink.admin.toolkit;

import java.security.SecureRandom;

/**
 * Random Group Id Generator
 */
public final class RandomGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * generate group id
     *
     * @return group id
     */
    public static String generateRandom() {
        return generateRandom(6);
    }

    /**
     * generate group id
     *
     * @param length the length of group id
     * @return group id
     */
    public static String generateRandom(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
