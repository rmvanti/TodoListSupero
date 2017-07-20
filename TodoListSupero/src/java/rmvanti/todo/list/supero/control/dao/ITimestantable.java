package rmvanti.todo.list.supero.control.dao;

import java.util.Date;

/**
 * @author rmvanti
 */
public interface ITimestantable {
    
    public Date getCreatedIn();
    public Date getUpdatedIn();
    public void setUpdatedIn(Date newDate);
    
}//fim interface
