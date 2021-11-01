package alistair.dashboard.utilities;

import alistair.dashboard.models.User;

public final class Session {
    private static User sessionUser;

    public static User getSessionUser() {
        return sessionUser;
    }

    public static void setSessionUser(User user) {
        sessionUser = user;
    }
}
