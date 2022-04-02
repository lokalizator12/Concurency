

public class Bankomat {

    private int haveValue;

    public Bankomat(int haveValue) {
        this.haveValue = haveValue;
    }

    public void ifHaveMoney(String name, int value ){
        System.out.println(name + " подошёл к банкомату");
        if (this.haveValue >= value ){
            this.haveValue -= value;
            System.out.println(name + " вывел " + value +
                    " рублей. В банкомате осталось " + this.haveValue + " рублей.");

        }else{
            System.out.println("В банкомате недостаточно денег для " + name);
        }
    }






}
