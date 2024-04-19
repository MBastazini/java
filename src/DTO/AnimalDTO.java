package DTO;

public class AnimalDTO {
    private int id;
    private String genero;
    private String especie;
    private String raca;
    private boolean disponivel;
    private String informacoesAdicionais;

    public AnimalDTO(int id, String genero, String especie, String raca, boolean disponivel, String informacoesAdicionais) {
        this.id = id;
        this.genero = genero;
        this.especie = especie;
        this.raca = raca;
        this.disponivel = disponivel;
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public int getId() {
        return id;
    }

    public String getGenero() {
        return genero;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    
}
