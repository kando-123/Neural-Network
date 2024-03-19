/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

public class Neuron
{
    private double inputValue;
    private double outputValue;
    private final List<Connection> inputConnections;
    private final List<Connection> outputConnections;
    private double gradient;
    private double bias;
    private final double rate;

    private static final Random random = new Random();

    public Neuron(double learningRate)
    {
        assert (learningRate > 0.0 && learningRate < 1.0);
        
        rate = learningRate;
        bias = random.nextDouble(-1.0, +1.0);
        inputConnections = new ArrayList<>();
        outputConnections = new ArrayList<>();
    }

    public void addInputConnection(Connection connection)
    {
        inputConnections.add(connection);
    }

    public void addOutputConnection(Connection connection)
    {
        outputConnections.add(connection);
    }
    
    public void setValue(double newValue)
    {
        inputValue = newValue;
        outputValue = transferFunction(inputValue);
    }
    
    public double getValue()
    {
        return outputValue;
    }

    private static double transferFunction(double x)
    {
        return Math.tanh(x);
    }

    private static double transferDerivative(double x)
    {
        double y = Math.tanh(x);
        return 1.0 - y * y;
    }

    public void computeHiddenValue()
    {
        inputValue = bias;
        for (var connection : inputConnections)
        {
            inputValue += connection.tail.getValue() * connection.weight;
        }
        outputValue = transferFunction(inputValue);
    }
    
    public void computeOutputValue()
    {
        inputValue = bias;
        for (var connection : inputConnections)
        {
            inputValue += connection.tail.getValue() * connection.weight;
        }
        outputValue = inputValue;
    }

    public void computeOutputGradient(double desiredOutput)
    {
        gradient = 2.0 * (outputValue - desiredOutput);
    }

    public void computeHiddenGradient()
    {
        double sum = 0.0;
        for (var connection : outputConnections)
        {
            sum += connection.head.getGradient() * connection.weight;
        }
        gradient = sum * transferDerivative(inputValue);
    }
    
    public double getGradient()
    {
        return gradient;
    }

    public void updateInputs()
    {
        for (var connection : inputConnections)
        {
            connection.weight -= rate * gradient * connection.tail.getValue();
        }
        bias -= rate * gradient;
    }

    @Override
    public String toString()
    {
        StringBuilder description = new StringBuilder();
        description.append("\tNeuron[")
                .append(inputConnections.size())
                .append(" inputs, ")
                .append(outputConnections.size())
                .append(" outputs]\n");
        for (var connection : inputConnections)
        {
            description.append("\t (i) ")
                    .append(connection.toString())
                    .append("\n");

        }
        for (var connection : outputConnections)
        {
            description.append("\t (o) ")
                    .append(connection.toString())
                    .append("\n");
        }

        return description.toString();
    }
}
