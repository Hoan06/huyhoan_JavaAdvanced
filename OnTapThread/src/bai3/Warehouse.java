package bai3;

import java.util.LinkedList;
import java.util.Queue;

public class Warehouse   {
    Queue<Integer> queue = new LinkedList<>();
    int capacity = 5;

    synchronized void produce(int item) throws InterruptedException {
        if (queue.size() == capacity){
            System.out.println("Kho đầy ! ...");
            wait();
        }
        queue.offer(item);
        System.out.println("Sản xuất sản phẩm " + queue.size());
        notify();
    }

    synchronized void consume() throws InterruptedException {
        if (queue.size() == 0){
            System.out.println("Kho rỗng ! ...");
            wait();
        }
        System.out.println("Tiêu thụ sản phẩm " + queue.peek());
        queue.poll();
        notify();
    }
}
