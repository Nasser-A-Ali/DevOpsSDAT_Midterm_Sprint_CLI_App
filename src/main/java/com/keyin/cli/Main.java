package com.keyin.cli;

import com.keyin.client.RESTClient;
import com.keyin.models.Album;
import com.keyin.models.Artist;
import com.keyin.models.Song;

import java.util.*;

public class Main {
    private final Scanner scanner;
    private final RESTClient restClient;

    public Main(Scanner scanner, RESTClient restClient) {
        this.scanner = scanner;
        this.restClient = restClient;
    }

    public void run() {
        while (true) {
            System.out.println("\n🎵 Welcome to the Music Library CLI 🎵");
            System.out.println("1: View all songs");
            System.out.println("2: Search for an artist");
            System.out.println("3: View albums by an artist");
            System.out.println("4: Add new song");
            System.out.println("5: Add new artist");
            System.out.println("6: Edit song details");
            System.out.println("7: View tracks in an album");
            System.out.println("8: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 8) {
                System.out.println("\nExiting the program. Goodbye!");
                break;
            }

            handleUserChoice(choice);
        }
    }

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> viewAllSongs();
            case 2 -> searchForArtist();
            case 3 -> viewAlbumsByArtist();
            case 4 -> addNewSong();
            case 5 -> addArtist();
            case 6 -> editSongDetails();
            case 7 -> viewAlbumTracks();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewAllSongs() {
        System.out.println("\nViewing all songs...");
        List<Song> songsFromAPI = restClient.getAllSongs();

        if (songsFromAPI.isEmpty()) {
            System.out.println("No songs available.");
        } else {
            songsFromAPI.forEach(song -> {
                String artistName = (song.getArtist() != null) ? song.getArtist().getName() : "Unknown Artist";
                System.out.println(artistName + " - " + song.getTitle());
            });
        }
    }

    private void searchForArtist() {
        System.out.println("\nSearching for an artist...");
        System.out.print("Enter the artist's ID: ");
        long artistId = scanner.nextLong();
        scanner.nextLine();

        Artist artist = restClient.getArtistById(artistId);
        System.out.println((artist != null) ? "Artist Found: " + artist.getName() : "Could not find artist. Please try another!");
    }

    private void viewAlbumsByArtist() {
        System.out.println("\nViewing albums by artist...");
        System.out.print("Enter the artist's ID: ");
        long artistId = scanner.nextLong();
        scanner.nextLine();

        Artist artist = restClient.getArtistById(artistId);
        if (artist == null) {
            System.out.println("Artist not found with ID: " + artistId);
            return;
        }

        List<Album> albums = restClient.getAlbumsByArtistId(artistId);
        if (albums.isEmpty()) {
            System.out.println("No albums found for artist: " + artist.getName());
        } else {
            System.out.println("Albums by " + artist.getName() + ":");
            albums.forEach(album -> System.out.println(album.getTitle() + ", " + album.getReleaseYear()));
        }
    }

    private void addNewSong() {
        System.out.println("\nAdding a new song...");
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();

        System.out.print("Enter artist ID: ");
        long artistId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter duration: ");
        double duration = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter release date (YYYY-MM-DD): ");
        String releaseDate = scanner.nextLine();

        Artist artist = new Artist();
        artist.setId(artistId);
        restClient.addSong(new Song(0, title, artist, genre, duration, releaseDate));

        System.out.println("New song added: " + title);
    }

    private void addArtist() {
        System.out.println("\nAdding a new artist...");
        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        System.out.print("Enter debut year: ");
        int debutYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter country: ");
        String country = scanner.nextLine();

        restClient.addArtist(new Artist(0, name, debutYear, genre, country));
        System.out.println("New artist added: " + name);
    }

    private void viewAlbumTracks() {
        System.out.println("\nViewing songs in an album...");
        System.out.print("Enter album title: ");
        String albumTitle = scanner.nextLine();
        boolean albumFound = false;

        List<Album> albumList = restClient.getAllAlbums();
        for (Album album : albumList) {
            if (album.getTitle().equalsIgnoreCase(albumTitle)) {
                System.out.println("Tracks in " + albumTitle + ":");
                for (Song track : album.getListOfSongs()) {
                    System.out.println(" - " + track);
                }
                albumFound = true;
                break;
            }
        }
        if (!albumFound) {
            System.out.println("Album not found. Please try another!");
        }
    }

    private void editSongDetails() {
        System.out.println("\nEditing song details...");
        System.out.print("Enter the song ID: ");
        long songId = scanner.nextLong();
        scanner.nextLine();

        Song song = restClient.getSongById(songId);
        if (song == null) {
            System.out.println("No song found with ID: " + songId);
            return;
        }

        System.out.print("Enter new title (or press Enter to keep current): ");
        String title = scanner.nextLine();
        if (!title.isBlank()) song.setTitle(title);

        System.out.print("Enter new genre (or press Enter to keep current): ");
        String genre = scanner.nextLine();
        if (!genre.isBlank()) song.setGenre(genre);

        System.out.print("Enter new duration (or -1 to keep current): ");
        double duration = scanner.nextDouble();
        scanner.nextLine();
        if (duration != -1) song.setDuration(duration);

        System.out.print("Enter new release date (or press Enter to keep current): ");
        String releaseDate = scanner.nextLine();
        if (!releaseDate.isBlank()) song.setReleaseDate(releaseDate);

        restClient.updateSong(songId, song);
        System.out.println("Song details updated successfully!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RESTClient restClient = new RESTClient();
        restClient.setServerURL("http://localhost:8080");
        new Main(scanner, restClient).run();
        scanner.close();
    }
}
