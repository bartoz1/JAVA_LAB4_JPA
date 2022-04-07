package com.rushkappa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection;
    public MageRepository() {
        collection = new ArrayList<Mage>();
    }
    public Optional<Mage> find(String name) {
        for (Mage m : collection) {
            if ( m.getName() == name) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }
    public void delete(String name) throws IllegalArgumentException {
        Optional<Mage> optMage = find(name);

        if (optMage.isEmpty()) {
            throw new IllegalArgumentException("Mage not found");
        }
        collection.remove(optMage.get());

    }
    public void save(Mage mage) {
        Optional<Mage> optMage = find(mage.getName());

        if (!optMage.isEmpty()) {
            throw new IllegalArgumentException("Already in collection");
        }
        collection.add(mage);
    }

}
