package rmvanti.todo.list.supero.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import rmvanti.todo.list.supero.control.dao.AbstractAutoNumberEntity_;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-20T17:45:22")
@StaticMetamodel(Task.class)
public class Task_ extends AbstractAutoNumberEntity_ {

    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, String> title;
    public static volatile SingularAttribute<Task, Date> doneDate;
    public static volatile SingularAttribute<Task, Boolean> done;

}