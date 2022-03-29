package com.rushkappa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Entity
public class Tower {
    @Id
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    @OneToMany(mappedBy = "tower", fetch = FetchType.EAGER)
    private List<Mage> mages = new ArrayList<>();

    public Tower(){};

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
    }


    @Override
    public String toString() {
        return "Tower{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
