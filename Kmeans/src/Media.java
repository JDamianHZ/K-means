public class Media {
    public int centroide;
    public double[] medias;
    public int contador;

    public Media(int centroide, double[] medias, int contador) {
        this.centroide = centroide;
        this.medias = medias;
        this.contador = contador;
    }
    
    public int getCentroide(){
        return centroide;
    }
    public double[] getMedias() {
        return medias;
    }
    public int getContador(){
        return contador;
    }
}
