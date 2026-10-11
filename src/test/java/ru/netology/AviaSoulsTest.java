package ru.netology;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void shouldCompareTicketsByPrice() {
        Ticket cheap = new Ticket("MOW", "LED", 3000, 1000, 1200);
        Ticket expensive = new Ticket("MOW", "LED", 5000, 1400, 1600);

        assertTrue(cheap.compareTo(expensive) < 0);
        assertTrue(expensive.compareTo(cheap) > 0);
        assertEquals(0, cheap.compareTo(cheap));
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSouls manager = new AviaSouls();

        Ticket t1 = new Ticket("MOW", "LED", 5000, 1000, 1200); // 2 часа
        Ticket t2 = new Ticket("MOW", "LED", 3000, 1400, 1800); // 4 часа
        Ticket t3 = new Ticket("MOW", "LED", 4000, 800, 900); // 1 час
        Ticket t4 = new Ticket("MOW", "AER", 10000, 600, 1000); // Другой маршрут

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);
        manager.add(t4);

        Ticket[] expected = {t2, t3, t1}; // Отсортировано по цене: 3000, 4000, 5000
        Ticket[] actual = manager.search("MOW", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByTime() {
        Ticket longFlight = new Ticket("MOW", "LED", 5000, 1000, 1400); // 4 часа
        Ticket shortFlight = new Ticket("MOW", "LED", 3000, 1000, 1100); // 1 час

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertTrue(comparator.compare(shortFlight, longFlight) < 0);
        assertTrue(comparator.compare(longFlight, shortFlight) > 0);
    }

    @Test
    public void shouldSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();

        Ticket t1 = new Ticket("MOW", "LED", 5000, 1000, 1200); // 2 часа
        Ticket t2 = new Ticket("MOW", "LED", 3000, 1400, 1800); // 4 часа
        Ticket t3 = new Ticket("MOW", "LED", 4000, 800, 900); // 1 час

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {t3, t1, t2};
        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", comparator);

        assertArrayEquals(expected, actual);
    }
}