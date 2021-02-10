package gr.auth.geompokon;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


public class Population  {

    protected HashSet<Individual> population;

    public Population(Collection<Individual> population) {
        this.population = new HashSet<>(population);
    }

    public Population() {
        this.population = new HashSet<>();
    }

    public HashSet<Individual> getPopulation() {
        return population;
    }

    public void add(Individual obj) {
        population.add(obj);
    }

    public void addAll(Individual[] individuals) {
        population.addAll(Arrays.asList(individuals));
    }

    public void intersect(Population other) {
        this.population.retainAll(other.getPopulation());
    }

    public void union(Population other) {
        this.population.addAll(other.getPopulation());
    }

    public void difference(Population other) {
        this.population.removeAll(other.getPopulation());
    }
}
