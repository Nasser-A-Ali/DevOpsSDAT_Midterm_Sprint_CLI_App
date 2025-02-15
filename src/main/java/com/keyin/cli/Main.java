package com.keyin.cli;

import com.keyin.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // Just some test songs
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Bohemian Rhapsody", 101, "Rock", 5.55));
        songs.add(new Song(2, "Billie Jean", 102, "Pop", 4.54));
        songs.add(new Song(3, "Imagine", 103, "Rock", 3.12));

        while (true) {
            //These options were just some filler options. They don't need to be the final result
            System.out.println("\n\uD83C\uDFB5Welcome to the Music Library CLI\uD83C\uDFB5");
            System.out.println("");
            System.out.println("Select from one of the following options:");
            System.out.println("1: View all songs");
            System.out.println("2: Search for an artist");
            System.out.println("3: View albums by an artist");
            System.out.println("4: Add new song");
            System.out.println("5: Edit song details");
            System.out.println("6: Delete a song");
            System.out.println("7: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nViewing all songs...");
                    if (songs.isEmpty()) {
                        System.out.println("No songs available.");
                    } else {
                        for (Song song : songs) {
                            System.out.println(song.getArtistId() + " - " + song.getTitle());
                        }
                    }
                    //Code goes here
                    scanner.nextLine(); //Probably *not* optional
                }
                case 2 -> {
                    System.out.println("\nSearching for an artist...");
                    //Code goes here
                    scanner.nextLine(); //Probably optional
                }
                case 3 -> {
                    System.out.println("\nViewing albums by artist...");
                    //Code goes here
                    scanner.nextLine(); //Probably optional
                }
                case 4 -> {
                    System.out.println("\nAdding a new song...");
                    //Code goes here
                    scanner.nextLine(); //Probably optional
                }
                case 5 -> {
                    System.out.println("\nEditing song details...");
                    //Code goes here
                    scanner.nextLine(); //Probably optional
                }
                case 6 -> {
                    System.out.println("\nDeleting a song...");
                    //Code goes here
                    scanner.nextLine(); //Probably optional
                }
                case 7 -> {
                    System.out.println("\nExiting the program. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\nPress Enter to return to the main menu...");
            scanner.nextLine(); //Probably *not* optional
        }
    }
}
