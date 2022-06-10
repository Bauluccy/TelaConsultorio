package GUI;

import Classes.Consulta;
import Classes.Joins;
import Classes.Medicos;
import Classes.Pacientes;
import DAO.ConsultaDAO;
import DAO.ErpDAOException;
import DAO.JoinsDAO;
import DAO.MedicosDAO;
import DAO.PacientesDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fifol
 */
public class TelaConsulta extends javax.swing.JFrame {

    ArrayList<Joins> consultaJoin = new ArrayList();
    
    public TelaConsulta() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaConsultas = new javax.swing.JTable();
        botaoAdicionar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        TabelaConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Hora", "Paciente", "Médico", "Compareceu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TabelaConsultas);
        if (TabelaConsultas.getColumnModel().getColumnCount() > 0) {
            TabelaConsultas.getColumnModel().getColumn(0).setHeaderValue("Data");
            TabelaConsultas.getColumnModel().getColumn(1).setHeaderValue("Hora");
            TabelaConsultas.getColumnModel().getColumn(2).setHeaderValue("Paciente");
            TabelaConsultas.getColumnModel().getColumn(3).setHeaderValue("Médico");
            TabelaConsultas.getColumnModel().getColumn(4).setHeaderValue("Compareceu");
        }

        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoAdicionarMouseClicked(evt);
            }
        });

        jMenu1.setText("Opções");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoAdicionar)
                .addContainerGap(517, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botaoAdicionar)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        DefaultTableModel table = (DefaultTableModel) TabelaConsultas.getModel();
        String nomePaciente, nomeMedico, compareceuString = null;
        Date data;
        Time hora;
        int compareceu;
        
        if(TabelaConsultas.getRowCount() > 0){
            while (TabelaConsultas.getRowCount() > 0){
                table.removeRow(0);
            }
        }
        
        JoinsDAO CDAO;
        
        try{
            CDAO = new JoinsDAO();
            consultaJoin = CDAO.joinsListar();
        }catch(ErpDAOException e){
            System.out.println("Erro de conexão ao banco.");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco." + ex);
        } catch (Exception ex) {
            Logger.getLogger(TelaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < consultaJoin.size(); i++){
            DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = dtOutput.format(consultaJoin.get(i).getDataConsulta());
            
            DateFormat hrOutput = new SimpleDateFormat("HH:mm");
            String horaFormatada = hrOutput.format(consultaJoin.get(i).getHoraConsulta());
            
            nomePaciente = consultaJoin.get(i).getNomePaciente();
            nomeMedico = consultaJoin.get(i).getNomeMedico();
            compareceu = consultaJoin.get(i).getCompareceu();
            if(compareceu == 1){
                compareceuString = "Sim";
            }if(compareceu == 0){
                compareceuString = "Não";
            }
                
            
            table.addRow(new String[]{dataFormatada,horaFormatada,nomePaciente,nomeMedico, compareceuString});
        }
        
    }//GEN-LAST:event_formWindowActivated

    private void botaoAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoAdicionarMouseClicked
        
        
        
    }//GEN-LAST:event_botaoAdicionarMouseClicked

        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaConsultas;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
