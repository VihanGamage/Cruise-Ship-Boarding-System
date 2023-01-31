package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Cruiseship {

    public static void main(String[] args) throws FileNotFoundException {

        Passenger passenger = new Passenger("empty","empty",0);

        Cabin[][] cabin = new Cabin[12][3];    //creating cabin object 2D array
        for (int i = 0; i < 12; i++) {
            for (int j=0; j<3; j++){
                cabin[i][j] = new Cabin(passenger);    //adding passenger objects to 2D array
            }
        }
        Queue queue = new Queue(10);   //creating queue object

        menu(cabin,passenger,queue);   //calling main menu
    }

    public static void menu(Cabin[][] cabin, Passenger passenger, Queue queue) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n------------------------------");   //menu
            System.out.println("\t\t\tMENU");
            System.out.println("'A'-> Add passenger to a cabin");
            System.out.println("'V'-> View all cabins");
            System.out.println("'E'-> Display empty cabins");
            System.out.println("'D'-> Delete passenger from cabin");
            System.out.println("'F'-> Find cabin from passenger name");
            System.out.println("'S'-> Store program data into file");
            System.out.println("'L'-> Load program data from file");
            System.out.println("'O'-> View passengers ordered alphabetically by name");
            System.out.println("'T'-> Total expenses");
            System.out.println("--------------------------------");
            System.out.print("Enter your choice (press 'q' to quit the programme) : ");    //getting input
            String choice = input.next();
            System.out.println();
            switch (choice.toLowerCase(Locale.ROOT)) {    //switch
                case "a" -> add(cabin, passenger, queue);
                case "v" -> view(cabin, passenger);
                case "e" -> empty(cabin, passenger);
                case "d" -> delete(cabin, passenger, queue);
                case "f" -> find(cabin, passenger);
                case "s" -> store(cabin, passenger);
                case "l" -> load(cabin, passenger);
                case "o" -> order(cabin, passenger);
                case "t" -> total(cabin, passenger);
            }
            if (choice.equalsIgnoreCase("q")) {      //breaking the loop
                break;
            }
        }
    }

    public static void add(Cabin[][] cabin, Passenger passenger, Queue queue){    //add passenger method
        Scanner input = new Scanner(System.in);
        int count=0;
        for (int i=0; i<12; i++) {     //checking the all cabins are full
            if ((cabin[i][0].getPassenger()!=passenger) || (cabin[i][1].getPassenger()!=passenger) || (cabin[i][2].getPassenger()!=passenger)) {
                count++;
            }
        }
        if (count==12){
            System.out.println("All Cabins are booked...Adding to the Queue");   //adding to the queue
            System.out.print("Enter first name: ");    //getting first name
            String fname = input.next();
            System.out.print("Enter surname: ");     //getting surname
            String sname = input.next();
            System.out.print("Enter expenses: ");     //getting expenses
            int expense = input.nextInt();
            queue.addingQueue(new Passenger(fname,sname,expense));   //adding elements to queue
            System.out.println("Successfully added to the Queue");
        }else {
            System.out.print("Enter cabin number: ");    //adding to the cabin object array
            int num = input.nextInt();
            if ((cabin[num-1][0].getPassenger() != passenger) || (cabin[num-1][1].getPassenger() != passenger) || (cabin[num-1][2].getPassenger() != passenger)) {
                System.out.println("It's booked");      //checking at least 1 passenger is in cabin
            } else {
                System.out.print("How many passengers : ");
                int slot = input.nextInt();     //getting passengers amount
                for (int k = 1; k <= slot; k++) {
                    System.out.println("Enter details for passenger " + k);
                    System.out.print("Enter first name: ");     //getting first name
                    String fname = input.next();
                    System.out.print("Enter surname: ");       //getting surname
                    String sname = input.next();
                    System.out.print("Enter expenses: ");
                    try {              //try catch block
                        int expense = input.nextInt();        //getting expenses
                        cabin[num-1][k-1].setPassenger(new Passenger(fname, sname, expense));   //adding new passenger to object array
                    }catch (Exception e){
                        System.out.println("Invalid input");
                    }
                }
            }
        }
    }

    public static void view(Cabin[][] cabin, Passenger passenger){    //view passengers method
        for (int i=0; i<12; i++) {
            for (int j = 0; j < 3; j++) {     //getting passenger names
                System.out.println("cabin " + (i+1) + " passenger " + (j+1) + " " + cabin[i][j].getPassenger());
            }
            System.out.println("-------------------");
        }
    }
    public static void empty(Cabin[][] cabin, Passenger passenger) {     //display empty cabins method
        for (int i = 0; i < 12; i++) {
            if ((cabin[i][0].getPassenger().equals(passenger)) && (cabin[i][1].getPassenger().equals(passenger)) && (cabin[i][2].getPassenger().equals(passenger))){
                System.out.println("Cabin "+(i+1)+" is Empty");      //printing empty cabins
            }
        }
    }
    public static void delete(Cabin[][] cabin, Passenger passenger,Queue queue){      //delete passengers method
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cabin number: ");   //getting cabin number
        int num = input.nextInt();
        System.out.print("Enter passenger number: ");    //getting passenger number
        int slot = input.nextInt();
        if (queue.empty()){      //checking the queue is empty
            cabin[num-1][slot-1].setPassenger(passenger);     //setting deleted passenger to empty passenger
        }else {
            Passenger psg = queue.deleteQueue();    //return passenger details
            cabin[num-1][slot-1].setPassenger(psg);     //adding passenger from queue
        }
    }
    public static void find(Cabin[][] cabin, Passenger passenger){   //find passengers method
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first name: ");    //getting first name
        String fname = input.next();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                if (cabin[i][j].getPassenger().getFirstName().equals(fname)){    //checking by firstname
                    System.out.println("passenger is in cabin "+(i+1)+" passenger slot "+(j+1));
                }
            }
        }
    }
    public static void store(Cabin[][] cabin, Passenger passenger) throws FileNotFoundException {   //store passengers details method
        PrintWriter out = new PrintWriter("store data.txt");    //creating new file
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                out.println("cabin " + (i+1) + " passenger " + (j+1) + " " + cabin[i][j].getPassenger());    //writing data to file
            }
            out.println("-------------------");
        }
        out.close();     //closing the file
    }
    public static void load(Cabin[][] cabin, Passenger passenger) throws FileNotFoundException {    //load passengers details method
        File file = new File("C:\\Users\\user\\Desktop\\task3\\store data.txt");
        Scanner data = new Scanner(file);
        while (data.hasNextLine()){      //reading from document
            System.out.println(data.nextLine());      //printing data
        }
    }
    public static void order(Cabin[][] cabin, Passenger passenger){      //sort passenger names method
        int k=0;
        String[] array2 = new String[36];    //creating another null array
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                String fname = cabin[i][j].getPassenger().getFirstName();
                array2[k++] = fname;     //adding elements to null array
            }
        }
        String tempo;
        for (int i=0; i< array2.length; i++){
            for (int j=i+1; j< array2.length; j++){
                if (array2[i].compareTo(array2[j]) > 0){     //checking higher order
                    tempo = array2[i];
                    array2[i] = array2[j];
                    array2[j] = tempo;
                }
            }
        }
        System.out.println("Names in alphabetical order :");
        for (int i=0; i<36; i++){
            if (!Objects.equals(array2[i], "empty")){
                System.out.println(array2[i]);        //printing alphabetical order
            }
        }
    }
    public static void total(Cabin[][] cabin, Passenger passenger) {      //total expenses of passengers  method
        int tot=0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                int expense = cabin[i][j].getPassenger().getExpenses();
                if (expense>0){
                    System.out.println(cabin[i][j].getPassenger());     //view passenger expenses
                }
                tot += expense;     //increment total
            }
        }
        System.out.println("\nTotal Expenses : "+tot);   //printing total
    }

}