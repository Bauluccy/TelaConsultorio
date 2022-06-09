/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Pacientes;
import conexaoDAO.Util.conexaoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 282138
 */
public class PacientesDAO {

    private Connection conn;

    public PacientesDAO() {
     try {
            this.conn = conexaoDAO.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + ":\n" + e.getMessage());
        }
    }

    public ArrayList listar() throws SQLException {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList Agenda = new ArrayList();

        try {
            String SQL = "SELECT * FROM pacientes";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome_pac = rs.getString("nome_pac");
                int fone = rs.getInt("fone");
                Date data_nasc = rs.getDate("data_nasc");
                char sexo;
                if (rs.getString("sexo") == null) {
                    sexo = ' ';
                } else {
                    sexo = (rs.getString("sexo")).charAt(0);
                }

                Agenda.add(new Pacientes(codigo, nome_pac, fone, data_nasc, sexo));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar agenda " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }

        return Agenda;

    }

    public void inserir(Pacientes pacientes) {

        PreparedStatement ps = null;
        Connection connL = null;

        if (pacientes == null) {
            JOptionPane.showMessageDialog(null, "O objeto pacientes não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO pacientes (nome_pac ,fone , data_nasc ,sexo)"
                    + "values (?,?,?,?)";

            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, pacientes.getNome_pac());
            ps.setInt(2, pacientes.getFone());
            java.util.Date dataJAVA = pacientes.getData_nasc();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            ps.setString(4, Character.toString(pacientes.getSexo()));

            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo paciente " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }

    }

    public Pacientes procurar(int codigo) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Pacientes pacientes = new Pacientes();
        pacientes = null;

        try {
            String SQL = "SELECT * FROM pacientes WHERE codigo = ?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();

            while (rs.next()) {
                codigo = rs.getInt("codigo");
                String nome_pac = rs.getString("nome_pac");
                int fone = rs.getInt("fone");
                Date data_nasc = rs.getDate("data_nasc");
                char sexo;
                if (rs.getString("sexo") == null) {
                    sexo = ' ';
                } else {
                    sexo = (rs.getString("sexo")).charAt(0);
                }

                pacientes = new Pacientes(codigo, nome_pac, fone, data_nasc, sexo);

            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar agenda " + sqle);
        } finally {
            //conexaoAulaDAO.close(connL, ps);
        }

        return pacientes;
    }

    public void atualizar(Pacientes pacientes) {
        PreparedStatement ps = null;
        Connection connL = null;

        if (pacientes == null) {
            JOptionPane.showMessageDialog(null, "O objeto pacientes não pode ser nulo.");
        }

        try {
            String SQL = "UPDATE pacientes set nome_pac=?, fone=?, data_nasc=?, sexo=?  WHERE codigo=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, pacientes.getNome_pac());
            ps.setInt(2, pacientes.getFone());
            java.util.Date dataJAVA = pacientes.getData_nasc();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            ps.setString(4, Character.toString(pacientes.getSexo()));
            ps.setInt(5, pacientes.getCodigo());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar pacientes " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }

    public void excluir(Pacientes pacientes) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (pacientes == null) {
            JOptionPane.showMessageDialog(null, "O objeto pacientes não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM pacientes WHERE codigo=?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, pacientes.getCodigo());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pacientes " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }

    
}
