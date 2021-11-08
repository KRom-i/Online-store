import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MyArrayTest {

    /**
     Задание 1.
     Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
     Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
     идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
     иначе в методе необходимо выбросить RuntimeException.
     Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ]
     */

    @ParameterizedTest
    @MethodSource("parametersForNewArray")
    public void shouldReturnArrayAfterFourThisArray(int[] expected, int[] value){
        Assertions.assertArrayEquals (expected, MyArray.returnArrayAfterFourThisArray (value));
    }

    private static Stream<Arguments> parametersForNewArray(){
        return Stream.of (
                Arguments.of (new int[]{1, 7}, new int[]{2, 4, 4, 2, 3, 4, 1, 7}),
                Arguments.of (new int[]{2, 3}, new int[]{2, 4, 4, 2, 3}),
                Arguments.of (new int[]{3, 9}, new int[]{2, 1, 5, 4, 3, 9, 1, 7})
        );
    }

    @Test
    public void shouldReturnRuntimeException(){
        Assertions.assertThrows (
                RuntimeException.class, ()->{
                    MyArray.returnArrayAfterFourThisArray (
                        new int[]{2, 9, 2, 2, 3, 3, 1, 7});
                });
    }

    /**
     Задание 2.
     Написать метод, который проверяет состав массива из чисел 1 и 4.
     Если в нем нет хоть одной четверки или единицы, то метод вернет false;
     Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     */

    @ParameterizedTest
    @MethodSource("parametersCheckArray")
    public void TestMethodPresenceOneOrFourThisArray(boolean expected, int[] value){
        Assertions.assertEquals (expected, MyArray.presenceOneOrFourThisArray (value));
    }

    private static Stream<Arguments> parametersCheckArray(){
        return Stream.of (
                Arguments.of (true, new int[]{2, 4, 1, 7}),
                Arguments.of (true, new int[]{2, 4, 2}),
                Arguments.of (true, new int[]{2, 1, 5, 3, 9, 7}),
                Arguments.of (false, new int[]{2, 9, 2, 2, 3, 3, 7})
        );
    }


}
