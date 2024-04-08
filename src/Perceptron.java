import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Perceptron {

    private double[] weights;
    private double bias;
    private double learningRate;

    private int epoch;
    public List<Observation> trainingList;
    public List<Observation> testList;

    private Map<String, Integer> labelMap = new HashMap<>();
    private String positiveLabel, negativeLabel;


    public Perceptron(double learningRate, String trainingPath, String testPath, int epoch) {
        this.learningRate = learningRate;
        trainingList = loadData(trainingPath);
        testList = loadData(testPath);
        this.epoch = epoch;
        int numOfWeights = trainingList.get(0).getFeatures().length;
        initializeWeights(numOfWeights);
    }

    public List<Observation> loadData(String path) {
        List<Observation> list = new ArrayList<>();
        int labelIndex = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitedLine = line.split(",");
                double[] features = new double[splitedLine.length - 1];
                for (int i = 0; i < features.length; i++) {
                    features[i] = Double.parseDouble(splitedLine[i]);
                }
                String label = splitedLine[splitedLine.length - 1];

                Observation observation = new Observation(label, features);
                list.add(observation);

                if (!labelMap.containsKey(label)) {
                    labelMap.put(label, labelIndex++);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        positiveLabel = labelMap.keySet().iterator().next();
        for (String label : labelMap.keySet()) {
            if (!label.equals(positiveLabel)) {
                negativeLabel = label;
                labelMap.put(label, 0);
            }
        }
        labelMap.put(positiveLabel, 1);

        return list;
    }

    private void initializeWeights(int size) {
        weights = new double[size];
        for (int i = 0; i < size; i++) {
            weights[i] = Math.random() * 0.1 + 0.05;
        }
        bias = Math.random() * 0.1 + 0.05;
    }

    private int activation(double net) {
        return net > 0 ? 1 : 0;
    }

    private double netCalc(Observation observation) {
        double net = 0;
        for (int i = 0; i < observation.getFeatures().length; i++) {
            net += observation.getFeatures()[i] * weights[i];
        }
        net += bias;
        return net;
    }

    public void train(List<Observation> observationList) {
        for (int i = 0; i < epoch; i++) {
            for (Observation observation : observationList) {
                double predicted = activation(netCalc(observation));
                double trueLabel = labelMap.get(observation.getLabel());
                double error = trueLabel - predicted;
                for (int j = 0; j < observation.getFeatures().length; j++) {
                    weights[j] += (trueLabel - predicted) * learningRate * observation.getFeatures()[j];
                }
                bias += learningRate * error;
            }
            double accuracy = testAccuracy(testList);
            System.out.println("Dokładność po epoce " + (i + 1) + ": " + accuracy + "%");
            Collections.shuffle(observationList);
        }
    }

    public double testAccuracy(List<Observation> testList) {
        int correct = 0;
        for (Observation o : testList) {
            String prediction = predict(o);
            if (o.getLabel().equals(prediction)) {
                correct++;
            }
        }
        return (double) correct / testList.size() * 100;
    }


    public String predict(Observation observation) {
        int prediction = activation(netCalc(observation));
        return prediction == 1 ? positiveLabel : negativeLabel;
    }

    public void classifyFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Podaj cechy obserwacji oddzielone przecinkami:");
            String input = reader.readLine();
            String[] featureStrings = input.split(",");
            double[] features = new double[featureStrings.length];
            for (int i = 0; i < featureStrings.length; i++) {
                features[i] = Double.parseDouble(featureStrings[i]);
            }

            Observation observation = new Observation(features);
            String prediction = predict(observation);
            System.out.println("Klasyfikacja dla podanej obserwacji: " + prediction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
