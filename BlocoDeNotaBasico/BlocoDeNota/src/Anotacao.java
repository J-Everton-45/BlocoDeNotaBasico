import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Anotacao {

    private int id;
    private String texto;
    private LocalDateTime dataCriacao;
    private boolean removida;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Anotacao(int id, String texto) {
        this.id = id;

        if (texto == null || texto.trim().equals("")) {
            this.texto = "Anotação vazia";
        } else {
            this.texto = texto;
        }

        this.dataCriacao = LocalDateTime.now();
        this.removida = false;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public boolean isRemovida() {
        return removida;
    }

    public boolean setTexto(String novoTexto) {
        if (removida) {
            System.out.println("Não é possível editar: anotação está removida.");
            return false;
        }

        if (novoTexto == null || novoTexto.trim().equals("")) {
            System.out.println("Novo texto não pode ser vazio.");
            return false;
        }

        this.texto = novoTexto;
        return true;
    }

    public boolean removerLogicamente() {
        if (removida) {
            System.out.println("Anotação já está removida.");
            return false;
        }

        this.removida = true;
        return true;
    }

    @Override
    public String toString() {
        String status = removida ? "REMOVIDA" : "ATIVA";
        return "ID: " + id +
                " | Data: " + dataCriacao.format(FORMATO) +
                " | Status: " + status +
                " | Texto: " + texto;
    }
}