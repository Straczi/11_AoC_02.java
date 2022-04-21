package com.company;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
//ziemliches Stück scheisse, aber funktioniert erstmal
    public static void main(String[] args) throws Exception {
        ArrayList<String> inputList = getInputData("inputData.txt");
        ArrayList<String> inputListBackUp;
        do{
            inputListBackUp = new ArrayList<>(inputList);
            updateSeats(inputList);
        } while (listsAreDifferent(inputList, inputListBackUp));
        int occupiedCounter =0;
        for (String s : inputList) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '#') {
                    occupiedCounter++;
                }
            }
        }

        System.out.println(occupiedCounter);

    }
    //* -> is becoming empty
    //$ -> is being occupied
    public static void updateSeats(ArrayList<String> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.get(i).length(); j++) {
                if( (inputList.get(i).charAt(j) == '#') && (adjacentSeats(inputList, i, j)>=5) ){
                    inputList.set(i, replaceCharAt(inputList, i, j, '*'));
                }
                if( (inputList.get(i).charAt(j) == 'L') && (adjacentSeats(inputList, i, j)==0) ){
                    inputList.set(i, replaceCharAt(inputList, i, j, '$'));
                }
            }
        }
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.get(i).length(); j++) {
                if(inputList.get(i).charAt(j) == '*'){
                    inputList.set(i, replaceCharAt(inputList, i, j, 'L'));
                }
                else if(inputList.get(i).charAt(j) == '$'){
                    inputList.set(i, replaceCharAt(inputList, i, j, '#'));
                }
            }
        }
    }

    public static boolean listsAreDifferent (ArrayList<String>inputList, ArrayList<String>inputListBackUp){
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.get(i).length(); j++) {
                if(inputList.get(i).charAt(j) != inputListBackUp.get(i).charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String replaceCharAt (ArrayList<String>inputList, int ListIndex, int StringIndex, char ch){
        StringBuilder myString = new StringBuilder( inputList.get(ListIndex) );
        myString.setCharAt(StringIndex, ch);
        return myString.toString();

    }
// 3, 9
    public static int adjacentSeats(ArrayList<String>inputList, int ListIndex, int StringIndex){
        int seatCounter =0;
        final int ListIndexBackUp = ListIndex;
        final int StringIndexBackUp = StringIndex;


        while(ListIndex !=0 && StringIndex != 0 ) {  //top left
            if(isOccupied(inputList,ListIndex-1, StringIndex-1)) {
                seatCounter++;
                break;
            }
            if(inputList.get(ListIndex-1).charAt(StringIndex-1) != '.'){
                break;
            }
            ListIndex--;
            StringIndex--;
        }
        ListIndex = ListIndexBackUp;
        StringIndex = StringIndexBackUp;

        while(ListIndex !=0  ) { //top
            if(isOccupied(inputList,ListIndex-1, StringIndex)) {
                seatCounter++;
                break;
            }
            if(inputList.get(ListIndex-1).charAt(StringIndex) != '.'){
                break;
            }
            ListIndex--;
        }
        ListIndex = ListIndexBackUp;

        while(ListIndex !=0 && StringIndex != inputList.get(ListIndex).length()-1  ) { //top right
            if(isOccupied(inputList,ListIndex-1, StringIndex+1)) {
                seatCounter++;
                break;
            }
            if(inputList.get(ListIndex-1).charAt(StringIndex+1) != '.'){
                break;
            }
            ListIndex--;
            StringIndex++;
        }
        ListIndex = ListIndexBackUp;
        StringIndex = StringIndexBackUp;

        while(StringIndex !=0  ) { //left
           if( isOccupied(inputList,ListIndex, StringIndex-1)){
               seatCounter++;
               break;
           }
            if(inputList.get(ListIndex).charAt(StringIndex-1) != '.'){
                break;
            }
            StringIndex--;
        }
        StringIndex = StringIndexBackUp;

        while (StringIndex != inputList.get(ListIndex).length()-1 ) { //right
            if(isOccupied(inputList,ListIndex, StringIndex+1)) {
                seatCounter++;
                break;
            }
            if(inputList.get(ListIndex).charAt(StringIndex+1) != '.'){
                break;
            }
            StringIndex++;
        }
        StringIndex = StringIndexBackUp;

        while (ListIndex != inputList.size()-1 && StringIndex != 0 ) {  //down left
            if(isOccupied(inputList,ListIndex+1, StringIndex-1)) {
                seatCounter++;
                break;
            }
            if(inputList.get(ListIndex+1).charAt(StringIndex-1) != '.'){
                break;
            }
            ListIndex++;
            StringIndex--;
        }
        ListIndex = ListIndexBackUp;
        StringIndex = StringIndexBackUp;

        while(ListIndex !=inputList.size()-1  ) { //down
            if(isOccupied(inputList,ListIndex+1, StringIndex)) {
                seatCounter++;
                break;
            }
            if(inputList.get(ListIndex+1).charAt(StringIndex) != '.'){
                break;
            }
            ListIndex++;
        }
        ListIndex = ListIndexBackUp;
        while (ListIndex !=inputList.size()-1 && StringIndex != inputList.get(ListIndex).length()-1  ) { //down right
            if( isOccupied(inputList,ListIndex+1, StringIndex+1 )) {
                seatCounter++;
                break;
            }
            if(inputList.get(ListIndex+1).charAt(StringIndex+1) != '.'){
                break;
            }
            ListIndex++;
            StringIndex++;
        }

        return seatCounter;
    }

    public static boolean isOccupied (ArrayList<String>inputList, int ListIndex, int StringIndex){
        return ( inputList.get(ListIndex).charAt(StringIndex) == '#' || inputList.get(ListIndex).charAt(StringIndex) == '*' );
    }



    public static ArrayList<String> getInputData (String filename) throws Exception{
        File inputData = new File(filename);
        Scanner scanner = new Scanner(inputData);
        ArrayList<String> dataList = new ArrayList<>();
        while (scanner.hasNext()) {
            dataList.add(scanner.next());
        }
        return dataList;
    }

}
