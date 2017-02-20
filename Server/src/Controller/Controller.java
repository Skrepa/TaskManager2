/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.XML;
import Model.IDManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import Model.Journal;
import Model.Task;
import View.MainScreen;
import View.View;
import exception.UnspecifiedEventException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * класс для управления всей программой
 * @author Даниил
 */
public class Controller /*implements SysTrayMenuListener*/ {

    private static Controller instance;

    private Journal journal;

    private View view;
    
    private static final String FileName = "TaskManager.xml";
    
    /**
     * 
     * @return singleton instance
     */
    public static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    private Controller() 
    {
        journal = new Journal();
        try (InputStream in = new BufferedInputStream(new FileInputStream(FileName)))
        {
            journal = (Journal) XML.deserialize(in,Journal.class);
        } catch (Exception e) {
            View.showException(e);
        }
        IDManager.addUsed(journal.getIDs());
    }
    /**
     * Создание события
     * @param name название события
     * @param author автор события
     * @param data дата события
     * @param time время за которое нужно напомнить о событии
     * @param text текст события
     */
    public void createTask(String name, String author, Date data, long time, String text) 
    {
        try {
            Task task = new Task(name, author, data, time, text);
            journal.addTask(task);
            save();
            update();
        } catch (Exception e) {
            View.showException(e);
        }
    }
    /**
     * Удаление события
     * @param ID идентификатор события
     */
    public void deleteTask(int ID)
    {

        try {
            if (journal.getTask(ID) == null) {
                throw new UnspecifiedEventException("Вы не выбрали напоминание для удаления");//Exception
            }
            journal.deleteTask(ID);

            save();
            update();
        } catch (UnspecifiedEventException e) {
            View.showException(e);
        }
    }
    /**
     * Изменение события
     * @param ID идентификатор события
     * @param name название события
     * @param author автор события
     * @param data дата события
     * @param time время за которое нужно напомнить о событии
     * @param text текст события
     */
    public void setTask(int ID, String name, String author, Date data, long time, String text) 
    {
        {
            try {
                Task task = journal.getTask(ID);
                task.setName(name);
                task.setAuthor(author);
                task.setDate(data);
                task.setTime(time);
                task.setText(text);
                save();
                update();
            } catch (Exception e) {
                View.showException(e);
            }
        }

    }
    /**
     * получение события по его идентфиатору
     * @param ID
     * @return событие
     */
    public Task getTask(int ID) 
    {
        try {
            return journal.getTask(ID);
        } catch (Exception e) {
            View.showException(e);
            throw e;
        }
    }
    /**
     * сохранение журнала
     */
    public synchronized void save() 
    {
        synchronized (journal) 
        {
            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(FileName)))
            {
                XML.serialize(journal, out);
                out.flush();
            } catch (Exception e) {
                View.showException(e);
            }
        }
    }
    /**
     * Открытие окна журнала
     */
    public void openView() 
    {
        this.view = new MainScreen();
        view.setVisible(true);//true
        update();
    }
    /**
     * Получение журнала
     * @return журнал
     */
    public Journal getJournal(){
        return journal;
    }
    public void update(){
        if(view!=null)
            view.update(journal);
    }
}
