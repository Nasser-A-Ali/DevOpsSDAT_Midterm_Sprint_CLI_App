# Music Library CLI App

## Overview
This program features a **Client Side CLI** that interacts with a **[Server Side API](https://github.com/Nasser-A-Ali/DevOpsSDAT_Midterm_Sprint_API)** and a **MySQL** database for data storage. 
The system handles relationships between **songs**, **artists**, and **albums**, enabling users to manage and view their data. The user interacts with the program through a **CLI** (Command Line Interface), 
offering 6 menu options for various tasks. This system is practical and ideal for managing entities within a Music Library.

## Features
- **Songs**, **Artists**, and **Albums** are managed and displayed.
- The client interacts with the server-side API to fetch and update data.
- The relationship between songs, artists, and albums is represented in the data model.

## Menu Options
1. **View All Songs** - List all Songs in the database.
2. **Search For An Artist** - Locate an Artist within the database by name.
3. **View Albums By An Artist** - List all Albums by a specified Artist ID.
4. **Add a Song** - Allows users to add a new song to the system.
5. **Edit Song Details** - Allows users to edit the details of an existing song.
6. **View Tracks in An Album** - Allows users to view all Songs within a specified Album.
7. **Exit Program**

## Relationships
- **Songs** belong to one **Album** and one **Artist**.
- **Albums** can have multiple **Songs**.
- **Artists** can have multiple **Songs** and **Albums**.

## Technologies Used
- **Java**: Main programming language.
- **RESTful API**: For communication between client and server.
- **Maven**: Build automation tool for dependency management, project building, and packaging.
- **Mockito**: Testing framework for mocking objects in unit tests, used for testing API calls and business logic.
- **MySQL**: Relational database for storing songs, artists, and album data, with data interactions handled by the server-side API.

## How It Works
1. The client sends requests to the server-side API to fetch data (Songs, Artists, Albums).
2. Users can add, update or view objects, interacting with the server-side API to fetch and update data in the MySQL database.
3. The system displays the fetched data in a structured format to the user, allowing them to explore relationships.
   The relationship between songs, artists, and albums is represented in the data model stored in the database.


## Set Up The Project: 
To get a local copy up and running, follow these simple steps:
```bash
git clone https://github.com/Nasser-A-Ali/DevOpsSDAT_Midterm_Sprint_CLI_App.git
cd DevOpsSDAT_Midterm_Sprint_CLI_App
```
```
git clone https://github.com/Nasser-A-Ali/DevOpsSDAT_Midterm_Sprint_API.git
cd DevOpsSDAT_Midterm_Sprint_API
```


## Credits

This project, **Library Management System (LMS)**, was collaboratively developed by a dedicated team:

- **[Sara Woodford](https://github.com/sarwoodford)**
- **[Brandon Shea](https://github.com/BJamesShea)** 
- **[Adam Stevenson](https://github.com/Adam-S988)**
- **[Stephen Crocker](https://github.com/SearchingSteve)**
- **[Abdalnasser Ali](https://github.com/Nasser-A-Ali)** 
  


Each author played an essential role in bringing this project to life, contributing unique skills and insights to create a Music Library CLI. 
Thanks goes out to all contributors for their hard work and dedication!
