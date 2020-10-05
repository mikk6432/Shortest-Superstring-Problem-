import java.util.*;

public class Main {
    public String makeNameNaive(List<String> names) {
        while(names.size()>1){
            System.out.println(names.toString());
            //concat them and add to names
            //remove words, decreasing names.size()
            int maxAmountOfOverlap = 0;
            int maxIndexFirstName = 0;
            int maxIndexSecondName = 0;
            boolean shouldRemoveSecondName = false;

            BigLoop:
            for (int first = 0, size = names.size(); first < size; first++) {
                String firstName = names.get(first);
                for (int second = first+1, namesSize = names.size(); second < namesSize; second++) {
                    String secondName = names.get(second);



                    if (firstName.contains(secondName)) {
                        System.out.println(names.get(first) + " contains second: " + names.get(second));
                        names.remove(second);
                        size--;
                        namesSize--;
                        second--;
                        System.out.println(size);
                        secondName = names.get(second);
                    }
                    System.out.println("Does second index: " + second + " match with: " + names.get(second) );

                    int firstNameLength = firstName.length();
                    int secondNameLength = secondName.length();
                    int overlapMax = 0;

                    for (int firstIndex = Math.min(firstNameLength, secondNameLength) - 1; firstIndex >= 0; firstIndex--) {
                        if (firstName.substring(0, firstIndex + 1).equals(secondName.substring(secondNameLength - 1 - firstIndex, secondNameLength))) {
                            overlapMax = firstIndex + 1;
                            break;
                        }
                    }

                    for (int secondIndex = Math.min(firstNameLength, secondNameLength) - 1; secondIndex >= 0; secondIndex--) {
                        if (firstName.substring(firstNameLength - 1 - secondIndex, firstNameLength).equals(secondName.substring(0, secondIndex + 1))) {
                            if (secondIndex + 1 > overlapMax) {
                                overlapMax = secondIndex + 1;
                                break;
                            }
                        }
                    }

                    if (overlapMax > maxAmountOfOverlap) {
                        System.out.println("max oberlap: " + overlapMax);
                        System.out.println(names.toString());
                        System.out.println("first index + name: " + first + " " + names.get(first));
                        System.out.println("second index + name: " + second + " " + names.get(second));
                        maxAmountOfOverlap = overlapMax;
                        maxIndexFirstName = first;
                        maxIndexSecondName = second;
                    }
                }
            }

            if(maxAmountOfOverlap == 0){
                //no overlap found
                String firstName = names.get(0);
                String secondName = names.get(names.size()-1);
                String concatedString = firstName + secondName;
                names.add(concatedString);
                names.remove(0);
                names.remove(names.size()-2);
                continue;
            }

            System.out.println("max overlap: " + maxAmountOfOverlap);
            System.out.println("first index: " + maxIndexFirstName);
            System.out.println("second index: " + maxIndexSecondName);
            System.out.println("words: " + names.get(maxIndexFirstName) + " " + names.get(maxIndexSecondName));

            //Handle String stuff
            String firstName = names.get(maxIndexFirstName);
            String secondName = names.get(maxIndexSecondName);
            String concatedString = firstName + secondName.substring(maxAmountOfOverlap);
            names.add(concatedString);
            names.remove(maxIndexFirstName);
            names.remove(maxIndexSecondName);
        }
        return names.get(0);
    }
}
