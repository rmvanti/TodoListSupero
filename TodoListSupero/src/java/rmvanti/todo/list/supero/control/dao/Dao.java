package rmvanti.todo.list.supero.control.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author rmvanti
 * @param <E> Entity type
 * @param <K> Primary key type
 */
public class Dao<E,K> implements IDao<E,K>{

    private final EntityManager manager;
    private final Class clazz;
    private final List<Class> interfaces;
    private final boolean isTimestantable;
    
    public Dao(Class entityClass, EntityManager manager){
        this.manager = manager;
        this.clazz = entityClass;
        
        this.interfaces = new ArrayList();               
        this.interfaces.addAll(Arrays.asList(this.clazz.getInterfaces()));
        
        this.isTimestantable = this.interfaces.contains(ITimestantable.class);
    }
    
    private void verifyTimestantableEntity(E entity) {
        if (this.isTimestantable) {
            ITimestantable timestantableEntity = (ITimestantable) entity;
            timestantableEntity.setUpdatedIn(new Date());
        }
    }
    
    @Override
    public void insert(E entity) {
        this.manager.getTransaction().begin();
        verifyTimestantableEntity(entity);
        this.manager.persist(entity);
        this.manager.getTransaction().commit();
    }

    @Override
    public void delete(E entity) {
        this.manager.getTransaction().begin();
        this.manager.remove(entity);
        this.manager.getTransaction().commit();
    }

    @Override
    public void update(E entity) {
        this.manager.getTransaction().begin();
        verifyTimestantableEntity(entity);
        this.manager.merge(entity);
        this.manager.getTransaction().commit();
    }

    @Override
    public int count() {
        CriteriaQuery cq = this.manager.getCriteriaBuilder().createQuery();
        Root<E> rt = cq.from(this.clazz);
        cq.select(this.manager.getCriteriaBuilder().count(rt));
        Query q = this.manager.createQuery(cq);
        return ((Integer) q.getSingleResult());
    }

    @Override
    public List<E> findAll() {
        CriteriaQuery query = this.manager.getCriteriaBuilder().createQuery();
        query.select(query.from(this.clazz));
        return new ArrayList(this.manager.createQuery(query).getResultList());
    }

    @Override
    public E findById(K primaryKey) {
        return (E) this.manager.find(clazz, primaryKey);
    }
            
}//fim class
