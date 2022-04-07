package com.rushkappa;

import java.util.Optional;

public class MageController {
    private MageRepository repository;
    public MageController(MageRepository repository) {
        this.repository = repository;
    }
    public String find(String name) {

        Optional<Mage> optMage = repository.find(name);
        if (optMage.isEmpty()) {
            return "not found";
        }
        return optMage.get().toString();

    }
    public String delete(String name) {
        String odp = "done";
        try {
            repository.delete(name);

        } catch (IllegalArgumentException e) {
            odp = "not found";
        }
        return odp;
    }
    public String save(String name, String level) {
        String odp = "done";
        try {
            Mage mage = new Mage(name, Integer.parseInt(level));
            repository.save(mage);

        } catch (IllegalArgumentException e) {
            odp = "bad request";
        }
        return odp;
    }

}
