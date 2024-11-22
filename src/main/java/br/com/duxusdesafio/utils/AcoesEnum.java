package br.com.duxusdesafio.utils;

public enum AcoesEnum {
    SALVAR("salvar", "Salvar um objeto"),
    OBTER("obter", "Obter um ou mais objetos"),
    ATUALIZAR("atualizar", "Atualizar um objeto criado e persistido"),
    EXCLUIR("excluir", "Remover um objeto criado e persistido"),
    LISTAR("listar", "Listar objetos");

    private final String acao;
    private final String descricao;

    AcoesEnum(String acao, String descricao) {
        this.acao = acao;
        this.descricao = descricao;
    }

    public String getAcao() {
        return acao;
    }

    public String getDescricao() {
        return descricao;
    }
}
