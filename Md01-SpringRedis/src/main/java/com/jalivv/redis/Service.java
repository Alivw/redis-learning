package com.jalivv.redis;

/**
 * @Description 限流操作
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/11 10:48
 */
public class Service {

    // 控制单元
    public void  service(){

    }


    // 业务操作
    public void business() {
        System.out.println("正在执行业务.....");
    }
}


class MyThread extends Thread {
    Service service = new Service();
    @Override
    public void run() {
        while (true) {
            service.service();
        }
    }
}

class Main{
    public static void main(String[] args) {
        new MyThread().start();
    }
}
