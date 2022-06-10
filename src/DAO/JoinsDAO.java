package DAO;

import Classes.Consulta;
import Classes.Joins;
import conexaoDAO.Util.conexaoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class JoinsDAO {
    
    private Connection conn;
    public JoinsDAO() {
        try {
            this.conn = conexaoDAO.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + ":\n" + e.getMessage());
        }
    }
    
    public ArrayList joinsListar() throws Exception{
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList joinConsulta = new ArrayList();
        
        try{
            String sql = "SELECT pacientes.codigo, medicos.id, consultas.data, consultas.hora, pacientes.nome_pac, medicos.nome_med, consultas.compareceu FROM medicos, pacientes, consultas WHERE consultas.id_medico = medicos.id AND consultas.id_paciente = pacientes.codigo ORDER BY consultas.data, consultas.hora";
            connL = this.conn;
            ps = connL.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                   DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
                   Date data = rs.getDate("data");
                   Time hora = rs.getTime("hora");
                   String nome_paciente = rs.getString("nome_pac");
                   String nome_medico = rs.getString("nome_med");
                   int compareceu = rs.getInt("compareceu");
                   
                   joinConsulta.add(new Joins(data, hora,nome_paciente, nome_medico, compareceu));
               }
            
        }catch(SQLException sqle){
            throw new Exception("Erro ao listar as consultas" + sqle);
        }
        finally{
            conexaoDAO.close(conn, ps);
        }
        return joinConsulta;
    }
    
    public void joinsInserir(Joins joins){
        PreparedStatement ps = null;
        Connection connL = null;
        String compareceuString;
        
        if(joins == null){
            JOptionPane.showMessageDialog(null, "O objeto consulta não pode ser nulo.");
        }
        
        try{
            String sql = "SELECT pacientes.codigo, medicos.id, consultas.data, consultas.hora, pacientes.nome_pac, medicos.nome_med, consultas.compareceu FROM medicos, pacientes, consultas WHERE consultas.id_medico = medicos.id AND consultas.id_paciente = pacientes.codigo ORDER BY consultas.data, consultas.hora";
            connL = this.conn;
            ps = connL.prepareStatement(sql);
            
            
            java.util.Date dataJava = joins.getDataConsulta();
            java.sql.Date dataSQL = new java.sql.Date(dataJava.getTime());
            ps.setDate(1, dataSQL);
            java.util.Date horaSQL = joins.getHoraConsulta();
            ps.setTime(2, (Time) horaSQL);
            ps.setString(3, joins.getNomePaciente());
            ps.setString(4, joins.getNomeMedico());
            ps.setInt(5, joins.getCompareceu());
            
            ps.executeUpdate();
            
//            if(joins.getCompareceu() == 1){
//                compareceuString = "Sim";
//            }
//            if(joins.getCompareceu() == 0){
//                compareceuString = "Não";
//            }
             
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao inserir uma nova consulta!" + sqle);
        }finally{
            conexaoDAO.close(connL, ps);
        }
        
        
    }
    
    public void joinsAtualizar(Joins joins){
        
        PreparedStatement ps = null;
        Connection connL = null;
        if (joins == null) {
            JOptionPane.showMessageDialog(null, "O objeto não pode ser nulo.");
        }
        try{
                String sql = "UPDATE consultas,pacientes,medicos set consultas.data=?, consultas.hora=?,"
                + "pacientes.nome_pac=?, medicos.nome_med=?, consultas.compareceu=? ";
                connL = this.conn;
                ps = connL.prepareStatement(sql);
                
        }catch(SQLException sqle){
            
        }
    }
    
    public void joinsDelete(Consulta consulta){
         PreparedStatement ps = null;
        Connection connL = null;
        
        if(consulta == null){
            JOptionPane.showMessageDialog(null, "O objeto não pode ser nulo.");
        }
        
        try{
            String SQL = "DELETE FROM consultas WHERE ID_consulta=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, consulta.getID_consulta());
            ps.executeUpdate();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao excluir consulta " + sqle);
        }finally {
            conexaoDAO.close(connL, ps);
        }
    }
     
    
}
