import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertThat;

class MainTest {

    int listSize = 200;
    List<String> names;
    List<String> original;
    Main testClass;

    @BeforeEach
    void setUp() {
        testClass = new Main();
        names = new ArrayList<>(listSize);
        original = new ArrayList<>(listSize);
        for(int i = 0; i < listSize; i++) {
            String randomString = randomString();
            names.add(randomString);
            original.add(randomString);
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
    public void overlapTest() {
        String firstString = "abc";
        String secondString = "cde";
        assertThat(Main.findOverlap(firstString, secondString), Is.is(1));
        assertThat(Main.findOverlap(secondString, firstString), Is.is(0));
    }

    @Test
    public void overlapTestNoOverlap() {
        String firstString = "abc";
        String secondString = "def";
        assertThat(Main.findOverlap(firstString, secondString), Is.is(0));
        assertThat(Main.findOverlap(secondString, firstString), Is.is(0));
    }

    @Test
    public void overlapTestDifferentLengths() {
        String firstString = "abcde";
        String secondString = "ea";
        assertThat(Main.findOverlap(firstString, secondString), Is.is(1));
        assertThat(Main.findOverlap(secondString, firstString), Is.is(1));
    }

    @Test
    public void overlapTestAlotOfOverlap() {
        String firstString = "abcde";
        String secondString = "bcdef";
        assertThat(Main.findOverlap(firstString, secondString), Is.is(4));
        assertThat(Main.findOverlap(secondString, firstString), Is.is(0));
    }



    @Test
    public void makeNameNaive() {

        System.out.println("Original list = " + original.toString());
        int lengthOfOriginal = numOfChars(original);
        String result = testClass.makeNameNaive(this.names);
        int lengthOfNew = result.length();
        System.out.println("New list = " + result);
        System.out.println("Original length = " + lengthOfOriginal);
        System.out.println("Found length = " + lengthOfNew);

        for(String s : original){
            if(!result.contains(s)) {
                System.out.println(result + " Does not contain: " + s);
            } else{
                System.out.println(result + "Does contain: " + s);
            }
            assertThat(result.contains(s), Is.is(true));
        }
        System.out.println("result = " + result);
    }
}