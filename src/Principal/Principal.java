/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Controle.ControleMedicos;
import Classes.Pacientes;
import Classes.Consulta;
import Controle.ControleConsultas;
import Classes.Medicos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        ArrayList<Medicos> Lista = new ArrayList<Medicos>();
        ArrayList<Pacientes> Agenda = new ArrayList<Pacientes>();
        ArrayList<Consulta> Consultas = new ArrayList<Consulta>();

        char op = ' ';

        do {
            op = (JOptionPane.showInputDialog(null, "Digite:\nL para listar Médicos \nP para Listar Paciente\nM para listar Consultas "
                    + "\nI para inserir Médico \nJ para inserir Paciente\nO para inserir Consulta"
                    + "\nE para excluir Médico \nR para excluir Paciente \nD para excluir Consulta"
                    + "\nA para atualizar Médico \nH para atualizar Paciente \nX para atualizar Consulta"
                    + "\nS para sair").charAt(0));

            switch (op) {
                case 'L':
                case 'l':
                    ControleMedicos.listarMedico(Lista);
                    break;
                case 'I':
                case 'i':
                    ControleMedicos.Inserir();
                    break;
                case 'E':
                case 'e':
                    ControleMedicos.ExcluirMedico();
                    break;
                case 'A':
                case 'a':
                    ControleMedicos.AtualizarMedico();
                    break;
                case 'p':
                case 'P':
                    Controle.ControlePacientes.listarPacientes(Agenda);
                    break;
                case 'J':
                case 'j':
                    Controle.ControlePacientes.Inserir();
                    break;
                case 'R':
                case 'r':
                    Controle.ControlePacientes.ExcluirPacientes();
                    break;
                case 'H':
                case 'h':
                    Controle.ControlePacientes.AtualizarPacientes();
                    break;
                case 'M':
                case 'm':
                    ControleConsultas.listarConsulta(Consultas);
                    break;
                case 'O':
                case 'o':
                    ControleConsultas.Inserir();
                    break;                
                case 'd':
                case 'D':
                    ControleConsultas.ExcluirConsulta();
                    break;
                case 'x':
                case 'X':
                    ControleConsultas.AtualizarConsulta();
                    break;
            }
        } while (op != 's' && op != 'S');

    }
}
