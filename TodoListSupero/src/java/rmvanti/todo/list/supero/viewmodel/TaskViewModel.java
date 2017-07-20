package rmvanti.todo.list.supero.viewmodel;

/**
 * @author rmvanti
 */
public class TaskViewModel {
    
    private Integer id;
    private String createdIn;
    private String updatedIn;
    private String doneDate;
    private boolean done;
    private String title;
    private String description;
    
    private boolean error;
    private String msg;
    
    public TaskViewModel(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }

    public String getUpdatedIn() {
        return updatedIn;
    }

    public void setUpdatedIn(String updatedIn) {
        this.updatedIn = updatedIn;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
                    
}//fim class
