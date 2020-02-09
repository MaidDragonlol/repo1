import java.util.Scanner;

public class Computer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        float firstNum  = in.nextFloat();
        System.out.println("请输入第二个数字：");
        float secondNum  = in.nextFloat();
        System.out.println("请输入运算符号：");
        String countQuato = in.next();
        System.out.println(count(firstNum,secondNum,countQuato));//调用count输出结果
    }
    private static float count(float firstNum,float secondNum , String countQuota) {
        //通过工厂类获取对象
        //用多态
        Operation operation = OperationFactory.getOperation(countQuota);  //这里用了多态（父类引用指向子类对象），等式右边返回值是Operation的实现类
        return operation.getResult(firstNum, secondNum);
        //不用多态
       /* switch (countQuota){
            case "+" :  OperationFactory.getOperation(float firstNum,float secondNum ,);
            case "+" :  OperationFactory.getOperation(float firstNum,float secondNum ,);
            default:break;
            前面要改一下，接口改成抽象类，抽象类里面加上方法体运算，好像也不行。改成全部加在普通类里面，这样就不是工厂模式了
            所以说这里必须用多态？
        }*/
    }

}
