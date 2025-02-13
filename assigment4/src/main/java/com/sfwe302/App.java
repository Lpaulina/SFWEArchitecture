package com.sfwe302;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MyListDemo myListDemo = new MyListDemo();
        myListDemo.loadData();
        System.out.println(myListDemo.list.size());
    }
}
