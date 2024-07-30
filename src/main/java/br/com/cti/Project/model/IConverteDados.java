package br.com.cti.Project.model;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
   }
   
