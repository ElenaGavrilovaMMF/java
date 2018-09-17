package main;

import entity.Ship;
import entity.Store;
import parser.FileParser;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A class that mimics the operation of the port.
 */
public final class ShipApp {

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private ShipApp() {
    }

    /**
     * A method in which information from a file is read.
     * Creates and starts streams-ships.
     *
     * @param args {@code String[]}
     */
    public static void main(final String[] args) {
        final List<String> listData = FileUtil.readFile("file/characteristic");
        final List<Ship> ships = FileParser.parseDataList(listData);

        final ArrayList<Future<String>> listFuture
                = new ArrayList<Future<String>>();
        final ExecutorService executorService = Executors
                .newFixedThreadPool(2);
        System.out.println(Store.getInstance().toString());
        for (final Ship ship : ships) {
            listFuture.add(executorService.submit(ship));
        }
        executorService.shutdown();
        for (final Future<String> future : listFuture) {
            try {
                System.out.println(future.get() + " result fixed");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
