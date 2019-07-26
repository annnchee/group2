import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleInteractive {
    private Scanner sc = new Scanner(System.in);
    private ToDoList tdl = new ToDoList();

    public ConsoleInteractive(){
        sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public String analyzeRequest(String request){
        if(!request.startsWith("todolist "))
            return null; //return error indicator

        request = request.substring(9);


        if(request.equals("-h")){
            //System.out.println("call help");
            return "help";//to call print all cmd
        }

        if(request.equals("add")){
            //System.out.println("call add");
            return this.addTask();
        }

        if(request.startsWith("view")){
            try{
                request = request.substring(5);
            } catch (StringIndexOutOfBoundsException e){
                return this.viewAllTask();
            }
            String taskDesc = request;
            return this.viewTask(taskDesc);
        }

        if(request.startsWith("update")){
            try{
                request = request.substring(7);
            } catch (StringIndexOutOfBoundsException e){
                return "update shall be followed the task description";
            }
            String taskDesc = request;
            return this.updateTask(taskDesc);
        }

        if(request.startsWith("delete")){
            try{
                request = request.substring(7);
            } catch (StringIndexOutOfBoundsException e){
                return "delete shall be followed the task description";
            }
            String taskDesc = request;
            return this.deleteTask(taskDesc);
        }

        else
            return null;
    }

    public void printAllCmd(){
        //System.out.println("\n");
        System.out.println("\ntodolist add : to add a new task");
        System.out.println("todolist view: to view all tasks");
        System.out.println("todolist view [task desc] : to view a task regarding to its description");
        System.out.println("todolist update [task desc] : to update a task regarding to its description");
        System.out.println("todolist delete [task desc] : to delete a task regarding to its description");
    }

    public String addTask(){
        System.out.print("\nTask Desc : ");
        String desc = this.getInput();

        Collection<Task> tasks = tdl.getAllTasks();
        for(Task t : tasks){
            if(desc.equals(t.getDescription()))
                return "Cannot create Task, Duplicated Task Desc";//error on duplicated desc
        }
//
//        System.out.print("\nTask Complement Status (T/F): ");
//        String status = this.getInput();
//        boolean isCompleted = false;
//        if(status == "T" || status == "t")

//        System.out.println("\nTask Catagory : ");
//        String catagory = this.getInput();

        System.out.print("Task Tag : " );
        String tag = this.getInput();

        Task task = new Task(desc, false, tag);
        tdl.addTask(task);
        System.out.println("Success! You have add a task " + task.getDescription());
        return "success";
    }

    public String viewTask(String taskDesc){
        Task task = tdl.getTask(taskDesc);

        System.out.println("\nTask Desc : " + task.getDescription());
        System.out.println("Task Completed Status : " + task.isComplete());
        System.out.println("Task Tag : " + task.getTag());

        return "success";
    }

    public String viewAllTask(){
        Collection<Task> tasks = tdl.getAllTasks();
        for(Task t : tasks){
            this.viewTask(t.getDescription());
        }
        return "success";
    }

    public String updateTask(String desc){
        this.viewTask(desc);

        System.out.print("\nDo you want to make the task completed (y/n): ");
        String isChanged = this.getInput();
        boolean isCompleted = false;

        if(isChanged.equalsIgnoreCase("y"))
            isCompleted = true;
        else if(isChanged.equalsIgnoreCase("n")){}
        else
            return "Invalid input for task complete status";

        String tag = null;
        System.out.print("Do you want to update the task tag (y/n): ");
        String tagIsChange = this.getInput();
        if(tagIsChange.equalsIgnoreCase("y")){
            System.out.print("Task Tag : ");
            tag = this.getInput();
        }
        else if(tagIsChange.equalsIgnoreCase("n")){}
        else
            return "Invalid input for updating task tag";


        Collection<Task> tasks = tdl.getAllTasks();
        for(Task t : tasks){
            if(t.getDescription().equals(desc)){
                t.setComplete(isCompleted);
                t.setTag(tag);
                System.out.println("Success! You have marked " + t.getDescription() + "completed!");
            }
        }


        this.viewTask(desc);

        return "success";
    }

    public String deleteTask(String desc){
        System.out.print("Are you sure to delete this task " + desc + " (y/n) : ");
        String isConfirmed = this.getInput();

        if(isConfirmed.equalsIgnoreCase("y")){
            tdl.removeTask(desc);
            System.out.println("Success! You have deleted the task " + desc);
            return "success";
        }
        else if(isConfirmed.equalsIgnoreCase("n")){return "success";}
        else
            return "Invalid input for task delete command";
    }
}
