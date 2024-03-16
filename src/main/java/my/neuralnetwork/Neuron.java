/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

/**
 * Neuron. It holds a value and it stores backward and forward connections.
 * 
 * @author Kay Jay O'Nail
 */
public class Neuron
{
    /**
     * The current value of this neuron.
     * 
     * It is public to make the access quicker. Besides, it would need both
     * setter and getter.
     */
    public double value;
    
    /**
     * List of input connections.
     */
    private final List<Connection> inputConnections;
    
    /**
     * List of output connections.
     */
    private final List<Connection> outputConnections;
    
    
    private double gradient;
    
    /**
     * Constructor.
     */
    public Neuron()
    {
        value = 1.0;
        inputConnections = new ArrayList<>();
        outputConnections = new ArrayList<>();
    }
    
    /**
     * Adds the connection to the list of input connections. Invoked only from
     * the <code>Connection.joinNodes</code> method. (Impossible to be invoked
     * elsewhere, since the connection cannot be created outside that method.)
     * 
     * @param connection 
     */
    public void addInputConnection(Connection connection)
    {
        inputConnections.add(connection);
    }
    
    /**
     * Adds the connection to the list of output connections. Invoked only from
     * the <code>Connection.joinNodes</code> method. (Impossible to be invoked
     * elsewhere, since the connection cannot be created outside that method.)
     * 
     * @param connection 
     */
    public void addOutputConnection(Connection connection)
    {
        outputConnections.add(connection);
    }
    
    /**
     * Transfer function.
     * 
     * @param x
     * @return hyperbolic tangent of x
     */
    private static double transferFunction(double x)
    {
        return Math.tanh(x);
    }
    
    /**
     * Transfer function derivative.
     * 
     * @param x
     * @return derivative of hyperbolic tangent of x
     */
    private static double transferDerivative(double x)
    {
        double y = Math.tanh(x);
        return 1.0 - y * y;
    }
    
    /**
     * Computes <code>value</code> as transfer function of the sum of the values
     * of the connections, i.e. the preceding neurons' <code>value</code>s times
     * the <code>weight</code>s of the conenctions.
     */
    public void computeValue()
    {
        double sum = 0.0;
        for (var connection : inputConnections)
        {
            sum += connection.getValue();
        }
        value = transferFunction(sum);
    }
    
    public void computeOutputGradient(double desiredOutput)
    {
        
    }
    
    public void computeHiddenGradient()
    {
        
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
