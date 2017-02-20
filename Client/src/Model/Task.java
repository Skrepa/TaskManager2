/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import javax.xml.bind.annotation.*;

/**
 * событие
 * @author Даниил
 */
@XmlRootElement(name = "task")
@XmlType(propOrder = {"author","name","text","date","time","id"})
public class Task implements XMLSerializable
{
    private String author;
    private String name;
    private String text;
    private Date date;
    private long time;
    private int id;
    
    public enum Status 
    {
        /**
         * событие было добавлено но еще не началось
         */
        ACTIVE,
        /**
         * событие в процессе 
         */
        IN_PROGRESS,
        /**
         * событие завершено
         */
        COMPLETED
    }
    /**
     * @return статус события
     */
    public Status getStatus() 
    {
        Date left = new Date(getDate().getTime() - getTime() * 1000);
        Date now = new Date();
        if (now.before(left)) {
            return Status.ACTIVE;
        }
        if (now.before(getDate())) {
            return Status.IN_PROGRESS;
        }
        return Status.COMPLETED;
    }
    /**
     * @return идентификатор
     */
    @XmlElement
    public void setId(int id) 
    {
        this.id=id;
    }
    public int getId() 
    {
        return id;
    }
    /**
     * @param author автор события
     */
    @XmlElement
    public void setAuthor(String author) 
    {
        this.author = author;
    }
    /**
     * @param name имя автора
     */
    @XmlElement
    public void setName(String name) 
    {
        this.name = name;
    }
    /**
     * @param text текст события
     */
    @XmlElement
    public void setText(String text) 
    {
        this.text = text;
    }
    /**
     * @param date дата события
     */
    @XmlElement
    public void setDate(Date date) 
    {
        this.date = date;
    }
    /**
     * @param time время за которое нужно предупредить о событии
     */
    @XmlElement
    public void setTime(long time) 
    {
        this.time = time;
    }
    public Task(){}
    /**
     * конструктор
     * @param name название события
     * @param author имя автора
     * @param data дата события
     * @param time время за которое нужно предупредить о событии
     * @param text текст события
     */
    public Task(String name, String author, Date data, long time, String text) 
    {
        id = IDManager.nextID();
        this.author = author;
        this.name = name;
        this.text = text;
        this.date = data;
        this.time = time;
    }
    /**
     * @return имя автора
     */
    public String getAuthor() 
    {
        return author;
    }
    /**
     * 
     * @return название события
     */
    public String getName() 
    {
        return name;
    }
    /**
     * 
     * @return текст события
     */
    public String getText() 
    {
        return text;
    }
    /**
     * 
     * @return дата события
     */
    public Date getDate() 
    {
        return date;
    }
    /**
     * 
     * @return время за которое нужно предупредить о событии
     */
    public long getTime() 
    {
        return time;
    }
    /**
     * сравнение объекта по параметру
     * @param o событие
     * @return true or false
     */
    @Override
    public boolean equals(Object o) 
    {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task other = (Task) o;
        if (!author.equals(other.author)) {
            return false;
        }
        if (!name.equals(other.name)) {
            return false;
        }
        if (!text.equals(other.text)) {
            return false;
        }
        if (!date.equals(other.date)) {
            return false;
        }
        if (time != other.time) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode(){
        return id;
    }
    /**
     * преобразование в строку
     * @return строка содержащая имя автора, название события, дату события, за какое время до события нужно о нем напомнить и текст события
     */
    @Override
    public String toString() 
    {
        String a = "Автор:" + author + "\n" + "Название:" + name + "\n" + "Дата:" + date + "\n" + "Напомнить за:" + time + "\n" + "Текст:" + text;
        return a;
    }

}
