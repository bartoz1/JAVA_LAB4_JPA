package com.rushkappa;

import com.rushkappa.entity.Mage;
import com.rushkappa.entity.Tower;
import com.rushkappa.repository.MageRepository;
import com.rushkappa.repository.TowerRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello new project in Maven - Java 15");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4Pu");

        MageRepository mageRepository = new MageRepository(emf);
        TowerRepository towerRepository = new TowerRepository(emf);




//        Tower chosen = towerRepository.find(tower1.getName());
//        mage1.setTower(chosen);
//        mage2.setTower(chosen);
//        chosen = towerRepository.find(tower2.getName());
//        mage3.setTower(chosen);

        Tower tower1 = new Tower("WiezaPG", 40);
        Tower tower2 = new Tower("TheLonelyPillar", 99);
        towerRepository.add(tower1);
        towerRepository.add(tower2);

        Mage mage1 = new Mage("Merlin", 99, tower1);
        Mage mage2 = new Mage("Artur", 1,tower1);
        Mage mage3 = new Mage("Veigar", 27, tower2);

        mageRepository.add(mage1);
        mageRepository.add(mage2);
        mageRepository.add(mage3);


//        mage1.sendToTower(tower2);
//        mage2.sendToTower(tower1);
//        mage3.sendToTower(tower1);

        boolean runApp = true;
        Scanner scanner = new Scanner(System.in);

        while (runApp) {
            showMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Mage name and level:");
                    String name = scanner.next();
                    int level = scanner.nextInt();
                    Mage newMage = new Mage(name, level, tower1);
                    mageRepository.add(newMage);
                    //newMage.sendToTower(tower1);
                    break;
                case 2:
                    System.out.println("Tower name and height:");
                    name = scanner.next();
                    int height = scanner.nextInt();
                    Tower newTower = new Tower(name, height);
                    towerRepository.add(newTower);
                    break;
                case 3:
                    System.out.println("Level grater than:");
                    level = scanner.nextInt();
                    for (Mage m: mageRepository.findMagesByTowerAndLvl(tower1, level)) {
                        System.out.println(m.toString());
                    }
                    break;
                case 4:
                    System.out.println("Height grater than:");
                    height = scanner.nextInt();
                    for (Tower t: towerRepository.findAll()) {
                        System.out.println(t.toString());
                    }

                    /*System.out.println("TEST");
                    for (Mage m: tower1.getMages()) {
                        System.out.println(m.toString());
                    }*/
                    break;
                case 5:
                    runApp = false;
                    break;
                default:
                    System.out.println("Wrong value!");
            }
        }


        emf.close();
    }
    public static void showMenu() {
        System.out.println("Menu:");
        System.out.println("1: add mage");
        System.out.println("2: add tower");
        System.out.println("3: show mages with lvl >");
        System.out.println("4: show towers with height >");
        System.out.println("5: exit");
    }
}
