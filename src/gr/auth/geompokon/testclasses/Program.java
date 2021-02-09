package gr.auth.geompokon.testclasses;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.Population;
import gr.auth.geompokon.generification.TypeToIndividualBijection;
import gr.auth.geompokon.interfaces.CrossoverMechanism;
import gr.auth.geompokon.interfaces.FitnessFunction;
import gr.auth.geompokon.interfaces.MutationMechanism;
import gr.auth.geompokon.interfaces.SelectionMechanism;
import gr.auth.geompokon.sampleimplementations.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Program {

    static int MAX_ITERATIONS = 99;
    static TypeToIndividualBijection<?> bijection;
    static FitnessFunction fitnessFunction;
    static SelectionMechanism selectionMechanism;
    static CrossoverMechanism crossoverMechanism;
    static MutationMechanism mutationMechanism;

    public static void main(String[] args) {
        Program.run();
    }

    protected static void run() {
        bijection = new IntegerToBitsBijection();
        fitnessFunction = new DivisibilityFitness(new int[]{ 9, 4, 25 });
        selectionMechanism = new RouletteSelection();
        crossoverMechanism = new UniformCrossover();
        mutationMechanism = new BitStringMutator(0.08);

        Population population = getStartingPopulation();

        for (int i=0; i<MAX_ITERATIONS; i++) {
            population = runIteration(population);
        }

        List<?> result = population.getPopulation().stream().map(bijection::toType).collect(Collectors.toList());

        for (Object i : result) {
            System.out.println(i);
        }
    }

    protected static Population runIteration(Population population) {

        Population selectedPopulation = selectionMechanism.selectForCrossover(population, fitnessFunction);
        Population notSelected = new Population(population.getPopulation());
        notSelected.difference(selectedPopulation);

        Population resultPopulation = new Population();
        // add not selected individuals
        resultPopulation.union(notSelected);

        // preform crossover
        Individual[] individuals = selectedPopulation.getPopulation().toArray(new Individual[0]);
        for (int i=0; i<individuals.length/2; i++) {
            Individual i1 = individuals[2*i];
            Individual i2 = individuals[2*i + 1];

            Individual[] children = crossoverMechanism.crossOver(i1, i2);
            for (Individual child : children) {
                mutationMechanism.mutate(child);
            }
            resultPopulation.addAll(children);
        }

        if (individuals.length % 2 == 1) {
            resultPopulation.add(individuals[individuals.length-1]);
        }

        return resultPopulation;
    }

    protected static Population getStartingPopulation() {
        // 20 to 40 random integers
        Random random = new Random();
        int populationSize = 20 + random.nextInt(21);

        Population result = new Population(new HashSet<>());

        for (int i=0; i<populationSize; i++) {
            result.add(
                    new Individual(
                            Integer.toString(
                                    random.nextInt(), 2)));
        }

        return result;
    }
}
