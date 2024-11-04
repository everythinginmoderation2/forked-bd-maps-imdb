package com.amazon.ata.maps;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a movie in IMDB. Each movie's name must be unique.
 */
public class Movie {
    private final String name;
    private final String director;
    private final LocalDate yearReleased;
//    private final Set<Actor> cast = new HashSet<>();

    /**
     * Constructs a new movie with the given parameters.
     *
     * @param name Movie's name. Must be unique
     * @param director Movie's director. May be null
     * @param yearReleased Movie's release year. May be null
     */
    public Movie(String name, String director, LocalDate yearReleased) {
        this.name = name;
        this.director = director;
        this.yearReleased = yearReleased;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getYearReleased() {
        return yearReleased;
    }

//    public Set<Actor> getCast() {
//        return cast;
//    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Director: " + director + "\n" + "Release date: " + yearReleased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
