package rmvanti.todo.list.supero.service;

import com.google.gson.Gson;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rmvanti.todo.list.supero.control.dao.Dao;
import rmvanti.todo.list.supero.control.dao.IDao;
import rmvanti.todo.list.supero.model.Task;
import rmvanti.todo.list.supero.viewmodel.TaskViewModel;

/**
 * @author rmvanti
 */
@Path("task")
public class TaskListService {
    
    private final EntityManager manager;
    
    public TaskListService(){
        this.manager = Persistence.createEntityManagerFactory("TodoListSuperoPU").createEntityManager();
    }
    
    private TaskViewModel taskToModel(Task task, TaskViewModel model){
        model.setId(task.getId());
        //model.setCreatedIn(task.getCreatedIn());
        //model.setUpdatedIn(task.getUpdatedIn());
        model.setDescription(task.getDescription());
        model.setTitle(task.getTitle());
        model.setDone(task.isDone());
        //model.setDoneDate(task.getDoneDate());        
        return model;
    }
    
    private Task modelToTask(TaskViewModel model, Task task){
        //task.setUpdatedIn(model.getUpdatedIn());
        task.setDescription(model.getDescription());
        task.setTitle(model.getTitle());
        task.setDone(model.isDone());
        return task;
    }
    
    @PreDestroy
    private void destroy(){
        this.manager.close();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getList(){ 
        IDao<Task,Integer> dao = new Dao(Task.class, this.manager);
        List<Task> list = dao.findAll();
        return Response.ok()
                .entity(new GenericEntity<List<Task>>(list){})
                //.header("Access-Control-Allow-Origin", "*")
                //.header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS")
                //.header("Access-Control-Allow-Credentials", "true")
                //.header("Origin", "tasklist")
                .build();
    }
    
    @GET
    @Path("{id}")    
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id") int id){ 
        Gson gson = new Gson();
        IDao<Task,Integer> dao = new Dao(Task.class, this.manager);
        
        Task task = dao.findById(id);
        TaskViewModel model = new TaskViewModel();
        
        if(task == null){
            model.setError(true);
            model.setMsg("Registro não encontrado!");
        }else{
            taskToModel(task, model);
        }        
        return Response.ok(gson.toJson(model, TaskViewModel.class))
                //.header("Access-Control-Allow-Origin", "*")
                //.header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS")
                //.header("Access-Control-Allow-Credentials", "true")
                //.header("Origin", "tasklist")
                .build();
    }
    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response insert(String json){
        Gson gson = new Gson();
        IDao<Task,Integer> dao = new Dao(Task.class, this.manager);
        
        TaskViewModel model = gson.fromJson(json, TaskViewModel.class);
        Task task = new Task();        
        task = modelToTask(model, task);
        
        dao.insert(task);
        model.setError(false);
        model.setMsg("Registro incluído com sucesso!");
                
        return Response.ok(gson.toJson(model, TaskViewModel.class))
                //.header("Access-Control-Allow-Origin", "*")
                //.header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS")
                //.header("Access-Control-Allow-Credentials", "true")
                //.header("Origin", "tasklist")
                .build();
    }
    
    @OPTIONS
    public Response cors(){
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Origin", "tasklist")
                .allow("origin","tasklist").build();
    }
    
    /*
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id){
        Gson gson = new Gson();
        IDao<Task,Integer> dao = new Dao(Task.class, this.manager);
                
        TaskViewModel model = new TaskViewModel();
        Task task = dao.findById(id);
        
        if(task == null){    
            model.setError(true);
            model.setMsg("Registro não encontrado!");
        }else{
            model.setError(true);
            model.setMsg("Registro excluído com sucesso!");
        }
        return Response.ok(gson.toJson(model, TaskViewModel.class)).build();
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(String json){
        Gson gson = new Gson();
        IDao<Task,Integer> dao = new Dao(Task.class, this.manager);
                
        TaskViewModel model = gson.fromJson(json, TaskViewModel.class);
        Task task = dao.findById(model.getId());
        
        task = modelToTask(model, task);
        
        dao.update(task);
        return Response.ok(gson.toJson(model, TaskViewModel.class)).build();
    }
    
    */
    
}//fim class
