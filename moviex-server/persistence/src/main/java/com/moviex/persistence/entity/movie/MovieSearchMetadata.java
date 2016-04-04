package com.moviex.persistence.entity.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

@Entity
@Table(name = "\"MovieSearchMetadata\"")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "imdbID")
@JsonIgnoreProperties(ignoreUnknown = true)
@Indexed
@Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
public class MovieSearchMetadata {

    @Id
    @DocumentId
    @Column(name = "\"Id\"")
    @Setter(AccessLevel.PRIVATE)
    private String imdbID;

    @Column(name = "\"Title\"")
    @Field(index = Index.YES)
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

    @Override
    public String toString() {
        return title;
    }
}
