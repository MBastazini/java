package br.com.cti.Project.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCritica(@JsonAlias("status") String status,
                           @JsonAlias("num_results") int numResults,
                           @JsonAlias("results") List<DadosResultado> results) {

}

