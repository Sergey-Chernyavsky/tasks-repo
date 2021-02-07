import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    private static final String PATH_CATALOG = "c:\\tests\\";

    public static void main(String[] args) throws IOException {
        File file;
        if(args[0].contains(":")){
            file = new File(args[0]);
        }
        else{
            file = new File(PATH_CATALOG + args[0]);
        }

        File[] files = file.listFiles();
        if(files.length > 5) throw new RuntimeException("Количество файлов в каталоге больше 5");

        Map<Integer, BigDecimal> sumValues = new HashMap<Integer, BigDecimal>();

        for(int i = 1; i <= 16; i++){
            sumValues.put(i, new BigDecimal(0.0));
        }

        for(int i = 0; i < files.length; i++){
            if(files[i].isFile()){
                BufferedReader reader = new BufferedReader(new FileReader(files[i]));
                int key = 1;
                while (reader.ready()){
                    String s = reader.readLine();
                    BigDecimal value = new BigDecimal(s.split("\\\\")[0]);
                    value = sumValues.get(key).add(value);
                    sumValues.put(key, value);
                    key++;
                }
                reader.close();
            }
        }

        BigDecimal maxValue = new BigDecimal(0);
        int maxValueKey = 0;
        for(Map.Entry<Integer, BigDecimal> map : sumValues.entrySet()){
            if (map.getValue().compareTo(maxValue) > 0){
                maxValue = map.getValue();
                maxValueKey = map.getKey();
            }
        }
        System.out.println(maxValueKey);
    }
}
