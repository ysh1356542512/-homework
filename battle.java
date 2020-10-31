package demo;

import java.util.Random;
import java.util.Scanner;

public class battle {
    public static void battle(){
        venturer wang = new venturer(new ActorAttacked(){
            @Override
            public void attacked(venturer attacker, int shuchu,String skill) {
                System.out.println(attacker.getName()+"发动技能"+skill+ " 造成了"+shuchu+"点伤害");
                attacker.attacked(shuchu);
            }
        }, "wang");


        venturer li1 = new venturer(new ActorAttacked(){
            @Override
            public void attacked(venturer attacker, int shuchu,String skill) {
                System.out.print(attacker.getY());
                attacker.attacked(shuchu);
            }
        }, "li1");
        wang.setAttacker(li1);
        li1.setAttacker(wang);
        wang.attacked(li1.getshuchu());
//这里是之前想一对多的部分代码 想想还是留着吧
//        wang.setAttacker(li);
//        li.setAttacker(wang);
//        wang.attacked(li.getshuchu());
//        System.out.println("请选择你要攻击的目标:");
//        Scanner sc = new Scanner(System.in);
//        int z = sc.nextInt();
//        if(z==1){
//            wang.attacked(li1.getshuchu(),0,x+1);
//        } else if(z==2){
//            wang.attacked(li2.getshuchu(),0,x+1);
//        } else{
//            wang.attacked(li3.getshuchu(),0,x+1);
//        }
    }
}
