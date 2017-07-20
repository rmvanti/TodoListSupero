package rmvanti.todo.list.supero.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import rmvanti.todo.list.supero.control.dao.AbstractAutoNumberEntity;

/**
 * @author rmvanti
 */
@Entity
public class Task extends AbstractAutoNumberEntity {
    
    @Temporal(TemporalType.DATE)
    private Date doneDate;
    
    private boolean done;
    private String title;
    private String description;
    
    public Task(){
        super();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
        if(done){
            this.doneDate = new Date();
        } else {
            this.doneDate = null;
        }//fim else
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDoneDate() {
        return doneDate;
    }        
    
}//fim class
