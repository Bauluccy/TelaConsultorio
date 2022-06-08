/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Consulta;
import Classes.Medicos;
import Classes.Pacientes;
import conexaoDAO.Util.conexaoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultaDAO {

    private Connection conn;

    public ConsultaDAO() {
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
        ArrayList agendaConsultas = new ArrayList();

        try {
            String SQL = "SELECT * FROM consultas";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int ID_consulta = rs.getInt("ID_consulta");
                int id_medico = rs.getInt("id_medico");
                int id_paciente = rs.getInt("id_paciente");
                Date data = rs.getDate("data");
                DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
                Time hora = rs.getTime("hora");
                int compareceu = rs.getInt("compareceu");
                agendaConsultas.add(new Consulta(ID_consulta, id_medico, id_paciente, data, hora, compareceu));
            }

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar consultas " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }

        return agendaConsultas;
    }

    public void inserir(Consulta consulta) {

        PreparedStatement ps = null;
        Connection connL = null;

        if (consulta == null) {
            JOptionPane.showMessageDialog(null, "O objeto consulta não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO consultas ( id_medico, id_paciente, data, hora, compareceu) "
                    + "values (?,?,?,?,?)";

            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, consulta.getId_medico());
            ps.setInt(2, consulta.getId_paciente());
            java.util.Date dataJAVA = consulta.getData();
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            java.util.Date horaSQL = consulta.getHora();  // Data da classe Java Util
            ps.setTime(4, (Time) horaSQL);
            
            ps.setInt(5, consulta.getCompareceu());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo consulta " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }
    
    public List<Consulta> joins(){
               PreparedStatement ps = null;
               Connection connL = null;
               ResultSet rs = null;
               ArrayList join = new ArrayList();
               
               Consulta consulta = new Consulta();
               Medicos medico = new Medicos();
               Pacientes pacientes = new Pacientes();
               
           try{
               String sql = "SELECT consultas.data, consultas.hora, pacientes.nome_pac, medicos.nome_med FROM medicos, pacientes, consultas WHERE consultas.id_medico = medicos.id AND consultas.id_paciente = pacientes.codigo ORDER BY consultas.data, consultas.hora";
               connL = this.conn;
               ps = connL.prepareStatement(sql);
               rs = ps.executeQuery();
               
               while(rs.next()){
                   DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
                   consulta.setData(rs.getDate("data"));
                   consulta.setHora(rs.getTime("hora"));
                   pacientes.setNome_pac(rs.getString("pacientes.nome_pac"));
                   medico.setNome_med("medicos.nome_med");
                   consulta.setCompareceu(rs.getInt("compareceu"));
                   
                   
                   
                   join.add(consulta);
               }
               
           }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Erro de conexão com o banco!");
           }
           return join;
    }


    public Consulta procurar(int ID_Cli) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Consulta consulta = new Consulta();
        consulta = null;
        try {
            String SQL = "SELECT * FROM consultas WHERE ID_consulta = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, ID_Cli);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ID_consulta = rs.getInt("ID_consulta");
                int id_medico = rs.getInt("id_medico");
                int id_paciente = rs.getInt("id_paciente");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                int compareceu = rs.getInt("compareceu");
                
                consulta = new Consulta(ID_consulta, id_medico, id_paciente, data, hora, compareceu);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar consulta " + sqle);
        } finally {
            //conexaoAulaDAO.close(connL, ps);
        }
        return consulta;
    }

    public void atualizar(Consulta consulta) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (consulta == null) {
            JOptionPane.showMessageDialog(null, "O objeto consulta não pode ser nulo.");
        }
        try {
            String SQL = "UPDATE consultas set id_medico=?, id_paciente=?, data=?, hora=?, compareceu=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, consulta.getId_medico());
            ps.setInt(2, consulta.getId_paciente());
            java.util.Date dataJAVA = consulta.getData();
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(3, dataSQL);
            java.util.Date horaSQL = consulta.getHora();  // Data da classe Java Util
            ps.setTime(4, (Time) horaSQL);
            
            ps.setInt(5, consulta.getCompareceu());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar consulta " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }

    public void excluir(Consulta consulta) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (consulta == null) {
            JOptionPane.showMessageDialog(null, "O objeto consulta não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM consultas WHERE ID_consulta=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, consulta.getID_consulta());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir consulta " + sqle);
        } finally {
            conexaoDAO.close(connL, ps);
        }
    }
}
