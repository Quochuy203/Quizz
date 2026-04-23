package com.example.quizz;

import com.example.quizz.model.GameState;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.*;

public class SaveService {

    private static final String SAVE_PATH = "save.json";
    private static final Gson gson = new Gson();

    public static void save(GameState state) {
        try (Writer writer = new FileWriter(SAVE_PATH)) {
            gson.toJson(state, writer);
        } catch (IOException e) {
            System.err.println("Erreur sauvegarde: " + e.getMessage());
        }
    }

    public static GameState load() {
        try (Reader reader = new FileReader(SAVE_PATH)) {
            return gson.fromJson(reader, GameState.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean hasSave() {
        return Files.exists(Paths.get(SAVE_PATH));
    }

    public static void deleteSave() {
        try {
            Files.deleteIfExists(Paths.get(SAVE_PATH));
        } catch (IOException e) {
            System.err.println("Erreur suppression save: " + e.getMessage());
        }
    }
}