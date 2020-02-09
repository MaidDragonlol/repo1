public class OperationFactory {
    public static Operation getOperation(String a){
        Operation o = null;  //
        switch (a){
            case "+" :  o = new AddOperation();
            case "-" :  o = new SubOperation();
            default:break;
        }
        return o;
    }

}
