import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArray {

    public static int[] returnArrayAfterFourThisArray(int[] value){
        int index = -1;

        for (int i = value.length - 1; i > -1; i--) {
            if (value[i] == 4){
                index = i;
                break;
            }
        }

        if (index == -1){
            throw new RuntimeException ("Four not found");
        }

        if (index + 1 == value.length){
            throw new RuntimeException ("No values found after four");
        }

        return Arrays.copyOfRange (value, index + 1, value.length);
    }

    public static boolean presenceOneOrFourThisArray(int[] value){
        for (int i: value) {
            if (i == 1 || i == 4){
                return true;
            }
        }
        return false;
    }
}
