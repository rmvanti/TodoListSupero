package rmvanti.todo.list.supero.control.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author rmvanti
 */
@MappedSuperclass
public abstract class AbstractAutoNumberEntity implements Serializable, IIdentifiable<Integer>, ITimestantable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    
    @Temporal(TemporalType.DATE)
    protected final Date createdIn; 
    
    @Temporal(TemporalType.DATE)
    protected Date updatedIn;     
    
    public AbstractAutoNumberEntity(){
        this.createdIn = new Date();        
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
       return this.id;
    }            

    @Override
    public Date getCreatedIn() {
        return this.createdIn;
    }

    @Override
    public Date getUpdatedIn() {
        return updatedIn;
    }

    @Override
    public void setUpdatedIn(Date newDate) {
        this.updatedIn = newDate;
    }
                    
}//fim class
