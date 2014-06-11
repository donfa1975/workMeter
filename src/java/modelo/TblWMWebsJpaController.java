/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import modelo.exceptions.NonexistentEntityException;
import modelo.exceptions.PreexistingEntityException;
import modelo.exceptions.RollbackFailureException;

/**
 *
 * @author fabricio.diaz
 */
public class TblWMWebsJpaController implements Serializable {

    public TblWMWebsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblWMWebs tblWMWebs) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tblWMWebs);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTblWMWebs(tblWMWebs.getIdWebs()) != null) {
                throw new PreexistingEntityException("TblWMWebs " + tblWMWebs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblWMWebs tblWMWebs) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tblWMWebs = em.merge(tblWMWebs);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblWMWebs.getIdWebs();
                if (findTblWMWebs(id) == null) {
                    throw new NonexistentEntityException("The tblWMWebs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TblWMWebs tblWMWebs;
            try {
                tblWMWebs = em.getReference(TblWMWebs.class, id);
                tblWMWebs.getIdWebs();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblWMWebs with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblWMWebs);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblWMWebs> findTblWMWebsEntities() {
        return findTblWMWebsEntities(true, -1, -1);
    }

    public List<TblWMWebs> findTblWMWebsEntities(int maxResults, int firstResult) {
        return findTblWMWebsEntities(false, maxResults, firstResult);
    }

    private List<TblWMWebs> findTblWMWebsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblWMWebs.class));
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

    public TblWMWebs findTblWMWebs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblWMWebs.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblWMWebsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblWMWebs> rt = cq.from(TblWMWebs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
