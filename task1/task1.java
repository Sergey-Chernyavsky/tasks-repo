import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1 {
    private static final String PATH_FILE = "c:\\tests\\";
    private static final String TXT = ".txt";
    private static final double PERCENT = 0.9;
    private static final String NEW_LINE = "\\n";

    public static void main(String[] args) {
        String fileName = PATH_FILE + args[0] + TXT;
        try{
            List<Short> sort = sortList(fileReader(fileName));
            System.out.println(percentile(sort, PERCENT) + NEW_LINE);
            System.out.println(median(sort) + NEW_LINE);
            System.out.println(max(sort) + NEW_LINE);
            System.out.println(min(sort) + NEW_LINE);
            System.out.println(average(sort) + NEW_LINE);
        }
        catch (IOException e){
            System.out.println("No file!");
        }
    }

    private static List<Short> fileReader(String fileName) throws IOException{
        FileReader fileReader;
        List<Short> array = new ArrayList<Short>();

        fileReader = new FileReader(fileName);
        BufferedReader bufReader = new BufferedReader(fileReader);

        int count = 0;
        while (bufReader.ready()){
            if(count < 1000){
                short i = Short.parseShort(bufReader.readLine());
                array.add(i);
            }
            else {
                throw new RuntimeException("Файл содержит более 1000 строк");
            }
            count++;
        }
        fileReader.close();
        bufReader.close();
        return array;
    }

    private static List<Short> sortList(List<Short> list){
        Collections.sort(list);
        return list;
    }

    private static BigDecimal max(List<Short> list){
        BigDecimal max = new BigDecimal(list.get(list.size() - 1));
        return max.setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal min(List<Short> list){
        BigDecimal min = new BigDecimal(list.get(0));
        return min.setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal percentile(List<Short> list, double percent){
        double realIndex = percent * (list.size() - 1);
        int index = (int) realIndex;
        double frac = realIndex - index;
        if (index - 1 < list.size()){
            BigDecimal percentile = new BigDecimal(list.get(index) * (1 - frac) + list.get(index + 1) * frac);
            return percentile.setScale(2, RoundingMode.HALF_UP);
        }
        else{
            return new BigDecimal(list.get(index)).setScale(2, RoundingMode.HALF_UP);
        }
    }

    private static BigDecimal median(List<Short> list){
        BigDecimal median = new BigDecimal(list.get(list.size()/2));
        return median.setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal average(List<Short> list){
        double sum = 0;
        for(Short i : list){
            sum += i;
        }
        BigDecimal average = new BigDecimal( sum / list.size());
        return average.setScale(2, RoundingMode.HALF_UP);
    }
}
