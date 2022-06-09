/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Medicos;
import conexaoDAO.Util.conexaoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MedicosDAO {

    private Connection conn;
    short cont = 0;

    public MedicosDAO() {
        try {
            this.conn = conexaoDAO.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + ":\n" + e.getMessage());
        }
    }

    public ArrayList listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList Lista = new ArrayList();

        try {
            String SQL = "SELECT * FROM medicos";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome_med");
                String especialidade = rs.getString("especialidade");
                char sexo;
                if (rs.getString("sexo") == null) {
                    sexo = ' ';
                } else {
                    sexo = (rs.getString("sexo")).charAt(0);
                }
                Date data_nasc = rs.getDate("data_nasc");
                int fone = rs.getInt("fone");

                Lista.add(new Medicos(id, nome, especialidade, data_nasc, sexo, fone));

            }

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar todos os médicos " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }

        return Lista;
    }

    public void inserir(Medicos medico) {

        PreparedStatement ps = null;
        Connection connL = null;


        if (medico == null) {
            JOptionPane.showMessageDialog(null, "O objeto medico não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO medicos (nome_med, especialidade, data_nasc, sexo, fone) values (?,?,?,?,?)";

            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, medico.getNome_med());
            ps.setString(2, medico.getEspecialidade());
            java.util.Date dataJAVA = medico.getData_nasc();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            ps.setString(4, Character.toString(medico.getSexo()));
            ps.setInt(5, medico.getFone());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo médico " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }


    public void atualizar(Medicos medico) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (medico == null) {
            JOptionPane.showMessageDialog(null, "O objeto medico não pode ser nulo.");
        }

        try {
            String SQL = "UPDATE medicos set nome_med=?, especialidade=?, data_nasc=?, sexo=?, fone=? WHERE id=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, medico.getNome_med());
            ps.setString(2, medico.getEspecialidade());
            java.util.Date dataJAVA = medico.getData_nasc();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            ps.setString(4, Character.toString(medico.getSexo()));
            ps.setInt(5, medico.getFone());
            ps.setInt(6, medico.getId());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar médico " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }

    public void excluir(Medicos medico) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (medico == null) {
            JOptionPane.showMessageDialog(null, "O objeto medicos não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM medicos WHERE id=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, medico.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir médico " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }
    
    public Medicos procurar (int id) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Medicos medico = new Medicos();
        medico = null;
        
        try {
            String SQL = "SELECT * FROM medicos where id = ?";
            connL = this.conn;
            
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int idMed = rs.getInt("id");
                String nome = rs.getString("nome_med");
                String especialidade = rs.getString("especialidade");
                Date data_nasc = rs.getDate("data_nasc");
                char sexo;
                if(rs.getString("sexo") == null) {
                    sexo = ' ';
                } else {
                    sexo = (rs.getString("sexo")).charAt(0);
                }
                int fone = rs.getInt("fone");
                
                medico = new Medicos(idMed, nome, especialidade, data_nasc, sexo, fone);
                //JOptionPane.showMessageDialog(null, medico.getNome_med());
            }
            
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar médicos" + sqle);
        } finally {
            //conexaoMedicosDAO.close(connL, ps);
        }
        return medico;
        
    }

    

}
