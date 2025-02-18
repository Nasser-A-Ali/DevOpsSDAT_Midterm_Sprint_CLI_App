package com.keyin.cli;

import com.keyin.client.RESTClient;
import com.keyin.models.Album;
import com.keyin.models.Artist;
import com.keyin.models.Song;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RESTClient restClient = new RESTClient();
        restClient.setServerURL("http://localhost:8080");

        while (true) {
            System.out.println("\n🎵 Welcome to the Music Library CLI 🎵");
            System.out.println("1: View all songs");
            System.out.println("2: Search for an artist");
            System.out.println("3: View albums by an artist");
            System.out.println("4: Add new song");
            System.out.println("5: Edit song details");
            System.out.println("6: Delete a song");
            System.out.println("7: View tracks in an album");
            System.out.println("8: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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
                    break;

                case 2:
                    System.out.println("\nSearching for an artist...");
                    System.out.print("Enter the artist's name: ");
                    String artistName = scanner.nextLine();

                    Artist artist = restClient.getArtistByName(artistName);

                    if (artist != null) {
                        System.out.println("Artist Found: " + artist.getName());
                    } else {
                        System.out.println("Could not find artist. Please try another!");
                    }
                    break;



                case 3:
                    System.out.println("\nViewing albums by artist...");
                    System.out.print("Enter the artist's id: ");
                    long artistId = scanner.nextLong();
                    scanner.nextLine();

                    List<Album> albumsFromAPI = restClient.getAlbumsByArtistId(artistId);
                    if (albumsFromAPI.isEmpty()) {
                        System.out.println("No albums by this artist, Please try another!");
                    } else{
                        String artistsName = albumsFromAPI.get(0).getArtist().getName();
                        System.out.println("Albums by " + artistsName + ":");
                        for (Album album : albumsFromAPI){
                            System.out.println(album.getTitle() + ", "  + album.getReleaseYear());
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nAdding a new song...");
                    System.out.println("Enter song title: ");
                    String newTitle = scanner.nextLine();

                    System.out.println("Enter artist ID: ");
                    long newArtistId = scanner.nextLong();
                    scanner.nextLine();

                    Artist newArtist = restClient.getArtistById(newArtistId);
                    if (newArtist == null) {
                        System.out.println("Invalid artist ID. Please enter an existing artist.");
                        break;
                    }

                    System.out.println("Enter song Genre: ");
                    String newGenre = scanner.nextLine();

                    System.out.println("Enter song duration (e.g., 4.30 for 4min30sec): ");
                    double newDuration = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Enter song release date (YYYY-MM-DD): ");
                    String releaseDate = scanner.nextLine();

                    Song newSong = new Song(0, newTitle, newArtist, newGenre, newDuration, releaseDate);
                    restClient.addSong(newSong);

                    System.out.println("New song added: " + newTitle);

                    break;

                case 5:
                    System.out.println("\nEditing song details...");
                    System.out.print("Enter the ID of the song you want to edit: ");
                    long songId = scanner.nextLong();
                    scanner.nextLine();

                    Song songToEdit = restClient.getSongById(songId);
                    if (songToEdit == null) {
                        System.out.println("No song found with ID: " + songId);
                        break;
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
                    break;

                case 6:
                    System.out.println("\nDeleting a song...");
                    System.out.print("Enter the ID of the song to delete: ");
                    long deleteId = scanner.nextLong();
                    scanner.nextLine();

                    Song song = restClient.getSongById(deleteId);
                    if (song == null) {
                        System.out.println("No song found with ID: " + deleteId);
                        break;
                    }

                    System.out.print("Are you sure you want to delete this song? (yes/no): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();

                    if (confirmation.equals("yes")) {
                        restClient.deleteSong(deleteId);
                        System.out.println("Song with ID " + deleteId + " deleted!");
                    } else {
                        System.out.println("Deletion canceled.");
                    }
                    break;

                case 7:
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
                    break;

                case 8:
                    System.out.println("\nExiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
