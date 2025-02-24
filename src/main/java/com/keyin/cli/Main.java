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
            System.out.println("5: Add new artist");
            System.out.println("6: Edit song details");
            System.out.println("7: View tracks in an album");
            System.out.println("8: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

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
                
                    long artistId;
                    while (true) {
                        System.out.print("Enter the artist's ID: ");
                        if (scanner.hasNextLong()) {
                            artistId = scanner.nextLong();
                            scanner.nextLine(); 
                            break; 
                        } else {
                            System.out.println("Invalid input. Please enter a numeric artist ID.");
                            scanner.next(); 
                        }
                    }
                
                    Artist artist = restClient.getArtistById(artistId);
                
                    if (artist != null) {
                        System.out.println("Artist Found: " + artist.getName());
                    } else {
                        System.out.println("Could not find artist. Please try another!");
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.print("Enter the artist's ID: ");
                        if (scanner.hasNextLong()) {
                            artistId = scanner.nextLong();
                            scanner.nextLine(); 
                            break; 
                        } else {
                            System.out.println("Invalid input. Please enter a numeric artist ID.");
                            scanner.next(); 
                        }
                    }
                    artist = restClient.getArtistById(artistId);
                     if (artist == null) {
                        System.out.println("Could not find artist. Please try another!");
                        break;
                    } else {
                        List<Album> albumsFromAPI = restClient.getAlbumsByArtistId(artistId);
                        if (albumsFromAPI.isEmpty()) {
                            System.out.println("No albums by this artist. Please try another!");
                        } else {
                            String artistsName = albumsFromAPI.get(0).getArtist().getName();
                            System.out.println("Albums by " + artistsName + ":");
                            for (Album album : albumsFromAPI) {
                                System.out.println(album.getTitle() + ", " + album.getReleaseYear());
                            }
                        }
                    }
                    break;

                case 4:
                    if (restClient.getAllArtists().isEmpty()) {
                        System.out.println("No artists available. *To have a valid song, an artist must have first created it!*");
                        System.out.println("Would you like to add an artist first? (Y/N)");
                        String addArtist = scanner.nextLine();
                        if (addArtist.equalsIgnoreCase("Y")) {
                            addArtist(scanner, restClient);
                        } else {
                            System.out.println("No artist added. Please add an artist first before adding a song.");
                            break;
                        }
                    }
                    System.out.println("\nAdding a new song...");
                    while (true) {
                        System.out.print("Enter the artist's ID: ");
                        if (scanner.hasNextLong()) {
                            artistId = scanner.nextLong();
                            scanner.nextLine(); 
                            break; 
                        } else {
                            System.out.println("Invalid input. Please enter a numeric artist ID.");
                            scanner.next(); 
                        }
                    }
                    System.out.print("Enter song title: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Enter song Genre: ");
                    String newGenre = scanner.nextLine();

                    double newDuration;
                    while (true) {
                        System.out.print("Enter song duration (e.g., 4.30 for 4min30sec): ");
                        if (scanner.hasNextLong()) {
                            newDuration = scanner.nextDouble();
                            scanner.nextLine(); 
                            break; 
                        } else {
                            System.out.println("Invalid input. Please enter a numeric duration.");
                            scanner.next(); 
                        }
                    }
    

                    System.out.print("Enter song release date (YYYY-MM-DD): ");
                    String releaseDate = scanner.nextLine();

                    Artist artistReference = new Artist();
                    artistReference.setId(artistId); 
                    Song newSong = new Song(0, newTitle, artistReference, newGenre, newDuration, releaseDate);
                    restClient.addSong(newSong);

                    System.out.println("New song added: " + newTitle);
                    break;

                case 5:
                    addArtist(scanner, restClient); 
                    break;

                case 6:
                if (restClient.getAllSongs().isEmpty()) {   
                    System.out.println("No songs available. Please add a song first before editing.");
                    break;
                }
                    System.out.println("\nEditing song details...");
                    long songId;
                    while (true){
                    System.out.print("Enter the ID of the song you want to edit: ");
                    if (scanner.hasNextLong()) {
                        songId = scanner.nextLong();
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a numeric song ID.");
                        scanner.next(); 
                    }
                    }

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

                    double editDuration;
                    while (true){
                        System.out.print("Enter new duration (or -1 to keep current: " + songToEdit.getDuration() + "): ");
                   
                        if (scanner.hasNextDouble()) {
                            editDuration = scanner.nextDouble();
                            scanner.nextLine(); 
                            break; 
                        } else {
                            System.out.println("Invalid input. Please enter a numeric duration.");
                            scanner.next(); 
                        }
                    }
                    
                    if (editDuration != -1) songToEdit.setDuration(editDuration);

                    System.out.print("Enter new release date (or press Enter to keep current: " + songToEdit.getReleaseDate() + "): ");
                    String editReleaseDate = scanner.nextLine();
                    if (!editReleaseDate.isBlank()) songToEdit.setReleaseDate(editReleaseDate);

                    restClient.updateSong(songId, songToEdit);
                    System.out.println("Song details updated successfully!");
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


    private static void addArtist(Scanner scanner, RESTClient restClient) {
        System.out.println("\nAdding a new artist...");
        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        System.out.print("Enter debut year: ");
        int debutYear;
        while(true) {
            if (scanner.hasNextInt()) {
                debutYear = scanner.nextInt();
                scanner.nextLine(); 
                break;
            }
            else{
            System.out.println("Invalid input. Please enter a numeric debut year.");
            scanner.nextLine(); 
            }
        }
      

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter country: ");
        String country = scanner.nextLine();

        Artist newArtist = new Artist(0, name, debutYear, genre, country);
        restClient.addArtist(newArtist);

        System.out.println("New artist added: " + name);
    }
}
