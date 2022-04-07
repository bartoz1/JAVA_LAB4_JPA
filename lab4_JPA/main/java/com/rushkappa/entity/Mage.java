package com.rushkappa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.flogger.Flogger;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
public class Mage {
    @Id
    @Getter
    private String name;

    @Getter
    @Setter
    private int level;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tower")
    private Tower tower;

    public Mage() {}

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=" + tower +
                '}';
    }
}
