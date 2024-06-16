/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.telaquati;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.mycompany.db.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class TelaQuati extends javax.swing.JFrame {

    /**
     * Creates new form TelaQuatii
     */
    public TelaQuati() {
        initComponents();
        carregarTabela();
    }

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) TbQuati.getModel();
        modelo.setRowCount(0); // Limpa a tabela

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        if (conexaoBanco.conectar()) {
            try (Connection conexao = conexaoBanco.getConnection()) {
                String sql = "SELECT nome, email, nickname, senha FROM cadastroTela";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String nickname = rs.getString("nickname");
                    String senha = rs.getString("senha");
                    modelo.addRow(new Object[]{nome, email, nickname, senha});
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage());
            }
        }
    }

    private void adicionarUsuario() {
        String nome = jvNome.getText();
        String email = jvEmail.getText();
        String nickname = jvNickName.getText();
        String senha = new String(jvSenha.getPassword());

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        if (conexaoBanco.conectar()) {
            try (Connection conexao = conexaoBanco.getConnection()) {
                String sql = "INSERT INTO cadastroTela (nome, email, nickname, senha) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setString(3, nickname);
                ps.setString(4, senha);
                ps.executeUpdate();
                carregarTabela();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + ex.getMessage());
            }
        }
    }

    private void alterarUsuario() {
        String nome = jvNome.getText();
        String email = jvEmail.getText();
        String nickname = jvNickName.getText();
        String senha = new String(jvSenha.getPassword());
        int selectedRow = TbQuati.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para alterar.");
            return;
        }
        String oldEmail = TbQuati.getValueAt(selectedRow, 1).toString();

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        if (conexaoBanco.conectar()) {
            try (Connection conexao = conexaoBanco.getConnection()) {
                String sql = "UPDATE cadastroTela SET nome = ?, email = ?, nickname = ?, senha = ? WHERE email = ?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setString(3, nickname);
                ps.setString(4, senha);
                ps.setString(5, oldEmail);
                ps.executeUpdate();
                carregarTabela();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar usuário: " + ex.getMessage());
            }
        }
    }

    private void removerUsuario() {
        int selectedRow = TbQuati.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.");
            return;
        }
        String email = TbQuati.getValueAt(selectedRow, 1).toString();

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        if (conexaoBanco.conectar()) {
            try (Connection conexao = conexaoBanco.getConnection()) {
                String sql = "DELETE FROM cadastroTela WHERE email = ?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
                carregarTabela();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover usuário: " + ex.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jvNome = new javax.swing.JTextField();
        jvEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jbAdicionar = new javax.swing.JButton();
        jbAlterar = new javax.swing.JButton();
        jbRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbQuati = new javax.swing.JTable();
        jvNickName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jvSenha = new javax.swing.JPasswordField();

        jPasswordField1.setText("jPasswordField1");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(720, 600));

        jPanel2.setBackground(new java.awt.Color(40, 40, 40));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CADASTRO QUATI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(250, 250, 250))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(40, 40, 40));
        jLabel3.setText("Nome ");

        jvNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jvNomeActionPerformed(evt);
            }
        });

        jvEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jvEmailFocusLost(evt);
            }
        });
        jvEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jvEmailKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(40, 40, 40));
        jLabel7.setText("E-mail");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(40, 40, 40));
        jLabel16.setText("Senha");

        jbAdicionar.setBackground(new java.awt.Color(40, 40, 40));
        jbAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        jbAdicionar.setText("ADICIONAR");
        jbAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdicionarActionPerformed(evt);
            }
        });

        jbAlterar.setBackground(new java.awt.Color(40, 40, 40));
        jbAlterar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbAlterar.setForeground(new java.awt.Color(255, 255, 255));
        jbAlterar.setText("ALTERAR");
        jbAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAlterarActionPerformed(evt);
            }
        });

        jbRemover.setBackground(new java.awt.Color(40, 40, 40));
        jbRemover.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbRemover.setForeground(new java.awt.Color(255, 255, 255));
        jbRemover.setText("REMOVER");
        jbRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverActionPerformed(evt);
            }
        });

        TbQuati.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "E-MAIL", "NICKNAME", "SENHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TbQuati.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbQuatiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TbQuati);
        if (TbQuati.getColumnModel().getColumnCount() > 0) {
            TbQuati.getColumnModel().getColumn(0).setResizable(false);
            TbQuati.getColumnModel().getColumn(1).setResizable(false);
            TbQuati.getColumnModel().getColumn(2).setResizable(false);
            TbQuati.getColumnModel().getColumn(3).setResizable(false);
        }

        jvNickName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jvNickNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(40, 40, 40));
        jLabel4.setText("Nickname");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jvNickName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(82, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jvNome, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jvEmail)
                    .addComponent(jLabel16)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jvSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addGap(74, 74, 74))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jvEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jvNome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jvNickName, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jvSenha))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jvEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jvEmailFocusLost
        // TODO add your handling code here:
        String emailPattern = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailPattern, jvEmail.getText())) {
            JOptionPane.showMessageDialog(null, "Por favor, digite um e-mail válido", "Error", JOptionPane.ERROR_MESSAGE);
            jvEmail.requestFocus();
        }
    }//GEN-LAST:event_jvEmailFocusLost

    private void jvEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jvEmailKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == evt.VK_ENTER) {
            jbAdicionar.requestFocus();
        }
    }//GEN-LAST:event_jvEmailKeyPressed

    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdicionarActionPerformed
        // TODO add your handling code here:
        adicionarUsuario();
    }//GEN-LAST:event_jbAdicionarActionPerformed

    private void jbAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAlterarActionPerformed
        // TODO add your handling code here:
        alterarUsuario();
    }//GEN-LAST:event_jbAlterarActionPerformed

    private void jbRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverActionPerformed
        // TODO add your handling code here:
        removerUsuario();
    }//GEN-LAST:event_jbRemoverActionPerformed

    private void TbQuatiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbQuatiMouseClicked
        // TODO add your handling code here:
        int selectedRow = TbQuati.getSelectedRow();
        jvNome.setText(TbQuati.getValueAt(selectedRow, 0).toString());
        jvEmail.setText(TbQuati.getValueAt(selectedRow, 1).toString());
        jvNickName.setText(TbQuati.getValueAt(selectedRow, 2).toString());
        jvSenha.setText(TbQuati.getValueAt(selectedRow, 3).toString());
    }//GEN-LAST:event_TbQuatiMouseClicked

    private void jvNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jvNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jvNomeActionPerformed

    private void jvNickNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jvNickNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jvNickNameActionPerformed

    private void limparCampos() {
        jvNome.requestFocus();
        jvNome.setText("");
        jvEmail.setText("");
        jvSenha.setText("");
        jvNickName.setText("");

    }//

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TelaQuati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaQuati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaQuati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaQuati.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatMaterialDarkerIJTheme.setup();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaQuati().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TbQuati;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAdicionar;
    private javax.swing.JButton jbAlterar;
    private javax.swing.JButton jbRemover;
    private javax.swing.JTextField jvEmail;
    private javax.swing.JTextField jvNickName;
    private javax.swing.JTextField jvNome;
    private javax.swing.JPasswordField jvSenha;
    // End of variables declaration//GEN-END:variables
}
