/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Исключение на случай когда не выбрано событие 
 * @author Daniil
 */
public class UnspecifiedEventException extends Exception{
    private int errorCode;
    
    /** 
     * Создает новое исключение 
     */
    public UnspecifiedEventException()
    {
        super();
    }
    /**
     * Создает новое исключение на этапе выполнения с указанным сообщением
     * @param message сообщение ошибки
     */
    public UnspecifiedEventException(String message)
    {
        this(0, message);
    }
    /**
     * Переопределение конструктора
     * @param errorCode инициализация поля
     * @param message сообщение ошибки
     */
    public UnspecifiedEventException(int errorCode, String message)
    {
        // Вызываем конструктор предка
        super(message);
        // Добавляем инициализацию своего поля
        this.errorCode = errorCode;
    }
    /**
     * Метод для получени кода ошибки
     * @return код ошибки
     */
    public int getErrorCode()
    {
        return errorCode;
    }
    
    
}
