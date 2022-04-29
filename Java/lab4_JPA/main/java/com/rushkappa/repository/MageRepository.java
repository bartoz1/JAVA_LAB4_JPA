package com.rushkappa.repository;

import com.rushkappa.entity.Mage;
import com.rushkappa.entity.Tower;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MageRepository extends JpaRepository<Mage, String> {

    /**
     * @param emf   thread safe factory which will be used for creating {@link EntityManager}
     */
    public MageRepository(EntityManagerFactory emf) {
        super(emf, Mage.class);
    }

    public List<Mage> findAllWithLevelGraterThan(int level){
        EntityManager em = getEmf().createEntityManager();
        List<Mage> list = em.createQuery("select mage from Mage mage where mage.level > " + level, Mage.class)
                //.setParameter("level", level)
                .getResultList();
        em.close();
        return list;
    }

    public List<Mage> findMagesByTowerAndLvl(Tower tower, int level) {
        EntityManager em = getEmf().createEntityManager();
        List<Mage> list = em
                .createQuery("SELECT m FROM Mage m WHERE m.tower = :tower AND m.level > :lvl", Mage.class)
                .setParameter("tower",tower)
                .setParameter("lvl", level)
                .getResultList();
        return list;
    }
}
