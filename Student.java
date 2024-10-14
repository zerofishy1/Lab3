import java.util.LinkedList;
public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private double averageGrade;


    public Student(String firstName, String lastName, int age, double averageGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.averageGrade = averageGrade;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    // Переопределение метода toString() для удобного вывода информации о студенте
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
class HashTable<K, V> {
    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public HashTable(int capacity) {
        // Приведение типов для создания массива связанных списков
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        size = 0;
    }


    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }


    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }


    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }
}
class Main {
    public static void main(String[] args) {

        HashTable<String, Student> studentTable = new HashTable<>(10);

        // Добавление студентов в хэш-таблицу
        studentTable.put("ZK123", new Student("John", "Doe", 20, 4.5));
        studentTable.put("ZK124", new Student("Jane", "Smith", 22, 4.8));
        studentTable.put("ZK125", new Student("Michael", "Johnson", 21, 4.2));

        // Получение информации о студентах по номеру зачетной книжки
        System.out.println("Student ZK123: " + studentTable.get("ZK123"));
        System.out.println("Student ZK124: " + studentTable.get("ZK124"));
        System.out.println("Student ZK125: " + studentTable.get("ZK125"));

        // Удаление студента
        studentTable.remove("ZK124");

        // Проверка размера таблицы
        System.out.println("Size after removing ZK124: " + studentTable.size());

        // Попытка получить студента, которого больше нет
        System.out.println("Student ZK124 after removal: " + studentTable.get("ZK124"));

        // Проверка на пустоту
        System.out.println("Is table empty? " + studentTable.isEmpty());
    }
}

