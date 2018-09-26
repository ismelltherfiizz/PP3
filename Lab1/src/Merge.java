public class Merge {

    private Perforator[] array;
    private Perforator[] tempArray;
    private int length;

    public static void main(String[] args){
        Perforator daewoo = new Perforator(1700, 5200, "Orange");
        Perforator total = new Perforator(1200, 5300, "Blue");
        Perforator bosch = new Perforator(1300, 4980, "Black");
        Perforator sony = new Perforator(2100, 6100, "White");
        Perforator alb = new Perforator(900, 5400, "Green");
        Perforator perform = new Perforator(1500, 5900, "Red");
        Perforator[] perforatorArray = {daewoo, total, bosch, sony, alb, perform};
        Merge mms = new Merge();
        mms.sort(perforatorArray);
        Merge.printSortedArrayMerge(perforatorArray);
        }


    public void sort(Perforator perforatorArray[]) {
        this.array = perforatorArray;
        this.length = perforatorArray.length;
        this.tempArray = new Perforator[length];
        doMergeSort(0, length - 1);
    }

    private void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            doMergeSort(lowerIndex, middle);
            doMergeSort(middle + 1, higherIndex);
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    public static void printSortedArrayMerge(Perforator[] perforatorArray){
        for(int i = 0; i < perforatorArray.length; i++){
            System.out.println(perforatorArray[i].getBeatsPerMinute() + " beats per minute");
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempArray[i].setBeatsPerMinute(array[i].getBeatsPerMinute());
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempArray[i].getBeatsPerMinute() <= tempArray[j].getBeatsPerMinute()) {
                array[k].setBeatsPerMinute(tempArray[i].getBeatsPerMinute());
                i++;
            } else {
                array[k].setBeatsPerMinute(tempArray[j].getBeatsPerMinute());
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k].setBeatsPerMinute(tempArray[i].getBeatsPerMinute());
            k++;
            i++;
        }
        }

    }
