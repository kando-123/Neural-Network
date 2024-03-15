/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

/**
 *
 * @author Kay Jay O'Nail
 */
public class Neuron
{
    private double value;
    
    private final List<Connection> inputConnections;
    private final List<Connection> outputConnections;
    
    public Neuron()
    {
        value = 1.0;
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
}
