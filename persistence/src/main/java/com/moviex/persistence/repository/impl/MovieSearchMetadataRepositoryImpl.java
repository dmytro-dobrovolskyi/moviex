package com.moviex.persistence.repository.impl;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieSearchMetadataRepositoryCustom;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class MovieSearchMetadataRepositoryImpl implements MovieSearchMetadataRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MovieSearchMetadata> searchByTitle(String title, boolean isAdvanced) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder searchQueryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(MovieSearchMetadata.class).get();

        Query query = searchQueryBuilder.keyword()
                .fuzzy()
                .onField("title")
                .matching(title)
                .createQuery();

        if (isAdvanced) {
            query = searchQueryBuilder
                    .bool()
                    .should(query)
                    .should(
                            searchQueryBuilder
                                    .keyword()
                                    .wildcard()
                                    .onField("title")
                                    .matching(title + "*")
                                    .createQuery()
                    )
                    .createQuery();
        }
        fullTextEntityManager.createFullTextQuery(query, MovieSearchMetadata.class);

        @SuppressWarnings("unchecked")
        List<MovieSearchMetadata> resultList = fullTextEntityManager
                .createFullTextQuery(query, MovieSearchMetadata.class)
                .getResultList();

        return resultList;
    }
}
