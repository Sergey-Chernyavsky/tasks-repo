import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task2 {
    private static final String NEW_LINE = "\\n";

    public static void main(String[] args) throws IOException {
        FileReader fileQuadrangle = new FileReader(args[0]);
        FileReader filePoints = new FileReader(args[1]);
        BufferedReader bufQuadrangle = new BufferedReader(fileQuadrangle);
        BufferedReader bufPoints = new BufferedReader(filePoints);

        ArrayList<Float> coordinateX = new ArrayList<Float>();
        ArrayList<Float> coordinateY = new ArrayList<Float>();

        int count = 0;
        while (bufQuadrangle.ready()) {
            if(count < 4){
                String s = bufQuadrangle.readLine();
                String[] splitS = s.split("\\s");
                float x = Float.parseFloat(splitS[0]);
                float y = Float.parseFloat(splitS[1].split("\\\\")[0]);
                coordinateX.add(x);
                coordinateY.add(y);
            }
            else{
                throw new RuntimeException("Количество координат превышает 4");
            }
            count++;
        }
        fileQuadrangle.close();
        bufQuadrangle.close();
        count = 0;

        float maxX = Math.max(Math.max(coordinateX.get(0), coordinateX.get(1)), Math.max(coordinateX.get(2), coordinateX.get(3)));
        float minX = Math.min(Math.min(coordinateX.get(0), coordinateX.get(1)), Math.min(coordinateX.get(2), coordinateX.get(3)));

        float maxY = Math.max(Math.max(coordinateY.get(0), coordinateY.get(1)), Math.max(coordinateY.get(2), coordinateY.get(3)));
        float minY = Math.min(Math.min(coordinateY.get(0), coordinateY.get(1)), Math.min(coordinateY.get(2), coordinateY.get(3)));

        while (bufPoints.ready()) {
            if(count >= 100){
                throw new RuntimeException("Превышение допустимого количества точек");
            }
            String s = bufPoints.readLine();
            String[] splitS = s.split("\\s");
            float x = Float.parseFloat(splitS[0]);
            float y = Float.parseFloat(splitS[1].split("\\\\")[0]);

            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                System.out.println(2 + NEW_LINE);
            }
            else if((x < minX || x > maxX) || (y < minY || y > maxY)){
                System.out.println(3 + NEW_LINE);
            } else if ((x == coordinateX.get(0) && y == coordinateY.get(0)) ||
                    (x == coordinateX.get(1) && y == coordinateY.get(1)) ||
                    (x == coordinateX.get(2) && y == coordinateY.get(2)) ||
                    (x == coordinateX.get(3) && y == coordinateY.get(3))) {
                System.out.println(0 + NEW_LINE);
            } else if (pointOnLine(coordinateX.get(0), coordinateX.get(1), coordinateY.get(0), coordinateY.get(1), x, y) ||
                    pointOnLine(coordinateX.get(1), coordinateX.get(2), coordinateY.get(1), coordinateY.get(2), x, y) ||
                    pointOnLine(coordinateX.get(2), coordinateX.get(3), coordinateY.get(2), coordinateY.get(3), x, y) ||
                    pointOnLine(coordinateX.get(0), coordinateX.get(3), coordinateY.get(0), coordinateY.get(3), x, y)) {
                System.out.println(1 + NEW_LINE);
            }
            count++;
        }
        filePoints.close();
        bufPoints.close();
    }

    private static boolean pointOnLine(float xQuadrangle1, float xQuadrangle2, float yQuadrangle1, float yQuadrangle2, float xPoint, float yPoint) {
        float dx1 = xQuadrangle2 - xQuadrangle1;
        float dy1 = yQuadrangle2 - yQuadrangle1;
        float dx = xPoint - xQuadrangle1;
        float dy = yPoint - yQuadrangle1;

        float point = dx1 * dy - dx * dy1;
        if(point == 0) return true;
        else return false;
    }
}
