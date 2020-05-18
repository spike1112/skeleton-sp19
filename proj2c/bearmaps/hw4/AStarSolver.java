package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{

    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private int numExplored;

    private double timeSpent;
    //Record the distance from vertex v to start
    private Map<Vertex, Double> distTo;
    private ExtrinsicMinPQ<Vertex> pq;
    private Map<Vertex, Vertex> edgeTo;


    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        pq = new ArrayHeapMinPQ<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        distTo.put(start, 0.0);
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        solution = new ArrayList<>();
        numExplored = 0;

        while (true) {
            if (sw.elapsedTime() > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solution.clear();
                numExplored = 0;
                solutionWeight = 0;
                break;
            }
            if (pq.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
                solution.clear();
                numExplored = 0;
                solutionWeight = 0;
                break;
            }
            if (pq.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distTo.get(end);
                for (Vertex u = end; !u.equals(start); u = edgeTo.get(u)) {
                    solution.add(u);
                }
                solution.add(start);
                Collections.reverse(solution);
                break;
            }
            Vertex p = pq.removeSmallest();
            numExplored = numExplored + 1;
            for (WeightedEdge<Vertex> v : input.neighbors(p)) {
                relax(input, v, end);
            }

        }
        timeSpent = sw.elapsedTime();
    }

    private void relax(AStarGraph<Vertex> g, WeightedEdge<Vertex> v, Vertex end) {
        Vertex p = v.from(), q = v.to();
        double weight = v.weight();
        if (distTo.get(p) + weight < distTo.getOrDefault(q, Double.MAX_VALUE)) {
            distTo.put(q, distTo.get(p) + weight);
            double newPriority = distTo.get(q) + g.estimatedDistanceToGoal(q, end);
            if (pq.contains(q)) {
                pq.changePriority(q, newPriority);
            } else {
                pq.add(q, newPriority);
            }
            edgeTo.put(q, p);
        }

    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
