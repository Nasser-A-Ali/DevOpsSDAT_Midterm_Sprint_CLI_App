package com.keyin.cli;

import com.keyin.client.RESTClient;
import com.keyin.models.Album;
import com.keyin.models.Artist;
import com.keyin.models.Song;

import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RESTClient restClient = new RESTClient();
        restClient.setServerURL("http://localhost:8080");


        List<Song> album1Songs = List.of(new Song(1, "Hey Jude", 101, "Rock", 4.50, 1968),
                new Song(2, "Let It Be", 101, "Rock", 3.50, 1970));
        List<Song> album2Songs = List.of(new Song(3, "Bad Guy", 102, "Pop", 4.10, 2019),
                new Song(4, "When The Party's Over", 102, "Pop", 3.30, 2018));

        Artist artist1 = new Artist(1, "The Beatles", 1960, "Rock", "UK", null, null);
        Artist artist2 = new Artist(2, "Billie Eilish", 2015, "Pop", "USA", null, null);

        List<Album> albums = new ArrayList<>();

        albums.add(new Album(1, "Abbey Road", artist1, 1969, 17, album1Songs, "Rock"));
        albums.add(new Album(2, "Happier Than Ever", artist2, 2021, 16, album2Songs, "Pop"));

        Album album1 = new Album(1, "Abbey Road", artist1, 1969, 17, album1Songs, "Rock");
        Album album2 = new Album(2, "Happier Than Ever", artist2, 2021, 16, album2Songs, "Pop");

        artist1.setAlbum(album1);
        artist2.setAlbum(album2);

        Map<Integer, Artist> artistMap = new HashMap<>();
        artistMap.put(artist1.getId(), artist1);
        artistMap.put(artist2.getId(), artist2);
        

        while (true) {
            // These options were just some filler options. They don't need to be the final
            // result
            System.out.println("\n\uD83C\uDFB5Welcome to the Music Library CLI\uD83C\uDFB5");
            System.out.println("");
            System.out.println("Select from one of the following options:");
            System.out.println("1: View all songs");
            System.out.println("2: Search for an artist");
            System.out.println("3: View albums by an artist");
            System.out.println("4: Add new song");
            System.out.println("5: Edit song details");
            System.out.println("6: Delete a song");
            System.out.println("7. View tracks in an album");
            System.out.println("8: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nViewing all songs...");
                    List<Song> songsFromAPI = restClient.getAllSongs();

                    if (songsFromAPI.isEmpty()) {
                        System.out.println("No songs available.");
                    } else {
                        for (Song song : songsFromAPI) {

                            Artist artist = artistMap.get(song.getArtistId());
                            String artistName = (artist != null) ? artist.getName() : "Unknown Artist";

                            System.out.println(artistName + " - " + song.getTitle());
                        }
                    }
                    scanner.nextLine();
                }
                case 2 -> {

                    System.out.println("\nSearching for an artist...");
                    scanner.nextLine();
                    System.out.println("Enter the artist's name: ");
                    String artistName = scanner.nextLine();
                    boolean artistFound = false;

                    for (Artist artist : List.of(artist1, artist2))
                        if (artist.getName().equalsIgnoreCase(artistName)) {
                            System.out.println("Artist: " + artist);
                            artistFound = true;
                            break;
                        }

                    if (!artistFound) {
                        System.out.println("Could not find artist. Please try another!");
                    }

                    scanner.nextLine(); // Probably optional

                }
                case 3 -> {
                    System.out.println("\nViewing albums by artist...");
                    scanner.nextLine();
                    System.out.println("Enter the artist's name: ");
                    String artistName = scanner.nextLine();
                    boolean albumsFound = false;


                    List<Album> albumsFromAPI = restClient.getAllAlbums();

                    for (Album album : albumsFromAPI) {
                        if (album.getArtist().getName().equalsIgnoreCase(artistName)) {
                            System.out.println("Album by " + artistName + ": " + album.getTitle() + ", " + album.getReleaseYear());
                            albumsFound = true;
                        }
                    }

                    if (!albumsFound) {
                        System.out.println("No albums found for this artist. Please try another!");
                    }

                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
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

                    System.out.println("Enter song release year: ");
                    int newReleaseYear = scanner.nextInt();
                    scanner.nextLine();


                    Song newSong = new Song(newId, newTitle, newArtistId, newGenre, newDuration, newReleaseYear);
                    restClient.addSong(newSong);


                    System.out.println("New song added: " + newTitle);

                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine();

                    scanner.nextLine(); // Probably optional
                }

                case 5 -> {
                    System.out.println("\nEditing song details...");

                    System.out.println("Enter the ID of the song you want to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();

                    List<Song> songsFromAPI = restClient.getAllSongs();
                    Song songToEdit = null;

                    for (Song song : songsFromAPI) {
                        if (song.getId() == editId) {
                            songToEdit = song;
                            break;
                        }
                    }

                    if (songToEdit == null) {
                        System.out.println("No song found with ID: " + editId);
                        return;
                    }

                    System.out.println("Enter new title (or press Enter to keep current: " + songToEdit.getTitle() + "): ");
                    String newTitle = scanner.nextLine();
                    if (!newTitle.isBlank()) {
                        songToEdit.setTitle(newTitle);
                    }

                    System.out.println("Enter new artist ID (or -1 to keep current: " + songToEdit.getArtistId() + "): ");
                    int newArtistId = scanner.nextInt();
                    scanner.nextLine();
                    if (newArtistId != -1) {
                        songToEdit.setArtistId(newArtistId);
                    }

                    System.out.println("Enter new genre (or press Enter to keep current: " + songToEdit.getGenre() + "): ");
                    String newGenre = scanner.nextLine();
                    if (!newGenre.isBlank()) {
                        songToEdit.setGenre(newGenre);
                    }

                    System.out.println("Enter new duration (or -1 to keep current: " + songToEdit.getDuration() + "): ");
                    double newDuration = scanner.nextDouble();
                    scanner.nextLine();
                    if (newDuration != -1) {
                        songToEdit.setDuration(newDuration);
                    }

                    System.out.println("Enter new release year (or -1 to keep current: " + songToEdit.getReleaseYear() + "): ");
                    int newReleaseYear = scanner.nextInt();
                    scanner.nextLine();
                    if (newReleaseYear != -1) {
                        songToEdit.setReleaseYear(newReleaseYear);
                    }

                    restClient.updateSong(editId, songToEdit);

                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine();
                }

                case 6 -> {
                    System.out.println("\nDeleting a song...");

                    System.out.println("Enter the ID of the song to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    boolean removed = songs.removeIf(song -> song.getId() == deleteId);

                    if (removed) {
                        System.out.println("Song with ID: " + deleteId + " removed!");
                    } else {
                        System.out.println("No song with ID: " + deleteId + " found.");
                    }

                    System.out.println("\nPress Enter to return to the main menu...");

                    scanner.nextLine();
                }
                case 7 -> {
                    System.out.println("\nViewing songs in an album...");
                    scanner.nextLine();
                    System.out.println("Enter album title: ");
                    String albumTitle = scanner.nextLine();
                    boolean albumFound = false;

                    for (Album album : albums) {
                        if (album.getTitle().equalsIgnoreCase(albumTitle)) {
                            System.out.println("Tracks in " + albumTitle + ": ");
                            for (Song song : album.getListOfSongs()) {
                                System.out.println(" - " + song);
                            }
                            albumFound = true;
                            break;
                        }
                        if (!albumFound) {
                            System.out.println("Album with this title not found. Please try another!");
                        }
                    }
                    scanner.nextLine(); // Probably optional
                }
                case 8 -> {
                    System.out.println("\nExiting the program. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\nPress Enter to return to the main menu...");
            scanner.nextLine(); // Probably *not* optional
        }
    }
}
