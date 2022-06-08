package Controle;

import Classes.Consulta;
import DAO.ConsultaDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleConsultas {

    public static void listarConsulta(ArrayList<Consulta> agendaConsultas) {
        try {
            ConsultaDAO x = new ConsultaDAO();
            agendaConsultas = x.listar();
        } catch (Exception ex) {
            System.out.println("problema");
        }
        String msg = "Lista de consultas\n";
        int tamanho = agendaConsultas.size();
        Consulta consulta = new Consulta();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (tamanho == 0) {
            JOptionPane.showMessageDialog(null, "Agenda Vazia !!");
        } else {
            for (int i = 0; i < tamanho; i++) {
                msg = msg + "ID; " + agendaConsultas.get(i).getID_consulta();
                msg = msg + "\nID Médico: " + agendaConsultas.get(i).getId_medico();
                msg = msg + "\nID Paciente: " + agendaConsultas.get(i).getId_paciente();
                msg = msg + "\nData da consulta: " + agendaConsultas.get(i).getData();
                msg = msg + "\nHora da consulta: " + agendaConsultas.get(i).getHora();
                msg = msg + "\nPaciente Compareceu: " + agendaConsultas.get(i).getCompareceu();
                msg = msg + "\n___________________________________________________ \n";
            }
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    public static void Inserir() {        
        Consulta consulta = new Consulta();
        Date dataHoraAtual = new Date();
        
        consulta.setId_medico(Integer.parseInt(JOptionPane.showInputDialog("Digite o id do médico")));
        consulta.setId_paciente(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Paciente")));
        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dt = dtOutput.parse(JOptionPane.showInputDialog("Digite a data da consulta"));
            consulta.setData(dt);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        String hora = (JOptionPane.showInputDialog("Digite a hora da consulta"));;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Time time;
        try {
            time = new Time(format.parse(hora).getTime());
            consulta.setHora(time);
        } catch (ParseException ex) {
            
        }
        
        
        
        consulta.setCompareceu(Integer.parseInt(JOptionPane.showInputDialog("Digite 1 se ele compareceu e 0 se não compareceu")));
        ConsultaDAO pdao = new ConsultaDAO();
        pdao.inserir(consulta);
    }

    public static void procurar() {

        int codigo = 0;
        Consulta consulta = new Consulta();

        codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID da consulta para localizar"));

        try {
            ConsultaDAO x = new ConsultaDAO();
            consulta = x.procurar(codigo);
        } catch (Exception ex) {
            System.out.println("problema");
        }
        String msg = "Dados da consulta com ID indicado \n";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (consulta == null) {
            JOptionPane.showMessageDialog(null, "Não encontrado !!");
        } else {
            msg = msg + "ID_Consulta: " + consulta.getID_consulta();
            msg = msg + "\nID Médico: " + consulta.getId_medico();
            msg = msg + "\nID Paciente: " + consulta.getId_paciente();
            msg = msg + "\nData de Nascimento: " + sdf.format(consulta.getData());
            msg = msg + "\nEndereço: " + consulta.getHora();
            msg = msg + "\nNúmero: " + consulta.getCompareceu();
            msg = msg + "\n___________________________________________________ \n";
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    public static void ExcluirConsulta() {

        Consulta consulta = new Consulta();
        ConsultaDAO pdao = new ConsultaDAO();
        int codigo;
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da consulta a excluir"));
        consulta = pdao.procurar(codigo);
        if (consulta != null) {
            pdao.excluir(consulta);
            JOptionPane.showMessageDialog(null, "A consulta com o " + codigo + " foi excluida com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "A consulta com o " + codigo + " não foi localizada.");
        }
    }

    public static void AtualizarConsulta() {
        Consulta consulta = new Consulta();
        ConsultaDAO pdao = new ConsultaDAO();
        Date dataHoraAtual = new Date();

        int codigo;
        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da consulta a atualizar"));
        consulta = pdao.procurar(codigo);
        if (consulta != null) {
            consulta.setID_consulta(codigo);
            consulta.setId_medico(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do médico: ", consulta.getId_medico())));
            consulta.setId_paciente(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do paciente", consulta.getId_paciente())));
            try {
                Date dt = dtOutput.parse(JOptionPane.showInputDialog(null, "Digite a data de Nascimento", sdf.format(consulta.getData())));
                consulta.setData(dt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
            consulta.setHora(Time.valueOf(JOptionPane.showInputDialog(null, "Digite a hora", consulta.getHora())));
            consulta.setCompareceu(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite 1 se ele compareceu e 0 se não compareceu", consulta.getCompareceu())));
            
           
            
            pdao.atualizar(consulta);
        } else {
            JOptionPane.showMessageDialog(null, "A consulta com o codigo " + codigo + " não foi localizada.");
        }
    }
}
