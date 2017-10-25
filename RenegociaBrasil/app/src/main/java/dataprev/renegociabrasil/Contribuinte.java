package dataprev.renegociabrasil;

/**
 * Created by Administrador on 25/10/2017.
 */

public class Contribuinte {

    private String numeroinscricao;
    private String nome;
    private boolean possuidebitos;

    public Contribuinte(String numeroinscricao, String nome, boolean possuidebitos) {
        this.numeroinscricao = numeroinscricao;
        this.nome = nome;
        this.possuidebitos = possuidebitos;
    }

    public String getNumeroinscricao() {
        return numeroinscricao;
    }

    public void setNumeroinscricao(String numeroinscricao) {
        this.numeroinscricao = numeroinscricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPossuidebitos() {
        return possuidebitos;
    }

    public void setPossuidebitos(boolean possuidebitos) {
        this.possuidebitos = possuidebitos;
    }
}
