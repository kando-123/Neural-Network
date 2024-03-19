/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

public class Layer
{
    private final List<Neuron> neurons;

    public Layer(int size, double learningRate)
    {
        assert (size > 0);

        neurons = new ArrayList<>(size);
        for (int i = 0; i < size; ++i)
        {
            neurons.add(new Neuron(learningRate));
        }
    }

    public static void joinLayers(Layer prev, Layer next)
    {
        assert (prev != null && next != null);

        for (var tail : prev.neurons)
        {
            for (var head : next.neurons)
            {
                Connection.joinNeurons(tail, head);
            }
        }
    }

    public void assign(List<Double> input)
    {
        assert (input.size() == neurons.size());

        int i = 0;
        for (var neuron : neurons)
        {
            neuron.setValue(input.get(i++));
        }
    }

    public void computeHiddenValues()
    {
        for (var neuron : neurons)
        {
            neuron.computeHiddenValue();
        }
    }
    
    public void computeOutputValues()
    {
        for (var neuron : neurons)
        {
            neuron.computeOutputValue();
        }
    }

    public List<Double> exportValues()
    {
        var result = new ArrayList<Double>(neurons.size());
        for (var neuron : neurons)
        {
            result.add(neuron.getValue());
        }
        return result;
    }

    public double calculateError(List<Double> desiredOutputs)
    {
        double aggregateError = 0.0;
        for (int i = 0; i < desiredOutputs.size(); ++i)
        {
            double partialError = neurons.get(i).getValue() - desiredOutputs.get(i);
            aggregateError += partialError * partialError;
        }
        return aggregateError;
    }

    public void computeOutputGradients(List<Double> desiredOutputs)
    {
        assert (desiredOutputs.size() == neurons.size());
        
        int i = 0;
        for (var neuron : neurons)
        {
            neuron.computeOutputGradient(desiredOutputs.get(i++));
        }
    }

    public void computeHiddenGradients()
    {
        for (var neuron : neurons)
        {
            neuron.computeHiddenGradient();
        }
    }

    public void updateInputs()
    {
        for (var neuron : neurons)
        {
            neuron.updateInputs();
        }
    }

    @Override
    public String toString()
    {
        StringBuilder description = new StringBuilder();
        description.append("Layer[")
                .append(neurons.size())
                .append(" neurons]\n");
        for (var neuron : neurons)
        {
            description.append(neuron.toString());
        }

        return description.toString();
    }
}
