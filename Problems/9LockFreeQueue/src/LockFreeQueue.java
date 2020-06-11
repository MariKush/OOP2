/*
CAS (Compare and Set) - это принцип параллельного программирования, суть
которого заключается в следующем:
1. Читаем значение переменной;
2. Производим некоторую обработку;
3. Производим проверку текущего значения переменной в предположении, что
значение все еще равно старому;
4. Если значение было изменено другим потоком, то повторяем обработку.

Неблокирующая очередь - это вариант очереди, реализованной на потоках. Это
означает, что базовые операции вставки и удаления выполняются быстрее за счёт
того, что над ними работают несколько обработчиков. Однако, во избежание
блокировки потоков, в этой очереди используется принцип CAS.
 */

import java.util.concurrent.atomic.AtomicReference;

// Черга буде представлена у вигляді списку вузлів
public class LockFreeQueue<T> {

    /*
    Черга містить 2 поля AtomicReference<Node>: посилання head на перший вузол у
    списку та посилання tail - на останній. Перший вузол у списку для зручності 
    буде вважатись порожнім (незалежно від значення), або сигнальним.
     */
    private AtomicReference<Node> head, tail;

    public LockFreeQueue() {
        head = new AtomicReference(new Node(null));
        tail = head;
    }

    public class Node {

        private T value;

        // Посилання AtomicReference<Node> на наступний вузол у черзі
        private AtomicReference<Node> next;

        public Node(T v) {
            value = v;
            next = new AtomicReference<>(null);
        }
    }

    /*
    Зчитує, вилучає та повертає значення першого елемента черги (не рахуючи 
    сигнальний вузол).
     */
    public T deq() throws IllegalStateException {
        while (true) {
            Node first = head.get();
            Node last = tail.get();
            Node next = first.next.get();

            if (first == head.get()) {
                // Якщо голова і хвіст вказують на один вузол
                if (first == last) {
                    /*
                    Перевірка черги на порожність шляхом з'ясування, чи не
                    вказує поле next голови черги на null.
                     */
                    if (next == null) {
                        throw new IllegalStateException("The Queue is empty");
                    }

                    // Якщо хвіст не на останньому місці, переносимо його далі
                    tail.compareAndSet(last, next);
                } else {
                    // Зчитуємо значення з наступника сигнального вузла
                    T value = next.value;

                    /*
                    Викликаємо процедуру CAS, щоб змістити голову черги з
                    сигнального вузла на його наступника. У такий спосіб, цей
                    наступник стане новим сигнальним вузлом.
                     */
                    if (head.compareAndSet(first, next)) {
                        return value;
                    }
                }
            }
        }
    }

    // Виведення черги на екран
    public void show(LockFreeQueue Q) {
        Node first = head.get();
        Node last = tail.get();
        while (first != last.next.get()) {
            if (first != head.get()) {
                System.out.print(first.value + " ");
            }
            first = first.next.get();
        }
        System.out.println();
    }

    // Додавання в кінець черги
    public void enq(T value) {
        // Створення нового вузла із заданим значенням для додавання
        Node node = new Node(value);

        // Знаходження останнього вузла в черзі
        while (true) {
            Node last = tail.get();
            Node next = last.next.get();

            if (last == tail.get()) {

                /*
                Щоб упевнитись, що вузол дійсно останній, перевіряємо, чи не має
                він наступника. Якщо виявився останнім - виконується спроба
                додавання.
                 */
                if (next == null) {

                    /*
                    Виклик CAS для додавання нового вузла. Це необхідно робити
                    через CAS, оскільки інший потік може намагатись зробити те ж
                    саме.
                     */
                    if (last.next.compareAndSet(next, node)) {
                        tail = new AtomicReference(node);
                        return;
                    }
                } else {

                    /*
                    Якщо вузол, на який вказував tail, мав наступника, інший
                    потік намагається виправити ситуацію перед тим, як
                    повернутись до операції вставки вузла.
                     */
                    tail.compareAndSet(last, next);
                }
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("Code built successfully!");
    }
}
