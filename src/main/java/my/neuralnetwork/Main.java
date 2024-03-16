/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package my.neuralnetwork;

import java.util.*;

/**
 *
 * @author Kay Jay O'Nail
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Network network = new Network(2, 4, 1);
            System.out.println(network.toString());
            
            network.propagateForward(Arrays.asList(1.0, 1.0));
            var results = network.getResults();
            System.out.println("Results:");
            for (int i = 0; i < results.size(); ++i)
            {
                System.out.println("[%d] %f".formatted(i, results.get(i)));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
