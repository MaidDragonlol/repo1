//生产工作流程
public class FactoryPattern {
    public static void main(String[] args){
        //产品A
        Factory mFactoryA = new FactoryA();
        mFactoryA.Manufacture().Show();

        //产品B
        Factory mFactoryB = new FactoryB();
        mFactoryB.Manufacture().Show();
    }
}

