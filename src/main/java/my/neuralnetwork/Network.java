/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

public class Network
{
    private final List<Layer> layers;
    private final Layer inputLayer;
    private final Layer outputLayer;
    private final int inputSize;
    private final int outputSize;

    public Network(List<Integer> topology, double learningRate)
    {
        assert (topology.size() > 1);

        layers = new ArrayList<>(topology.size());
        for (var size : topology)
        {
            assert (size > 0);

            layers.add(new Layer(size, learningRate));
        }
        inputLayer = layers.get(0);
        outputLayer = layers.get(layers.size() - 1);
        inputSize = topology.get(0);
        outputSize = topology.get(topology.size() - 1);
        for (int i = 1; i < layers.size(); ++i)
        {
            Layer previous = layers.get(i - 1);
            Layer current = layers.get(i);
            Layer.joinLayers(previous, current);
        }
    }

    private void propagateForward(List<Double> input)
    {
        assert (input.size() == inputSize);
        
        inputLayer.assign(input);
        for (int i = 1; i < layers.size() - 1; ++i)
        {
            layers.get(i).computeHiddenValues();
        }
        outputLayer.computeOutputValues();
    }

    private void propagateBackward(List<Double> desiredOutputs)
    {
        assert (desiredOutputs.size() == outputSize);
        
        outputLayer.computeOutputGradients(desiredOutputs);
        for (int i = layers.size() - 2; i > 0; --i)
        {
            layers.get(i).computeHiddenGradients();
        }
        
        for (int i = layers.size() - 1; i > 0; --i)
        {
            layers.get(i).updateInputs();
        }
    }
    
    public List<Double> computeFor(List<Double> input) throws Exception
    {
        if (input.size() == inputSize)
        {
            propagateForward(input);
            return outputLayer.exportValues();
        }
        else
        {
            throw new Exception("Network.computeFor : incompatible vectors");
        }
    }
        
    private void train(SingleTraining training) throws Exception
    {
        propagateForward(training.getInputs());
        propagateBackward(training.getOutputs());
    }
    
    public void trainSet(TrainingSet set, int epochsCount) throws Exception
    {
        if (set.getInputSize() == inputSize && set.getOutputSize() == outputSize)
        {
            int count = 0;
            while (count < epochsCount)
            {
                if (set.epochHasPassed())
                {
                    ++count;
                }
                train(set.getNext());
            }
        }
        else
        {
            throw new Exception("Network.trainSet : incompatible sizes");
        }
    }

    @Override
    public String toString()
    {
        StringBuilder description = new StringBuilder();
        description.append("Network[")
                .append(layers.size())
                .append(" layers]\n");
        for (var layer : layers)
        {
            description.append(layer.toString());
        }

        return description.toString();
    }
}
