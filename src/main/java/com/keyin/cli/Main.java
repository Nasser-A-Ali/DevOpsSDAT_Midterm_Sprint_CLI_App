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
            System.out.println("5: Edit song details");
            System.out.println("6: View tracks in an album");
            System.out.println("7: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 7) {
                System.out.println("\nExiting the program. Goodbye!");
                break;
            }

            handleUserChoice(choice);
        }
    }

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                viewAllSongs();
                break;
            case 2:
                searchForArtist();
                break;
            case 3:
                viewAlbumsByArtist();
                break;
            case 4:
                addNewSong();
                break;
            case 5:
                editSongDetails();
                break;
            case 6:
                viewAlbumTracks();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewAllSongs() {
        System.out.println("\nViewing all songs...");
        List<Song> songsFromAPI = restClient.getAllSongs();

        if (songsFromAPI.isEmpty()) {
            System.out.println("No songs available.");
        } else {
            for (Song song : songsFromAPI) {
                String artistName = (song.getArtist() != null) ? song.getArtist().getName() : "Unknown Artist";
                System.out.println(artistName + " - " + song.getTitle());
            }
        }
    }

    private void searchForArtist() {
        System.out.println("\nSearching for an artist...");
        System.out.print("Enter the artist's name: ");
        String artistName = scanner.nextLine();

        Artist artist = restClient.getArtistByName(artistName);

        if (artist != null) {
            System.out.println("Artist Found: " + artist.getName());
        } else {
            System.out.println("Could not find artist. Please try another!");
        }
    }

    private void viewAlbumsByArtist() {
        System.out.println("\nViewing albums by artist...");
        System.out.print("Enter the artist's id: ");
        long artistId = scanner.nextLong();
        scanner.nextLine();

        // First, get the artist to display their name correctly
        Artist artist = restClient.getArtistById(artistId);
        if (artist == null) {
            System.out.println("Artist not found with ID: " + artistId);
            return;
        }

        List<Album> albumsFromAPI = restClient.getAlbumsByArtistId(artistId);
        if (albumsFromAPI.isEmpty()) {
            System.out.println("No albums found for artist: " + artist.getName());
        } else {
            System.out.println("Albums by " + artist.getName() + ":");
            for (Album album : albumsFromAPI) {
                System.out.println(album.getTitle() + ", " + album.getReleaseYear());
            }
        }
    }

    private void addNewSong() {
        System.out.println("\nAdding a new song...");
        System.out.print("Enter song title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter artist ID: ");
        long newArtistId = scanner.nextLong();
        scanner.nextLine();

        Artist artistReference = new Artist();
        artistReference.setId(newArtistId);

        System.out.print("Enter song Genre: ");
        String newGenre = scanner.nextLine();

        System.out.print("Enter song duration (e.g., 4.30 for 4min30sec): ");
        double newDuration = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter song release date (YYYY-MM-DD): ");
        String releaseDate = scanner.nextLine();

        Song newSong = new Song(0, newTitle, artistReference, newGenre, newDuration, releaseDate);
        restClient.addSong(newSong);

        System.out.println("New song added: " + newTitle);
    }

    private void editSongDetails() {
        System.out.println("\nEditing song details...");
        System.out.print("Enter the ID of the song you want to edit: ");
        long songId = scanner.nextLong();
        scanner.nextLine();

        Song songToEdit = restClient.getSongById(songId);
        if (songToEdit == null) {
            System.out.println("No song found with ID: " + songId);
            return;
        }

        System.out.print("Enter new title (or press Enter to keep current: " + songToEdit.getTitle() + "): ");
        String editTitle = scanner.nextLine();
        if (!editTitle.isBlank()) songToEdit.setTitle(editTitle);

        System.out.print("Enter new genre (or press Enter to keep current: " + songToEdit.getGenre() + "): ");
        String editGenre = scanner.nextLine();
        if (!editGenre.isBlank()) songToEdit.setGenre(editGenre);

        System.out.print("Enter new duration (or -1 to keep current: " + songToEdit.getDuration() + "): ");
        double editDuration = scanner.nextDouble();
        scanner.nextLine();
        if (editDuration != -1) songToEdit.setDuration(editDuration);

        System.out.print("Enter new release date (or press Enter to keep current: " + songToEdit.getReleaseDate() + "): ");
        String editReleaseDate = scanner.nextLine();
        if (!editReleaseDate.isBlank()) songToEdit.setReleaseDate(editReleaseDate);

        restClient.updateSong(songId, songToEdit);
        System.out.println("Song details updated successfully!");
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RESTClient restClient = new RESTClient();
        restClient.setServerURL("http://localhost:8080");

        Main app = new Main(scanner, restClient);
        app.run();

        scanner.close();
    }
}