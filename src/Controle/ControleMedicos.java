/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Classes.Medicos;
import DAO.MedicosDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class ControleMedicos {
    
    public static void procurar() {
        int id;
        MedicosDAO a = new MedicosDAO();
        id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
        a.procurar(id);
    }

    public static void listarMedico(ArrayList<Medicos> listaMedicos) {
        try {
            MedicosDAO x = new MedicosDAO();
            listaMedicos = x.listar();
        } catch (Exception ex) {
            System.out.println("problema");
        }
        String msg = "Lista de Médicos \n";
        int tamanho = listaMedicos.size();
        Medicos med = new Medicos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (tamanho == 0) {
            JOptionPane.showMessageDialog(null, "Lista Vazia !!");
        } else {
            for (int i = 0; i < tamanho; i++) {

                msg = msg + "Posição: " + i;
                msg = msg + "\nNome: " + listaMedicos.get(i).getNome_med();
                msg = msg + "\nSexo: " + listaMedicos.get(i).getSexo();
                msg = msg + "\nData de Nascimento: " + sdf.format(listaMedicos.get(i).getData_nasc());
                msg = msg + "\nEspecialidade: " + listaMedicos.get(i).getEspecialidade();
                msg = msg + "\nFone: " + listaMedicos.get(i).getFone();
                msg = msg + "\n___________________________________________________ \n";
            }
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    public static void Inserir() {

        Medicos medico = new Medicos();

        medico.setNome_med(JOptionPane.showInputDialog("Digite o nome"));
        medico.setEspecialidade(JOptionPane.showInputDialog("Digite a especialidade"));
        medico.setSexo(JOptionPane.showInputDialog("Digite o sexo").charAt(0));

        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dt = dtOutput.parse(JOptionPane.showInputDialog("Digite a data de Nascimento"));
            medico.setData_nasc(dt);
       
        } catch (ParseException e) {
            e.printStackTrace();
        } 
      
         JOptionPane.showMessageDialog(null, "teste " + medico.getData_nasc());
         
        medico.setFone(Integer.parseInt(JOptionPane.showInputDialog("Digite o telefone")));
        MedicosDAO medicoDao = new MedicosDAO();

        medicoDao.inserir(medico);

    }

    public static void ExcluirMedico() {

        Medicos medico = new Medicos();
        MedicosDAO mdao = new MedicosDAO();
        int codigo;
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do médico a excluir"));
        medico = mdao.procurar(codigo);
        if (medico != null) {
          
            JOptionPane.showMessageDialog(null, "O médico com o codigo " + codigo + " foi excluido com sucesso.");
            mdao.excluir(medico);
        } else {
            JOptionPane.showMessageDialog(null, "O médico com o codigo " + codigo + " não foi localizado.");
        }
    }

    public static void AtualizarMedico() {
        Medicos medico = new Medicos();
        MedicosDAO mdao = new MedicosDAO();
        int codigo;
        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do médico a atualizar"));
        medico = mdao.procurar(codigo);
        if (medico != null) {
            medico.setId(codigo);
            medico.setNome_med(JOptionPane.showInputDialog(null, "Digite o nome", medico.getNome_med()));
            medico.setEspecialidade(JOptionPane.showInputDialog(null, "Digite o nome", medico.getEspecialidade()));
            medico.setSexo(JOptionPane.showInputDialog(null, "Digite o sexo", medico.getSexo()).charAt(0));
            try {
                Date dt = dtOutput.parse(JOptionPane.showInputDialog(null, "Digite a data de Nascimento", sdf.format(medico.getData_nasc())));
                medico.setData_nasc(dt);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            medico.setFone(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o telefone", medico.getFone())));
         
            mdao.atualizar(medico);

        } else {
            JOptionPane.showMessageDialog(null, "O médico com o codigo " + codigo + " não foi localizado.");
        }

    }

}
