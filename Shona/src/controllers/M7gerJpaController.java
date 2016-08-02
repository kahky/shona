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
import entities.Users;
import entities.TwridTransaction;
import java.util.ArrayList;
import java.util.Collection;
import entities.Grarat;
import entities.M7ger;
import entities.Zalat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bassem
 */
public class M7gerJpaController implements Serializable {

    public M7gerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(M7ger m7ger) {
        if (m7ger.getTwridTransactionCollection() == null) {
            m7ger.setTwridTransactionCollection(new ArrayList<TwridTransaction>());
        }
        if (m7ger.getGraratCollection() == null) {
            m7ger.setGraratCollection(new ArrayList<Grarat>());
        }
        if (m7ger.getZalatCollection() == null) {
            m7ger.setZalatCollection(new ArrayList<Zalat>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users createdbyid = m7ger.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid = em.getReference(createdbyid.getClass(), createdbyid.getId());
                m7ger.setCreatedbyid(createdbyid);
            }
            Collection<TwridTransaction> attachedTwridTransactionCollection = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionTwridTransactionToAttach : m7ger.getTwridTransactionCollection()) {
                twridTransactionCollectionTwridTransactionToAttach = em.getReference(twridTransactionCollectionTwridTransactionToAttach.getClass(), twridTransactionCollectionTwridTransactionToAttach.getId());
                attachedTwridTransactionCollection.add(twridTransactionCollectionTwridTransactionToAttach);
            }
            m7ger.setTwridTransactionCollection(attachedTwridTransactionCollection);
            Collection<Grarat> attachedGraratCollection = new ArrayList<Grarat>();
            for (Grarat graratCollectionGraratToAttach : m7ger.getGraratCollection()) {
                graratCollectionGraratToAttach = em.getReference(graratCollectionGraratToAttach.getClass(), graratCollectionGraratToAttach.getId());
                attachedGraratCollection.add(graratCollectionGraratToAttach);
            }
            m7ger.setGraratCollection(attachedGraratCollection);
            Collection<Zalat> attachedZalatCollection = new ArrayList<Zalat>();
            for (Zalat zalatCollectionZalatToAttach : m7ger.getZalatCollection()) {
                zalatCollectionZalatToAttach = em.getReference(zalatCollectionZalatToAttach.getClass(), zalatCollectionZalatToAttach.getId());
                attachedZalatCollection.add(zalatCollectionZalatToAttach);
            }
            m7ger.setZalatCollection(attachedZalatCollection);
            em.persist(m7ger);
            if (createdbyid != null) {
                createdbyid.getM7gerCollection().add(m7ger);
                createdbyid = em.merge(createdbyid);
            }
            for (TwridTransaction twridTransactionCollectionTwridTransaction : m7ger.getTwridTransactionCollection()) {
                M7ger oldM7grIdOfTwridTransactionCollectionTwridTransaction = twridTransactionCollectionTwridTransaction.getM7grId();
                twridTransactionCollectionTwridTransaction.setM7grId(m7ger);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
                if (oldM7grIdOfTwridTransactionCollectionTwridTransaction != null) {
                    oldM7grIdOfTwridTransactionCollectionTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionTwridTransaction);
                    oldM7grIdOfTwridTransactionCollectionTwridTransaction = em.merge(oldM7grIdOfTwridTransactionCollectionTwridTransaction);
                }
            }
            for (Grarat graratCollectionGrarat : m7ger.getGraratCollection()) {
                M7ger oldM7grIdOfGraratCollectionGrarat = graratCollectionGrarat.getM7grId();
                graratCollectionGrarat.setM7grId(m7ger);
                graratCollectionGrarat = em.merge(graratCollectionGrarat);
                if (oldM7grIdOfGraratCollectionGrarat != null) {
                    oldM7grIdOfGraratCollectionGrarat.getGraratCollection().remove(graratCollectionGrarat);
                    oldM7grIdOfGraratCollectionGrarat = em.merge(oldM7grIdOfGraratCollectionGrarat);
                }
            }
            for (Zalat zalatCollectionZalat : m7ger.getZalatCollection()) {
                M7ger oldM7grIdOfZalatCollectionZalat = zalatCollectionZalat.getM7grId();
                zalatCollectionZalat.setM7grId(m7ger);
                zalatCollectionZalat = em.merge(zalatCollectionZalat);
                if (oldM7grIdOfZalatCollectionZalat != null) {
                    oldM7grIdOfZalatCollectionZalat.getZalatCollection().remove(zalatCollectionZalat);
                    oldM7grIdOfZalatCollectionZalat = em.merge(oldM7grIdOfZalatCollectionZalat);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(M7ger m7ger) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            M7ger persistentM7ger = em.find(M7ger.class, m7ger.getId());
            Users createdbyidOld = persistentM7ger.getCreatedbyid();
            Users createdbyidNew = m7ger.getCreatedbyid();
            Collection<TwridTransaction> twridTransactionCollectionOld = persistentM7ger.getTwridTransactionCollection();
            Collection<TwridTransaction> twridTransactionCollectionNew = m7ger.getTwridTransactionCollection();
            Collection<Grarat> graratCollectionOld = persistentM7ger.getGraratCollection();
            Collection<Grarat> graratCollectionNew = m7ger.getGraratCollection();
            Collection<Zalat> zalatCollectionOld = persistentM7ger.getZalatCollection();
            Collection<Zalat> zalatCollectionNew = m7ger.getZalatCollection();
            if (createdbyidNew != null) {
                createdbyidNew = em.getReference(createdbyidNew.getClass(), createdbyidNew.getId());
                m7ger.setCreatedbyid(createdbyidNew);
            }
            Collection<TwridTransaction> attachedTwridTransactionCollectionNew = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionNewTwridTransactionToAttach : twridTransactionCollectionNew) {
                twridTransactionCollectionNewTwridTransactionToAttach = em.getReference(twridTransactionCollectionNewTwridTransactionToAttach.getClass(), twridTransactionCollectionNewTwridTransactionToAttach.getId());
                attachedTwridTransactionCollectionNew.add(twridTransactionCollectionNewTwridTransactionToAttach);
            }
            twridTransactionCollectionNew = attachedTwridTransactionCollectionNew;
            m7ger.setTwridTransactionCollection(twridTransactionCollectionNew);
            Collection<Grarat> attachedGraratCollectionNew = new ArrayList<Grarat>();
            for (Grarat graratCollectionNewGraratToAttach : graratCollectionNew) {
                graratCollectionNewGraratToAttach = em.getReference(graratCollectionNewGraratToAttach.getClass(), graratCollectionNewGraratToAttach.getId());
                attachedGraratCollectionNew.add(graratCollectionNewGraratToAttach);
            }
            graratCollectionNew = attachedGraratCollectionNew;
            m7ger.setGraratCollection(graratCollectionNew);
            Collection<Zalat> attachedZalatCollectionNew = new ArrayList<Zalat>();
            for (Zalat zalatCollectionNewZalatToAttach : zalatCollectionNew) {
                zalatCollectionNewZalatToAttach = em.getReference(zalatCollectionNewZalatToAttach.getClass(), zalatCollectionNewZalatToAttach.getId());
                attachedZalatCollectionNew.add(zalatCollectionNewZalatToAttach);
            }
            zalatCollectionNew = attachedZalatCollectionNew;
            m7ger.setZalatCollection(zalatCollectionNew);
            m7ger = em.merge(m7ger);
            if (createdbyidOld != null && !createdbyidOld.equals(createdbyidNew)) {
                createdbyidOld.getM7gerCollection().remove(m7ger);
                createdbyidOld = em.merge(createdbyidOld);
            }
            if (createdbyidNew != null && !createdbyidNew.equals(createdbyidOld)) {
                createdbyidNew.getM7gerCollection().add(m7ger);
                createdbyidNew = em.merge(createdbyidNew);
            }
            for (TwridTransaction twridTransactionCollectionOldTwridTransaction : twridTransactionCollectionOld) {
                if (!twridTransactionCollectionNew.contains(twridTransactionCollectionOldTwridTransaction)) {
                    twridTransactionCollectionOldTwridTransaction.setM7grId(null);
                    twridTransactionCollectionOldTwridTransaction = em.merge(twridTransactionCollectionOldTwridTransaction);
                }
            }
            for (TwridTransaction twridTransactionCollectionNewTwridTransaction : twridTransactionCollectionNew) {
                if (!twridTransactionCollectionOld.contains(twridTransactionCollectionNewTwridTransaction)) {
                    M7ger oldM7grIdOfTwridTransactionCollectionNewTwridTransaction = twridTransactionCollectionNewTwridTransaction.getM7grId();
                    twridTransactionCollectionNewTwridTransaction.setM7grId(m7ger);
                    twridTransactionCollectionNewTwridTransaction = em.merge(twridTransactionCollectionNewTwridTransaction);
                    if (oldM7grIdOfTwridTransactionCollectionNewTwridTransaction != null && !oldM7grIdOfTwridTransactionCollectionNewTwridTransaction.equals(m7ger)) {
                        oldM7grIdOfTwridTransactionCollectionNewTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionNewTwridTransaction);
                        oldM7grIdOfTwridTransactionCollectionNewTwridTransaction = em.merge(oldM7grIdOfTwridTransactionCollectionNewTwridTransaction);
                    }
                }
            }
            for (Grarat graratCollectionOldGrarat : graratCollectionOld) {
                if (!graratCollectionNew.contains(graratCollectionOldGrarat)) {
                    graratCollectionOldGrarat.setM7grId(null);
                    graratCollectionOldGrarat = em.merge(graratCollectionOldGrarat);
                }
            }
            for (Grarat graratCollectionNewGrarat : graratCollectionNew) {
                if (!graratCollectionOld.contains(graratCollectionNewGrarat)) {
                    M7ger oldM7grIdOfGraratCollectionNewGrarat = graratCollectionNewGrarat.getM7grId();
                    graratCollectionNewGrarat.setM7grId(m7ger);
                    graratCollectionNewGrarat = em.merge(graratCollectionNewGrarat);
                    if (oldM7grIdOfGraratCollectionNewGrarat != null && !oldM7grIdOfGraratCollectionNewGrarat.equals(m7ger)) {
                        oldM7grIdOfGraratCollectionNewGrarat.getGraratCollection().remove(graratCollectionNewGrarat);
                        oldM7grIdOfGraratCollectionNewGrarat = em.merge(oldM7grIdOfGraratCollectionNewGrarat);
                    }
                }
            }
            for (Zalat zalatCollectionOldZalat : zalatCollectionOld) {
                if (!zalatCollectionNew.contains(zalatCollectionOldZalat)) {
                    zalatCollectionOldZalat.setM7grId(null);
                    zalatCollectionOldZalat = em.merge(zalatCollectionOldZalat);
                }
            }
            for (Zalat zalatCollectionNewZalat : zalatCollectionNew) {
                if (!zalatCollectionOld.contains(zalatCollectionNewZalat)) {
                    M7ger oldM7grIdOfZalatCollectionNewZalat = zalatCollectionNewZalat.getM7grId();
                    zalatCollectionNewZalat.setM7grId(m7ger);
                    zalatCollectionNewZalat = em.merge(zalatCollectionNewZalat);
                    if (oldM7grIdOfZalatCollectionNewZalat != null && !oldM7grIdOfZalatCollectionNewZalat.equals(m7ger)) {
                        oldM7grIdOfZalatCollectionNewZalat.getZalatCollection().remove(zalatCollectionNewZalat);
                        oldM7grIdOfZalatCollectionNewZalat = em.merge(oldM7grIdOfZalatCollectionNewZalat);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = m7ger.getId();
                if (findM7ger(id) == null) {
                    throw new NonexistentEntityException("The m7ger with id " + id + " no longer exists.");
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
            M7ger m7ger;
            try {
                m7ger = em.getReference(M7ger.class, id);
                m7ger.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The m7ger with id " + id + " no longer exists.", enfe);
            }
            Users createdbyid = m7ger.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid.getM7gerCollection().remove(m7ger);
                createdbyid = em.merge(createdbyid);
            }
            Collection<TwridTransaction> twridTransactionCollection = m7ger.getTwridTransactionCollection();
            for (TwridTransaction twridTransactionCollectionTwridTransaction : twridTransactionCollection) {
                twridTransactionCollectionTwridTransaction.setM7grId(null);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
            }
            Collection<Grarat> graratCollection = m7ger.getGraratCollection();
            for (Grarat graratCollectionGrarat : graratCollection) {
                graratCollectionGrarat.setM7grId(null);
                graratCollectionGrarat = em.merge(graratCollectionGrarat);
            }
            Collection<Zalat> zalatCollection = m7ger.getZalatCollection();
            for (Zalat zalatCollectionZalat : zalatCollection) {
                zalatCollectionZalat.setM7grId(null);
                zalatCollectionZalat = em.merge(zalatCollectionZalat);
            }
            em.remove(m7ger);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<M7ger> findM7gerEntities() {
        return findM7gerEntities(true, -1, -1);
    }

    public List<M7ger> findM7gerEntities(int maxResults, int firstResult) {
        return findM7gerEntities(false, maxResults, firstResult);
    }

    private List<M7ger> findM7gerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(M7ger.class));
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

    public M7ger findM7ger(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(M7ger.class, id);
        } finally {
            em.close();
        }
    }

    public int getM7gerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<M7ger> rt = cq.from(M7ger.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
