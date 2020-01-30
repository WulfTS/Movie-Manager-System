import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class MovieManager {
    //create ordered lists to handle movies
    OrderedArrayList<Movie> receivedMovies = new OrderedArrayList<>();
    OrderedArrayList<Movie> releasedMovies = new OrderedArrayList<>();

    // java I/O on movies file
    FileInputStream inputFile;
    Scanner scanner;
    FileOutputStream outputFile;
    PrintWriter writer;

    public MovieManager() throws FileNotFoundException {

    }
    //read from file and input into proper lists
    public void readIntoMovieLists() throws ParseException, IOException {
        inputFile = new FileInputStream("movies.txt");
        scanner = new Scanner(inputFile);
        while (scanner.hasNext()) {
            String[] movieInfo = scanner.nextLine().split(", ");
            Movie movie = new Movie(movieInfo);
            if (movie.getStatus() == Movie.Status.RECEIVED) {
                receivedMovies.add(movie);
            }
            if (movie.getStatus() == Movie.Status.RELEASED) {
                releasedMovies.add(movie);
            }
        }
        scanner.close();
        inputFile.close();

    }

    public void writeMoviesFromListsToFile() throws IOException {
        outputFile = new FileOutputStream("movies.txt");
        writer = new PrintWriter(outputFile);
        for (Movie movie : releasedMovies) { writer.println(movie.toMovieFileString());}
        for (Movie movie : receivedMovies) { writer.println(movie.toMovieFileString());}
        writer.close();
        outputFile.close();
    }

    //TODO: Create method for adding movie to coming list
    public void addMovie(){ }

    public void moveMoviesToTheaterWithGivenReleaseDate(String givenDate) throws ParseException {
        Date inputDate = Movie.MOVIE_FILE_DATE_FORMAT.parse(givenDate);
        Iterator<Movie> iterator = receivedMovies.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getReleaseDate().equals(inputDate)) {
                releasedMovies.add(movie);
                receivedMovies.remove(movie);
                movie.setStatus(Movie.Status.RELEASED);
            }
        }
    }

    public int countNumOfMoviesThatReleaseBeforeDate(String givenDate) throws ParseException {
        int count = 0;
        Date inputDate = Movie.MOVIE_FILE_DATE_FORMAT.parse(givenDate);
        for(Movie movie : receivedMovies) {
            if (movie.getReleaseDate().before(inputDate)) {
                count++;
            }
        }
        return count;
    }

    //display movies
    //TODO: Make a proper display
    public void displayMovies(){
        Iterator<Movie> receivedMovieIter = receivedMovies.iterator();

        while(receivedMovieIter.hasNext()){
            System.out.println(receivedMovieIter.next());
        }
        Iterator<Movie> releasedMovieIter = releasedMovies.iterator();

        while(releasedMovieIter.hasNext()) {
            System.out.println(releasedMovieIter.next());
        }
    }

}
