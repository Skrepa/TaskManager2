/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Исключение на случай когда не существует идентификатор
 * @author Daniil
 */
public class NonexistentIDException extends Exception{
    
    /** 
     * Создает новое исключение 
     */
    public NonexistentIDException()
    {
        super();
    }
    
}
