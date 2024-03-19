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
            Network network = new Network(Arrays.asList(2, 4, 4, 1), 0.1);
            
            TrainingSet training = new TrainingSet(2, 1);
            training.add(Arrays.asList(0.0, 0.0), Arrays.asList(0.0));
            training.add(Arrays.asList(0.0, 1.0), Arrays.asList(1.0));
            training.add(Arrays.asList(1.0, 0.0), Arrays.asList(1.0));
            training.add(Arrays.asList(1.0, 1.0), Arrays.asList(0.0));
            
            network.trainSet(training, 10_000);
            var xor00 = network.computeFor(Arrays.asList(0.0, 0.0));
            var xor01 = network.computeFor(Arrays.asList(0.0, 1.0));
            var xor10 = network.computeFor(Arrays.asList(1.0, 0.0));
            var xor11 = network.computeFor(Arrays.asList(1.0, 1.0));
            
            System.out.println("0 xor 0 = %f".formatted(xor00.get(0)));
            System.out.println("0 xor 1 = %f".formatted(xor01.get(0)));
            System.out.println("1 xor 0 = %f".formatted(xor10.get(0)));
            System.out.println("1 xor 1 = %f".formatted(xor11.get(0)));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
