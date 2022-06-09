/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author letissia
 */
public class ErpDAOException extends Exception{
 public ErpDAOException(){

    }

    public ErpDAOException(String arg){
        super(arg);
        System.out.println("Erro"+ arg);
    }

    public ErpDAOException(Throwable arg){
        super(arg);
        System.out.println("Erro" + arg);
    }

    public ErpDAOException(String arg, Throwable arg1){
        super(arg, arg1);
        System.out.println("Erro" + arg);
    }

}
