package br.prgomesr.debitoapi.util.remessa.bb;

import org.apache.commons.lang3.StringUtils;

public class CriarIdentificadorBanco {

    public static String criar(String agencia, String conta) {
        int soma = 0;
        int total = 0;
        int diferenca = 0;
        String digito = "";
        if (!agencia.isEmpty() && !conta.isEmpty()) {
            digito = agencia + conta;
            soma = agencia.length() + conta.length();
            total = 13;
            diferenca = total - soma + (agencia.length());

            if (agencia.length() + conta.length() < 13) {
                digito = StringUtils.rightPad(agencia, diferenca, "0") + conta;
            }
        }

        return digito;
    }
}
