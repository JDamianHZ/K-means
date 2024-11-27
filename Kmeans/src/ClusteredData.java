public class ClusteredData {
    public double[] futures;
    public int centroide;

   

    public ClusteredData(double[] futures ,int centroide) {
       this.futures = futures;
        this.centroide = centroide;
    }

    public int getCentroide(){
        return centroide;
    }
    public double[] getFutures() {
        return futures;
    }
    

}
