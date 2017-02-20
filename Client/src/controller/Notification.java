package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Task;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * нотификация о событии
 * @author Daniil
 */
public class Notification extends Component {
    private static Notification instance;
    /**
     * @return singleton instance
     */
    public static Notification getInstance(){
        if(instance == null)
            instance = new Notification();
        return instance;
    }
    private Notification(){}
    /**
     * содержание нотификации
     * @param t событие
     */
    public void show(Task t){
        JOptionPane.showMessageDialog(this, t.getName() + "\n" + t.getText() + "\n" + t.getDate(), "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public void showInfo(String e) 
    {
        JOptionPane.showMessageDialog(this, e, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    public void showException(Exception e) 
    {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Eггог", JOptionPane.ERROR_MESSAGE);
    }
}
