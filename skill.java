package demo;

import java.util.Scanner;
//技能类
public class skill {
    static int count = 2;
    private static String [] skill = {"","熊的力量","西内西内","巴啦啦能量","喝口酒","跳个舞"};
    private static int [] skillLV = {0,2,3,4,6,8};
    private int temp = 0;
    private String temp1 = "";
    venturer s = new venturer();
    public void skill(){
        System.out.println("-----------------------------------------------------有以下技能：-----------------------------------------------------");
        for(int i = 1;i<skill.length;i++) {
            System.out.print(i+"."+skill[i]+"  需要LV："+skillLV[i]);
                switch (skill[i]) {
                    case "熊的力量":
                        System.out.print("(属性提升：增加自身50防御 持续三回合)       ");
                        break;
                    case "西内西内":
                        System.out.print("(物理输出：造成自身两倍攻击力的伤害)        ");
                        break;
                    case "巴啦啦能量":
                        System.out.print("(治疗：回复自身最大生命值百分之二十的血量)   ");
                        break;
                    case "喝口酒":
                        System.out.print("(属性提升：提高自身一倍的攻击 持续两回合)   ");
                        break;
                    case "跳个舞":
                        System.out.print("(属性提升:提高自身一倍的防御 持续三回合)    ");
                        break;
                    case "西内":
                        System.out.print("(物理输出：造成自身攻击力等量的伤害)       ");
                        break;
                    case "肉蛋冲击":
                        System.out.print("(百分比输出:造成自身最大生命百分之二十的输出)");
                        break;
                    default:
                        break;
                }
                if(i%2==0){
                    System.out.println("");
                }
        }
        System.out.println("输入0回到主页");
        System.out.println("-----------------------------------------"+"你的等级为："+s.getLV()+" -----------------------------------------------------");
        System.out.print("请选择你要学习的技能：");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if(x>0) {                                //用count来计算你已学习了几个技能 若少于四个 就直接将该技能加入数组中
            if (s.getLV() >= skillLV[x]) {
                if (count <= 3) {
                    s.getSkillNow()[count+1] = skill[x];
                    s.getSkillNowLV()[count + 1] = skillLV[x];
                    count++;
                    System.out.println("-----------------------------------------------------学习成功-----------------------------------------------------" + "\n" + "-----------------------------------------------------正在回到主页-----------------------------------------------------");
                    s.fun();
                } else {                     //若等于四个后 就要选择要替换的技能
                    System.out.println("技能已满 请选择要掉换的技能：");
                    for (int i = 1; i <= 4; i++) {                   //打印出你已学习的技能和属性
                        System.out.print(i + "." + s.getSkillNow()[i] + "  ");
                        switch (s.getSkillNow()[i]) {
                            case "熊的力量":
                                System.out.print("(属性提升：增加自身50防御 持续三回合)       ");
                                break;
                            case "西内西内":
                                System.out.print("(物理输出：造成自身两倍攻击力的伤害)        ");
                                break;
                            case "巴啦啦能量":
                                System.out.print("(治疗：回复自身最大生命值百分之二十的血量)   ");
                                break;
                            case "喝口酒":
                                System.out.print("(属性提升：提高自身一倍的攻击 持续两回合)   ");
                                break;
                            case "跳个舞":
                                System.out.print("(属性提升:提高自身一倍的防御 持续三回合)    ");
                                break;
                            case "西内":
                                System.out.print("(物理输出：造成自身攻击力等量的伤害)       ");
                                break;
                            case "肉蛋冲击":
                                System.out.print("(百分比输出:造成自身最大生命百分之二十的输出)");
                                break;
                            default:
                                break;
                        }
                        if(i%2==0){
                            System.out.println("");
                        }
                    }
                    int y = sc.nextInt();
                    temp = s.getSkillNowLV()[y];
                    s.getSkillNowLV()[y] = skillLV[x];
                    skillLV[x] = temp;
                    temp1 = s.getSkillNow()[y];
                    s.getSkillNow()[y] = skill[x];
                    skill[x] = temp1;
                    System.out.println("-----------------------------------------------------学习成功-----------------------------------------------------" + "\n" + "-----------------------------------------------------正在回到主页-----------------------------------------------------");
                    s.fun();
                }
            } else {
                System.out.println("-------------------------------------------------------等级不够！------------------------------------------------------");
                skill();
            }
        } else{
            System.out.println("-----------------------------------------------------正在回到主页-----------------------------------------------------");
            s.fun();
        }
    }

}
