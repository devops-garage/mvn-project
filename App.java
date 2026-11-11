package com.devops;

import java.util.LinkedHashMap;
import java.util.Map;

public class App {

    private static final int WIDTH = 64;

    public static void main(String[] args) {
        printBanner("WELCOME TO DEVOPS LEARNING");

        System.out.println();
        System.out.println("DevOps joins the people who build software (Dev)");
        System.out.println("with the people who run it (Ops) in one continuous loop.");

        // Stage -> description and tools (insertion order is the lifecycle order)
        Map<String, String[]> stages = new LinkedHashMap<String, String[]>();
        stages.put("Plan",    new String[]{"Decide what to build and track the tasks.",
                                           "Jira, Trello, Confluence, GitHub Projects"});
        stages.put("Code",    new String[]{"Write code and keep it in version control.",
                                           "Git, GitHub, GitLab, IntelliJ IDEA, VS Code"});
        stages.put("Build",   new String[]{"Compile and package the app automatically.",
                                           "Maven, Gradle, Jenkins, GitHub Actions, Docker"});
        stages.put("Test",    new String[]{"Catch bugs and quality problems early.",
                                           "JUnit, Selenium, SonarQube, Postman"});
        stages.put("Release", new String[]{"Version and store the tested artifact.",
                                           "Nexus, JFrog Artifactory, Docker Hub, Argo CD"});
        stages.put("Deploy",  new String[]{"Ship the release with repeatable scripts.",
                                           "Kubernetes, Ansible, Terraform, Helm"});
        stages.put("Operate", new String[]{"Run, scale and configure the system.",
                                           "Kubernetes, AWS, Azure, Linux, Nginx"});
        stages.put("Monitor", new String[]{"Watch metrics, logs and alerts, then feed back to Plan.",
                                           "Prometheus, Grafana, ELK Stack, Datadog"});

        printLoop(stages);
        printStages(stages);

        System.out.println();
        printLine('=');
        System.out.println("Every alert found in Monitor goes back into Plan.");
        System.out.println("That is what makes DevOps a loop, not a line.");
        printLine('=');
    }

    private static void printLoop(Map<String, String[]> stages) {
        System.out.println();
        printHeading("THE DEVOPS LIFECYCLE");

        StringBuilder loop = new StringBuilder();
        int i = 0;
        for (String name : stages.keySet()) {
            if (i > 0) {
                loop.append(" -> ");
            }
            if (i == 4) {
                loop.append("\n  ");
            }
            loop.append(name);
            i++;
        }
        System.out.println("  " + loop + " -> (back to Plan)");
        System.out.println("  [ Dev: Plan to Test ]   [ Ops: Release to Monitor ]");
    }

    private static void printStages(Map<String, String[]> stages) {
        System.out.println();
        printHeading("STAGES AND TOOLS");

        int number = 1;
        for (Map.Entry<String, String[]> entry : stages.entrySet()) {
            String side = number <= 4 ? "Dev" : "Ops";
            System.out.println();
            System.out.println(number + ". " + entry.getKey() + "  (" + side + ")");
            System.out.println("   What : " + entry.getValue()[0]);
            System.out.println("   Tools: " + entry.getValue()[1]);
            number++;
        }
    }

    private static void printBanner(String title) {
        printLine('=');
        int padding = (WIDTH - title.length()) / 2;
        System.out.println(repeat(' ', padding) + title);
        printLine('=');
    }

    private static void printHeading(String title) {
        System.out.println(title);
        printLine('-');
    }

    private static void printLine(char c) {
        System.out.println(repeat(c, WIDTH));
    }

    // String.repeat needs Java 11, so this works on older Maven setups too.
    private static String repeat(char c, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
