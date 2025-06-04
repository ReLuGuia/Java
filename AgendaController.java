import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendaController {
    private AgendaView view;
    private ContatoDAO dao;
    private DefaultTableModel tableModel;

    public AgendaController(AgendaView view, ContatoDAO dao) {
        this.view = view;
        this.dao = dao;
        this.tableModel = (DefaultTableModel) view.getTabela().getModel();
        
        carregarContatos();
        configurarListeners();
    }

    private void carregarContatos() {
        tableModel.setRowCount(0);
        for (Contato contato : dao.listar()) {
            tableModel.addRow(new Object[]{
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getTelefone()
            });
        }
    }

    private void configurarListeners() {
        view.getBtnAdicionar().addActionListener(e -> adicionarContato());
        view.getBtnEditar().addActionListener(e -> editarContato());
        view.getBtnExcluir().addActionListener(e -> excluirContato());
        view.getBtnLimpar().addActionListener(e -> limparCampos());
        
        view.getTabela().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherCamposComSelecionado();
            }
        });
    }

    private void adicionarContato() {
        String nome = view.getCampoNome().getText();
        String email = view.getCampoEmail().getText();
        String telefone = view.getCampoTelefone().getText();

        if (validarCampos(nome, email, telefone)) {
            Contato novo = new Contato(nome, email, telefone);
            dao.salvar(novo);
            carregarContatos();
            limparCampos();
        }
    }

    private void editarContato() {
        int linhaSelecionada = view.getTabela().getSelectedRow();
        if (linhaSelecionada != -1) {
            int id = (int) tableModel.getValueAt(linhaSelecionada, 0);
            String nome = view.getCampoNome().getText();
            String email = view.getCampoEmail().getText();
            String telefone = view.getCampoTelefone().getText();

            if (validarCampos(nome, email, telefone)) {
                Contato contato = new Contato(nome, email, telefone);
                contato.setId(id);
                dao.atualizar(contato);
                carregarContatos();
                limparCampos();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione um contato para editar.");
        }
    }

    private void excluirContato() {
        int linhaSelecionada = view.getTabela().getSelectedRow();
        if (linhaSelecionada != -1) {
            int id = (int) tableModel.getValueAt(linhaSelecionada, 0);
            dao.excluir(id);
            carregarContatos();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(view, "Selecione um contato para excluir.");
        }
    }

    private void preencherCamposComSelecionado() {
        int linhaSelecionada = view.getTabela().getSelectedRow();
        if (linhaSelecionada != -1) {
            view.getCampoNome().setText(tableModel.getValueAt(linhaSelecionada, 1).toString());
            view.getCampoEmail().setText(tableModel.getValueAt(linhaSelecionada, 2).toString());
            view.getCampoTelefone().setText(tableModel.getValueAt(linhaSelecionada, 3).toString());
        }
    }

    private void limparCampos() {
        view.getCampoNome().setText("");
        view.getCampoEmail().setText("");
        view.getCampoTelefone().setText("");
        view.getTabela().clearSelection();
    }

    private boolean validarCampos(String nome, String email, String telefone) {
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos são obrigatórios.");
            return false;
        }
        return true;
    }
}