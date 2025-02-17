package com.sfwe302;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MySetDemo myListDemo = new MySetDemo();
        myListDemo.loadData();
        System.out.println(myListDemo.list.size());
    }
}
