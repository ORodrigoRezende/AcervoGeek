// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package visao;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Livro;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JLivro extends JFrame implements ActionListener {
   private BancodeDados bd;
   private JTextField tfId;
   private JTextField tfNome;
   private JTextField tfGenero;
   private JTextField tfValor;
   private JTextField tfAutor;
   private JButton btsv;
   private JButton btal;
   private JButton btrm;
   private JButton btcn;
   private DefaultTableModel modeloTabela;
   private JTable tabela;

   public JLivro(BancodeDados var1) {
      super("Cadastro de Livros");
      this.bd = var1;
      this.setDefaultCloseOperation(3);
      this.setSize(1280, 720);
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new GridBagLayout());
      GridBagConstraints var2 = new GridBagConstraints();
      var2.insets = new Insets(8, 8, 8, 8);
      JPanel var3 = new JPanel(new GridBagLayout());
      var3.setBorder(BorderFactory.createTitledBorder("Dados do Livro"));
      GridBagConstraints var4 = new GridBagConstraints();
      var4.insets = new Insets(6, 6, 6, 6);
      var4.fill = 2;
      var4.weightx = 1.0;
      var4.gridx = 0;
      var4.gridy = 0;
      var4.gridwidth = 1;
      var3.add(new JLabel("ID:"), var4);
      this.tfId = new JTextField();
      var4.gridy = 1;
      var3.add(this.tfId, var4);
      var4.gridy = 2;
      var3.add(new JLabel("Nome do Livro:"), var4);
      this.tfNome = new JTextField();
      var4.gridy = 3;
      var3.add(this.tfNome, var4);
      var4.gridy = 4;
      var3.add(new JLabel("Genero:"), var4);
      this.tfGenero = new JTextField();
      var4.gridy = 5;
      var3.add(this.tfGenero, var4);
      var4.gridy = 6;
      var3.add(new JLabel("Valor:"), var4);
      this.tfValor = new JTextField();
      var4.gridy = 7;
      var3.add(this.tfValor, var4);
      var4.gridy = 8;
      var3.add(new JLabel("Autor:"), var4);
      this.tfAutor = new JTextField();
      var4.gridy = 9;
      var3.add(this.tfAutor, var4);
      var4.gridy = 10;
      var4.fill = 0;
      var4.anchor = 10;
      var4.gridwidth = 1;
      JPanel var5 = new JPanel();
      this.btsv = new JButton("Salvar");
      this.btsv.addActionListener(this);
      this.btal = new JButton("Alterar");
      this.btal.addActionListener(this);
      this.btrm = new JButton("Apagar");
      this.btrm.addActionListener(this);
      this.btcn = new JButton("Limpar");
      this.btcn.addActionListener(this);
      var5.add(this.btsv);
      var5.add(this.btal);
      var5.add(this.btrm);
      var5.add(this.btcn);
      var3.add(var5, var4);
      var2.gridx = 0;
      var2.gridy = 0;
      var2.weightx = 0.0;
      var2.weighty = 1.0;
      this.add(this.createSpacer(), var2);
      var2.gridx = 0;
      var2.gridy = 1;
      var2.weighty = 0.0;
      var2.fill = 1;
      var2.anchor = 10;
      var2.gridwidth = 1;
      var2.weightx = 0.35;
      this.add(var3, var2);
      var2.gridx = 0;
      var2.gridy = 2;
      var2.weighty = 1.0;
      this.add(this.createSpacer(), var2);
      this.modeloTabela = new DefaultTableModel() {
         public boolean isCellEditable(int var1, int var2) {
            return false;
         }
      };
      this.modeloTabela.addColumn("ID");
      this.modeloTabela.addColumn("Nome");
      this.modeloTabela.addColumn("Genero");
      this.modeloTabela.addColumn("Valor");
      this.modeloTabela.addColumn("Autor");
      this.tabela = new JTable(this.modeloTabela);
      this.tabela.setRowHeight(22);
      this.tabela.setAutoCreateRowSorter(true);
      this.tabela.getSelectionModel().addListSelectionListener((var2x) -> {
         if (!var2x.getValueIsAdjusting() && this.tabela.getSelectedRow() != -1) {
            int var3 = this.tabela.convertRowIndexToModel(this.tabela.getSelectedRow());
            Object var4 = this.modeloTabela.getValueAt(var3, 0);
            this.tfId.setEditable(false);
            if (var4 != null) {
               int var5 = Integer.parseInt(var4.toString());

               try {
                  Livro var6 = (Livro)var1.getrLivro().buscar(var5);
                  this.tfId.setText(String.valueOf(var6.getId()));
                  this.tfNome.setText(var6.getNome());
                  this.tfGenero.setText(var6.getGenero());
                  this.tfValor.setText(String.valueOf(var6.getValor()));
                  this.tfAutor.setText(var6.getAutor());
               } catch (IDNaoExistenteExeception var7) {
                  System.err.println("Livro não encontrado: " + var5);
               }
            }
         }

      });
      JScrollPane var6 = new JScrollPane(this.tabela);
      var6.setBorder(BorderFactory.createTitledBorder("Livros"));
      var2.gridx = 1;
      var2.gridy = 0;
      var2.gridheight = 3;
      var2.weightx = 0.65;
      var2.weighty = 1.0;
      var2.fill = 1;
      this.add(var6, var2);
      this.CarregarTabelodoBanco();
   }

   private void CarregarTabelodoBanco() {
      this.modeloTabela.setRowCount(0);
      if (this.bd != null && this.bd.getrLivro() != null) {
         Iterator var1 = this.bd.getrLivro().getEntidades().iterator();

         while(var1.hasNext()) {
            Livro var2 = (Livro)var1.next();
            this.modeloTabela.addRow(new Object[]{var2.getId(), var2.getNome(), var2.getGenero(), var2.getValor(), var2.getAutor()});
         }

      }
   }

   private JPanel createSpacer() {
      JPanel var1 = new JPanel();
      var1.setOpaque(false);
      return var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2;
      if (var1.getSource() == this.btsv) {
         try {
            var2 = Integer.parseInt(this.tfId.getText().trim());
         } catch (NumberFormatException var11) {
            JOptionPane.showMessageDialog(this, "ID inválido. Digite um número inteiro.", "Erro", 0);
            return;
         }

         if (this.bd.getrLivro().idExiste(var2)) {
            JOptionPane.showMessageDialog(this, "ID já existe.", "Erro", 0);
            return;
         }

         float var3;
         try {
            var3 = Float.parseFloat(this.tfValor.getText().trim());
         } catch (NumberFormatException var10) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite um números.", "Erro", 0);
            return;
         }

         Livro var4 = new Livro(this.tfNome.getText(), this.tfGenero.getText(), var3, this.tfAutor.getText(), var2);
         this.bd.getrLivro().inserir(var4);
         this.modeloTabela.addRow(new Object[]{var2, var4.getNome(), var4.getGenero(), var4.getValor(), var4.getAutor()});
         this.LimpaCampos();
      } else {
         boolean var6;
         if (var1.getSource() == this.btal) {
            var2 = this.tabela.getSelectedRow();
            if (var2 == -1) {
               return;
            }

            this.tabela.convertRowIndexToModel(var2);

            int var13;
            try {
               var13 = Integer.parseInt(this.tfId.getText().trim());
            } catch (NumberFormatException var9) {
               JOptionPane.showMessageDialog(this, "ID inválido. Digite um número inteiro.", "Erro", 0);
               return;
            }

            float var5;
            try {
               var5 = Float.parseFloat(this.tfValor.getText().trim());
            } catch (NumberFormatException var8) {
               JOptionPane.showMessageDialog(this, "Valor inválido. Digite um números.", "Erro", 0);
               return;
            }

            var6 = false;
            Livro var7 = new Livro(this.tfNome.getText(), this.tfGenero.getText(), var5, this.tfAutor.getText(), var13);
            if (this.bd != null && this.bd.getrLivro() != null) {
               var6 = this.bd.getrLivro().alterar(var7);
               if (!var6) {
                  JOptionPane.showMessageDialog(this, "Falha ao alterar — ID não encontrado.", "Erro", 0);
                  return;
               }
            }

            if (var6) {
               this.CarregarTabelodoBanco();
               this.tabela.clearSelection();
               this.LimpaCampos();
            }
         } else if (var1.getSource() == this.btrm) {
            var2 = this.tabela.getSelectedRow();
            if (var2 == -1) {
               return;
            }

            int var12 = this.tabela.convertRowIndexToModel(var2);
            Object var14 = this.modeloTabela.getValueAt(var12, 0);
            if (var14 == null) {
               return;
            }

            int var15 = Integer.parseInt(var14.toString());
            if (this.bd != null && this.bd.getrLivro() != null) {
               var6 = this.bd.getrLivro().excluir(var15);
               if (!var6) {
                  JOptionPane.showMessageDialog(this, "Falha ao excluir — ID não encontrado.", "Erro", 0);
                  return;
               }
            }

            this.modeloTabela.removeRow(var12);
            this.LimpaCampos();
         } else if (var1.getSource() == this.btcn) {
            this.LimpaCampos();
            this.tabela.clearSelection();
         }
      }

   }

   private void LimpaCampos() {
      this.tfId.setText("");
      this.tfId.setEditable(true);
      this.tfNome.setText("");
      this.tfGenero.setText("");
      this.tfValor.setText("");
      this.tfAutor.setText("");
   }
}
