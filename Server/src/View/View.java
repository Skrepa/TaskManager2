/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Journal;
import javax.swing.JOptionPane;

/**
 * интерфейс 
 * @author Даниил
 */
public interface View 
{
    /**
     * обновление обрабатываемого журнала
     * @param journal журнал событий
     */
    void update(Journal journal);
    /**
     * показать или скрыть окно на экране
     * @param b true or false
     */
    void setVisible(boolean b);
    
    
    /**
     * показать сообщение об ошибке пользователю
     * @param e сообщение ошибки
     */
    public static void showException(Exception e) 
    {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Eггог", JOptionPane.ERROR_MESSAGE);
    }
}
