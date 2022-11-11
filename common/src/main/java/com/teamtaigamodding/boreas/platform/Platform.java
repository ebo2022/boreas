package com.teamtaigamodding.boreas.platform;

public final class Platform {

    private Platform() {
    }

    /**
     * Throw an {@link AssertionError}.
     */
    public static <T> T error() {
        throw new AssertionError();
    }
}
