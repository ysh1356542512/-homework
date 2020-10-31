package demo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
//总结来说 对自己很失望 写的不好 把商城背包技能写完之后 就一直用接口回调来写战斗系统 太菜了 写一对多的时候一直出问题 到后面再看了一下题目才感觉应该用循环来写回合制
//用接口回调来写反击机制 但是已经星期五晚上了 没有时间了 也熬了四天熬不动了 还是太菜了真的  烦  现在可能还是有些小问题 而且也挺乱的因为自己大改了很多次 凑合看吧
//想休息一天 等我有时间 我肯定会完善的 把我相加的都加了 先交吧先交吧

//创建一个类储存经验值 金币 等级 会随战斗而改变的值 否则会延迟打印  （虽然攻击防御啥的也要，但是..太多了）
class a{
    static int jingyan = 0;
    static int money = 1000;
    static int LV = jingyan/100 +1;
    public static void setMoney(int M){
        money = M;
    }
    public static void setJingyan(int J){ jingyan = J; }
    public static void setLV(int L){LV = L;}

}
//创建一个冒险者的类
public class venturer {
    //有参无参构造方法
    public venturer() {}
    private ActorAttacked mAddi;
    public venturer(ActorAttacked actorAttacked, String wang) {
        this.mAddi = actorAttacked;
    }
    private static int count = 1;
    //冒险者
//    定义冒险者的属性
    private static String name;
    int  HP = a.LV * 500, ATK = a.LV * 200, DEF = 50 * a.LV, speak = 400;
    private int shuchu = 0;
    private int leavedBloocd = HP;      //每次战斗结束后重置血量
    private venturer attacker; //攻击者
    public int getLV(){return a.LV;}
//创建一个内部怪物类
    public class monster {
//        定义怪物的属性
        private String nameM = "",Skill;
        private int HPM, ATKM, DEFM, speedM;
        public monster(){}
        public monster(String nameM, int HPM, int ATKM, int DEFM, int speedM,String Skill) {
            this.nameM = nameM;this.HPM = HPM;this.ATKM = ATKM;this.DEFM = DEFM;this.speedM = speedM; this.Skill = Skill;
            ArrayList<monster> monsters = new ArrayList<>();
        }
//        定义怪物的种类
        public ArrayList<monster> a() {
            ArrayList<monster> monsters = new ArrayList<>();
            monster m1 = new monster("黑道老大泷歌", a.LV * 200, a.LV * 200, a.LV * 50, 400 + a.LV * 10,"社会的毒打");
            monster m2 = new monster("游吟诗人汉中", a.LV * 300, a.LV * 200, a.LV * 50, 400 + a.LV * 10,"舌战群儒");
            monster m3 = new monster("团支书记楷爹", a.LV * 300, a.LV * 200, a.LV * 50, 380 + a.LV * 10,"青年大学习");
            monster m4 = new monster("网瘾少年玺哥", a.LV * 400, 150 + a.LV * 50, 50 + a.LV * 50, 400 + a.LV * 10,"wdnmd");
            monster m5 = new monster("闷声发财有新", a.LV * 450, 50+a.LV * 100, a.LV * 100, 300 + a.LV* 10,"骚话连篇");
            monster m6 = new monster("2B青年--豪", a.LV * 500, a.LV * 300, a.LV * 100, 350 + a.LV * 5,"阿巴阿巴");
            monsters.add(m1);monsters.add(m2);monsters.add(m3);monsters.add(m4);monsters.add(m5);monsters.add(m6);
            return monsters;
        }
        public String getNameM () {
            return nameM;
        }
        public int getHPM () {
            return HPM;
        }
        public int getATKM () {
            return ATKM;
        }
        public int getDEFM () {
            return DEFM;
        }
        public int getSpeedM () {
            return speedM;
        }
        public String getSkill1(){return Skill;}
    }
    //怪物  随机生成一个类型的怪物
    monster c = new monster();
    Random r = new Random();
    int x = r.nextInt(6);
    monster m1 = c.a().get(x);
//    得到它的技能和名字 还有血量
    private String y = m1.getNameM();
    private String z = m1.getSkill1();
    private int LeavedBloocd1 = m1.getHPM();

public void setName() {
    System.out.print("冒险者 你的名字是：" );
    Scanner sc = new Scanner(System.in);
    this.name = sc.next();
}

