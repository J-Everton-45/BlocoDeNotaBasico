public class BlocoDeNotas {

    private Anotacao[] anotacoes;
    private int quantidade;
    private int proximoId;

    public BlocoDeNotas() {
        anotacoes = new Anotacao[100]; // limite simples
        quantidade = 0;
        proximoId = 1;
    }

    public boolean adicionar(String texto) {
        if (quantidade >= anotacoes.length) {
            System.out.println("Bloco cheio! Não é possível adicionar mais anotações.");
            return false;
        }

        Anotacao a = new Anotacao(proximoId, texto);
        anotacoes[quantidade] = a;
        quantidade++;
        proximoId++;
        return true;
    }

    public boolean editarPorIndice(int indice, String novoTexto) {
        if (!validarIndice(indice)) {
            return false;
        }

        anotacoes[indice].setTexto(novoTexto);
        return true;
    }

    public boolean removerPorIndice(int indice) {
        if (!validarIndice(indice)) {
            return false;
        }

        anotacoes[indice].removerLogicamente();
        return true;
    }

    public void listarTodas() {
        boolean mostrou = false;

        for (int i = 0; i < quantidade; i++) {
            if (!anotacoes[i].isRemovida()) {
                System.out.println("Índice: " + i + " | " + anotacoes[i]);
                mostrou = true;
            }
        }

        if (!mostrou) {
            System.out.println("Não há anotações ativas para listar.");
        }
    }

    public void buscar(String textoBusca) {
        if (textoBusca == null || textoBusca.trim().equals("")) {
            System.out.println("Texto de busca não pode ser vazio.");
            return;
        }

        boolean encontrou = false;
        String busca = textoBusca.toLowerCase();

        for (int i = 0; i < quantidade; i++) {
            if (!anotacoes[i].isRemovida()) {
                String texto = anotacoes[i].getTexto().toLowerCase();
                if (texto.contains(busca)) {
                    System.out.println("Índice: " + i + " | " + anotacoes[i]);
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma anotação encontrada com esse texto.");
        }
    }

    private boolean validarIndice(int indice) {
        if (indice < 0 || indice >= quantidade) {
            System.out.println("Índice inválido. Use um índice entre 0 e " + (quantidade - 1) + ".");
            return false;
        }
        return true;
    }

    public boolean temAnotacaoAtiva() {
        for (int i = 0; i < quantidade; i++) {
            if (!anotacoes[i].isRemovida()) {
                return true;
            }
        }
        return false;
    }
}