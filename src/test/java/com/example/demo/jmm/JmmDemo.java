package com.example.demo.jmm;


class Number{
    volatile int number= 10;//����volatile���±�����Ȼ�ɼ���
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
                //��ͣһ���߳�
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number.add();//��10�޸�Ϊ11
            System.out.println(Thread.currentThread().getName()+"\t update number:"+number.number);
        },"A").start();
        while(number.number==10){
            //��Ҫ��һ��֪ͨ���Ƹ���main�߳�number��Ϊ11����while
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}
