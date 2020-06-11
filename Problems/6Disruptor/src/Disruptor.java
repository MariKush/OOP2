/*
Disruptor - это паттерн параллельного проектирования. Он обеспечивает очень
высокоскоростной, низколатентный (с низкой задержкой) способ обмена информацией
между потоками. Другими словами, его целью является поиск такого варианта
обработки большого количества операций, при котором возможная задержка будет
минимальной (избегая при этом сложности параллельного кода).
 */
public class Disruptor implements Runnable {

    // Количество итераций для обработки операций (10 миллионов)
    public static final long ITERATIONS = 10L * 1000L * 1000L;

    /*
    Использование счетчиков с модификатором volatile, благодаря которому
    виртуальная машина Java будет вставлять инструкцию блокировки для обновления
    значений счётчиков, которая предоставляет эффективный барьер для памяти.
     */
    public static volatile long s1, s2;

    @Override
    public void run() {
        long value = s2;
        while (true) {
            while (value == s1) {
                // Выполнение итерации
            }
            value = ++s2;
        }
    }

    /*
    В результате работы не должно возникнуть зависания и значения счётчиков
    должны сойтись.
     */
    public static void main(String[] args) {
        Thread t = new Thread(new Disruptor());
        t.setDaemon(true);
        t.start();

        long start = System.nanoTime(), value = s1;
        while (s1 < ITERATIONS) {
            while (s2 != value) {
                // busy spin
            }
            value = ++s1;
        }
        long duration = System.nanoTime() - start;

        System.out.println("Duration: " + duration + " nanoseconds");
        System.out.println("Nanoseconds per operation: " + duration / (ITERATIONS * 2));
        System.out.println("Operations per second: " + (ITERATIONS * ITERATIONS * 2 * 2) / duration);
        System.out.println("s1 = " + s1 + ", s2 = " + s2);

        /*
        Если удалить модификаторы volatile, принцип паттерна Disruptor будет
        нарушен, что приведет к зависанию программы из-за слишком большого
        объема обрабатываемых данных без блокирующего барьера.
         */
    }
}
