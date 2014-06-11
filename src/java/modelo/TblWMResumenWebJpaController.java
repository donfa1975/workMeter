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
public class TblWMResumenWebJpaController implements Serializable {

    public TblWMResumenWebJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblWMResumenWeb tblWMResumenWeb) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tblWMResumenWeb);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTblWMResumenWeb(tblWMResumenWeb.getIdReporteWeb()) != null) {
                throw new PreexistingEntityException("TblWMResumenWeb " + tblWMResumenWeb + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblWMResumenWeb tblWMResumenWeb) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tblWMResumenWeb = em.merge(tblWMResumenWeb);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblWMResumenWeb.getIdReporteWeb();
                if (findTblWMResumenWeb(id) == null) {
                    throw new NonexistentEntityException("The tblWMResumenWeb with id " + id + " no longer exists.");
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
            TblWMResumenWeb tblWMResumenWeb;
            try {
                tblWMResumenWeb = em.getReference(TblWMResumenWeb.class, id);
                tblWMResumenWeb.getIdReporteWeb();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblWMResumenWeb with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblWMResumenWeb);
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

    public List<TblWMResumenWeb> findTblWMResumenWebEntities() {
        return findTblWMResumenWebEntities(true, -1, -1);
    }

    public List<TblWMResumenWeb> findTblWMResumenWebEntities(int maxResults, int firstResult) {
        return findTblWMResumenWebEntities(false, maxResults, firstResult);
    }

    private List<TblWMResumenWeb> findTblWMResumenWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblWMResumenWeb.class));
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

    public TblWMResumenWeb findTblWMResumenWeb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblWMResumenWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblWMResumenWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblWMResumenWeb> rt = cq.from(TblWMResumenWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
