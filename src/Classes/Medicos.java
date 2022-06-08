/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.util.Date;


public class Medicos {

    private int id;
    private String nome_med;
    private String especialidade;
    private Date data_nasc;
    private char sexo;
    private int fone;
    
    public Medicos() {
    }

    public Medicos(int id, String nome_med, String especialidade, Date data_nasc, char sexo, int fone) {
        this.id = id;
        this.nome_med = nome_med;
        this.especialidade = especialidade;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.fone = fone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_med() {
        return nome_med;
    }

    public void setNome_med(String nome_med) {
        this.nome_med = nome_med;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getFone() {
        return fone;
    }

    public void setFone(int fone) {
        this.fone = fone;
    }

    

    
    
    
}
