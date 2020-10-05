import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class MainTest {

    int listSize = 500;
    List<String> names;
    Main testClass;

    @BeforeEach
    void setUp() {
        testClass = new Main();
        names = new ArrayList<>(listSize);
        for(int i = 0; i< listSize; i++) {
            names.add(randomString());
        }
    }


    public String randomString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = (int) (Math.random()*3 + 2);
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append(Character.toUpperCase((char) randomLimitedInt));
        }
        return buffer.toString();
    }

    public int numOfChars(List<String> list){
        int result = 0;
        for(String s: list){
            result = result + s.length();
        }
        return result;
    }

    @Test
    public void makeNameNaive() {
        System.out.println("Original list = " + names.toString());
        int lengthOfOriginal = numOfChars(names);
        String result = testClass.makeNameNaive(this.names);
        int lengthOfNew = result.length();
        int score = ((lengthOfOriginal-lengthOfNew)/lengthOfOriginal);
        System.out.println("Original length = " + lengthOfOriginal);
        System.out.println("Found length = " + lengthOfNew);
        System.out.println("Score: " + score);

        System.out.println("result = " + result);
    }
}