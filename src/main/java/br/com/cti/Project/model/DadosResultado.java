package br.com.cti.Project.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultado(@JsonAlias("book_title") String bookTitle,
                             @JsonAlias("book_author") String bookAuthor,
                             @JsonAlias("summary") String summary,
                             @JsonAlias("publication_dt") String publicationDt,
                             @JsonAlias("url") String url,
                             @JsonAlias("byline") String byline) {
    
}
