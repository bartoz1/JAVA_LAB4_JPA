package com.rushkappa.repository;

import com.rushkappa.entity.Mage;
import com.rushkappa.entity.Tower;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TowerRepository extends JpaRepository<Tower, String>{

    /**
     * @param emf   thread safe factory which will be used for creating {@link EntityManager}
     */
    public TowerRepository(EntityManagerFactory emf) {
        super(emf, Tower.class);
    }

    public List<Tower> findAllWithHeightGraterThan(int height){
        EntityManager em = getEmf().createEntityManager();
        List<Tower> list = em.createQuery("select tower from Tower tower where tower.height > " + height, Tower.class)
                //.setParameter("height", height)
                .getResultList();
        em.close();
        return list;
    }
}
