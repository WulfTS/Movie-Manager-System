import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Comparable<Movie> {

    //create enum Status
    enum Status {RECEIVED, RELEASED}

    // date format for dates in file https://www.javatpoint.com/java-string-to-date
    public static SimpleDateFormat MOVIE_FILE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    //data fields
    private Date releaseDate;
    private String name;
    private String description;
    private Date receiveDate;
    private Status status;

    //Constructors
    public Movie() {} //default constructor
    public Movie(Date releaseDate, String name, String description, Date receiveDate, Status status) {
        this.releaseDate = releaseDate;
        this.name = name;
        this.description = description;
        this.receiveDate = receiveDate;
        this.status = status;
    }

    public Movie(String[] fileData) throws ParseException {
        this.name = fileData[0];
        this.releaseDate = MOVIE_FILE_DATE_FORMAT.parse(fileData[1]);
        this.description = fileData[2];
        this.receiveDate = MOVIE_FILE_DATE_FORMAT.parse(fileData[3]);
        //convert string to enum https://www.baeldung.com/java-string-to-enum
        this.status = Status.valueOf(fileData[4].toUpperCase());
    }

    public String toMovieFileString () {
        return this.name + ", " + MOVIE_FILE_DATE_FORMAT.format(this.releaseDate) + ", " + this.description + ", " + MOVIE_FILE_DATE_FORMAT.format(this.receiveDate) + ", " + this.status.toString().toLowerCase();
    }

    //getters and setters
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //borrowing Date data type compareTo to override Movie compareTo
    @Override
    public int compareTo(Movie other) {
        return this.getReleaseDate().compareTo(other.getReleaseDate());
    }

    @Override
    //TODO: Make proper movie display
    public String toString() {
        return "Make proper movie display";
    }
}



