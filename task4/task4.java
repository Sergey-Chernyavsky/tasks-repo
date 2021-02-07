import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task4 {
    private static final String PATH_FILE = "c:\\tests\\";
    private static final String TXT = ".txt";
    private static final String NEW_LINE = "\\n";

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader fileReader = new BufferedReader(new FileReader(PATH_FILE + args[0] + TXT));

        Map<Integer, Date> entrance = new HashMap<Integer, Date>();
        Map<Integer, Date> exit = new HashMap<Integer, Date>();
        Map<Date, Integer> manPerMinute = new TreeMap<Date, Integer>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm");
        Date startDay = dateFormat.parse("8:00");
        Date stopDay = dateFormat.parse("20:00");

        while (true){
            long min = startDay.getTime() + 60000;
            startDay = new Date(min);
            if(startDay.compareTo(stopDay) > 0){
                break;
            }
            manPerMinute.put(startDay, 0);
        }

        int person = 1;
        while (fileReader.ready()){
            String string = fileReader.readLine().replace(NEW_LINE, "");
            Date entranceTime = dateFormat.parse(string.split(" ")[0]);
            Date exitTime = dateFormat.parse(string.split(" ")[1]);

            if(entranceTime.compareTo(exitTime) < 0){
                entrance.put(person, entranceTime);
                exit.put(person, exitTime);
            }

            person++;
        }
        fileReader.close();

        for(Map.Entry<Integer, Date> entr : entrance.entrySet()){
            for(Map.Entry<Date, Integer> man : manPerMinute.entrySet()){
                if(entr.getValue().compareTo(man.getKey()) <= 0){
                    man.setValue(man.getValue() + 1);
                }
            }
        }

        for(Map.Entry<Integer, Date> ext : exit.entrySet()){
            for(Map.Entry<Date, Integer> man : manPerMinute.entrySet()){
                if(ext.getValue().compareTo(man.getKey()) <= 0){
                    man.setValue(man.getValue() - 1);
                }
            }
        }

        int maxPersons = 0;
        for(Map.Entry<Date, Integer> man: manPerMinute.entrySet()){
            if(maxPersons < man.getValue()){
                maxPersons = man.getValue();
            }

        }

        int p1 = 0;
        int p2 = 0;
        List<String> intervals = new ArrayList<String>();

        String strEntrance = null;
        String strExit = null;
        for(Map.Entry<Date, Integer> man : manPerMinute.entrySet()){
            p1 = man.getValue();
            if(p1 > p2 && p1 == maxPersons){
                strEntrance = dateFormat.format(man.getKey());
            }
            if(p1< p2 && p2 == maxPersons){
                strExit = dateFormat.format(man.getKey());
                intervals.add(strEntrance + " " + strExit + NEW_LINE);
            }
            p2 = man.getValue();

        }

        if(intervals.size() == 1)System.out.println(intervals.get(0));
        else if(intervals.size() > 1){
            for(String interval : intervals){
                System.out.println(interval);
            }
        }
    }
}
