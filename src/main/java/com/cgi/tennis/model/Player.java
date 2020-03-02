package com.cgi.tennis.model;

import java.util.Objects;
import java.util.UUID;

import static org.apache.commons.lang3.Validate.notBlank;

public class Player {

    private String uuid = UUID.randomUUID().toString();
    private String name;

    public Player(String name) {
        notBlank(name, "name should not be blank");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return uuid.equals(player.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
