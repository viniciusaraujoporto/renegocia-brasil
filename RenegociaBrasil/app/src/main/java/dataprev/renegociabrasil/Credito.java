package dataprev.renegociabrasil;

import java.security.Principal;

/**
 * Created by Administrador on 25/10/2017.
 */

public class Credito {

    public String getNumerocredito() {
        return numerocredito;
    }

    public void setNumerocredito(String numerocredito) {
        this.numerocredito = numerocredito;
    }

    public String getValorprincipal() {
        return valorprincipal;
    }

    public void setValorprincipal(String valorprincipal) {
        this.valorprincipal = valorprincipal;
    }

    public String getValormulta() {
        return valormulta;
    }

    public void setValormulta(String valormulta) {
        this.valormulta = valormulta;
    }

    public String getValorjuros() {
        return valorjuros;
    }

    public void setValorjuros(String valorjuros) {
        this.valorjuros = valorjuros;
    }

    public Credito(String numerocredito, String valorprincipal, String valormulta, String valorjuros, String valortotal) {
        this.numerocredito = numerocredito;
        this.valorprincipal = valorprincipal;
        this.valormulta = valormulta;
        this.valorjuros = valorjuros;
        this.valortotal = valortotal;
    }

    private String numerocredito;
    private String valorprincipal;
    private String valormulta;
    private String valorjuros;
    private String valortotal;

    public String getValortotal() {
        return valortotal;
    }

    public void setValortotal(String valortotal) {
        this.valortotal = valortotal;
    }
}
