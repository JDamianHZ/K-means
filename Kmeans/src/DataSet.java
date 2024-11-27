import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataSet {
    static String csvFile ="D:\\semetres\\7mo\\IA\\Kmeans1\\xclara2.csv";
    public String id;
    public double[] futures;
    
   public static ArrayList<DataSet> datos = new ArrayList<DataSet>();


    public  DataSet(String id, double[] futures){ {
        this.id = id;
        this.futures = futures;
    }
    }

    public String getId() {
        return id;
    }
    public double[] getFutures() {
        return futures;
    }



    public static void LeerDatos(){
        String line;
        String cvsSplitBy = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           br.readLine();
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(cvsSplitBy);
                String id = valores[0];

                double[] futures = new double[valores.length-1];
                for (int i = 1; i < valores.length; i++) {
                    futures[i-1] = Double.parseDouble(valores[i]);
                }
                datos.add(new DataSet(id, futures));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void imprimirDatos(){
        for (DataSet data : datos) {
            double[] futures = data.getFutures();
            if (futures.length >= 2) {
                System.out.println(data.getId()+" ");
                for (double future: data.getFutures()) {
                    System.out.print(future+" ");
                }
            } else {
                System.out.println("ID: " + data.getId() + " tiene datos incompletos.");
            }
        }
    }
    



}
