package rmvanti.todo.list.supero.control.dao;

import java.util.List;

/**
 * @author rmvanti
 * @param <E> Entity type
 * @param <K> Primary key type
 */
public interface IDao<E,K> {
    
    public void insert(E entity);
    public void delete(E entity);
    public void update(E entity);
    
    public int count();
    public List<E> findAll();
    public E findById(K id);
    
}//fim interface
