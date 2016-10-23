package all; /**
 * Created by Jakub on 2016-10-10.
 */
import java.util.ArrayList;
import java.util.List;

public class NeuralCell
{
    private List<Double> Dendrites;
    private List<Double> Synapses;


    public NeuralCell()
    {
        Dendrites = new ArrayList<>(100);
        Synapses = new ArrayList<>(100);
    }


    public double finalizeData(double membranePotential)
    {
        return membranePotential;
    }

    public int getInputSize()
    {
        return Dendrites.size();
    }

    public void addInput()
    {
        Dendrites.add(0.0);
        Synapses.add(1.0);
    }

    public void addInput(int count)
    {
        for(int i = 1; i <= count; i++)
        this.addInput();
    }

    public double getInputData(int index)
    {
        return Dendrites.get(index);
    }


    public void setInputData(int index, double value)
    {
        Dendrites.set(index, value);
    }

    public double getInputWeight(int index)
    {
        return Synapses.get(index);
    }


    public void setInputWeight(int index, double weight)
    {
        Synapses.set(index, weight);
    }

    public double processCellNode(int index)
    {
        return (Dendrites.get(index)*Synapses.get(index));
    }

    public double getMembranePotential()
    {
        if(getInputSize() == 0)
            return -1;

        double sum = 0;
        for (int i = 0; i < getInputSize(); i++)
        sum+=processCellNode(i);

        return sum;
    }

    public double getOutput()
    {
        if(getInputSize() == 0)
            return -1;

        return finalizeData(getMembranePotential());
    }
}