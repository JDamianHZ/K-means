import java.util.ArrayList;
import java.util.Arrays;
public class KMeans {

public static ArrayList<Centroide> centroides = new ArrayList<Centroide>();
public static ArrayList<ClusteredData> clusterdatas = new ArrayList<ClusteredData>();
public static ArrayList<DataSet> datos = DataSet.datos;
public static ArrayList<Media> mediacus = new ArrayList<Media>();

    public static int NumCentroides =3;

    public static void SeleccionCentroide() {
        int dimension = datos.get(0).getFutures().length;
        for (int i = 0; i < NumCentroides; i++) {
            double[] randomCoordinates = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                randomCoordinates[j] = -20 + Math.random() * (100 - (-20));
            }
            centroides.add(new Centroide(randomCoordinates));
        }
    }

    public static void CalcularDistanciaEuclidiana() {
        clusterdatas.clear(); // Limpiar la lista antes de llenarla nuevamente
        for (DataSet data : datos) {
    
            double distanciaMinima = Double.MAX_VALUE;
            int indiceCentroideMasCercano = -1;
    
            for (int i = 0; i < centroides.size(); i++) {
                Centroide centroide = centroides.get(i);
                double suma = 0;
    
                // Calcular la distancia euclidiana
                for (int j = 0; j < data.futures.length; j++) {
                    suma += Math.pow(data.futures[j] - centroide.futures[j], 2);
                }
                double distancia = Math.sqrt(suma);
    
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    indiceCentroideMasCercano = i;
                }
            }
            // Guarda el dato agrupado en la nueva lista
            clusterdatas.add(new ClusteredData(data.futures, indiceCentroideMasCercano));
        }
    }
    

    public static void ImprimirCentroides(){
        for (Centroide centroide : centroides) {
            System.out.println("Centroide: ");
            System.out.println(centroide.getFutures());
        }

    }

    public static void imprimirAgrupados() {
        for (ClusteredData data : clusterdatas) {
            System.out.println(Arrays.toString(data.getFutures()) + " Centroide: " + data.getCentroide());
    }
}

    public static void MediaCentroides() {
        mediacus.clear(); // Limpiar las medias previas si existen
    
        for (int j = 0; j < NumCentroides; j++) {
            double[] sumaFutures = new double[datos.get(j).getFutures().length];
            int contador = 0;
    
            for (ClusteredData data : clusterdatas) {
                if (data.getCentroide() == j) { // Si el dato pertenece al centroide actual
                    for (int k=0; k < data.getFutures().length; k++) {
                        sumaFutures[k] += data.getFutures()[k];
                    }
                    contador++;
                }
            }
    
            // Solo calcular la media si hay datos asignados al centroide
            if (contador > 0) {
                double[] mediaFutures = new double[sumaFutures.length];
                for (int k = 0; k < sumaFutures.length; k++) {
                    mediaFutures[k] = sumaFutures[k] / contador; // Calcular la media de cada característica
                }
                centroides.get(j).futures = mediaFutures;

                // Agregar la media a la lista de medias (dependiendo de cómo la uses, tal vez necesites ajustar esto)
                mediacus.add(new Media(j, mediaFutures, contador)); 
            }
        }
    }

    public static void DistanciaMedias() {
        clusterdatas.clear(); // Limpiar la lista antes de llenarla nuevamente
    
        for (DataSet data : datos) {
            double distanciaMinima = Double.MAX_VALUE;
            int indiceCentroideMasCercano = -1;
    
            for (int i = 0; i < mediacus.size(); i++) {
                Media media = mediacus.get(i); // Utilizar `Media` en lugar de `Centroide`
                double suma = 0;
    
                // Calcular la distancia euclidiana en múltiples dimensiones
                for (int j = 0; j < data.futures.length; j++) {
                    suma += Math.pow(data.futures[j] - media.medias[j], 2);
                }
                double distancia = Math.sqrt(suma);
    
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    indiceCentroideMasCercano = i;
                }
            }
    
            // Guarda el dato agrupado en la nueva lista
            clusterdatas.add(new ClusteredData(data.futures, indiceCentroideMasCercano));
        }
    }
    

    public static void ImprimirMedias() {
        for (Media media : mediacus) {
            System.out.print("Media de centroide " + mediacus.indexOf(media) + ": [");
            
            // Imprimir todas las medias de las características
            for (int i = 0; i < media.medias.length; i++) {
                System.out.print(media.medias[i]);
                if (i < media.medias.length - 1) {
                    System.out.print(", ");
                }
            }
            
            System.out.println("] Número de datos: " + media.getContador());
        }
    }
    
}
