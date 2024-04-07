import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Perceptron {

    private double[] weights;
    private double theta;
    private double learningConstant;

    public List<Observation> observationList;

    public Perceptron(double learningConstant, String path) {
        loadData(path);
        this.learningConstant = learningConstant;
        this.weights = new double[observationList.size()];
        weigthInitializacion();
    }

    public void loadData(String path){
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                String[] spliitedLine = line.split(",");
                double[] features = new double[spliitedLine.length-1];
                for (int i = 0; i < features.length; i++) {
                    features[i] = Double.parseDouble(spliitedLine[i]);
                }
                String label = spliitedLine[spliitedLine.length-1];
                Observation observation = new Observation(label, features);
                observationList.add(observation);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void weigthInitializacion(){
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random() * 0.1 - 0.05;
        }
        theta = Math.random() * 0.1 - 0.05;
    }

    private int netCalc(Observation observation){
        double net = 0;
        for (int i = 0; i < observation.getFeatures().length; i++) {
            net += observation.getFeatures()[i]*weights[i];
        }
        net += theta;
        if (net > 0){
            return 1;
        }else{
            return 0;
        }
    }
    public void train(){
        for (int i = 0; i < observationList.size(); i++) {
            int error = 0;
            for (Observation observation : observationList){

            }
        }
    }
}
