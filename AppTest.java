package com.devops;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream captured;

    @BeforeEach
    void captureOutput() {
        captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(originalOut);
    }

    private String runApp() {
        App.main(new String[]{});
        return captured.toString();
    }

    @Test
    void mainRunsWithoutErrors() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    @Test
    void printsWelcomeBanner() {
        assertTrue(runApp().contains("WELCOME TO DEVOPS LEARNING"));
    }

    @Test
    void printsAllEightLifecycleStages() {
        String output = runApp();
        assertAll(
            () -> assertTrue(output.contains("1. Plan")),
            () -> assertTrue(output.contains("2. Code")),
            () -> assertTrue(output.contains("3. Build")),
            () -> assertTrue(output.contains("4. Test")),
            () -> assertTrue(output.contains("5. Release")),
            () -> assertTrue(output.contains("6. Deploy")),
            () -> assertTrue(output.contains("7. Operate")),
            () -> assertTrue(output.contains("8. Monitor"))
        );
    }

    @Test
    void printsKeyToolsForEachSide() {
        String output = runApp();
        assertAll(
            () -> assertTrue(output.contains("Maven")),
            () -> assertTrue(output.contains("Docker")),
            () -> assertTrue(output.contains("Kubernetes")),
            () -> assertTrue(output.contains("Prometheus"))
        );
    }

    @Test
    void labelsDevAndOpsSides() {
        String output = runApp();
        assertAll(
            () -> assertTrue(output.contains("1. Plan  (Dev)")),
            () -> assertTrue(output.contains("5. Release  (Ops)"))
        );
    }

    @Test
    void explainsTheLoopBackToPlan() {
        assertTrue(runApp().contains("Every alert found in Monitor goes back into Plan."));
    }
}
