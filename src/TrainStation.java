import javafx.application.Application;
import javafx.stage.Stage;
import java.io.File;
import java.util.Scanner;

public class TrainStation extends Application {
    private String[] array = new String[42];
    private static String[] waitingRoom = new String[42];
    public static void main(String[] args){launch();}

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadPassengers(array);

        menu:
        while (true) {
            System.out.println("");
            System.out.println("----------------------------------------------");
            System.out.println("    Denuwara Manike Train A/C compartment");
            System.out.println("");
            System.out.println("                    MENU                 ");
            System.out.println("----------------------------------------------");
            System.out.println("A :\t to Add passenger to the Train queue ");
            System.out.println("V :\t to View the passengers in the queue ");
            System.out.println("D :\t to Delete passenger from the queue ");
            System.out.println("S :\t to Store Passenger data in to a file");
            System.out.println("L :\t to Load data from file to program ");
            System.out.println("R :\t to Run the simulation and produce report ");
            System.out.println("Q :\t to Exit the program");
            System.out.println("______________________________________________");
            System.out.print("Enter your choice here : ");
            System.out.print("");

            Scanner sc = new Scanner(System.in);
            String menu = sc.next().toUpperCase(); //all input values convert uppercase


            switch (menu) {
                case "A":
                    PassengerQueue.addQueue(array);
                    break;

                case "V":
                    PassengerQueue.viewQueue();
                    break;

                case "D":
                    PassengerQueue.remove();
                    break;

                case "S":
                    PassengerQueue.saveQueue();
                    break;

                case "L":
                    PassengerQueue.loadQueue();
                    break;

                case "R":
                    PassengerQueue.trainSimulationAndGenerateReport();
                    break;

                case "Q":
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("\n Invalid Input Try again \n\n");
            }
        }
    }

    private static void  loadPassengers(String[] array) {
        try {
            File myobj = new File("C:\\Users\\DOWNLOAD\\Desktop\\cw2\\Course work 2\\src\\CustomerDataBtoC.txt");
            Scanner myReader = new Scanner(myobj);

            for (int i = 0; i < 42; i++) {
                String names = myReader.nextLine().toLowerCase();
                int seat = i + 1;
                // print names and seat numbers
                if (!names.equals("null")) {
                    System.out.println(seat + " || " + names);
                }
                if (names.equals("null")) {
                    names= null;
                }
                else
                    array[i] = names;
            }
            System.out.println("");
            myReader.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
}
