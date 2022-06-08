
package Controle;


import Classes.Pacientes;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Classes.Pacientes;
import DAO.PacientesDAO;



/**
 *
 * @author cesar
 */
public class ControlePacientes {

    public static void listarPacientes(ArrayList <Pacientes>Agenda) {
        try {
            PacientesDAO x = new PacientesDAO();
            Agenda = x.listar();
        } catch (Exception ex) {
            System.out.println("problema");
        }
        String msg = "Lista de clientes da agenda \n";
        int tamanho = Agenda.size();
        Pacientes pacientes = new Pacientes();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (tamanho == 0) {
            JOptionPane.showMessageDialog(null, "Agenda Vazia !!");
        } else {
            for (int i = 0; i < tamanho; i++) {

                msg = msg + "ID_Cliente: " + Agenda.get(i).getCodigo();
                msg = msg + "\nNome: " + Agenda.get(i).getNome_pac();
                msg = msg + "\nFone 1:" + Agenda.get(i).getFone();
                msg = msg + "\nData de Nascimento: " + sdf.format(Agenda.get(i).getData_nasc());
                msg = msg + "\nSexo: " + Agenda.get(i).getSexo();
                msg = msg + "\n___________________________________________________ \n";

            }
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    public static void Inserir() {

        Pacientes pacientes = new Pacientes();

        pacientes.setNome_pac(JOptionPane.showInputDialog("Digite o nome"));
        pacientes.setSexo(JOptionPane.showInputDialog("Digite o sexo").charAt(0));
        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dt = dtOutput.parse(JOptionPane.showInputDialog("Digite a data de Nascimento"));
            pacientes.setData_nasc(dt);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        pacientes.setFone(Integer.parseInt(JOptionPane.showInputDialog("Digite o Telefone")));

        PacientesDAO pdao = new PacientesDAO();

        pdao.inserir(pacientes);

    }

    public static void procurar() {

        int codigo = 0;
        Pacientes pacientes = new Pacientes();

        codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do cliente para localizar"));

        try {
           PacientesDAO x = new PacientesDAO();
            pacientes = x.procurar(codigo);
        } catch (Exception ex) {
            System.out.println("problema");
        }
        String msg = "Dados do clientes com ID indicado \n";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (pacientes == null) {
            JOptionPane.showMessageDialog(null, "Não encontrado !!");
        } else {
            msg = msg + "ID_Cliente: " + pacientes.getCodigo();
            msg = msg + "\nNome: " +pacientes.getNome_pac();
            msg = msg + "\nFone 1:" + pacientes.getFone();
            msg = msg + "\nData de Nascimento: " + sdf.format(pacientes.getData_nasc());
            msg = msg + "\nSexo: " + pacientes.getSexo();
            msg = msg + "\n___________________________________________________ \n";
            msg = msg + "\n___________________________________________________ \n";
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    public static void ExcluirPacientes() {

        Pacientes pacientes = new Pacientes();
        PacientesDAO pdao = new PacientesDAO();
        int codigo;
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do paciente a excluir"));
        pacientes = pdao.procurar(codigo);
        if (pacientes != null) {
            pdao.excluir(pacientes);
            JOptionPane.showMessageDialog(null, "O paciente com o codigo " + codigo + " foi excluido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "O paciente com o codigo " + codigo + " não foi localizado.");
        }
    }

    public static void AtualizarPacientes() {
        Pacientes pacientes = new Pacientes();
        PacientesDAO pdao = new PacientesDAO();
        int codigo;
        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do paciente a atualizar"));
        pacientes = pdao.procurar(codigo);
        if (pacientes != null) {
            pacientes.setCodigo(codigo);
            pacientes.setNome_pac(JOptionPane.showInputDialog(null, "Digite o nome do paciente", pacientes.getNome_pac()));
            pacientes.setSexo(JOptionPane.showInputDialog(null, "Digite o sexo do paciente", pacientes.getSexo()).charAt(0));
            try {
                Date dt = dtOutput.parse(JOptionPane.showInputDialog(null, "Digite a data de Nascimento", sdf.format(pacientes.getData_nasc())));
                pacientes.setData_nasc(dt);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            pacientes.setFone(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o fone do paciente", pacientes.getFone())));

            pdao.atualizar(pacientes);

        } else {
            JOptionPane.showMessageDialog(null, "O paciente com o codigo " + codigo + " não foi localizado.");
        }

    }

}
