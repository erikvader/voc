import org.python.stdlib.collections.OrderedDict;
import org.python.types.Int;
import java.util.HashMap;

public class OrderedDictWorkload {
    public static void main(String[] argssss) {
        org.python.Object[] args = { null };
        HashMap<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        OrderedDict dict = new OrderedDict(args, kwargs);

        for(int i = 0; i < 500000; i++){
            dict.__setitem__(Int.getInt(i), Int.getInt(i));
        }

        for(int i = 0; i < 500000; i++){
            dict.__delitem__(dict.get(Int.getInt(i), Int.getInt(i)));
        }
    }
}
