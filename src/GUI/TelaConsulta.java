/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Consulta;
import Classes.Joins;
import Classes.Medicos;
import Classes.Pacientes;
import DAO.ConsultaDAO;
import DAO.MedicosDAO;
import DAO.PacientesDAO;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
        jButton1 = new javax.swing.JButton();

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

        jButton1.setText("Adicionar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
        
    }//GEN-LAST:event_formWindowActivated

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        DefaultTableModel table = (DefaultTableModel) TabelaConsultas.getModel();
        String nomePaciente, nomeMedico;
        int compareceu;
        
        if(TabelaConsultas.getRowCount() > 0){
            while (TabelaConsultas.getRowCount() > 0){
                table.removeRow(0);
            }
        }
        
        ConsultaDAO CDAO;
        
        try{
            CDAO = new ConsultaDAO();
            consultaJoin = CDAO.listar();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o bancoooooo.");
        }
        
        for(int i = 0; i < consultaJoin.size(); i++){
            DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = dtOutput.format(consultaJoin.get(i).getDataConsulta());
            
            DateFormat hrOutput = new SimpleDateFormat("HH:mm");
            String horaFormatada = hrOutput.format(consultaJoin.get(i).getHoraConsulta());
            
            nomePaciente = consultaJoin.get(i).getNomePaciente();
            nomeMedico = consultaJoin.get(i).getNomeMedico();
            compareceu = consultaJoin.get(i).getCompareceu();
            
            table.addRow(new String[]{dataFormatada,horaFormatada,nomePaciente,nomeMedico});
        }
    }//GEN-LAST:event_jButton1MouseClicked

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
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
