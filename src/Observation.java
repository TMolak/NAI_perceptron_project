import java.util.Arrays;

public class Observation {
    private String output;
    private double[] features;

    public Observation(String label, double[] features) {
        this.output = label;
        this.features = features;
    }

    public Observation(double[] features) {
        this.features = features;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public double[] getFeatures() {
        return features;
    }

    public void setFeatures(double[] features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "label='" + output + '\'' +
                ", features=" + Arrays.toString(features) +
                '}';
    }
}
