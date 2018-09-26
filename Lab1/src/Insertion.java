public class Insertion {
    public static void insertionSortAscending(Perforator[] perforatorArray) {
        int temp;
        int j;
        for(int i = 0; i < perforatorArray.length - 1; i++){
            if (perforatorArray[i].getRotationsPerMinute() > perforatorArray[i + 1].getRotationsPerMinute()) {
                temp = perforatorArray[i + 1].getRotationsPerMinute();
                perforatorArray[i + 1].setRotationsPerMinute(perforatorArray[i].getRotationsPerMinute());
                j = i;
                while (j > 0 && temp < perforatorArray[j - 1].getRotationsPerMinute()) {
                    perforatorArray[j].setRotationsPerMinute(perforatorArray[j - 1].getRotationsPerMinute());
                    j--;
                }
                perforatorArray[j].setRotationsPerMinute(temp);
            }
        }
    }



    public static void printSortedArrayInsertion(Perforator[] perforatorArray){
        for(int i = 0; i < perforatorArray.length; i++){
            System.out.println(perforatorArray[i].getRotationsPerMinute() + " rotations per minute");
        }
    }

    public static void main(String[] args) {
        Perforator daewoo = new Perforator(1700, 5200, "Orange");
        Perforator total = new Perforator(1200, 5300, "Blue");
        Perforator bosch = new Perforator(1300, 4980, "Black");
        Perforator sony = new Perforator(2100, 6100, "White");
        Perforator alb = new Perforator(900, 5400, "Green");
        Perforator perform = new Perforator(1500, 5900, "Red");

        Perforator[] perforatorArray = {daewoo, total, bosch, sony, alb, perform};
        Insertion.printSortedArrayInsertion(perforatorArray);
        Insertion.insertionSortAscending(perforatorArray);
        System.out.println("Sorted array:");
        Insertion.printSortedArrayInsertion(perforatorArray);




    }

}
