package com.keyin.cli;

import com.keyin.models.Song;

import java.sql.SQLOutput;
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
                    System.out.println("\nSearching for songs by artist ID...");
                    int artistIdToSearch = scanner.nextInt();
                    scanner.nextLine();

                    boolean found = false;

                    for (Song song:songs) {
                        if (song.getArtistId() == artistIdToSearch) {
                            System.out.println("Song: " + song.getTitle() + " | Genre: " + song.getGenre() + " | Duration: " + song.getDuration());
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No songs found for this artist.");
                    }

                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine();

                    scanner.nextLine();
                }
                case 3 -> {
                    System.out.println("\nViewing albums by artist...");
                    //Code goes here
                    scanner.nextLine(); //Probably optional
                }
                case 4 -> {
                    System.out.println("\nAdding a new song...");
                    System.out.println("Enter song ID: ");
                    int newId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter song title: ");
                    String newTitle = scanner.nextLine();

                    System.out.println("Enter artist ID: ");
                    int newArtistId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter song Genre: ");
                    String newGenre = scanner.nextLine();

                    System.out.println("Enter song duration (e.g 4min30 seconds as '4.30'): ");
                    double newDuration = scanner.nextDouble();
                    scanner.nextLine();

                    // create new song add to list
                    Song newSong = new Song(newId, newTitle, newArtistId, newGenre, newDuration);
                    songs.add(newSong);

                    System.out.println("New song added: " + newTitle);

                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine();





                    scanner.nextLine();
                }
                case 5 -> {
                    System.out.println("\nEditing song details...");

                    System.out.println("Enter the ID of the song you want to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();

                    // title
                    boolean found = false;
                    for(Song song : songs ) {
                        if (song.getId() == editId) {
                            System.out.println("Enter new title ( or press Enter to keep current title: " + song.getTitle() + "): ");
                            String newTitle = scanner.nextLine();
                            if(!newTitle.isBlank()) {
                                song.setTitle(newTitle);
                            }
                            // Artist ID
                            System.out.println("Enter new artist ID (or -1 to keep current ID: " + song.getArtistId() + "): ");
                            int newArtistId = scanner.nextInt();
                            scanner.nextLine();
                            if (newArtistId != -1) {
                                song.setArtistId(newArtistId);
                            }
                            // Genre
                            System.out.println("Enter new genre (or press Enter to keep current genre: " + song.getGenre() + "): ");
                            String newGenre = scanner.nextLine();
                            if (!newGenre.isBlank()) {
                                song.setGenre(newGenre);
                            }
                            // duration
                            System.out.println("Enter new duration (or -1 to keep current duration: " + song.getDuration() + "): ");
                            double newDuration = scanner.nextDouble();
                            scanner.nextLine();
                            if (newDuration != -1) {
                                song.setDuration(newDuration);
                            }
                            // Updated
                            System.out.println("Song updated!: " + song.getTitle());
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("No song found with IDL " + editId);
                    }

                    System.out.println("\nPress Enter to return to main menu...");
                    scanner.nextLine();
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
