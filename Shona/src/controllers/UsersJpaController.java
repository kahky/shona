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
import java.util.ArrayList;
import java.util.Collection;
import entities.Cars;
import entities.TwridTransaction;
import entities.Grarat;
import entities.Client;
import entities.Zalat;
import entities.SaderTransaction;
import entities.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bassem
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getM7gerCollection() == null) {
            users.setM7gerCollection(new ArrayList<M7ger>());
        }
        if (users.getCarsCollection() == null) {
            users.setCarsCollection(new ArrayList<Cars>());
        }
        if (users.getTwridTransactionCollection() == null) {
            users.setTwridTransactionCollection(new ArrayList<TwridTransaction>());
        }
        if (users.getGraratCollection() == null) {
            users.setGraratCollection(new ArrayList<Grarat>());
        }
        if (users.getClientCollection() == null) {
            users.setClientCollection(new ArrayList<Client>());
        }
        if (users.getZalatCollection() == null) {
            users.setZalatCollection(new ArrayList<Zalat>());
        }
        if (users.getSaderTransactionCollection() == null) {
            users.setSaderTransactionCollection(new ArrayList<SaderTransaction>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<M7ger> attachedM7gerCollection = new ArrayList<M7ger>();
            for (M7ger m7gerCollectionM7gerToAttach : users.getM7gerCollection()) {
                m7gerCollectionM7gerToAttach = em.getReference(m7gerCollectionM7gerToAttach.getClass(), m7gerCollectionM7gerToAttach.getId());
                attachedM7gerCollection.add(m7gerCollectionM7gerToAttach);
            }
            users.setM7gerCollection(attachedM7gerCollection);
            Collection<Cars> attachedCarsCollection = new ArrayList<Cars>();
            for (Cars carsCollectionCarsToAttach : users.getCarsCollection()) {
                carsCollectionCarsToAttach = em.getReference(carsCollectionCarsToAttach.getClass(), carsCollectionCarsToAttach.getId());
                attachedCarsCollection.add(carsCollectionCarsToAttach);
            }
            users.setCarsCollection(attachedCarsCollection);
            Collection<TwridTransaction> attachedTwridTransactionCollection = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionTwridTransactionToAttach : users.getTwridTransactionCollection()) {
                twridTransactionCollectionTwridTransactionToAttach = em.getReference(twridTransactionCollectionTwridTransactionToAttach.getClass(), twridTransactionCollectionTwridTransactionToAttach.getId());
                attachedTwridTransactionCollection.add(twridTransactionCollectionTwridTransactionToAttach);
            }
            users.setTwridTransactionCollection(attachedTwridTransactionCollection);
            Collection<Grarat> attachedGraratCollection = new ArrayList<Grarat>();
            for (Grarat graratCollectionGraratToAttach : users.getGraratCollection()) {
                graratCollectionGraratToAttach = em.getReference(graratCollectionGraratToAttach.getClass(), graratCollectionGraratToAttach.getId());
                attachedGraratCollection.add(graratCollectionGraratToAttach);
            }
            users.setGraratCollection(attachedGraratCollection);
            Collection<Client> attachedClientCollection = new ArrayList<Client>();
            for (Client clientCollectionClientToAttach : users.getClientCollection()) {
                clientCollectionClientToAttach = em.getReference(clientCollectionClientToAttach.getClass(), clientCollectionClientToAttach.getId());
                attachedClientCollection.add(clientCollectionClientToAttach);
            }
            users.setClientCollection(attachedClientCollection);
            Collection<Zalat> attachedZalatCollection = new ArrayList<Zalat>();
            for (Zalat zalatCollectionZalatToAttach : users.getZalatCollection()) {
                zalatCollectionZalatToAttach = em.getReference(zalatCollectionZalatToAttach.getClass(), zalatCollectionZalatToAttach.getId());
                attachedZalatCollection.add(zalatCollectionZalatToAttach);
            }
            users.setZalatCollection(attachedZalatCollection);
            Collection<SaderTransaction> attachedSaderTransactionCollection = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionSaderTransactionToAttach : users.getSaderTransactionCollection()) {
                saderTransactionCollectionSaderTransactionToAttach = em.getReference(saderTransactionCollectionSaderTransactionToAttach.getClass(), saderTransactionCollectionSaderTransactionToAttach.getId());
                attachedSaderTransactionCollection.add(saderTransactionCollectionSaderTransactionToAttach);
            }
            users.setSaderTransactionCollection(attachedSaderTransactionCollection);
            em.persist(users);
            for (M7ger m7gerCollectionM7ger : users.getM7gerCollection()) {
                Users oldCreatedbyidOfM7gerCollectionM7ger = m7gerCollectionM7ger.getCreatedbyid();
                m7gerCollectionM7ger.setCreatedbyid(users);
                m7gerCollectionM7ger = em.merge(m7gerCollectionM7ger);
                if (oldCreatedbyidOfM7gerCollectionM7ger != null) {
                    oldCreatedbyidOfM7gerCollectionM7ger.getM7gerCollection().remove(m7gerCollectionM7ger);
                    oldCreatedbyidOfM7gerCollectionM7ger = em.merge(oldCreatedbyidOfM7gerCollectionM7ger);
                }
            }
            for (Cars carsCollectionCars : users.getCarsCollection()) {
                Users oldCreatedbyidOfCarsCollectionCars = carsCollectionCars.getCreatedbyid();
                carsCollectionCars.setCreatedbyid(users);
                carsCollectionCars = em.merge(carsCollectionCars);
                if (oldCreatedbyidOfCarsCollectionCars != null) {
                    oldCreatedbyidOfCarsCollectionCars.getCarsCollection().remove(carsCollectionCars);
                    oldCreatedbyidOfCarsCollectionCars = em.merge(oldCreatedbyidOfCarsCollectionCars);
                }
            }
            for (TwridTransaction twridTransactionCollectionTwridTransaction : users.getTwridTransactionCollection()) {
                Users oldCreatedbyidOfTwridTransactionCollectionTwridTransaction = twridTransactionCollectionTwridTransaction.getCreatedbyid();
                twridTransactionCollectionTwridTransaction.setCreatedbyid(users);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
                if (oldCreatedbyidOfTwridTransactionCollectionTwridTransaction != null) {
                    oldCreatedbyidOfTwridTransactionCollectionTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionTwridTransaction);
                    oldCreatedbyidOfTwridTransactionCollectionTwridTransaction = em.merge(oldCreatedbyidOfTwridTransactionCollectionTwridTransaction);
                }
            }
            for (Grarat graratCollectionGrarat : users.getGraratCollection()) {
                Users oldCreatedbyidOfGraratCollectionGrarat = graratCollectionGrarat.getCreatedbyid();
                graratCollectionGrarat.setCreatedbyid(users);
                graratCollectionGrarat = em.merge(graratCollectionGrarat);
                if (oldCreatedbyidOfGraratCollectionGrarat != null) {
                    oldCreatedbyidOfGraratCollectionGrarat.getGraratCollection().remove(graratCollectionGrarat);
                    oldCreatedbyidOfGraratCollectionGrarat = em.merge(oldCreatedbyidOfGraratCollectionGrarat);
                }
            }
            for (Client clientCollectionClient : users.getClientCollection()) {
                Users oldCreatedbyidOfClientCollectionClient = clientCollectionClient.getCreatedbyid();
                clientCollectionClient.setCreatedbyid(users);
                clientCollectionClient = em.merge(clientCollectionClient);
                if (oldCreatedbyidOfClientCollectionClient != null) {
                    oldCreatedbyidOfClientCollectionClient.getClientCollection().remove(clientCollectionClient);
                    oldCreatedbyidOfClientCollectionClient = em.merge(oldCreatedbyidOfClientCollectionClient);
                }
            }
            for (Zalat zalatCollectionZalat : users.getZalatCollection()) {
                Users oldCreatedbyidOfZalatCollectionZalat = zalatCollectionZalat.getCreatedbyid();
                zalatCollectionZalat.setCreatedbyid(users);
                zalatCollectionZalat = em.merge(zalatCollectionZalat);
                if (oldCreatedbyidOfZalatCollectionZalat != null) {
                    oldCreatedbyidOfZalatCollectionZalat.getZalatCollection().remove(zalatCollectionZalat);
                    oldCreatedbyidOfZalatCollectionZalat = em.merge(oldCreatedbyidOfZalatCollectionZalat);
                }
            }
            for (SaderTransaction saderTransactionCollectionSaderTransaction : users.getSaderTransactionCollection()) {
                Users oldCreatedbyidOfSaderTransactionCollectionSaderTransaction = saderTransactionCollectionSaderTransaction.getCreatedbyid();
                saderTransactionCollectionSaderTransaction.setCreatedbyid(users);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
                if (oldCreatedbyidOfSaderTransactionCollectionSaderTransaction != null) {
                    oldCreatedbyidOfSaderTransactionCollectionSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionSaderTransaction);
                    oldCreatedbyidOfSaderTransactionCollectionSaderTransaction = em.merge(oldCreatedbyidOfSaderTransactionCollectionSaderTransaction);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getId());
            Collection<M7ger> m7gerCollectionOld = persistentUsers.getM7gerCollection();
            Collection<M7ger> m7gerCollectionNew = users.getM7gerCollection();
            Collection<Cars> carsCollectionOld = persistentUsers.getCarsCollection();
            Collection<Cars> carsCollectionNew = users.getCarsCollection();
            Collection<TwridTransaction> twridTransactionCollectionOld = persistentUsers.getTwridTransactionCollection();
            Collection<TwridTransaction> twridTransactionCollectionNew = users.getTwridTransactionCollection();
            Collection<Grarat> graratCollectionOld = persistentUsers.getGraratCollection();
            Collection<Grarat> graratCollectionNew = users.getGraratCollection();
            Collection<Client> clientCollectionOld = persistentUsers.getClientCollection();
            Collection<Client> clientCollectionNew = users.getClientCollection();
            Collection<Zalat> zalatCollectionOld = persistentUsers.getZalatCollection();
            Collection<Zalat> zalatCollectionNew = users.getZalatCollection();
            Collection<SaderTransaction> saderTransactionCollectionOld = persistentUsers.getSaderTransactionCollection();
            Collection<SaderTransaction> saderTransactionCollectionNew = users.getSaderTransactionCollection();
            Collection<M7ger> attachedM7gerCollectionNew = new ArrayList<M7ger>();
            for (M7ger m7gerCollectionNewM7gerToAttach : m7gerCollectionNew) {
                m7gerCollectionNewM7gerToAttach = em.getReference(m7gerCollectionNewM7gerToAttach.getClass(), m7gerCollectionNewM7gerToAttach.getId());
                attachedM7gerCollectionNew.add(m7gerCollectionNewM7gerToAttach);
            }
            m7gerCollectionNew = attachedM7gerCollectionNew;
            users.setM7gerCollection(m7gerCollectionNew);
            Collection<Cars> attachedCarsCollectionNew = new ArrayList<Cars>();
            for (Cars carsCollectionNewCarsToAttach : carsCollectionNew) {
                carsCollectionNewCarsToAttach = em.getReference(carsCollectionNewCarsToAttach.getClass(), carsCollectionNewCarsToAttach.getId());
                attachedCarsCollectionNew.add(carsCollectionNewCarsToAttach);
            }
            carsCollectionNew = attachedCarsCollectionNew;
            users.setCarsCollection(carsCollectionNew);
            Collection<TwridTransaction> attachedTwridTransactionCollectionNew = new ArrayList<TwridTransaction>();
            for (TwridTransaction twridTransactionCollectionNewTwridTransactionToAttach : twridTransactionCollectionNew) {
                twridTransactionCollectionNewTwridTransactionToAttach = em.getReference(twridTransactionCollectionNewTwridTransactionToAttach.getClass(), twridTransactionCollectionNewTwridTransactionToAttach.getId());
                attachedTwridTransactionCollectionNew.add(twridTransactionCollectionNewTwridTransactionToAttach);
            }
            twridTransactionCollectionNew = attachedTwridTransactionCollectionNew;
            users.setTwridTransactionCollection(twridTransactionCollectionNew);
            Collection<Grarat> attachedGraratCollectionNew = new ArrayList<Grarat>();
            for (Grarat graratCollectionNewGraratToAttach : graratCollectionNew) {
                graratCollectionNewGraratToAttach = em.getReference(graratCollectionNewGraratToAttach.getClass(), graratCollectionNewGraratToAttach.getId());
                attachedGraratCollectionNew.add(graratCollectionNewGraratToAttach);
            }
            graratCollectionNew = attachedGraratCollectionNew;
            users.setGraratCollection(graratCollectionNew);
            Collection<Client> attachedClientCollectionNew = new ArrayList<Client>();
            for (Client clientCollectionNewClientToAttach : clientCollectionNew) {
                clientCollectionNewClientToAttach = em.getReference(clientCollectionNewClientToAttach.getClass(), clientCollectionNewClientToAttach.getId());
                attachedClientCollectionNew.add(clientCollectionNewClientToAttach);
            }
            clientCollectionNew = attachedClientCollectionNew;
            users.setClientCollection(clientCollectionNew);
            Collection<Zalat> attachedZalatCollectionNew = new ArrayList<Zalat>();
            for (Zalat zalatCollectionNewZalatToAttach : zalatCollectionNew) {
                zalatCollectionNewZalatToAttach = em.getReference(zalatCollectionNewZalatToAttach.getClass(), zalatCollectionNewZalatToAttach.getId());
                attachedZalatCollectionNew.add(zalatCollectionNewZalatToAttach);
            }
            zalatCollectionNew = attachedZalatCollectionNew;
            users.setZalatCollection(zalatCollectionNew);
            Collection<SaderTransaction> attachedSaderTransactionCollectionNew = new ArrayList<SaderTransaction>();
            for (SaderTransaction saderTransactionCollectionNewSaderTransactionToAttach : saderTransactionCollectionNew) {
                saderTransactionCollectionNewSaderTransactionToAttach = em.getReference(saderTransactionCollectionNewSaderTransactionToAttach.getClass(), saderTransactionCollectionNewSaderTransactionToAttach.getId());
                attachedSaderTransactionCollectionNew.add(saderTransactionCollectionNewSaderTransactionToAttach);
            }
            saderTransactionCollectionNew = attachedSaderTransactionCollectionNew;
            users.setSaderTransactionCollection(saderTransactionCollectionNew);
            users = em.merge(users);
            for (M7ger m7gerCollectionOldM7ger : m7gerCollectionOld) {
                if (!m7gerCollectionNew.contains(m7gerCollectionOldM7ger)) {
                    m7gerCollectionOldM7ger.setCreatedbyid(null);
                    m7gerCollectionOldM7ger = em.merge(m7gerCollectionOldM7ger);
                }
            }
            for (M7ger m7gerCollectionNewM7ger : m7gerCollectionNew) {
                if (!m7gerCollectionOld.contains(m7gerCollectionNewM7ger)) {
                    Users oldCreatedbyidOfM7gerCollectionNewM7ger = m7gerCollectionNewM7ger.getCreatedbyid();
                    m7gerCollectionNewM7ger.setCreatedbyid(users);
                    m7gerCollectionNewM7ger = em.merge(m7gerCollectionNewM7ger);
                    if (oldCreatedbyidOfM7gerCollectionNewM7ger != null && !oldCreatedbyidOfM7gerCollectionNewM7ger.equals(users)) {
                        oldCreatedbyidOfM7gerCollectionNewM7ger.getM7gerCollection().remove(m7gerCollectionNewM7ger);
                        oldCreatedbyidOfM7gerCollectionNewM7ger = em.merge(oldCreatedbyidOfM7gerCollectionNewM7ger);
                    }
                }
            }
            for (Cars carsCollectionOldCars : carsCollectionOld) {
                if (!carsCollectionNew.contains(carsCollectionOldCars)) {
                    carsCollectionOldCars.setCreatedbyid(null);
                    carsCollectionOldCars = em.merge(carsCollectionOldCars);
                }
            }
            for (Cars carsCollectionNewCars : carsCollectionNew) {
                if (!carsCollectionOld.contains(carsCollectionNewCars)) {
                    Users oldCreatedbyidOfCarsCollectionNewCars = carsCollectionNewCars.getCreatedbyid();
                    carsCollectionNewCars.setCreatedbyid(users);
                    carsCollectionNewCars = em.merge(carsCollectionNewCars);
                    if (oldCreatedbyidOfCarsCollectionNewCars != null && !oldCreatedbyidOfCarsCollectionNewCars.equals(users)) {
                        oldCreatedbyidOfCarsCollectionNewCars.getCarsCollection().remove(carsCollectionNewCars);
                        oldCreatedbyidOfCarsCollectionNewCars = em.merge(oldCreatedbyidOfCarsCollectionNewCars);
                    }
                }
            }
            for (TwridTransaction twridTransactionCollectionOldTwridTransaction : twridTransactionCollectionOld) {
                if (!twridTransactionCollectionNew.contains(twridTransactionCollectionOldTwridTransaction)) {
                    twridTransactionCollectionOldTwridTransaction.setCreatedbyid(null);
                    twridTransactionCollectionOldTwridTransaction = em.merge(twridTransactionCollectionOldTwridTransaction);
                }
            }
            for (TwridTransaction twridTransactionCollectionNewTwridTransaction : twridTransactionCollectionNew) {
                if (!twridTransactionCollectionOld.contains(twridTransactionCollectionNewTwridTransaction)) {
                    Users oldCreatedbyidOfTwridTransactionCollectionNewTwridTransaction = twridTransactionCollectionNewTwridTransaction.getCreatedbyid();
                    twridTransactionCollectionNewTwridTransaction.setCreatedbyid(users);
                    twridTransactionCollectionNewTwridTransaction = em.merge(twridTransactionCollectionNewTwridTransaction);
                    if (oldCreatedbyidOfTwridTransactionCollectionNewTwridTransaction != null && !oldCreatedbyidOfTwridTransactionCollectionNewTwridTransaction.equals(users)) {
                        oldCreatedbyidOfTwridTransactionCollectionNewTwridTransaction.getTwridTransactionCollection().remove(twridTransactionCollectionNewTwridTransaction);
                        oldCreatedbyidOfTwridTransactionCollectionNewTwridTransaction = em.merge(oldCreatedbyidOfTwridTransactionCollectionNewTwridTransaction);
                    }
                }
            }
            for (Grarat graratCollectionOldGrarat : graratCollectionOld) {
                if (!graratCollectionNew.contains(graratCollectionOldGrarat)) {
                    graratCollectionOldGrarat.setCreatedbyid(null);
                    graratCollectionOldGrarat = em.merge(graratCollectionOldGrarat);
                }
            }
            for (Grarat graratCollectionNewGrarat : graratCollectionNew) {
                if (!graratCollectionOld.contains(graratCollectionNewGrarat)) {
                    Users oldCreatedbyidOfGraratCollectionNewGrarat = graratCollectionNewGrarat.getCreatedbyid();
                    graratCollectionNewGrarat.setCreatedbyid(users);
                    graratCollectionNewGrarat = em.merge(graratCollectionNewGrarat);
                    if (oldCreatedbyidOfGraratCollectionNewGrarat != null && !oldCreatedbyidOfGraratCollectionNewGrarat.equals(users)) {
                        oldCreatedbyidOfGraratCollectionNewGrarat.getGraratCollection().remove(graratCollectionNewGrarat);
                        oldCreatedbyidOfGraratCollectionNewGrarat = em.merge(oldCreatedbyidOfGraratCollectionNewGrarat);
                    }
                }
            }
            for (Client clientCollectionOldClient : clientCollectionOld) {
                if (!clientCollectionNew.contains(clientCollectionOldClient)) {
                    clientCollectionOldClient.setCreatedbyid(null);
                    clientCollectionOldClient = em.merge(clientCollectionOldClient);
                }
            }
            for (Client clientCollectionNewClient : clientCollectionNew) {
                if (!clientCollectionOld.contains(clientCollectionNewClient)) {
                    Users oldCreatedbyidOfClientCollectionNewClient = clientCollectionNewClient.getCreatedbyid();
                    clientCollectionNewClient.setCreatedbyid(users);
                    clientCollectionNewClient = em.merge(clientCollectionNewClient);
                    if (oldCreatedbyidOfClientCollectionNewClient != null && !oldCreatedbyidOfClientCollectionNewClient.equals(users)) {
                        oldCreatedbyidOfClientCollectionNewClient.getClientCollection().remove(clientCollectionNewClient);
                        oldCreatedbyidOfClientCollectionNewClient = em.merge(oldCreatedbyidOfClientCollectionNewClient);
                    }
                }
            }
            for (Zalat zalatCollectionOldZalat : zalatCollectionOld) {
                if (!zalatCollectionNew.contains(zalatCollectionOldZalat)) {
                    zalatCollectionOldZalat.setCreatedbyid(null);
                    zalatCollectionOldZalat = em.merge(zalatCollectionOldZalat);
                }
            }
            for (Zalat zalatCollectionNewZalat : zalatCollectionNew) {
                if (!zalatCollectionOld.contains(zalatCollectionNewZalat)) {
                    Users oldCreatedbyidOfZalatCollectionNewZalat = zalatCollectionNewZalat.getCreatedbyid();
                    zalatCollectionNewZalat.setCreatedbyid(users);
                    zalatCollectionNewZalat = em.merge(zalatCollectionNewZalat);
                    if (oldCreatedbyidOfZalatCollectionNewZalat != null && !oldCreatedbyidOfZalatCollectionNewZalat.equals(users)) {
                        oldCreatedbyidOfZalatCollectionNewZalat.getZalatCollection().remove(zalatCollectionNewZalat);
                        oldCreatedbyidOfZalatCollectionNewZalat = em.merge(oldCreatedbyidOfZalatCollectionNewZalat);
                    }
                }
            }
            for (SaderTransaction saderTransactionCollectionOldSaderTransaction : saderTransactionCollectionOld) {
                if (!saderTransactionCollectionNew.contains(saderTransactionCollectionOldSaderTransaction)) {
                    saderTransactionCollectionOldSaderTransaction.setCreatedbyid(null);
                    saderTransactionCollectionOldSaderTransaction = em.merge(saderTransactionCollectionOldSaderTransaction);
                }
            }
            for (SaderTransaction saderTransactionCollectionNewSaderTransaction : saderTransactionCollectionNew) {
                if (!saderTransactionCollectionOld.contains(saderTransactionCollectionNewSaderTransaction)) {
                    Users oldCreatedbyidOfSaderTransactionCollectionNewSaderTransaction = saderTransactionCollectionNewSaderTransaction.getCreatedbyid();
                    saderTransactionCollectionNewSaderTransaction.setCreatedbyid(users);
                    saderTransactionCollectionNewSaderTransaction = em.merge(saderTransactionCollectionNewSaderTransaction);
                    if (oldCreatedbyidOfSaderTransactionCollectionNewSaderTransaction != null && !oldCreatedbyidOfSaderTransactionCollectionNewSaderTransaction.equals(users)) {
                        oldCreatedbyidOfSaderTransactionCollectionNewSaderTransaction.getSaderTransactionCollection().remove(saderTransactionCollectionNewSaderTransaction);
                        oldCreatedbyidOfSaderTransactionCollectionNewSaderTransaction = em.merge(oldCreatedbyidOfSaderTransactionCollectionNewSaderTransaction);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            Collection<M7ger> m7gerCollection = users.getM7gerCollection();
            for (M7ger m7gerCollectionM7ger : m7gerCollection) {
                m7gerCollectionM7ger.setCreatedbyid(null);
                m7gerCollectionM7ger = em.merge(m7gerCollectionM7ger);
            }
            Collection<Cars> carsCollection = users.getCarsCollection();
            for (Cars carsCollectionCars : carsCollection) {
                carsCollectionCars.setCreatedbyid(null);
                carsCollectionCars = em.merge(carsCollectionCars);
            }
            Collection<TwridTransaction> twridTransactionCollection = users.getTwridTransactionCollection();
            for (TwridTransaction twridTransactionCollectionTwridTransaction : twridTransactionCollection) {
                twridTransactionCollectionTwridTransaction.setCreatedbyid(null);
                twridTransactionCollectionTwridTransaction = em.merge(twridTransactionCollectionTwridTransaction);
            }
            Collection<Grarat> graratCollection = users.getGraratCollection();
            for (Grarat graratCollectionGrarat : graratCollection) {
                graratCollectionGrarat.setCreatedbyid(null);
                graratCollectionGrarat = em.merge(graratCollectionGrarat);
            }
            Collection<Client> clientCollection = users.getClientCollection();
            for (Client clientCollectionClient : clientCollection) {
                clientCollectionClient.setCreatedbyid(null);
                clientCollectionClient = em.merge(clientCollectionClient);
            }
            Collection<Zalat> zalatCollection = users.getZalatCollection();
            for (Zalat zalatCollectionZalat : zalatCollection) {
                zalatCollectionZalat.setCreatedbyid(null);
                zalatCollectionZalat = em.merge(zalatCollectionZalat);
            }
            Collection<SaderTransaction> saderTransactionCollection = users.getSaderTransactionCollection();
            for (SaderTransaction saderTransactionCollectionSaderTransaction : saderTransactionCollection) {
                saderTransactionCollectionSaderTransaction.setCreatedbyid(null);
                saderTransactionCollectionSaderTransaction = em.merge(saderTransactionCollectionSaderTransaction);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
