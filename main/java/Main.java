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

                    int firstNameLength = firstName.length();
                    int secondNameLength = secondName.length();
                    int overlapMax = 0;

                    if (secondName.contains(firstName)) {
                        System.out.println(names.get(second) + " contains first: " + names.get(first));
                        names.remove(first);
                        size--;
                        namesSize--;
                        second--;
                        System.out.println(size);
                        break;
                    }

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