    public String getName() {
        return name;
    }
    //这是为了循环回到主页的方法
    public void fun() {
        new zhuye().printf();
    }
//显示主页 其实感觉用方法也行 就不改了
    class zhuye {
        String[] weaponName = {"", "泷歌的指环", "汉中的诗集", "楷爹的猪皮铠甲", "2B青年的庇护"}; //商城中武器的名字数组
        int[] price = {0, 500, 1000, 500, 1000};  //商城中武器的价格
        int[] weapon = {0, 2, 3, 50, 100};   //武器的属性加成
        String weaponNameNow;    //现在装备的武器名字
        int size = 0;           //记录背包中武器的数量 方便打印
        int[] bag = new int[10];    //设置背包里武器属性的数组
        String[] bagName = new String[10];   //设置背包里武器名字的数组
        int weaponNow = 1;      //现在装备的武器的属性
        int fangju = 0;         //现在装备的防具的属性
//        private venturer.zhuye zhuye;
//    打印主页的方法
        public void printf() {
//            打印主页的画面
            System.out.println("--------------------------------------------------------欢迎来到瓦坎达大陆 冒险者----------------------------------------------");
            System.out.print("|  冒险者: "+getName() + "                                                         经验值：");
            int x = a.jingyan%100 / 10;
//            打印经验条
            for (int i = 1; i <= 10; i++) {
                if (i <= x) {
                    System.out.print("■");
                } else {
                    System.out.print("□"); }
            }
            System.out.print(a.jingyan%100 +"/"+ 100 + "  LV:" + a.LV + "  HP:■■■■■■ ");
            System.out.println(HP+"|");
            System.out.println("|                         1.商城 ＄                  2.背包 ◈                3.技能 ※                4.探索 ♆               |");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.print("你接下来的行程是:");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
//           根据输入的数字选择接下来的进程
            switch (choice) {
                case 1:
                    shop();
                    break;
                case 2:
                    bag();
                    break;
                case 3:
                    skill s = new skill();
                    s.skill();
                    break;
                case 4:
                    System.out.println("---------------------------------------------------------正在搜寻-------------------------------------------------");
                    Random IF = new Random();
                    int If = IF.nextInt(10);
                    if(If>=0&&If<=7) {
                        battle.battle();
                    } else{
                        box();
                    }
                    break;
                default:
                    this.printf();
                    break;
            }
        }
//        商店的方法
        public void shop() {
//            打印商店的画面
            System.out.println("-------------------------------------------------------------欢迎来到商城----------------------------------------------------");
            System.out.println("|                                        1.泷歌的指环(武器):500         2.汉中的诗集(武器):1000                                |");
            System.out.println("|                                        3.楷爹的猪皮铠甲(防具):500      4.2B青年的庇护(防具):1000                              |");
            System.out.println("|  你的金币 :"+a.money+"                                                                                     按0退出商城   |");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
//           通过循环 区别第一次进入商店和买完后继续买的情况
            for (int i = 0; i < 5; i++) {
                if (i == 0) {   //如果是第一次 则输出以下
                    System.out.print("你要购买哪件装备：");
                    Scanner sc = new Scanner(System.in);
                    int Weapon = sc.nextInt();
                    if(Weapon>0&&Weapon<=4) {
                        if (a.money >= price[Weapon]) {   //用判断句 判断你的金币是否充足
                            bag[size + 1] = weapon[Weapon];     //购买成功后将 在背包中增加这件武器
                            bagName[size + 1] = weaponName[Weapon];
                            a.setMoney(a.money-price[Weapon]);    //调用a类static的方法改变金币的数量
                            size++;    //记录背包中的武器数量增加1
                            System.out.println("----------------------------------------------------购买成功  您的金币剩余：" + a.money+"------------------------------------------------");
                        } else {
                            System.out.println("----------------------------------------------------您的金币不足,退回主页----------------------------------------------------");
                            this.printf();   //金币不足回到主页
                            break;
                        }
//                        输入0或错误直接回到主页
                    } else if(Weapon ==0){
                        System.out.println("退出商城"+"\n"+"-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        this.printf();
                    } else{
                        System.out.println("输入错误"+"\n"+"-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        this.printf();
                    }
//                    购买后的情况
                } else {
                    System.out.println("----------------------------------------------------是否继续购买？(请输入1(是)或2(否))------------------------------------------");
                    System.out.print("                                                          ");
                    Scanner b = new Scanner(System.in);
                    int IF = b.nextInt();
                    if (IF == 1) {
                        System.out.print("你要购买哪件装备：");
                        Scanner sc = new Scanner(System.in);
                        int Weapon = sc.nextInt();
                        if (a.money>= price[Weapon]) {
                            bag[size + 1] = weapon[Weapon];
                            bagName[size + 1] = weaponName[Weapon];
                            a.setMoney(a.money-price[Weapon]);
                            size++;
                            System.out.println("购买成功  您的金币剩余：" + a.money);
                        } else {
                            System.out.println("您的金币不足,退回主页");
                            this.printf();
                            break;
                        }
                    } else if (IF == 2) {
                        System.out.println("-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        this.printf();
                        break;
                    } else {
                        System.out.println("输入错误 回到主页");
                        this.printf();
                        break;
                    }
                }
            }
        }
//背包的方法
        public void bag() {
            System.out.println("-----------------------------------------------------您已拥有：" + size + "件装备-----------------------------------------------------");
            if (size >= 1) {       //若背包中有装备 则循环打印装备信息
                for (int i = 1; i <= size; i++) {
                    System.out.print("         "+i + "." + bagName[i]);
                }
                System.out.println("-----------------------------------------------------您需要装备哪件武器-----------------------------------------------------");
//               装备后的属性改变  可能有点问题 会延迟 因为攻击防御都没有加入a类中
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch (bagName[choice]) {
                    case "一":
                        ATK = (ATK * 2) / weaponNow;
                        System.out.println("-----------------------------------------------------成功装备" + bagName[choice] + "-----------------------------------------------------");
                        System.out.println("-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        weaponNameNow = bagName[choice];
                        weaponNow = bag[choice];
                        this.printf();
                        break;
                    case "二":
                        ATK = (ATK * 3) / weaponNow;
                        System.out.println("-----------------------------------------------------成功装备" + bagName[choice] + "-----------------------------------------------------");
                        System.out.println("-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        weaponNameNow = bagName[choice];
                        weaponNow = bag[choice];
                        this.printf();
                        break;
                    case "三":
                        DEF = DEF + 50 - fangju;
                        System.out.println("-----------------------------------------------------成功装备" + bagName[choice] + "-----------------------------------------------------");
                        System.out.println("-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        weaponNameNow = bagName[choice];
                        fangju = bag[choice];
                        this.printf();
                        break;
                    case "四":
                        DEF = DEF + 100 - fangju;
                        System.out.println("-----------------------------------------------------成功装备" + bagName[choice] + "-----------------------------------------------------");
                        System.out.println("-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        weaponNameNow = bagName[choice];
                        fangju = bag[choice];
                        this.printf();
                        break;
                    default:
                        System.out.println("输入错误 回到主页");
                        System.out.println("-----------------------------------------------------正在回归主页-----------------------------------------------------");
                        this.printf();
                        break;
                }
            } else{
                System.out.println("-----------------------------------------------------还不快去打怪赚金币！-----------------------------------------------------");
                this.printf();
            }
        }

    }
    //技能
//    这是主角现有的技能
    private static String [] skillNow = {"","西内","肉蛋冲击","未学习","未学习"};
    private static int [] skillNowLV = {0,1,1,0,0};
    public String[] getSkillNow(){return skillNow;}
    public int [] getSkillNowLV(){return skillNowLV;}
    //    战斗中显示技能和其效果 并选择技能的方法
    public String choiceSkill(){
        System.out.println("请选择使用的技能");
        for(int i = 1;i<=4;i++) {
            System.out.print(i+"."+skillNow[i]+"  ");
            switch (skillNow[i]) {
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
                case "未学习":
                    System.out.print("                                    ");
                default:
                    break;
            }
//            因为字比较多 所以每两个技能一行 通过循环i来判断
            if(i%2==0){
                System.out.println("");
            }
        }
//        增加逃跑功能因为遇到2b青年打不过
        System.out.println("5.逃跑");
        int jin=0,huo=0,tu=0;   //这是属性技能的持续时间
        Scanner sc = new Scanner(System.in);   //这是选择技能后的伤害计算
        String x = sc.next();
        switch(x) {
            case "西内":
                shuchu = ATK-m1.getDEFM();
                if(shuchu <0){
                    shuchu =0;
                }
                break;
            case "肉蛋冲击":
                shuchu = HP/5;
                break;
            case "熊的力量":
                DEF = DEF+50;
                jin = jin+3;
                shuchu = 0;
                break;
            case "西内西内":
                shuchu = ATK*2-m1.getDEFM();
                if(shuchu <0){
                    shuchu =0;
                }
                break;
            case "巴啦啦能量":
                leavedBloocd =leavedBloocd+ HP/5;
                shuchu = 0;
                if(leavedBloocd >=HP){
                    leavedBloocd = HP;
                }
                break;
            case "喝口酒":
                ATK = ATK*2;
                huo = huo+2;
                shuchu = 0;
                break;
            case "跳个舞":
                DEF = DEF+DEF;
                tu = tu+3;
                shuchu = 0;
                break;
            case "逃跑":
                System.out.println("------------------------------------------------溜了溜了-------------------------------------------");
                count = 1;
                fun();
                break;
            default:
                System.out.println("输入错误 请再次选择技能");
                choiceSkill();
                break;
        }
//        这是技能持续的技能  先减一  如果是负一则说明没有使用该技能 那么自动变回0； 如果等于0 那么就说明是使用了到了结束的时间 效果结束；如果大于1则直接下一回合
        jin--;huo--;tu--;
        if(jin==0){
            DEF = DEF -50;
        } else if(jin<0){
            jin = 0;
        }
        if(huo==0){
            ATK = ATK/2;
        } else if(huo<0){
            huo = 0;
        }
        if(tu==0){
            DEF = DEF/2;
        } else if(tu<0){
            tu = 0;
        }
        return x;    //返回使用的技能 方便后面打印使用了什么技能
    }
//    折磨人的回调回合制方法  把伤害传过来
    public void attacked(int attack) {
        if(count ==1) {            //count为回合数  若为第一回合则打印遇到了什么怪物
            System.out.println("你遇到了："+getY());
            leavedBloocd -= attack;
            if (leavedBloocd > 0) {
                String ab = choiceSkill();         //选择技能并返回技能的名字
                count++;                         //回合数加一
                mAddi.attacked(attacker, shuchu,ab);   //把攻击者 和 输出 和 技能的名字通过接口传过去
            } else {
                System.out.println( name+"死掉了,回到主页---");  //若死掉后 重置回合数
                count = 1;
                fun();
            }
        }else if (count % 2 == 1&&count!=1) {   //这是回合数为奇数 且不为1
            leavedBloocd -= attack;
            if (leavedBloocd > 0) {
                System.out.println("发动技能"+getZ()+"造成了"+attack+"点伤害");   //在血量减少后 打印血条
                System.out.print("玩家剩余血量");
                printfHP();
                System.out.println("");
                String ab = choiceSkill();
                count++;
                mAddi.attacked(attacker, shuchu,ab);
            } else {
                System.out.println("胜利！");
                System.out.println( name+"死掉了,回到主页---");
                count = 1;
                fun();
            }
        } else {
            LeavedBloocd1 -= attack;
            if ( LeavedBloocd1 > 0) {
                System.out.print(attacker.getY()+"剩余血量：");
                printfHPM();
                System.out.println("");
                count++;
                int shuchu1 = m1.getATKM()-DEF;
                mAddi.attacked(attacker,shuchu1,getZ());
            } else {
                System.out.print(attacker.getY()+"剩余血量：");
                printfHPM();
                System.out.println("");
                System.out.println( "---------------------------------------------------------敌人死亡  战斗结束---------------------------------------------------------");
                Random r = new Random();
                int addMoney = r.nextInt(100)+100;    //通过导入Random包来随机获取金币和经验
                a.setMoney(a.money+addMoney);                //用a类的方法修改static的值
                int addjingyan = r.nextInt(100)+100;
                a.setJingyan(a.jingyan+addjingyan);
                a.setLV(a.jingyan/100+1);
                count = 1;
                System.out.println("-----------------------------------------------------获得"+addMoney+"金币,"+addjingyan+"经验-----------------------------------------------------");
                System.out.println("-----------------------------------------------------是否需要继续探索(是则1,否则2)-----------------------------------------------------");
                Scanner sc = new Scanner(System.in);
                String x = sc.next();
                if(x.equals("1")){                        //判断是否继续
                    System.out.println("---------------------------------------------------------正在搜寻---------------------------------------------------------");
                    Random IF = new Random();
                    int If = IF.nextInt(10);   //用随机数  百分之八十是战斗 百分之二十是宝箱
                    if(If>=0&&If<=7) {
                        battle.battle();
                    } else{
                        box();
                    }
                } else if(x.equals("2")) {
                    System.out.println("---------------------------------------------------------正在退回主页---------------------------------------------------------");
                    fun();
                } else {
                    System.out.println("---------------------------------------------------------输入错误 自动退回主页---------------------------------------------------------");
                    fun();
                }
            }
        }
    }
//    宝箱方法
    public void box(){
        System.out.println("---------------------------------------------------------找到宝箱!---------------------------------------------------------");
        Random r = new Random();
        int x = r.nextInt(50)+50;
        int y = r.nextInt(50)+50;
        a.setMoney(a.money+x);
        a.setJingyan(a.jingyan+y);
        System.out.println("-----------------------------------------------------获得"+x+"金币,"+y+"经验-----------------------------------------------------");
        System.out.println("-----------------------------------------------------是否需要继续探索(是则1,否则2)-----------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        String xy = sc.next();
        if(xy.equals("1")){
            System.out.println("---------------------------------------------------------正在搜寻---------------------------------------------------------");
            Random IF = new Random();
            int If = IF.nextInt(10);
            if(If>=0&&If<=7) {
                battle.battle();
            } else{
                box();
            }
        } else if(xy.equals("2")) {
            System.out.println("---------------------------------------------------------正在退回主页---------------------------------------------------------");
            fun();
        } else {
            System.out.println("---------------------------------------------------------输入错误 自动退回主页---------------------------------------------------------");
            fun();
        }
    }
//    打印玩家血量
    public void printfHP(){
        int c = HP/50,d = leavedBloocd/50;
        for(int i = 1;i<=c;i++){
            if(i<=d){
                System.out.print("■");
            } else{
                System.out.print("□");
            }
        }
    }
//    打印怪物血量
    public void printfHPM(){
        int a = m1.getHPM()/50,b = LeavedBloocd1/50;
        for(int i = 1;i<=a;i++){
            if(i<=b){
                System.out.print("■");
            } else{
                System.out.print("□");
            }
        }
    }
    public venturer getAttacker() { return attacker; }
    public void setAttacker(venturer attacker) { this.attacker = attacker; }
    public void setAttacker1(demo.venturer attacker1){this.attacker = attacker;}
    public int getshuchu() { return shuchu; }

    public String getActorName() {
        return name;
    }

    //        public venturer(monster M){
//            this.M = M;
//        }
//    public venturer(){
//
//    }
    public String getY(){
        return y;
    }
    public String getZ(){
        return z;
    }
}