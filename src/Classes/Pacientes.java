/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.util.Date;

/**
 *
 * @author 282138
 */
public class Pacientes {
    int codigo;
    String nome_pac;
    int fone;
    Date data_nasc;
    char sexo;

    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome_pac() {
        return nome_pac;
    }

    public void setNome_pac(String nome_pac) {
        this.nome_pac = nome_pac;
    }

    public int getFone() {
        return fone;
    }

    public void setFone(int fone) {
        this.fone = fone;
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
    
    public Pacientes() {
    }

     public Pacientes(String nome) {
         this.codigo = 0;
         this.nome_pac = nome;
         this.fone = 0;
         this.data_nasc = null;
         this.sexo ='m' ;
         
         
         
     }

    public Pacientes(int codigo, String nome_pac, int fone, Date data_nasc, char sexo) {
        this.codigo = codigo;
        this.nome_pac = nome_pac;
        this.fone = fone;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
    }
    

}

    
   
    
    

