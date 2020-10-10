import java.util.*;

public class Main {

    public String makeNameNaive(List<String> names) {
        bitLoop:
        while(names.size()>1){
            //concat them and add to strings
            //remove words, decreasing strings.size()
            int maxAmountOfOverlap = 0;
            int maxIndexFirstName = 0;
            int maxIndexSecondName = 0;
            int currentNamesSize = names.size();

            for (int first = 0; first < currentNamesSize; first++) {
                String firstName = names.get(first);
                for (int second = first + 1; second < currentNamesSize; second++) {
                    String secondName = names.get(second);
                    boolean secondStringFirst = false;
                    int overlapMax;

                    if (firstName.contains(secondName)) {
                        names.remove(second);
                        currentNamesSize = names.size();
                        if(second == maxIndexFirstName || second == maxIndexSecondName) continue bitLoop;
                        if(maxIndexFirstName > maxIndexSecondName && second < maxIndexFirstName) maxIndexFirstName--;
                        if(maxIndexFirstName < maxIndexSecondName && second < maxIndexSecondName) maxIndexSecondName--;
                        second--;
                        continue;
                    }

                    if (secondName.contains(firstName)){
                        names.remove(first);
                        currentNamesSize = names.size();
                        if(first == maxIndexFirstName || first == maxIndexSecondName) continue bitLoop;
                        if(maxIndexFirstName > maxIndexSecondName && first < maxIndexFirstName) maxIndexFirstName--;
                        if(maxIndexFirstName < maxIndexSecondName && first < maxIndexSecondName) maxIndexSecondName--;
                        firstName = names.get(first);
                        second--;
                        continue;
                    }

                    // FirstName + secondString overlap
                    overlapMax = findOverlap(firstName, secondName);

                    // SecondString + FirstString
                    int flippedOverlap = findOverlap(secondName, firstName);
                    if (flippedOverlap > overlapMax) {
                        overlapMax = flippedOverlap;
                        secondStringFirst = true;
                    }


                    if (overlapMax > maxAmountOfOverlap) {
                        maxAmountOfOverlap = overlapMax;
                        if (secondStringFirst) {
                            maxIndexFirstName = second;
                            maxIndexSecondName = first;
                        } else {
                            maxIndexFirstName = first;
                            maxIndexSecondName = second;
                        }
                    }
                }
            }



            if(maxAmountOfOverlap == 0){
                //no overlap found
                String firstName = names.get(0);
                String secondName = names.get(currentNamesSize-1);
                String concatedString = firstName + secondName;
                names.add(concatedString);
                names.remove(0);
                names.remove(names.size()-2);
                continue;
            }

            //Handle String stuff
            String firstName = names.get(maxIndexFirstName);
            String secondName = names.get(maxIndexSecondName);
            String concatedString = firstName + secondName.substring(maxAmountOfOverlap);
            names.add(concatedString);
            if(maxIndexFirstName<maxIndexSecondName){
                names.remove(maxIndexFirstName);
                names.remove(maxIndexSecondName-1);
            } else{
                names.remove(maxIndexSecondName);
                names.remove(maxIndexFirstName-1);
            }
        }
        return names.get(0);
    }

    public static int findOverlap(String firstString, String secondString) {
        int firstStringLength = secondString.length();
        int secondStringLength = firstString.length();
        for (int firstIndex = Math.min(firstStringLength, secondStringLength) - 1; firstIndex >= 0; firstIndex--) {
            if (secondString.substring(0, firstIndex + 1).equals(firstString.substring(secondStringLength - 1 - firstIndex, secondStringLength))) {
                return firstIndex + 1;
            }
        }
        return 0;
    }
}
