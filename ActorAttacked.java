package demo;

//被攻击的接口
public interface ActorAttacked {
    void attacked(venturer attacker, int shuchu,String skill); //当被攻击时有可能会调用这个函数，第一个参数是攻击者，第二个参数是反击的攻击力
}
