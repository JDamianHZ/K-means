import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataSet {
    static String csvFile ="D:\\semetres\\7mo\\IA\\Kmeans\\xclara.csv";
    public double x;
    public double y;
    
   public static ArrayList<DataSet> datos = new ArrayList<DataSet>();


    public  DataSet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getx(){
        return x;
    }

    public double gety(){
        return y;
    }


    public static void LeerDatos(){
        String line;
        String cvsSplitBy = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           br.readLine();
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(cvsSplitBy);
                DataSet data = new DataSet(Double.parseDouble(valores[0]), Double.parseDouble(valores[1]));
                datos.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void imprimirDatos(){
        for (DataSet data : datos) {
            System.out.println("X: " + data.getx() + " Y: " + data.gety());
        }
    }





}
