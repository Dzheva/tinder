package application.entities;

import application.models.User;

import java.util.List;

public final class SessionData {
    public final User user;
    public final List<User> usersToShow;
    public int nextUserIndex;

    public SessionData(User user, List<User> usersToShow, int nextUserIndex) {
        this.user = user;
        this.usersToShow = usersToShow;
        this.nextUserIndex = nextUserIndex;
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "user=" + user +
                ", usersToShow=" + usersToShow +
                ", nextUserIndex=" + nextUserIndex +
                '}';
    }
}