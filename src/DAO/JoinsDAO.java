package DAO;

import Classes.Joins;
import conexaoDAO.Util.conexaoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    ArrayList Joins() throws Exception{
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList joinConsulta = new ArrayList();
        
        try{
            String sql = "SELECT pacientes.codigo, medicos.id, consultas.data, consultas.hora, pacientes.nome_pac, medicos.nome_med FROM medicos, pacientes, consultas WHERE consultas.id_medico = medicos.id AND consultas.id_paciente = pacientes.codigo ORDER BY consultas.data, consultas.hora";
            connL = this.conn;
            ps = connL.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                   int id_paciente = rs.getInt("pacientes.codigo");
                   int id_medico = rs.getInt("medicos.id");
                   DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
                   Date data = rs.getDate("data");
                   Time hora = rs.getTime("hora");
                   String nome_paciente = rs.getString("pacientes.nome_pac");
                   String nome_medico = rs.getString("medicos.nome_med");
                   int compareceu = rs.getInt("compareceu");
                   
                   joinConsulta.add(new Joins(id_paciente,id_medico,nome_medico, nome_paciente, data, hora));
               }
            return joinConsulta;
            
        }catch(Exception e){
            throw new Exception("Erro ao listar as consultas" + e);
        }
        finally{
            conexaoDAO.close(conn, ps);
        }
    }
    
}
