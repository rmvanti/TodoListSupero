package rmvanti.todo.list.supero.control.dao;

/**
 * @author rmvanti
 * @param <K> Primary key type
 */
public interface IIdentifiable <K> {
    
    public void setId(K id);
    public K getId();
    
}//fim interface
