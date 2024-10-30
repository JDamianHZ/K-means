import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        DataSet.LeerDatos();
        KMeans.SeleccionCentroide();
        KMeans.CalcularDistanciaEuclidiana();
        KMeans.MediaCentroides();
        KMeans.ImprimirMedias();

        boolean convergencia = false;
        int iteraciones=0;

        while (!convergencia) {
            System.out.println("Closter numero " + (iteraciones + 1)  );
            // Guardar los contadores actuales
            ArrayList<Integer> contadoresPrevios = new ArrayList<>();
            for (Media media : KMeans.mediacus) {
                contadoresPrevios.add(media.getContador());
            }
            

            // Recalcular las distancias y medias
            KMeans.DistanciaMedias();
            KMeans.MediaCentroides();
            KMeans.ImprimirMedias();

            // Comprobar si los contadores son los mismos que en la vuelta anterior
            convergencia = true;
            for (int i = 0; i < KMeans.mediacus.size(); i++) {
                if (contadoresPrevios.get(i) != KMeans.mediacus.get(i).getContador()) {
                    convergencia = false;
                    break;
                }
            }
            iteraciones++;
        }

        System.out.println("Convergencia alcanzada en la vuelta: "+ (iteraciones-1));
        System.out.println("Closter Final: ");
        KMeans.ImprimirMedias();
    }
}
