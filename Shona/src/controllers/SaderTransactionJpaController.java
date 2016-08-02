/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Cars;
import entities.Client;
import entities.SaderTransaction;
import entities.Users;
import entities.Zalat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bassem
 */
public class SaderTransactionJpaController implements Serializable {

    public SaderTransactionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SaderTransaction saderTransaction) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cars carId = saderTransaction.getCarId();
            if (carId != null) {
                carId = em.getReference(carId.getClass(), carId.getId());
                saderTransaction.setCarId(carId);
            }
            Client clientId = saderTransaction.getClientId();
            if (clientId != null) {
                clientId = em.getReference(clientId.getClass(), clientId.getId());
                saderTransaction.setClientId(clientId);
            }
            Users createdbyid = saderTransaction.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid = em.getReference(createdbyid.getClass(), createdbyid.getId());
                saderTransaction.setCreatedbyid(createdbyid);
            }
            Zalat zalatId = saderTransaction.getZalatId();
            if (zalatId != null) {
                zalatId = em.getReference(zalatId.getClass(), zalatId.getId());
                saderTransaction.setZalatId(zalatId);
            }
            em.persist(saderTransaction);
            if (carId != null) {
                carId.getSaderTransactionCollection().add(saderTransaction);
                carId = em.merge(carId);
            }
            if (clientId != null) {
                clientId.getSaderTransactionCollection().add(saderTransaction);
                clientId = em.merge(clientId);
            }
            if (createdbyid != null) {
                createdbyid.getSaderTransactionCollection().add(saderTransaction);
                createdbyid = em.merge(createdbyid);
            }
            if (zalatId != null) {
                zalatId.getSaderTransactionCollection().add(saderTransaction);
                zalatId = em.merge(zalatId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SaderTransaction saderTransaction) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SaderTransaction persistentSaderTransaction = em.find(SaderTransaction.class, saderTransaction.getId());
            Cars carIdOld = persistentSaderTransaction.getCarId();
            Cars carIdNew = saderTransaction.getCarId();
            Client clientIdOld = persistentSaderTransaction.getClientId();
            Client clientIdNew = saderTransaction.getClientId();
            Users createdbyidOld = persistentSaderTransaction.getCreatedbyid();
            Users createdbyidNew = saderTransaction.getCreatedbyid();
            Zalat zalatIdOld = persistentSaderTransaction.getZalatId();
            Zalat zalatIdNew = saderTransaction.getZalatId();
            if (carIdNew != null) {
                carIdNew = em.getReference(carIdNew.getClass(), carIdNew.getId());
                saderTransaction.setCarId(carIdNew);
            }
            if (clientIdNew != null) {
                clientIdNew = em.getReference(clientIdNew.getClass(), clientIdNew.getId());
                saderTransaction.setClientId(clientIdNew);
            }
            if (createdbyidNew != null) {
                createdbyidNew = em.getReference(createdbyidNew.getClass(), createdbyidNew.getId());
                saderTransaction.setCreatedbyid(createdbyidNew);
            }
            if (zalatIdNew != null) {
                zalatIdNew = em.getReference(zalatIdNew.getClass(), zalatIdNew.getId());
                saderTransaction.setZalatId(zalatIdNew);
            }
            saderTransaction = em.merge(saderTransaction);
            if (carIdOld != null && !carIdOld.equals(carIdNew)) {
                carIdOld.getSaderTransactionCollection().remove(saderTransaction);
                carIdOld = em.merge(carIdOld);
            }
            if (carIdNew != null && !carIdNew.equals(carIdOld)) {
                carIdNew.getSaderTransactionCollection().add(saderTransaction);
                carIdNew = em.merge(carIdNew);
            }
            if (clientIdOld != null && !clientIdOld.equals(clientIdNew)) {
                clientIdOld.getSaderTransactionCollection().remove(saderTransaction);
                clientIdOld = em.merge(clientIdOld);
            }
            if (clientIdNew != null && !clientIdNew.equals(clientIdOld)) {
                clientIdNew.getSaderTransactionCollection().add(saderTransaction);
                clientIdNew = em.merge(clientIdNew);
            }
            if (createdbyidOld != null && !createdbyidOld.equals(createdbyidNew)) {
                createdbyidOld.getSaderTransactionCollection().remove(saderTransaction);
                createdbyidOld = em.merge(createdbyidOld);
            }
            if (createdbyidNew != null && !createdbyidNew.equals(createdbyidOld)) {
                createdbyidNew.getSaderTransactionCollection().add(saderTransaction);
                createdbyidNew = em.merge(createdbyidNew);
            }
            if (zalatIdOld != null && !zalatIdOld.equals(zalatIdNew)) {
                zalatIdOld.getSaderTransactionCollection().remove(saderTransaction);
                zalatIdOld = em.merge(zalatIdOld);
            }
            if (zalatIdNew != null && !zalatIdNew.equals(zalatIdOld)) {
                zalatIdNew.getSaderTransactionCollection().add(saderTransaction);
                zalatIdNew = em.merge(zalatIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = saderTransaction.getId();
                if (findSaderTransaction(id) == null) {
                    throw new NonexistentEntityException("The saderTransaction with id " + id + " no longer exists.");
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
            SaderTransaction saderTransaction;
            try {
                saderTransaction = em.getReference(SaderTransaction.class, id);
                saderTransaction.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saderTransaction with id " + id + " no longer exists.", enfe);
            }
            Cars carId = saderTransaction.getCarId();
            if (carId != null) {
                carId.getSaderTransactionCollection().remove(saderTransaction);
                carId = em.merge(carId);
            }
            Client clientId = saderTransaction.getClientId();
            if (clientId != null) {
                clientId.getSaderTransactionCollection().remove(saderTransaction);
                clientId = em.merge(clientId);
            }
            Users createdbyid = saderTransaction.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid.getSaderTransactionCollection().remove(saderTransaction);
                createdbyid = em.merge(createdbyid);
            }
            Zalat zalatId = saderTransaction.getZalatId();
            if (zalatId != null) {
                zalatId.getSaderTransactionCollection().remove(saderTransaction);
                zalatId = em.merge(zalatId);
            }
            em.remove(saderTransaction);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SaderTransaction> findSaderTransactionEntities() {
        return findSaderTransactionEntities(true, -1, -1);
    }

    public List<SaderTransaction> findSaderTransactionEntities(int maxResults, int firstResult) {
        return findSaderTransactionEntities(false, maxResults, firstResult);
    }

    private List<SaderTransaction> findSaderTransactionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SaderTransaction.class));
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

    public SaderTransaction findSaderTransaction(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SaderTransaction.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaderTransactionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SaderTransaction> rt = cq.from(SaderTransaction.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
