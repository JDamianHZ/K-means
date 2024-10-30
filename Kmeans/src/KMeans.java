import java.util.ArrayList;
public class KMeans {

public static ArrayList<Centroide> centroides = new ArrayList<Centroide>();
public static ArrayList<ClusteredData> clusterdatas = new ArrayList<ClusteredData>();
public static ArrayList<DataSet> datos = DataSet.datos;
public static ArrayList<Media> mediacus = new ArrayList<Media>();

    public static int NumCentroides =3;

    public static void SeleccionCentroide() {
        for (int i = 0; i < NumCentroides; i++) {
            // Generar valores aleatorios en los rangos especificados para x e y
            double x = -20 + Math.random() * (100 - (-20));
            double y = -40 + Math.random() * (100 - (-40));
    
            // Crear y añadir el nuevo centroide
            Centroide centroide = new Centroide(x, y);
            centroides.add(centroide);
    
            System.out.println("Centroide " + i + ": X=" + x + " Y=" + y);
        }
    }

    public static void CalcularDistanciaEuclidiana(){
        clusterdatas.clear(); // Limpiar la lista antes de llenarla nuevamente
        for (DataSet data : datos) {

            double distanciaMinima = Double.MAX_VALUE;
            int indiceCentroideMasCercano = -1;

            for (int i = 0; i < centroides.size(); i++) {
                Centroide centroide = centroides.get(i);
                double suma = Math.sqrt(Math.pow(data.getx() - centroide.getx(), 2) + Math.pow(data.gety() - centroide.gety(), 2));
                //System.out.println("Dato: "+ datos.indexOf(data)+" Centroide: "+ i+" "+ data.getx()+ " "+ data.gety()+" suma: "+ suma);
                if (suma < distanciaMinima) {
                    distanciaMinima = suma;
                    indiceCentroideMasCercano = i;
                }
            }
            // Guarda el dato agrupado en la nueva lista
            clusterdatas.add(new ClusteredData(data.getx(), data.gety(), indiceCentroideMasCercano));
        }

    }

    public static void ImprimirCentroides(){
        for (Centroide centroide : centroides) {
            System.out.println("Centroide: ");
            System.out.println("X: " + centroide.getx() + " Y: " + centroide.gety());
        }

    }

    public static void imprimirAgrupados(){
        for (ClusteredData data : clusterdatas) {
            System.out.println("X: " + data.getx() + " Y: " + data.gety() + " Centroide: " + data.getCentroide());
        }
    }

    public static void MediaCentroides() {
        mediacus.clear(); // Limpiar las medias previas si existen
    
        for (int j = 0; j < NumCentroides; j++) {
            double sumax = 0;
            double sumay = 0;
            int contador = 0;
    
            for (ClusteredData data : clusterdatas) {
                if (data.getCentroide() == j) { // Si el dato pertenece al centroide actual
                    sumax += data.getx();
                    sumay += data.gety();
                    contador++;
                }
            }
    
            // Solo calcular la media si hay datos asignados al centroide
            if (contador > 0) {
                double mediax = sumax / contador;
                double mediay = sumay / contador;
                
                centroides.get(j).x = mediax;
                centroides.get(j).y = mediay;
    
                // Agregar la media a la lista de medias
                mediacus.add(new Media(j,mediax, mediay, contador)); 
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
    
                double suma = Math.sqrt(Math.pow(data.getx() - media.getMediax(), 2) + Math.pow(data.gety() - media.getMediay(), 2));
    
                if (suma < distanciaMinima) {
                    distanciaMinima = suma;
                    indiceCentroideMasCercano = i;
                }
            }
    
            // Guarda el dato agrupado en la nueva lista
            clusterdatas.add(new ClusteredData(data.getx(), data.gety(), indiceCentroideMasCercano));
        }
    }
    

    public static void ImprimirMedias(){
        for (Media media : mediacus) {
            System.out.println("media de centroide " + mediacus.indexOf(media)+" X: " + media.getMediax() + " Y: " + media.getMediay()+" Numero de datos: "+ media.getContador());
        }

    }
    
}
