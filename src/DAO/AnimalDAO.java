package DAO;

import DTO.AnimalDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class AnimalDAO {
    ArrayList<AnimalDTO> animais = new ArrayList<AnimalDTO>();
    public void cadastrarAnimal(AnimalDTO animal) {
        try (PreparedStatement stmt = ConexaoDAO.getConnection().prepareStatement("INSERT INTO animal (genero, especie, raca, disponivel, informacoesAdicionais) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, animal.getGenero());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setBoolean(4, animal.isDisponivel());
            stmt.setString(5, animal.getInformacoesAdicionais());
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar animal: " + e.getMessage());
        }
    }

    public ArrayList<AnimalDTO> listarAnimais() {
        try (PreparedStatement stmt = ConexaoDAO.getConnection().prepareStatement("SELECT * FROM animal ORDER BY id")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AnimalDTO animal = new AnimalDTO(rs.getInt("id"), rs.getString("genero"), rs.getString("especie"), rs.getString("raca"), rs.getBoolean("disponivel"), rs.getString("informacoesAdicionais"));
                animais.add(animal);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar animais: " + e.getMessage());
        }
        return animais;
    }

    public void atualizarAnimal(AnimalDTO animal) {
        try (PreparedStatement stmt = ConexaoDAO.getConnection().prepareStatement("UPDATE animal SET genero = ?, especie = ?, raca = ?, disponivel = ?, informacoesAdicionais = ? WHERE id = ?")) {
            stmt.setString(1, animal.getGenero());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setBoolean(4, animal.isDisponivel());
            stmt.setString(5, animal.getInformacoesAdicionais());
            stmt.setInt(6, animal.getId());
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar animal: " + e.getMessage());
        }
    }

    public void deletarAnimal(int id) {
        try (PreparedStatement stmt = ConexaoDAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar animal: " + e.getMessage());
        }
    }
    
}
