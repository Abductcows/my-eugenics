package gr.auth.geompokon.sampleimplementations.selection;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.Population;
import gr.auth.geompokon.interfaces.FitnessFunction;
import gr.auth.geompokon.interfaces.SelectionMechanism;

import java.util.HashSet;
import java.util.Random;

public class RouletteSelection implements SelectionMechanism {

    protected Random random = new Random();

    @Override
    public Population selectForCrossover(Population population, FitnessFunction fitnessFunction) {

        double sumOfFitnesses = population.getPopulation().stream().mapToDouble(fitnessFunction::getScore).sum();

        HashSet<Individual> resultSet = new HashSet<>();

        for (Individual ind : population.getPopulation()) {
            if (random.nextDouble() < fitnessFunction.getScore(ind) / sumOfFitnesses) {
                resultSet.add(ind);
            }
        }

        return new Population(resultSet);
    }
}
