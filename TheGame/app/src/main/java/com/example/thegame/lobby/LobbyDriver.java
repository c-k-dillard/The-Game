package com.example.thegame.lobby;

import java.util.ArrayList;
import java.util.List;

public final class LobbyDriver {
    private String lobbyName = null;
    private List<String> options = new ArrayList<>();

    public LobbyDriver(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public LobbyDriver() {
    }

    public void add(String n) {
        options.add(n);
    }

    public void del(String n) {
        for (int i = 0; i < options.size(); ++i) {
            if (options.get(i).equals(n)) {
                options.remove(i);
            }
        }
    }

    public List<String> getOptions() {
        return options;
    }
}
