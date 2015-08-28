package com.moviex.persistence.entity.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "\"MovieSearchMetadata\"")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "imdbID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchMetadata {

    @Id
    @Column(name = "\"Id\"")
    @Setter(AccessLevel.PRIVATE)
    private String imdbID;

    @Column(name = "\"Title\"")
    private String title;

    @Column(name = "\"Year\"")
    private String year;

    @Column(name = "\"Type\"")
    private String type;

    @Column(name = "\"Poster\"")
    private String poster;

    @Column(name = "\"ImdbRating\"")
    private String imdbRating;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Movie movie;

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("poster")
    public String getPoster() {
        return poster;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }
}
