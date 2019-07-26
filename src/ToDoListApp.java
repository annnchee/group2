public class ToDoListApp {
    public static void main(String args[]){
        ConsoleInteractive ci = new ConsoleInteractive();
        System.out.println("Please input your request, if you are not familiar with this app, please key in todolist -h");

        while(true) {
            System.out.println("\nPleae input your request: ");
            String request = ci.getInput();

            String aResult = ci.analyzeRequest(request);

            if(aResult == null) {
                System.out.println("\nInput is invalid, please use todolist -h for help.");
                continue;
            }

            if(aResult.equals("help")){
                ci.printAllCmd();
            }

            if(aResult != "success"){
                System.out.println("\n" + aResult);
            }
        }
    }
}
