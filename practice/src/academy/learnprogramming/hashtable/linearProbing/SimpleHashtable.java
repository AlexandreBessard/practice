package academy.learnprogramming.hashtable.linearProbing;

import java.util.Hashtable;
//Handle collision
/*
When the key is already occupied, we increment the hash value by one and then check the result in the index.
Ex: we want a value to an existing key to 5 but 5 has already a value, we increment 5 + 1 = 6
If 6 is occupied, increment and try if 7 is free and so on.... Linear fashion.
 */
public class SimpleHashtable extends Hashtable {

    private StoredEmployee[] hashtable;

    public SimpleHashtable() {
        hashtable = new StoredEmployee[10];
    }

    public void put(String key, Employee employee) {
        int hashedKey = hashKey(key);
        if (occupied(hashedKey)) {
            //Linear probing
            //Set the very first probe
            int stopIndex = hashedKey; //If array full, loop back around to 0 index
            if (hashedKey == hashtable.length - 1) {
                hashedKey = 0; //Set to zero, we loop, avoid ArrayOutOfBoundException
            }
            else {
                hashedKey++;
            }

            while (occupied(hashedKey) && hashedKey != stopIndex) {
                hashedKey = (hashedKey + 1) % hashtable.length; //10 % 10 = 0 go back to the array
            }
        }
        if (occupied(hashedKey)) { //Array is full
            System.out.println("Sorry, there's already an employee at position " + hashedKey);
        }
        else {
            hashtable[hashedKey] = new StoredEmployee(key, employee);
        }
    }

    //Linear probing
    public Employee get(String key) {
        int hashedKey = findKey(key);
        if (hashedKey == -1) {
            return null;
        }
        return hashtable[hashedKey].employee;
    }

    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }

    private int findKey(String key) {
        int hashedKey = hashKey(key);
        if (hashtable[hashedKey] != null &&
                hashtable[hashedKey].key.equals(key)) {
            //Find our employee by comparing the hashKey and Raw key from 'StoredEmployee'
            return hashedKey;
        }
        //Linear probing
        int stopIndex = hashedKey;
        if (hashedKey == hashtable.length - 1) {
            hashedKey = 0;
        }
        else {
            hashedKey++;
        }

        while (hashedKey != stopIndex &&
                hashtable[hashedKey] != null &&
                ( ! hashtable[hashedKey].key.equals(key))) {
            hashedKey = (hashedKey + 1) % hashtable.length;
        }

        if (stopIndex == hashedKey) {
            return -1;
        }
        else {
            return hashedKey;
        }

    }

    private boolean occupied(int index) {
        return hashtable[index] != null;
    }

    public void printHashtable() {
        for (int i = 0; i < hashtable.length; i++) {
            if (hashtable[i] == null) {
                System.out.println("empty");
            }
            else {
                System.out.println("Position " + i + ": " +hashtable[i].employee);
            }
        }
    }

}
