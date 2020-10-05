import java.util.*;

public class Main {
    public String makeNameNaive(List<String> names) {
        while(names.size()>1){
            //concat them and add to names
            //remove words, decreasing names.size()
            int maxAmountOfOverlap = 0;
            int maxIndexFirstName = 0;
            int maxIndexSecondName = 0;
            boolean shouldRemoveSecondName = false;

            int indexFirstName = 0;
            int indexSecondName = 0;

            BigLoop:
            for (int first = 0, size = names.size(); first < size; first++) {
                String firstName = names.get(first);
                for (int second = 0, namesSize = names.size(); second < namesSize; second++) {
                    String secondName = names.get(second);

                    int firstNameLength = firstName.length();
                    int secondNameLength = secondName.length();
                    int overlapMax = 0;
                    boolean firstNameFirst = false;


                    // If on the same name
                    if (indexFirstName == indexSecondName) {
                        indexSecondName++;
                        break;
                    }


                    if (firstName.contains(secondName)) {
                        shouldRemoveSecondName = true;
                        break BigLoop;
                    }

                    for (int firstIndex = Math.min(firstNameLength, secondNameLength) - 1; firstIndex >= 0; firstIndex--) {
                        if (firstName.substring(0, firstIndex + 1).equals(secondName.substring(secondNameLength - 1 - firstIndex, secondNameLength))) {
                            overlapMax = firstIndex + 1;
                            firstNameFirst = false;
                            break;
                        }
                    }

                    for (int secondIndex = Math.min(firstNameLength, secondNameLength) - 1; secondIndex >= 0; secondIndex--) {
                        if (firstName.substring(firstNameLength - 1 - secondIndex, firstNameLength).equals(secondName.substring(0, secondIndex + 1))) {
                            if (secondIndex + 1 > overlapMax) {
                                overlapMax = secondIndex + 1;
                                firstNameFirst = true;
                                break;
                            }
                        }
                    }

                    if (overlapMax > maxAmountOfOverlap) {
                        maxAmountOfOverlap = overlapMax;
                        if (firstNameFirst) {
                            maxIndexFirstName = indexFirstName;
                            maxIndexSecondName = indexSecondName;
                        } else {
                            maxIndexFirstName = indexSecondName;
                            maxIndexSecondName = indexFirstName;
                        }
                    }

                    indexSecondName++;
                }

                indexSecondName = 0;
                indexFirstName++;
            }

            if(shouldRemoveSecondName){
                if(indexFirstName>indexSecondName){
                    names.remove(indexSecondName);
                } else {
                    names.remove(indexSecondName);
                }
                continue;
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

            //Handle String stuff
            String firstName = names.get(maxIndexFirstName);
            String secondName = names.get(maxIndexSecondName);
            String concatedString = firstName + secondName.substring(maxAmountOfOverlap, secondName.length());
            names.add(concatedString);
            if(maxIndexFirstName < maxIndexSecondName){
                names.remove(maxIndexFirstName);
                names.remove(maxIndexSecondName-1);
            } else{
                names.remove(maxIndexSecondName);
                names.remove(maxIndexFirstName-1);
            }
        }
        return names.get(0);
    }
}
