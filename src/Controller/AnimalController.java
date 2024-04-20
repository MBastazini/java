package Controller;

import DAO.AnimalDAO;
import DTO.AnimalDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AnimalController {
    public void cadastrarAnimal(String genero, String especie, String raca, boolean disponivel, String informacoesAdicionais) {
        AnimalDAO animalDAO = new AnimalDAO();
        AnimalDTO animal = new AnimalDTO(0, genero, especie, raca, disponivel, informacoesAdicionais);
        animalDAO.cadastrarAnimal(animal);
    }

    public void atualizarAnimal(int id, String genero, String especie, String raca, boolean disponivel, String informacoesAdicionais) {
        AnimalDAO animalDAO = new AnimalDAO();
        AnimalDTO animal = new AnimalDTO(id, genero, especie, raca, disponivel, informacoesAdicionais);
        animalDAO.atualizarAnimal(animal);
    }

    public void deletarAnimal(int id) {
        AnimalDAO animalDAO = new AnimalDAO();
        animalDAO.deletarAnimal(id);
    }

    public void pesquisarAnimal(String genero, String especie, String raca, boolean disponivel, String informacoesAdicionais, JTable tabela) {
        AnimalDAO animalDAO = new AnimalDAO();
        ArrayList<AnimalDTO> animais = animalDAO.listarAnimais();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        try {
            for (AnimalDTO animal : animais) {
                boolean valido = (animal.getGenero().equals(genero) || genero.equals(""))
                        && (animal.getEspecie().equals(especie) || especie.equals(""))
                        && (animal.getRaca().equals(raca) || raca.equals(""))
                        && (animal.isDisponivel() == disponivel)
                        && (animal.getInformacoesAdicionais().equals(informacoesAdicionais) || informacoesAdicionais.equals(""));

                if (valido) {
                    model.addRow(new Object[]{animal.getEspecie(), animal.getRaca(), animal.getGenero(), animal.isDisponivel(), animal.getInformacoesAdicionais(), animal.getId()});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar animais: " + e.getMessage());
        }
    }

    public void listarAnimais(JTable tabela) {
        AnimalDAO animalDAO = new AnimalDAO();
        ArrayList<AnimalDTO> animais = animalDAO.listarAnimais();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        try {
            for (AnimalDTO animal : animais) {
                model.addRow(new Object[]{animal.getEspecie(), animal.getRaca(), animal.getGenero(), animal.isDisponivel(), animal.getInformacoesAdicionais(), animal.getId()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar animais: " + e.getMessage());
        }
    }
}
