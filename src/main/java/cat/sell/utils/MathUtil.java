package cat.sell.utils;

public class MathUtil {

    private static final Double Range = 0.01;
    public static boolean equals(Double d1,Double d2){
        Double res = Math.abs(d1-d2);
        if(res<Range)
            return true;
        else
            return false;
    }
}
