import org.python.stdlib.datetime.TimeDelta;
import org.python.types.*;
import org.python.Object;
import org.python.types.Float;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class Workload {

    private static double somethingRandom(int n) {
        Random rand = new Random();
        double total_mod = 0;



        for(int i = 0; i < n; i++){

            Object[] args = {
                Int.getInt(rand.nextInt(1000)),
                Int.getInt(rand.nextInt(1000)),
                Int.getInt(rand.nextInt(1000))
            };

            TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);

            Object[] args2 = {
                Int.getInt(rand.nextInt(1000)),
                Int.getInt(rand.nextInt(1000)),
                Int.getInt(rand.nextInt(1000))
            };

            TimeDelta TD2 = new TimeDelta(args2, TimeDelta.EMPTY_KWARGS);
            TD = TD.__mul__(TD2);
            TD2 = TD2.__add__(TD);
            TD.__str__();

            TimeDelta TDmod = TD.__mod__(TD2);
            double mod_sec = TDmod.total_seconds().value;

            total_mod += mod_sec;
        }

        return total_mod;
    }

    public static void main(String[] args) {
        int n = 1000;

        System.out.println("total mod: " + somethingRandom(n) + " seconds");

    }

}
