/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Client;
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
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bassem
 */
public class ClientJpaController implements Serializable {

    public ClientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) {
        if (client.getSaderTransactionCollection() == null) {
            client.setSaderTransactionCollection(new ArrayList<SaderTransaction>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users createdbyid = client.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid = em.getReference(createdbyid.getClass(), createdbyid.getId());
                client.setCreatedbyid(createdbyid);
            }
            Collection<SaderTransaction> attachedSaderTransactionCollection = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionSaderTransactionToAttach : client.getSaderTransactionCollection()) {
                saderTransactionCollectionSaderTransactionToAttach = em.getReference(saderTransactionCollectionSaderTransactionToAttach.getClass(), saderTransactionCollectionSaderTransactionToAttach.getId());
                attachedSaderTransactionCollection.add(saderTransactionCollectionSaderTransactionToAttach);
            }
            client.setSaderTransactionCollection(attachedSaderTransactionCollection);
            em.persist(client);
            if (createdbyid != null) {
                createdbyid.getClientCollection().add(client);
                createdbyid = em.merge(createdbyid);
            }
            for (SaderTransaction saderTransactionCollectionSaderTransaction : client.getSaderTransactionCollection()) {
                Client oldClientIdOfSaderTransactionCollectionSaderTransaction = saderTransactionCollectionSaderTransaction.getClientId();
                saderTransactionCollectionSaderTransaction.setClientId(client);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
                if (oldClientIdOfSaderTransactionCollectionSaderTransaction != null) {
                    oldClientIdOfSaderTransactionCollectionSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionSaderTransaction);
                    oldClientIdOfSaderTransactionCollectionSaderTransaction = em.merge(oldClientIdOfSaderTransactionCollectionSaderTransaction);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client persistentClient = em.find(Client.class, client.getId());
            Users createdbyidOld = persistentClient.getCreatedbyid();
            Users createdbyidNew = client.getCreatedbyid();
            Collection<SaderTransaction> saderTransactionCollectionOld = persistentClient.getSaderTransactionCollection();
            Collection<SaderTransaction> saderTransactionCollectionNew = client.getSaderTransactionCollection();
            if (createdbyidNew != null) {
                createdbyidNew = em.getReference(createdbyidNew.getClass(), createdbyidNew.getId());
                client.setCreatedbyid(createdbyidNew);
            }
            Collection<SaderTransaction> attachedSaderTransactionCollectionNew = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionNewSaderTransactionToAttach : saderTransactionCollectionNew) {
                saderTransactionCollectionNewSaderTransactionToAttach = em.getReference(saderTransactionCollectionNewSaderTransactionToAttach.getClass(), saderTransactionCollectionNewSaderTransactionToAttach.getId());
                attachedSaderTransactionCollectionNew.add(saderTransactionCollectionNewSaderTransactionToAttach);
            }
            saderTransactionCollectionNew = attachedSaderTransactionCollectionNew;
            client.setSaderTransactionCollection(saderTransactionCollectionNew);
            client = em.merge(client);
            if (createdbyidOld != null && !createdbyidOld.equals(createdbyidNew)) {
                createdbyidOld.getClientCollection().remove(client);
                createdbyidOld = em.merge(createdbyidOld);
            }
            if (createdbyidNew != null && !createdbyidNew.equals(createdbyidOld)) {
                createdbyidNew.getClientCollection().add(client);
                createdbyidNew = em.merge(createdbyidNew);
            }
            for (SaderTransaction saderTransactionCollectionOldSaderTransaction : saderTransactionCollectionOld) {
                if (!saderTransactionCollectionNew.contains(saderTransactionCollectionOldSaderTransaction)) {
                    saderTransactionCollectionOldSaderTransaction.setClientId(null);
                    saderTransactionCollectionOldSaderTransaction = em.merge(saderTransactionCollectionOldSaderTransaction);
                }
            }
            for (SaderTransaction saderTransactionCollectionNewSaderTransaction : saderTransactionCollectionNew) {
                if (!saderTransactionCollectionOld.contains(saderTransactionCollectionNewSaderTransaction)) {
                    Client oldClientIdOfSaderTransactionCollectionNewSaderTransaction = saderTransactionCollectionNewSaderTransaction.getClientId();
                    saderTransactionCollectionNewSaderTransaction.setClientId(client);
                    saderTransactionCollectionNewSaderTransaction = em.merge(saderTransactionCollectionNewSaderTransaction);
                    if (oldClientIdOfSaderTransactionCollectionNewSaderTransaction != null && !oldClientIdOfSaderTransactionCollectionNewSaderTransaction.equals(client)) {
                        oldClientIdOfSaderTransactionCollectionNewSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionNewSaderTransaction);
                        oldClientIdOfSaderTransactionCollectionNewSaderTransaction = em.merge(oldClientIdOfSaderTransactionCollectionNewSaderTransaction);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = client.getId();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
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
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            Users createdbyid = client.getCreatedbyid();
            if (createdbyid != null) {
                createdbyid.getClientCollection().remove(client);
                createdbyid = em.merge(createdbyid);
            }
            Collection<SaderTransaction> saderTransactionCollection = client.getSaderTransactionCollection();
            for (SaderTransaction saderTransactionCollectionSaderTransaction : saderTransactionCollection) {
                saderTransactionCollectionSaderTransaction.setClientId(null);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
            }
            em.remove(client);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
