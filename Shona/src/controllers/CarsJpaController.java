/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Cars;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Users;
import entities.SaderTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author bassem
 */
public class CarsJpaController extends BaseController implements Serializable {

    public void create(Cars cars) {
        if (cars.getSaderTransactionCollection() == null) {
            cars.setSaderTransactionCollection(new ArrayList<SaderTransaction>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users createdbyid = cars.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid = em.getReference(createdbyid.getClass(), createdbyid.getId());
                cars.setCreatedbyid(createdbyid);
            }
            Collection<SaderTransaction> attachedSaderTransactionCollection = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionSaderTransactionToAttach : cars.getSaderTransactionCollection()) {
                saderTransactionCollectionSaderTransactionToAttach = em.getReference(saderTransactionCollectionSaderTransactionToAttach.getClass(), saderTransactionCollectionSaderTransactionToAttach.getId());
                attachedSaderTransactionCollection.add(saderTransactionCollectionSaderTransactionToAttach);
            }
            cars.setSaderTransactionCollection(attachedSaderTransactionCollection);
            em.persist(cars);
            if (createdbyid != null) {
                createdbyid.getCarsCollection().add(cars);
                createdbyid = em.merge(createdbyid);
            }
            for (SaderTransaction saderTransactionCollectionSaderTransaction : cars.getSaderTransactionCollection()) {
                Cars oldCarIdOfSaderTransactionCollectionSaderTransaction = saderTransactionCollectionSaderTransaction.getCarId();
                saderTransactionCollectionSaderTransaction.setCarId(cars);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
                if (oldCarIdOfSaderTransactionCollectionSaderTransaction != null) {
                    oldCarIdOfSaderTransactionCollectionSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionSaderTransaction);
                    oldCarIdOfSaderTransactionCollectionSaderTransaction = em.merge(oldCarIdOfSaderTransactionCollectionSaderTransaction);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cars cars) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cars persistentCars = em.find(Cars.class, cars.getId());
            Users createdbyidOld = persistentCars.getCreatedbyid();
            Users createdbyidNew = cars.getCreatedbyid();
            Collection<SaderTransaction> saderTransactionCollectionOld = persistentCars.getSaderTransactionCollection();
            Collection<SaderTransaction> saderTransactionCollectionNew = cars.getSaderTransactionCollection();
            if (createdbyidNew != null) {
                createdbyidNew = em.getReference(createdbyidNew.getClass(), createdbyidNew.getId());
                cars.setCreatedbyid(createdbyidNew);
            }
            Collection<SaderTransaction> attachedSaderTransactionCollectionNew = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionNewSaderTransactionToAttach : saderTransactionCollectionNew) {
                saderTransactionCollectionNewSaderTransactionToAttach = em.getReference(saderTransactionCollectionNewSaderTransactionToAttach.getClass(), saderTransactionCollectionNewSaderTransactionToAttach.getId());
                attachedSaderTransactionCollectionNew.add(saderTransactionCollectionNewSaderTransactionToAttach);
            }
            saderTransactionCollectionNew = attachedSaderTransactionCollectionNew;
            cars.setSaderTransactionCollection(saderTransactionCollectionNew);
            cars = em.merge(cars);
            if (createdbyidOld != null && !createdbyidOld.equals(createdbyidNew)) {
                createdbyidOld.getCarsCollection().remove(cars);
                createdbyidOld = em.merge(createdbyidOld);
            }
            if (createdbyidNew != null && !createdbyidNew.equals(createdbyidOld)) {
                createdbyidNew.getCarsCollection().add(cars);
                createdbyidNew = em.merge(createdbyidNew);
            }
            for (SaderTransaction saderTransactionCollectionOldSaderTransaction : saderTransactionCollectionOld) {
                if (!saderTransactionCollectionNew.contains(saderTransactionCollectionOldSaderTransaction)) {
                    saderTransactionCollectionOldSaderTransaction.setCarId(null);
                    saderTransactionCollectionOldSaderTransaction = em.merge(saderTransactionCollectionOldSaderTransaction);
                }
            }
            for (SaderTransaction saderTransactionCollectionNewSaderTransaction : saderTransactionCollectionNew) {
                if (!saderTransactionCollectionOld.contains(saderTransactionCollectionNewSaderTransaction)) {
                    Cars oldCarIdOfSaderTransactionCollectionNewSaderTransaction = saderTransactionCollectionNewSaderTransaction.getCarId();
                    saderTransactionCollectionNewSaderTransaction.setCarId(cars);
                    saderTransactionCollectionNewSaderTransaction = em.merge(saderTransactionCollectionNewSaderTransaction);
                    if (oldCarIdOfSaderTransactionCollectionNewSaderTransaction != null && !oldCarIdOfSaderTransactionCollectionNewSaderTransaction.equals(cars)) {
                        oldCarIdOfSaderTransactionCollectionNewSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionNewSaderTransaction);
                        oldCarIdOfSaderTransactionCollectionNewSaderTransaction = em.merge(oldCarIdOfSaderTransactionCollectionNewSaderTransaction);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cars.getId();
                if (findCars(id) == null) {
                    throw new NonexistentEntityException("The cars with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cars cars;
            try {
                cars = em.getReference(Cars.class, id);
                cars.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cars with id " + id + " no longer exists.", enfe);
            }
            Users createdbyid = cars.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid.getCarsCollection().remove(cars);
                createdbyid = em.merge(createdbyid);
            }
            Collection<SaderTransaction> saderTransactionCollection = cars.getSaderTransactionCollection();
            for (SaderTransaction saderTransactionCollectionSaderTransaction : saderTransactionCollection) {
                saderTransactionCollectionSaderTransaction.setCarId(null);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
            }
            em.remove(cars);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cars> findCarsEntities() {
        return findCarsEntities(true, -1, -1);
    }

    public List<Cars> findCarsEntities(int maxResults, int firstResult) {
        return findCarsEntities(false, maxResults, firstResult);
    }

    private List<Cars> findCarsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cars.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cars findCars(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cars.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cars> rt = cq.from(Cars.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
