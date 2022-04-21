package com.rushkappa;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {

        List<Path> files = null;
        // get images
        Path source = Path.of(args[0]);
        try (Stream<Path> stream = Files.list(source)){
            files = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 1; i <= 10; i++) {
            ForkJoinPool pool = new ForkJoinPool(i);
            long start = System.currentTimeMillis();
            try {
                List<Path> finalFiles = files;
                pool.submit(()->finalFiles.parallelStream()
                        .map(ImageEditor::loadImage)
                        .map(ImageEditor::defaultEdit)
                        .forEach(ImageEditor::saveEdited)).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("for pool size " + i + " : time of processing = " + (System.currentTimeMillis()-start) +"ms");

            pool.shutdown();
        }




    }
}
