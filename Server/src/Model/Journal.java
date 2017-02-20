/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import exception.NonexistentIDException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * журнал содержащий события
 * @author Даниил
 */
@XmlRootElement(name = "journal")
@XmlType(propOrder = {"arr"})
public class Journal implements XMLSerializable {

    public HashMap<Integer, Task> arr = new HashMap<>();

    /**
     * добавление события
     * @param task
     */
    public void addTask(Task task) {
        arr.put(task.getId(), task);
    }
    /**
     * получение события по идентификатору
     * @param ID идентификатор
     * @return событие
     */
    public Task getTask(int ID) {
        return arr.get(ID);
    }
    /**
     * @return все идентификаторы
     */
    public Set<Integer> getIDs() {
        return arr.keySet();
    }
    /**
     * удаление события по его идентификатору
     * @param ID идентификатор
     */
    public void deleteTask(int ID)  {
        if (!arr.containsKey(ID)) {
            try {
                throw new NonexistentIDException();
            } catch (NonexistentIDException ex) {
            }
        }
        arr.remove(ID);
    }
}
