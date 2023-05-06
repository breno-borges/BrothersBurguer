/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

/**
 *
 * @author breno
 */
public class Lanches {

    private int id;
    private String nome;
    private double preco;
    private Bebida idBebida;
    private Bebida nomeBebida;

    private ComponentesLanche idComponenteLanche;
    private ComponentesLanche nomeComponente;

    public Lanches() {
    }

    public Lanches(int id, String nome, double preco, Bebida idBebida, Bebida nomeBebida, ComponentesLanche idComponenteLanche, ComponentesLanche nomeComponente) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.idBebida = idBebida;
        this.nomeBebida = nomeBebida;
        this.idComponenteLanche = idComponenteLanche;
        this.nomeComponente = nomeComponente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Bebida getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(Bebida idBebida) {
        this.idBebida = idBebida;
    }

    public Bebida getNomeBebida() {
        return nomeBebida;
    }

    public void setNomeBebida(Bebida nomeBebida) {
        this.nomeBebida = nomeBebida;
    }

    public ComponentesLanche getIdComponenteLanche() {
        return idComponenteLanche;
    }

    public void setIdComponenteLanche(ComponentesLanche idComponenteLanche) {
        this.idComponenteLanche = idComponenteLanche;
    }

    public ComponentesLanche getNomeComponente() {
        return nomeComponente;
    }

    public void setNomeComponente(ComponentesLanche nomeComponente) {
        this.nomeComponente = nomeComponente;
    }

}
