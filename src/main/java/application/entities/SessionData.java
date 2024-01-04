package application.entities;

import application.models.User;
import application.repositories.Repository;

import java.util.List;

public final class SessionData {
    public final int userId;
    public List<User> carouselUsers;
    public int carouselUserIndex;
    public int chatTargetUserId;

    public SessionData(int userId, List<User> carouselUsers, int carouselUserIndex) {
        this.userId = userId;
        this.carouselUsers = carouselUsers;
        this.carouselUserIndex = carouselUserIndex;
    }

    public User getUser() {
        return new Repository().getEntity(User.class, userId);
    }

    public User getChatTargetUser() {
        if (chatTargetUserId == 0) return null;
        return new Repository().getEntity(User.class, chatTargetUserId);
    }
}