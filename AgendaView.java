

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgendaView extends JFrame {
    private JTable tabela;
    private DefaultTableModel tableModel;
    private JTextField campoNome, campoEmail, campoTelefone;
    private JButton btnAdicionar, btnEditar, btnExcluir, btnLimpar;

    public AgendaView() {
        setTitle("Agenda de Contatos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");

        // Componentes
        tabela = new JTable(tableModel);
        campoNome = new JTextField(20);
        campoEmail = new JTextField(20);
        campoTelefone = new JTextField(20);
        
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        // Layout
        JPanel painelForm = new JPanel(new GridLayout(3, 2, 5, 5));
        painelForm.add(new JLabel("Nome:"));
        painelForm.add(campoNome);
        painelForm.add(new JLabel("Email:"));
        painelForm.add(campoEmail);
        painelForm.add(new JLabel("Telefone:"));
        painelForm.add(campoTelefone);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        add(painelForm, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // Getters
    public JTable getTabela() { return tabela; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTextField getCampoNome() { return campoNome; }
    public JTextField getCampoEmail() { return campoEmail; }
    public JTextField getCampoTelefone() { return campoTelefone; }
    public JButton getBtnAdicionar() { return btnAdicionar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnExcluir() { return btnExcluir; }
    public JButton getBtnLimpar() { return btnLimpar; }
}