import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Criar tabela se não existir
        ConexaoBD.criarTabelaSeNaoExistir();

        // Iniciar aplicação
        SwingUtilities.invokeLater(() -> {
            AgendaView view = new AgendaView();
            ContatoDAO dao = new ContatoDAO();
            new AgendaController(view, dao);
            view.setVisible(true);
        });
    }
}