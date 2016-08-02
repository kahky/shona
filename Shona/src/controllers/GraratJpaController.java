/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Grarat;
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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bassem
 */
public class GraratJpaController implements Serializable {

    public GraratJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grarat grarat) {
        if (grarat.getTwridTransactionCollection() == null) {
            grarat.setTwridTransactionCollection(new ArrayList<TwridTransaction>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            M7ger m7grId = grarat.getM7grId();
            if (m7grId != null) {
                m7grId = em.getReference(m7grId.getClass(), m7grId.getId());
                grarat.setM7grId(m7grId);
            }
            Users createdbyid = grarat.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid = em.getReference(createdbyid.getClass(), createdbyid.getId());
                grarat.setCreatedbyid(createdbyid);
            }
            Collection<TwridTransaction> attachedTwridTransactionCollection = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionTwridTransactionToAttach : grarat.getTwridTransactionCollection()) {
                twridTransactionCollectionTwridTransactionToAttach = em.getReference(twridTransactionCollectionTwridTransactionToAttach.getClass(), twridTransactionCollectionTwridTransactionToAttach.getId());
                attachedTwridTransactionCollection.add(twridTransactionCollectionTwridTransactionToAttach);
            }
            grarat.setTwridTransactionCollection(attachedTwridTransactionCollection);
            em.persist(grarat);
            if (m7grId != null) {
                m7grId.getGraratCollection().add(grarat);
                m7grId = em.merge(m7grId);
            }
            if (createdbyid != null) {
                createdbyid.getGraratCollection().add(grarat);
                createdbyid = em.merge(createdbyid);
            }
            for (TwridTransaction twridTransactionCollectionTwridTransaction : grarat.getTwridTransactionCollection()) {
                Grarat oldGrarIdOfTwridTransactionCollectionTwridTransaction = twridTransactionCollectionTwridTransaction.getGrarId();
                twridTransactionCollectionTwridTransaction.setGrarId(grarat);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
                if (oldGrarIdOfTwridTransactionCollectionTwridTransaction != null) {
                    oldGrarIdOfTwridTransactionCollectionTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionTwridTransaction);
                    oldGrarIdOfTwridTransactionCollectionTwridTransaction = em.merge(oldGrarIdOfTwridTransactionCollectionTwridTransaction);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grarat grarat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grarat persistentGrarat = em.find(Grarat.class, grarat.getId());
            M7ger m7grIdOld = persistentGrarat.getM7grId();
            M7ger m7grIdNew = grarat.getM7grId();
            Users createdbyidOld = persistentGrarat.getCreatedbyid();
            Users createdbyidNew = grarat.getCreatedbyid();
            Collection<TwridTransaction> twridTransactionCollectionOld = persistentGrarat.getTwridTransactionCollection();
            Collection<TwridTransaction> twridTransactionCollectionNew = grarat.getTwridTransactionCollection();
            if (m7grIdNew != null) {
                m7grIdNew = em.getReference(m7grIdNew.getClass(), m7grIdNew.getId());
                grarat.setM7grId(m7grIdNew);
            }
            if (createdbyidNew != null) {
                createdbyidNew = em.getReference(createdbyidNew.getClass(), createdbyidNew.getId());
                grarat.setCreatedbyid(createdbyidNew);
            }
            Collection<TwridTransaction> attachedTwridTransactionCollectionNew = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionNewTwridTransactionToAttach : twridTransactionCollectionNew) {
                twridTransactionCollectionNewTwridTransactionToAttach = em.getReference(twridTransactionCollectionNewTwridTransactionToAttach.getClass(), twridTransactionCollectionNewTwridTransactionToAttach.getId());
                attachedTwridTransactionCollectionNew.add(twridTransactionCollectionNewTwridTransactionToAttach);
            }
            twridTransactionCollectionNew = attachedTwridTransactionCollectionNew;
            grarat.setTwridTransactionCollection(twridTransactionCollectionNew);
            grarat = em.merge(grarat);
            if (m7grIdOld != null && !m7grIdOld.equals(m7grIdNew)) {
                m7grIdOld.getGraratCollection().remove(grarat);
                m7grIdOld = em.merge(m7grIdOld);
            }
            if (m7grIdNew != null && !m7grIdNew.equals(m7grIdOld)) {
                m7grIdNew.getGraratCollection().add(grarat);
                m7grIdNew = em.merge(m7grIdNew);
            }
            if (createdbyidOld != null && !createdbyidOld.equals(createdbyidNew)) {
                createdbyidOld.getGraratCollection().remove(grarat);
                createdbyidOld = em.merge(createdbyidOld);
            }
            if (createdbyidNew != null && !createdbyidNew.equals(createdbyidOld)) {
                createdbyidNew.getGraratCollection().add(grarat);
                createdbyidNew = em.merge(createdbyidNew);
            }
            for (TwridTransaction twridTransactionCollectionOldTwridTransaction : twridTransactionCollectionOld) {
                if (!twridTransactionCollectionNew.contains(twridTransactionCollectionOldTwridTransaction)) {
                    twridTransactionCollectionOldTwridTransaction.setGrarId(null);
                    twridTransactionCollectionOldTwridTransaction = em.merge(twridTransactionCollectionOldTwridTransaction);
                }
            }
            for (TwridTransaction twridTransactionCollectionNewTwridTransaction : twridTransactionCollectionNew) {
                if (!twridTransactionCollectionOld.contains(twridTransactionCollectionNewTwridTransaction)) {
                    Grarat oldGrarIdOfTwridTransactionCollectionNewTwridTransaction = twridTransactionCollectionNewTwridTransaction.getGrarId();
                    twridTransactionCollectionNewTwridTransaction.setGrarId(grarat);
                    twridTransactionCollectionNewTwridTransaction = em.merge(twridTransactionCollectionNewTwridTransaction);
                    if (oldGrarIdOfTwridTransactionCollectionNewTwridTransaction != null && !oldGrarIdOfTwridTransactionCollectionNewTwridTransaction.equals(grarat)) {
                        oldGrarIdOfTwridTransactionCollectionNewTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionNewTwridTransaction);
                        oldGrarIdOfTwridTransactionCollectionNewTwridTransaction = em.merge(oldGrarIdOfTwridTransactionCollectionNewTwridTransaction);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grarat.getId();
                if (findGrarat(id) == null) {
                    throw new NonexistentEntityException("The grarat with id " + id + " no longer exists.");
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
            Grarat grarat;
            try {
                grarat = em.getReference(Grarat.class, id);
                grarat.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grarat with id " + id + " no longer exists.", enfe);
            }
            M7ger m7grId = grarat.getM7grId();
            if (m7grId != null) {
                m7grId.getGraratCollection().remove(grarat);
                m7grId = em.merge(m7grId);
            }
            Users createdbyid = grarat.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid.getGraratCollection().remove(grarat);
                createdbyid = em.merge(createdbyid);
            }
            Collection<TwridTransaction> twridTransactionCollection = grarat.getTwridTransactionCollection();
            for (TwridTransaction twridTransactionCollectionTwridTransaction : twridTransactionCollection) {
                twridTransactionCollectionTwridTransaction.setGrarId(null);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
            }
            em.remove(grarat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grarat> findGraratEntities() {
        return findGraratEntities(true, -1, -1);
    }

    public List<Grarat> findGraratEntities(int maxResults, int firstResult) {
        return findGraratEntities(false, maxResults, firstResult);
    }

    private List<Grarat> findGraratEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grarat.class));
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

    public Grarat findGrarat(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grarat.class, id);
        } finally {
            em.close();
        }
    }

    public int getGraratCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grarat> rt = cq.from(Grarat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
