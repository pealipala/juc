package com.example.demo.jmm;


class Number{
    volatile int number= 10;//加了volatile导致变量天然可见性
    public void add(){
        this.number = 11;
    }
}
/**
 * @author yechaoze
 * @version 1.0
 * @date 2020/8/5 21:27
 */
public class JmmDemo {
    public static void main(String[] args) {
        Number number = new Number();
        new Thread(()->{
            try {
                //暂停一会线程
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number.add();//将10修改为11
            System.out.println(Thread.currentThread().getName()+"\t update number:"+number.number);
        },"A").start();
        while(number.number==10){
            //需要有一种通知机制告诉main线程number变为11跳出while
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}
