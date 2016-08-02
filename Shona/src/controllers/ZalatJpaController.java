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
import entities.M7ger;
import entities.Users;
import entities.TwridTransaction;
import java.util.ArrayList;
import java.util.Collection;
import entities.SaderTransaction;
import entities.Zalat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bassem
 */
public class ZalatJpaController implements Serializable {

    public ZalatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Zalat zalat) {
        if (zalat.getTwridTransactionCollection() == null) {
            zalat.setTwridTransactionCollection(new ArrayList<TwridTransaction>());
        }
        if (zalat.getSaderTransactionCollection() == null) {
            zalat.setSaderTransactionCollection(new ArrayList<SaderTransaction>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            M7ger m7grId = zalat.getM7grId();
            if (m7grId != null) {
                m7grId = em.getReference(m7grId.getClass(), m7grId.getId());
                zalat.setM7grId(m7grId);
            }
            Users createdbyid = zalat.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid = em.getReference(createdbyid.getClass(), createdbyid.getId());
                zalat.setCreatedbyid(createdbyid);
            }
            Collection<TwridTransaction> attachedTwridTransactionCollection = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionTwridTransactionToAttach : zalat.getTwridTransactionCollection()) {
                twridTransactionCollectionTwridTransactionToAttach = em.getReference(twridTransactionCollectionTwridTransactionToAttach.getClass(), twridTransactionCollectionTwridTransactionToAttach.getId());
                attachedTwridTransactionCollection.add(twridTransactionCollectionTwridTransactionToAttach);
            }
            zalat.setTwridTransactionCollection(attachedTwridTransactionCollection);
            Collection<SaderTransaction> attachedSaderTransactionCollection = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionSaderTransactionToAttach : zalat.getSaderTransactionCollection()) {
                saderTransactionCollectionSaderTransactionToAttach = em.getReference(saderTransactionCollectionSaderTransactionToAttach.getClass(), saderTransactionCollectionSaderTransactionToAttach.getId());
                attachedSaderTransactionCollection.add(saderTransactionCollectionSaderTransactionToAttach);
            }
            zalat.setSaderTransactionCollection(attachedSaderTransactionCollection);
            em.persist(zalat);
            if (m7grId != null) {
                m7grId.getZalatCollection().add(zalat);
                m7grId = em.merge(m7grId);
            }
            if (createdbyid != null) {
                createdbyid.getZalatCollection().add(zalat);
                createdbyid = em.merge(createdbyid);
            }
            for (TwridTransaction twridTransactionCollectionTwridTransaction : zalat.getTwridTransactionCollection()) {
                Zalat oldZalatIdOfTwridTransactionCollectionTwridTransaction = twridTransactionCollectionTwridTransaction.getZalatId();
                twridTransactionCollectionTwridTransaction.setZalatId(zalat);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
                if (oldZalatIdOfTwridTransactionCollectionTwridTransaction != null) {
                    oldZalatIdOfTwridTransactionCollectionTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionTwridTransaction);
                    oldZalatIdOfTwridTransactionCollectionTwridTransaction = em.merge(oldZalatIdOfTwridTransactionCollectionTwridTransaction);
                }
            }
            for (SaderTransaction saderTransactionCollectionSaderTransaction : zalat.getSaderTransactionCollection()) {
                Zalat oldZalatIdOfSaderTransactionCollectionSaderTransaction = saderTransactionCollectionSaderTransaction.getZalatId();
                saderTransactionCollectionSaderTransaction.setZalatId(zalat);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
                if (oldZalatIdOfSaderTransactionCollectionSaderTransaction != null) {
                    oldZalatIdOfSaderTransactionCollectionSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionSaderTransaction);
                    oldZalatIdOfSaderTransactionCollectionSaderTransaction = em.merge(oldZalatIdOfSaderTransactionCollectionSaderTransaction);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Zalat zalat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zalat persistentZalat = em.find(Zalat.class, zalat.getId());
            M7ger m7grIdOld = persistentZalat.getM7grId();
            M7ger m7grIdNew = zalat.getM7grId();
            Users createdbyidOld = persistentZalat.getCreatedbyid();
            Users createdbyidNew = zalat.getCreatedbyid();
            Collection<TwridTransaction> twridTransactionCollectionOld = persistentZalat.getTwridTransactionCollection();
            Collection<TwridTransaction> twridTransactionCollectionNew = zalat.getTwridTransactionCollection();
            Collection<SaderTransaction> saderTransactionCollectionOld = persistentZalat.getSaderTransactionCollection();
            Collection<SaderTransaction> saderTransactionCollectionNew = zalat.getSaderTransactionCollection();
            if (m7grIdNew != null) {
                m7grIdNew = em.getReference(m7grIdNew.getClass(), m7grIdNew.getId());
                zalat.setM7grId(m7grIdNew);
            }
            if (createdbyidNew != null) {
                createdbyidNew = em.getReference(createdbyidNew.getClass(), createdbyidNew.getId());
                zalat.setCreatedbyid(createdbyidNew);
            }
            Collection<TwridTransaction> attachedTwridTransactionCollectionNew = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionNewTwridTransactionToAttach : twridTransactionCollectionNew) {
                twridTransactionCollectionNewTwridTransactionToAttach = em.getReference(twridTransactionCollectionNewTwridTransactionToAttach.getClass(), twridTransactionCollectionNewTwridTransactionToAttach.getId());
                attachedTwridTransactionCollectionNew.add(twridTransactionCollectionNewTwridTransactionToAttach);
            }
            twridTransactionCollectionNew = attachedTwridTransactionCollectionNew;
            zalat.setTwridTransactionCollection(twridTransactionCollectionNew);
            Collection<SaderTransaction> attachedSaderTransactionCollectionNew = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionNewSaderTransactionToAttach : saderTransactionCollectionNew) {
                saderTransactionCollectionNewSaderTransactionToAttach = em.getReference(saderTransactionCollectionNewSaderTransactionToAttach.getClass(), saderTransactionCollectionNewSaderTransactionToAttach.getId());
                attachedSaderTransactionCollectionNew.add(saderTransactionCollectionNewSaderTransactionToAttach);
            }
            saderTransactionCollectionNew = attachedSaderTransactionCollectionNew;
            zalat.setSaderTransactionCollection(saderTransactionCollectionNew);
            zalat = em.merge(zalat);
            if (m7grIdOld != null && !m7grIdOld.equals(m7grIdNew)) {
                m7grIdOld.getZalatCollection().remove(zalat);
                m7grIdOld = em.merge(m7grIdOld);
            }
            if (m7grIdNew != null && !m7grIdNew.equals(m7grIdOld)) {
                m7grIdNew.getZalatCollection().add(zalat);
                m7grIdNew = em.merge(m7grIdNew);
            }
            if (createdbyidOld != null && !createdbyidOld.equals(createdbyidNew)) {
                createdbyidOld.getZalatCollection().remove(zalat);
                createdbyidOld = em.merge(createdbyidOld);
            }
            if (createdbyidNew != null && !createdbyidNew.equals(createdbyidOld)) {
                createdbyidNew.getZalatCollection().add(zalat);
                createdbyidNew = em.merge(createdbyidNew);
            }
            for (TwridTransaction twridTransactionCollectionOldTwridTransaction : twridTransactionCollectionOld) {
                if (!twridTransactionCollectionNew.contains(twridTransactionCollectionOldTwridTransaction)) {
                    twridTransactionCollectionOldTwridTransaction.setZalatId(null);
                    twridTransactionCollectionOldTwridTransaction = em.merge(twridTransactionCollectionOldTwridTransaction);
                }
            }
            for (TwridTransaction twridTransactionCollectionNewTwridTransaction : twridTransactionCollectionNew) {
                if (!twridTransactionCollectionOld.contains(twridTransactionCollectionNewTwridTransaction)) {
                    Zalat oldZalatIdOfTwridTransactionCollectionNewTwridTransaction = twridTransactionCollectionNewTwridTransaction.getZalatId();
                    twridTransactionCollectionNewTwridTransaction.setZalatId(zalat);
                    twridTransactionCollectionNewTwridTransaction = em.merge(twridTransactionCollectionNewTwridTransaction);
                    if (oldZalatIdOfTwridTransactionCollectionNewTwridTransaction != null && !oldZalatIdOfTwridTransactionCollectionNewTwridTransaction.equals(zalat)) {
                        oldZalatIdOfTwridTransactionCollectionNewTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionNewTwridTransaction);
                        oldZalatIdOfTwridTransactionCollectionNewTwridTransaction = em.merge(oldZalatIdOfTwridTransactionCollectionNewTwridTransaction);
                    }
                }
            }
            for (SaderTransaction saderTransactionCollectionOldSaderTransaction : saderTransactionCollectionOld) {
                if (!saderTransactionCollectionNew.contains(saderTransactionCollectionOldSaderTransaction)) {
                    saderTransactionCollectionOldSaderTransaction.setZalatId(null);
                    saderTransactionCollectionOldSaderTransaction = em.merge(saderTransactionCollectionOldSaderTransaction);
                }
            }
            for (SaderTransaction saderTransactionCollectionNewSaderTransaction : saderTransactionCollectionNew) {
                if (!saderTransactionCollectionOld.contains(saderTransactionCollectionNewSaderTransaction)) {
                    Zalat oldZalatIdOfSaderTransactionCollectionNewSaderTransaction = saderTransactionCollectionNewSaderTransaction.getZalatId();
                    saderTransactionCollectionNewSaderTransaction.setZalatId(zalat);
                    saderTransactionCollectionNewSaderTransaction = em.merge(saderTransactionCollectionNewSaderTransaction);
                    if (oldZalatIdOfSaderTransactionCollectionNewSaderTransaction != null && !oldZalatIdOfSaderTransactionCollectionNewSaderTransaction.equals(zalat)) {
                        oldZalatIdOfSaderTransactionCollectionNewSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionNewSaderTransaction);
                        oldZalatIdOfSaderTransactionCollectionNewSaderTransaction = em.merge(oldZalatIdOfSaderTransactionCollectionNewSaderTransaction);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = zalat.getId();
                if (findZalat(id) == null) {
                    throw new NonexistentEntityException("The zalat with id " + id + " no longer exists.");
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
            Zalat zalat;
            try {
                zalat = em.getReference(Zalat.class, id);
                zalat.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The zalat with id " + id + " no longer exists.", enfe);
            }
            M7ger m7grId = zalat.getM7grId();
            if (m7grId != null) {
                m7grId.getZalatCollection().remove(zalat);
                m7grId = em.merge(m7grId);
            }
            Users createdbyid = zalat.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid.getZalatCollection().remove(zalat);
                createdbyid = em.merge(createdbyid);
            }
            Collection<TwridTransaction> twridTransactionCollection = zalat.getTwridTransactionCollection();
            for (TwridTransaction twridTransactionCollectionTwridTransaction : twridTransactionCollection) {
                twridTransactionCollectionTwridTransaction.setZalatId(null);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
            }
            Collection<SaderTransaction> saderTransactionCollection = zalat.getSaderTransactionCollection();
            for (SaderTransaction saderTransactionCollectionSaderTransaction : saderTransactionCollection) {
                saderTransactionCollectionSaderTransaction.setZalatId(null);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
            }
            em.remove(zalat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Zalat> findZalatEntities() {
        return findZalatEntities(true, -1, -1);
    }

    public List<Zalat> findZalatEntities(int maxResults, int firstResult) {
        return findZalatEntities(false, maxResults, firstResult);
    }

    private List<Zalat> findZalatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Zalat.class));
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

    public Zalat findZalat(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zalat.class, id);
        } finally {
            em.close();
        }
    }

    public int getZalatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Zalat> rt = cq.from(Zalat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
