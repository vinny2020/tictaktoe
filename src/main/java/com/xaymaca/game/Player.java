package com.xaymaca.game;

import java.util.Objects;

public class Player {
    private String mark ;
    private boolean first = false;

    public Player(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setFirst() {
       first = true;
    }

    public boolean isFirst(){
        return first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(first, player.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
