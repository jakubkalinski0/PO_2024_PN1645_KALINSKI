package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The SimulationEngine class manages and runs multiple simulations either synchronously or asynchronously.
 */
public class SimulationEngine {
    private static final Logger logger = Logger.getLogger(SimulationEngine.class.getName());
    private final List<Simulation> simulations;
    private final List<Thread> threads;
    private ExecutorService threadPool;

    /**
     * Constructs a SimulationEngine with a given list of simulations.
     *
     * @param       simulations a list of simulations to be managed
     */
    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.threads = new ArrayList<>();
        this.threadPool = Executors.newFixedThreadPool(4);
    }

    /**
     * Runs all simulations synchronously in the current thread.
     */
    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    /**
     * Runs all simulations asynchronously using separate threads.
     */
    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
        awaitSimulationsEnd();
    }

    /**
     * Runs all simulations asynchronously using a thread pool.
     */
    public void runAsyncInThreadPool() {
        if (threadPool != null) {
            threadPool.shutdown();
        }
        threadPool = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulations) {
            threadPool.submit(simulation);
        }
    }

    /**
     * Waits for all simulations to complete execution and properly shuts down resources.
     */
    public void awaitSimulationsEnd() {
        try {
            for (Thread thread : threads) {
                thread.join();
            }
            threadPool.shutdown();
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Simulation execution interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
