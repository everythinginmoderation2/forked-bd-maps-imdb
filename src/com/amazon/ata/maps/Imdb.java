package com.amazon.ata.maps;

import java.util.*;

/**
 * Stores the relationships between movies and actors, allowing releasing a new movie
 * with all actors in the cast, adding a single actor to an existing (or new) movie,
 * unreleasing a movie completely, and querying actors by movie and vice versa.
 */
public class Imdb {
    Map<Actor, HashSet<Movie>> actorToMovieMap = new HashMap<>();
    Map<Movie, HashSet<Actor>> movieToActorMap = new HashMap<>();
    /**
     * Adds the new movie to the set of movies that an actor has appeared in.
     * If the movie already exists in the database, this will overwrite actors
     * associated with the movie with the new values provided.
     *
     * @param movie the movie being released
     * @param actors a set of actors that appear in the movie
     */
    public void releaseMovie(Movie movie, Set<Actor> actors) {
        if (movie != null && actors != null) {
            HashSet<Actor> actorsHashSet = new HashSet<>(actors);
            //Sets roster of actors to appropriate movie
            movieToActorMap.put(movie, actorsHashSet);

            //Adds new movie to set of movies that an actor has appeared in
            actors.forEach(thisActor -> {
                HashSet<Movie> moviesHashSet = actorToMovieMap.get(thisActor);
                if(moviesHashSet != null) moviesHashSet.add(movie);
                else {
                    moviesHashSet = new HashSet<>();
                    moviesHashSet.add(movie);
                }
               actorToMovieMap.put(thisActor, moviesHashSet);
            });


        }
    }

    /**
     * Removes the given movie from the database, including any actors
     * credited in the movie.
     *
     * @param movie the movie to remove
     * @return true if the movie was removed, false if it wasn't in Imdb
     *         to begin with
     */
    public boolean removeMovie(Movie movie) {
        return movieToActorMap.remove(movie, movieToActorMap.get(movie));
    }

    /**
     * Adds a new movie to the set of movies that an actor has appeared in.
     * If the movie already exists in the database, will add the actor
     * if they haven't been added already. If the movie doesn't yet exist
     * in the database, this will add the movie with the actor as the only
     * credit.
     *
     * @param movie the movie to add to the actors set of movies
     * @param actor the actor that appears in this movie
     */
    public void tagActorInMovie(Movie movie, Actor actor) {
        if (!actorToMovieMap.containsKey(actor)) {
            HashSet<Movie> movieSet = new HashSet<>();
            movieSet.add(movie);
            actorToMovieMap.put(actor, movieSet);
        } else {
            actorToMovieMap.get(actor).add(movie);
        }

        if (movieToActorMap.containsKey(movie)) {
            movieToActorMap.get(movie).add(actor);
        } else {
            HashSet<Actor> actorSet = new HashSet<>();
            actorSet.add(actor);
            movieToActorMap.put(movie, actorSet);
        }
    }

    /**
     * Returns a set of actors who are credited in the given movie. If a movie is not
     * released on IMDB throw an IllegalArgumentException.
     *
     * @param movie the movie to get actors for
     * @return the set of actors who are credited in the passed in movie
     */
    public Set<Actor> getActorsInMovie(Movie movie) {
        if (!movieToActorMap.containsKey(movie)) throw new IllegalArgumentException();
        return movieToActorMap.get(movie);
    }

    /**
     * Returns a set of movies that the specified actor has appeared in. If the
     * actor has not appeared in any movies, return an empty Set.
     *
     * @param actor the actor to get movies for
     * @return the set of movies that the passed in actor has appeared in
     */
    public Set<Movie> getMoviesForActor(Actor actor) {
        if (actorToMovieMap.isEmpty()) return new HashSet<>();
        return actorToMovieMap.get(actor);
    }

    /**
     * Returns all actors that IMDB has in its records as having appeared in a movie.
     *
     * @return a set of actors that IMDB has as appeared in movies
     */
    public Set<Actor> getAllActorsInIMDB() {
        return actorToMovieMap.keySet();
    }

    /**
     * Returns the total number of individual movie-actor pairs in the database.
     *
     * So if there are 2 movies, the first movie has 1 actor and the second one
     * has 6 actors, this method will return 7.
     *
     * @return The total number of movie-actor pairings: the number of times
     *         any actor has appeared in any movie
     */
    public int getTotalNumCredits() {
        int count = 0;
        for (Actor actor : getAllActorsInIMDB()) {
            count+=getMoviesForActor(actor).size();
        }
        return count;
    }
}
