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
import entities.Grarat;
import entities.M7ger;
import entities.TwridTransaction;
import entities.Users;
import entities.Zalat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bassem
 */
public class TwridTransactionJpaController implements Serializable {

    public TwridTransactionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TwridTransaction twridTransaction) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grarat grarId = twridTransaction.getGrarId();
            if (grarId != null) {
                grarId = em.getReference(grarId.getClass(), grarId.getId());
                twridTransaction.setGrarId(grarId);
            }
            M7ger m7grId = twridTransaction.getM7grId();
            if (m7grId != null) {
                m7grId = em.getReference(m7grId.getClass(), m7grId.getId());
                twridTransaction.setM7grId(m7grId);
            }
            Users createdbyid = twridTransaction.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid = em.getReference(createdbyid.getClass(), createdbyid.getId());
                twridTransaction.setCreatedbyid(createdbyid);
            }
            Zalat zalatId = twridTransaction.getZalatId();
            if (zalatId != null) {
                zalatId = em.getReference(zalatId.getClass(), zalatId.getId());
                twridTransaction.setZalatId(zalatId);
            }
            em.persist(twridTransaction);
            if (grarId != null) {
                grarId.getTwridTransactionCollection().add(twridTransaction);
                grarId = em.merge(grarId);
            }
            if (m7grId != null) {
                m7grId.getTwridTransactionCollection().add(twridTransaction);
                m7grId = em.merge(m7grId);
            }
            if (createdbyid != null) {
                createdbyid.getTwridTransactionCollection().add(twridTransaction);
                createdbyid = em.merge(createdbyid);
            }
            if (zalatId != null) {
                zalatId.getTwridTransactionCollection().add(twridTransaction);
                zalatId = em.merge(zalatId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TwridTransaction twridTransaction) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TwridTransaction persistentTwridTransaction = em.find(TwridTransaction.class, twridTransaction.getId());
            Grarat grarIdOld = persistentTwridTransaction.getGrarId();
            Grarat grarIdNew = twridTransaction.getGrarId();
            M7ger m7grIdOld = persistentTwridTransaction.getM7grId();
            M7ger m7grIdNew = twridTransaction.getM7grId();
            Users createdbyidOld = persistentTwridTransaction.getCreatedbyid();
            Users createdbyidNew = twridTransaction.getCreatedbyid();
            Zalat zalatIdOld = persistentTwridTransaction.getZalatId();
            Zalat zalatIdNew = twridTransaction.getZalatId();
            if (grarIdNew != null) {
                grarIdNew = em.getReference(grarIdNew.getClass(), grarIdNew.getId());
                twridTransaction.setGrarId(grarIdNew);
            }
            if (m7grIdNew != null) {
                m7grIdNew = em.getReference(m7grIdNew.getClass(), m7grIdNew.getId());
                twridTransaction.setM7grId(m7grIdNew);
            }
            if (createdbyidNew != null) {
                createdbyidNew = em.getReference(createdbyidNew.getClass(), createdbyidNew.getId());
                twridTransaction.setCreatedbyid(createdbyidNew);
            }
            if (zalatIdNew != null) {
                zalatIdNew = em.getReference(zalatIdNew.getClass(), zalatIdNew.getId());
                twridTransaction.setZalatId(zalatIdNew);
            }
            twridTransaction = em.merge(twridTransaction);
            if (grarIdOld != null && !grarIdOld.equals(grarIdNew)) {
                grarIdOld.getTwridTransactionCollection().remove(twridTransaction);
                grarIdOld = em.merge(grarIdOld);
            }
            if (grarIdNew != null && !grarIdNew.equals(grarIdOld)) {
                grarIdNew.getTwridTransactionCollection().add(twridTransaction);
                grarIdNew = em.merge(grarIdNew);
            }
            if (m7grIdOld != null && !m7grIdOld.equals(m7grIdNew)) {
                m7grIdOld.getTwridTransactionCollection().remove(twridTransaction);
                m7grIdOld = em.merge(m7grIdOld);
            }
            if (m7grIdNew != null && !m7grIdNew.equals(m7grIdOld)) {
                m7grIdNew.getTwridTransactionCollection().add(twridTransaction);
                m7grIdNew = em.merge(m7grIdNew);
            }
            if (createdbyidOld != null && !createdbyidOld.equals(createdbyidNew)) {
                createdbyidOld.getTwridTransactionCollection().remove(twridTransaction);
                createdbyidOld = em.merge(createdbyidOld);
            }
            if (createdbyidNew != null && !createdbyidNew.equals(createdbyidOld)) {
                createdbyidNew.getTwridTransactionCollection().add(twridTransaction);
                createdbyidNew = em.merge(createdbyidNew);
            }
            if (zalatIdOld != null && !zalatIdOld.equals(zalatIdNew)) {
                zalatIdOld.getTwridTransactionCollection().remove(twridTransaction);
                zalatIdOld = em.merge(zalatIdOld);
            }
            if (zalatIdNew != null && !zalatIdNew.equals(zalatIdOld)) {
                zalatIdNew.getTwridTransactionCollection().add(twridTransaction);
                zalatIdNew = em.merge(zalatIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = twridTransaction.getId();
                if (findTwridTransaction(id) == null) {
                    throw new NonexistentEntityException("The twridTransaction with id " + id + " no longer exists.");
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
            TwridTransaction twridTransaction;
            try {
                twridTransaction = em.getReference(TwridTransaction.class, id);
                twridTransaction.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The twridTransaction with id " + id + " no longer exists.", enfe);
            }
            Grarat grarId = twridTransaction.getGrarId();
            if (grarId != null) {
                grarId.getTwridTransactionCollection().remove(twridTransaction);
                grarId = em.merge(grarId);
            }
            M7ger m7grId = twridTransaction.getM7grId();
            if (m7grId != null) {
                m7grId.getTwridTransactionCollection().remove(twridTransaction);
                m7grId = em.merge(m7grId);
            }
            Users createdbyid = twridTransaction.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid.getTwridTransactionCollection().remove(twridTransaction);
                createdbyid = em.merge(createdbyid);
            }
            Zalat zalatId = twridTransaction.getZalatId();
            if (zalatId != null) {
                zalatId.getTwridTransactionCollection().remove(twridTransaction);
                zalatId = em.merge(zalatId);
            }
            em.remove(twridTransaction);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TwridTransaction> findTwridTransactionEntities() {
        return findTwridTransactionEntities(true, -1, -1);
    }

    public List<TwridTransaction> findTwridTransactionEntities(int maxResults, int firstResult) {
        return findTwridTransactionEntities(false, maxResults, firstResult);
    }

    private List<TwridTransaction> findTwridTransactionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TwridTransaction.class));
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

    public TwridTransaction findTwridTransaction(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TwridTransaction.class, id);
        } finally {
            em.close();
        }
    }

    public int getTwridTransactionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TwridTransaction> rt = cq.from(TwridTransaction.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
