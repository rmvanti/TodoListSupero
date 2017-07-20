package rmvanti.todo.list.supero.service.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import rmvanti.todo.list.supero.service.TaskListService;

/**
 * @author rmvanti
 */
@ApplicationPath("rs")
public class ServiceConfig extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet();
        set.add(TaskListService.class);
        return set;
    }
            
}//fim class
