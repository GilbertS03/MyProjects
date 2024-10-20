import java.util.*;

class Node{
    private Integer data;
    private Node next;
    public Node(){
        data = 0;
        next = null;
    }
    public Node(Integer d, Node n){
        data = d;
        next = n;
    }
    public void setData(Integer d){
        data = d;
    }
    public void setNext(Node x){
        next = x;
    }
    public Integer getData(){
        return data;
    }
    public Node getNext(){
        return next;
    }
}
class List{
    private Node head;
    private Node tail;
    public List(){
        head = tail = null;
    }
    public void addTail(Integer x){
        Node t = new Node(x, null);
        if (head == null){
            head = t;
            tail = t;

        }
        else{
            tail.setNext(t);
            tail = t;
        }
    }
    public void addHead(Integer x){
        Node t = new Node(x, null);
        if (head == null){
            head = t;
            tail = t;

        }
        else {
            t.setNext(head);
            head = t;
        }
    }

    public void removeHead(){
        Node t = head;
        if(head != null){
            if(head == tail){
                head = tail = null;
            }
            else{
                head = head.getNext();
            }
        }
    }

    public void removeTail(){
        if(head != null){
            if(head == tail){
                head = tail = null;
            }
            else{
                Node t = head;
                while (t.getNext() != tail){
                    t = t.getNext();
                }
                tail = t;
                tail.setNext(null);
            }
        }
    }

    public Integer getElement(int idx){
        if (head == null)
            return null;
        else{
            int ctr = 1;
            Node t = head;
            while (ctr != idx && t != null){
                t = t.getNext();
                ctr++;
            }
            if (t == null)
                return null;
            else
                return t.getData();
        }
    }

    public void print(){
        Node t = head;
        while(t != null){
            System.out.println(t.getData());
            t = t.getNext();
        }
    }
}
public class LinkedList {
    public static void main(String[] args) {
        List m = new List();
        System.out.println("Printing empty list");
        m.print();
        m.addTail(8);
        System.out.println("Printing one element list");
        m.print();
        m.addTail(3);
        m.addTail(4);
        System.out.println("Printing multi-element list");
        m.print();
        System.out.println("Adding element to beginning");
        m.addHead(7);
        m.print();
        List n = new List();
        System.out.println("New list, add head");
        n.addHead(3);
        n.print();
        System.out.println("Remove head");
        m.removeHead();
        m.print();
    }
}