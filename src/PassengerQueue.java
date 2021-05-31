import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class PassengerQueue {

    private static String[] waitingRoom = new String[42];
    private static String[] trainQueue = new String[42];

    private static int maxStayInQueue = 0;
    private static int minStayInQueue = 18;
    static ArrayList<Integer> queueTimer = new ArrayList<Integer>();
    static ArrayList<String> passengersName = new ArrayList<String>();

    public static void addQueue(String[] array) {
        waitingRoom = array; // loaded data store waiting room
        try {
            int diceNumber = (int) (Math.random() * 6 + 1);     //play dice to get random number
            System.out.println("");
            System.out.println("Random Number is = " + diceNumber); // print random number
            int count = 0;
            for (int i = 0; i < diceNumber; i++) {
                while (waitingRoom[count] == null) {
                    count++;
                }
                if (waitingRoom[count] != null) {

                    trainQueue[count] = waitingRoom[count];
                    waitingRoom[count] = null;
                    count++;

                }
            }
            System.out.println("--------------");
            System.out.println(" waiting room ");
            System.out.println("--------------");       //display waiting room
            System.out.println("SEAT NUMBER \t\t  NAME \n");
            for (int i =0; i<waitingRoom.length; i++){
                if (waitingRoom[i] != null) {
                    System.out.println((i + 1) + "\t\t\t\t\t" + waitingRoom[i]);
                }
            }
            System.out.println("");
        }catch (Exception e){
            System.out.println(" Waiting room is Empty. ");
        }
        //GUI
        Stage stage = new Stage();
        stage.setTitle("Train Queue");
        FlowPane layout = new FlowPane();
        layout.setPadding(new Insets(5));
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setStyle("-fx-background-color: black;");

        for (int i=0;i< trainQueue.length;i++){

            if(trainQueue[i] != null) {
                ToggleButton btn = new ToggleButton();
                btn.setPrefWidth(350);
                btn.setPrefHeight(10);
                btn.setText(trainQueue[i] + " | " + (i+1));
                btn.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
                btn.setStyle("-fx-background-color: #9dbda4; -fx-text-fill: white;");
                VBox.setMargin(btn, new Insets(10, 10, 10, 10));
                layout.getChildren().add(btn);
            }
        }
        stage.setScene(new Scene(layout, 360, 900));
        stage.showAndWait();
    }


    public static void viewQueue() {
        Stage stage = new Stage();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("View Train Queue");
        FlowPane layout = new FlowPane();
        layout.setPadding(new Insets(5));
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setStyle("-fx-background-color: black;");

        for (int i = 0; i < trainQueue.length; i++) {
            Button button = new Button();
            button.setId(Integer.toString(i));
            button.setPrefWidth(230);
            button.setPrefHeight(39);
            button.setStyle("-fx-background-color: #a0a3a3; -fx-text-fill: black;");
            button.setDisable(false);
            layout.getChildren().add(button);

            if (trainQueue[i] != null) {

                button.setText(trainQueue[i] + " || Seat " + (i + 1));
                button.setFont(Font.font("Cambria",FontWeight.BOLD,15));
            }
            else {
                button.setText("Empty");    //display empty seats empty and different color
                button.setFont(Font.font("Cambria",FontWeight.BOLD, 15));
                button.setStyle("-fx-background-color: #4c696b; -fx-text-fill: white;");
            }
        }
        Scene scene = new Scene(layout, 741, 820);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public static void remove() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your first name : ");
        String firstName = scan.next().toLowerCase();         //turn the string value to uppercase
        System.out.print("Enter your surname : ");
        String lastName = scan.next().toLowerCase();
        String fullName = (firstName + " " + lastName);     //turn the string value to uppercase

        for (int i = 0; i < trainQueue.length; i++) {            //create buttons id using for loop
            if ((trainQueue[i] != null) && (trainQueue[i].equals(fullName))) {
                System.out.println("seat numbers\t" + (i + 1));
                continue;
            }
        }

        for (int n = 0; n < trainQueue.length; n++) {            //create buttons id using for loop
                if ((trainQueue[n] != null) && (trainQueue[n].equals(fullName))) {
                    System.out.print("Enter seat number\t : ");
                    int seatNumber  = scan.nextInt() - 1 ;
                    System.out.println("");
                    n = seatNumber;

            System.out.println("Passenger Name : " + fullName + "\n\tPassenger Seat Number : " + (seatNumber+1) + " \nYou have been Removed from Train Queue\n Thank you!!");
                trainQueue[n] = null;
                break;
            } else if ((trainQueue[n] != null) && (n == 41) && !(trainQueue[n].equals(fullName))) {
                System.out.println("Passenger Name : " + fullName + " \n\tYou are not in TrainQueueu ...");
            } else if ((trainQueue[n] == null) && (n == 41)) {
                System.out.println("Passenger Name : " + fullName + " \n\tYou are not in TrainQueueu ...");
            }
        }
    }

    public static void saveQueue() {
        try {
            FileWriter myWriter = new FileWriter("waitingRoom.txt");    //write data to text files
            FileWriter myWriter2 = new FileWriter("TrainQueue.txt");

            StringBuilder dataToSave = new StringBuilder();
            StringBuilder dataToSave2 = new StringBuilder();
            for (int i = 0; i < 42; i++) {
                dataToSave.append(waitingRoom[i] + "\n");
            }

            for (int i = 0; i < 42; i++) {
                dataToSave2.append(trainQueue[i] + "\n");
            }
            myWriter.write(String.valueOf(dataToSave));
            myWriter2.write(String.valueOf(dataToSave2));
            myWriter.close();
            myWriter2.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadQueue() {
        try {
            File file = new File("waitingRoom.txt");
            File file1 = new File("TrainQueue.txt");

            Scanner scanner = new Scanner(file);
            Scanner scanner1 = new Scanner(file1);

            System.out.println("");//WaitingRoom Display ...
            System.out.println("Waiting Room ");
            System.out.println("______________");
            System.out.println("");
            System.out.println("Seat number \t " +  "Name \n");

            for (int i=0; i < 42; i++) {
                int seatNumber = i + 1;
                String passengerName = scanner.nextLine();

                if (!passengerName.equals("null")) {
                    System.out.println(seatNumber + "\t\t\t\t"  + passengerName );
                }
                waitingRoom.equals(passengerName);
            }
            System.out.println("--------------------------------");
            System.out.println("Waiting Room Loaded Successfully \n\n");

            //TrainQueue Display ...
            System.out.println("Train Queue");
            System.out.println("______________");
            System.out.println("");
            System.out.println("Seat number \t " +  "Name ");
            for (int i=0; i <42; i++) {
                int SeatNumber = i + 1;
                String passengerName = scanner1.nextLine();

                if (!passengerName.equals("null")) {
                    System.out.println(SeatNumber + "\t\t\t\t" + passengerName );
                }
                trainQueue.equals(passengerName);
            }
            System.out.println("--------------------------------");
            System.out.println("Train Queue Loaded Successfully \n\n");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void trainSimulationAndGenerateReport() {
        for (int i =0; i < trainQueue.length; i++){
            if (trainQueue[i] != null) {
                int random = (int) (Math.random() * 16 + 3); //genarate random numbers
                passengersName.add(" seat " + (i + 1) + " | " + trainQueue[i] );
                queueTimer.add(random);
            }
        }
        int queueLength = queueTimer.size(); //find train queue length
        int totalQueueTime = 0;
        for (int i=0; i < queueTimer.size();i++) {
            totalQueueTime += queueTimer.get(i);
            if (maxStayInQueue < queueTimer.get(i)) {
                maxStayInQueue = queueTimer.get(i);
            }
            if (minStayInQueue >= queueTimer.get(i)) {
                minStayInQueue = queueTimer.get(i);
            }
        }
        double averegeQueueTimer= totalQueueTime/ queueLength; //find average time
        Math.round(averegeQueueTimer);

        for (int i = 0; i < trainQueue.length; i++) {
            if ((trainQueue)[i] != null)
                trainQueue[i]=null;
        }
        //GUI
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Final Report - Train Queue");
        AnchorPane myPane = new AnchorPane();

        Label mainLabel = new Label(" ~ Final Report - Train Queue ~");
        mainLabel.setStyle (" -fx-text-fill: white ;-fx-font-weight: bolder;-fx-font-size: 30;");
        mainLabel.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor( mainLabel, 28.0);
        AnchorPane.setLeftAnchor( mainLabel, 30.0);
        myPane.getChildren().add(mainLabel);

        Label maxLen = new Label("01 | Maximum Length of the Train Queue - " + queueLength);
        maxLen.setStyle ("-fx-text-fill: white ;-fx-font-weight: bolder;-fx-font-size: 18;");
        maxLen.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor( maxLen, 28.0);
        AnchorPane.setLeftAnchor( maxLen, 30.0);
        AnchorPane.setTopAnchor(maxLen,75.0);
        myPane.getChildren().add(maxLen);

        Label maxTime = new Label("02 | Maximum Time in the train Queue  - " + maxStayInQueue);
        maxTime.setStyle ("-fx-text-fill: white ;-fx-font-weight: bolder;-fx-font-size: 18;");
        maxTime.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor( maxTime, 28.0);
        AnchorPane.setLeftAnchor( maxTime, 30.0);
        AnchorPane.setTopAnchor(maxTime, 100.0);
        myPane.getChildren().add(maxTime);

        Label minTime = new Label("03 |  Minimum Time in the train Queue - " + minStayInQueue);
        minTime.setStyle ("-fx-text-fill: white ;-fx-font-weight: bolder;-fx-font-size: 18;");
        minTime.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor( minTime, 28.0);
        AnchorPane.setLeftAnchor( minTime, 30.0);
        AnchorPane.setTopAnchor(minTime,125.0);
        myPane.getChildren().add(minTime);

        Label avgTime = new Label("04 | Average time in the train Queue - " + averegeQueueTimer);
        avgTime.setStyle ("-fx-text-fill: white ;-fx-font-weight: bolder;-fx-font-size: 18;");
        avgTime.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor( avgTime, 38.0);
        AnchorPane.setLeftAnchor( avgTime, 30.0);
        AnchorPane.setTopAnchor(avgTime,150.0);
        myPane.getChildren().add(avgTime);

        Label label = new Label("---------- Passenger Details ----------");
        label.setStyle("-fx-text-fill: white; -fx-font-weight: bolder;-fx-font-size: 22;");
        label.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor( label, 30.0);
        AnchorPane.setLeftAnchor( label, 30.0);
        AnchorPane.setTopAnchor(label,210.0);
        myPane.getChildren().add(label);

        double topToItem = 230.0;
        double topToItem1 = 230.0;

        for (int i = 0; i < passengersName.size(); i++){
            Label item = new Label(passengersName.get(i));
            item.setStyle("-fx-text-fill: white; -fx-font-weight: bolder;-fx-font-size: 18;");
            item.setAlignment(Pos.CENTER);
            AnchorPane.setLeftAnchor(item,60.0);
            topToItem += 25.0;
            AnchorPane.setTopAnchor(item,topToItem);
            myPane.getChildren().add(item);

            Label item1 = new Label( " Spent time: "+ + queueTimer.get(i) +"\n");
            item1.setStyle("-fx-text-fill: white; -fx-font-weight: bolder;-fx-font-size: 18;");
            item1.setAlignment(Pos.CENTER);
            AnchorPane.setLeftAnchor(item1,320.0);
            topToItem1 += 25.0;
            AnchorPane.setTopAnchor(item1,topToItem1);
            myPane.getChildren().add(item1);
        }
        myPane.setStyle("-fx-background-color: #515454");
        Scene scene = new Scene(myPane, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        reportFile(queueLength,averegeQueueTimer, maxStayInQueue, minStayInQueue, passengersName,  queueTimer );
        queueTimer.clear();
        passengersName.clear();
    }

    private static void reportFile(int queueLength, double averegeTimer, int maxStayInQueue, int minStayInQueue, ArrayList<String> passengersName, ArrayList<Integer> queueTimer ) {
        try {
            FileWriter reportFile = new FileWriter("finalReport.txt");       //create new file
            reportFile.write("01. Maximum Length of the Train Queue - " + queueLength + "\n");
            reportFile.write("02. Maximum Time in the train Queue - " + maxStayInQueue + "\n");
            reportFile.write("03. Minimum Time in the train Queue - " + minStayInQueue + "\n");
            reportFile.write("04. Average time in the train Queue - " + averegeTimer + "\n\n\n");

            for (int i = 0; i < passengersName.size(); i++){
                reportFile.write(  (i+1)+"." + passengersName.get(i)+" | Spent time: "+ queueTimer.get(i)+"\n");
            }
            reportFile.close();
            System.out.println("Data stored successfully !!");
        } catch (Exception e) {
            System.out.println("Operation is unsuccessful..." + e);
        }
    }

}

