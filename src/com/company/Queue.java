package com.company;

public class Queue {
    int size;    //Size of Circular Queue attribute
    int front_queue,rear_queue;    //circular queue attributes
    Passenger[] Queuelist;    //creating array for waiting list

    Queue(int size){      //constructor
        front_queue=-1;
        rear_queue=-1;
        this.size=size;
        this.Queuelist = new Passenger[size];
    }

    boolean full(){      //checking the queue is full method
        if (front_queue == 0 && rear_queue == size-1) {
            return true;     //checking queue is full and returen true
        }
        if (front_queue == rear_queue + 1) {
            return true;   //checking queue is full and returen true
        }
        return false;
    }
    boolean empty() {     //checking the queue is empty method
        if (front_queue == -1)
            return true;
        else
            return false;
    }

    void addingQueue(Passenger passenger){      //adding passengers method
        if (full()){
            System.out.println("Queue is full");
        }else {
            if (front_queue==-1)
                front_queue=0;    //if queue is not full get first passenger
            rear_queue=(rear_queue+1) % size;
            Queuelist[rear_queue] = passenger;    //get passengers to the queue

        }
    }
    Passenger deleteQueue() {        //Deleting passengers method
        Passenger passenger = null;
        if (empty()) {
            return passenger;
        } else {
            passenger = Queuelist[front_queue];    //return the first passenger in the queue to code
            if (front_queue == rear_queue) {
                front_queue = -1;
                rear_queue = -1;
            } else {
                front_queue = (front_queue + 1) % size;     //check queue passenger, and if it has only one passenger the array will reset
            }
            return passenger;   //return passenger to the code
        }
    }
}
