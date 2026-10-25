package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketTimeComparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AviaSoulsTest {

    @Test
    public void shouldFindZeroTicketsWhenNoMatch() {
        // 3) Находится 0 билетов (ни один не подходит)
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("MOW", "LED", 5000, 1000, 1200));
        manager.add(new Ticket("MOW", "LED", 3000, 800, 1100));

        Ticket[] expected = {};
        Ticket[] actual = manager.search("MOW", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindExactlyOneTicket() {
        // 2) Находится ровно один билет
        AviaSouls manager = new AviaSouls();

        Ticket correctTicket = new Ticket("MOW", "LED", 5000, 1000, 1200);
        manager.add(correctTicket);
        manager.add(new Ticket("MOW", "KZN", 3000, 800, 1100)); // Другой город назначения

        Ticket[] expected = { correctTicket };
        Ticket[] actual = manager.search("MOW", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMultipleTicketsSortedByPrice() {
        // 1) Находится несколько билетов (проверяем сортировку по цене)
        AviaSouls manager = new AviaSouls();

        Ticket expensive = new Ticket("MOW", "LED", 5000, 1000, 1200);
        Ticket cheap = new Ticket("MOW", "LED", 3000, 800, 1100);
        Ticket medium = new Ticket("MOW", "LED", 4000, 1400, 1500);

        manager.add(expensive);
        manager.add(cheap);
        manager.add(medium);

        Ticket[] expected = { cheap, medium, expensive };
        Ticket[] actual = manager.search("MOW", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindZeroTicketsWithCustomComparator() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("MOW", "LED", 5000, 1000, 1200));

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("LED", "MOW", new TicketTimeComparator());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindExactlyOneTicketWithCustomComparator() {
        AviaSouls manager = new AviaSouls();

        Ticket correctTicket = new Ticket("MOW", "LED", 5000, 1000, 1200);
        manager.add(correctTicket);
        manager.add(new Ticket("MOW", "KZN", 3000, 800, 1100));

        Ticket[] expected = { correctTicket };
        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", new TicketTimeComparator());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMultipleTicketsSortedByTime() {
        // 1) Находится несколько билетов (проверяем сортировку по времени полета)
        AviaSouls manager = new AviaSouls();

        Ticket flight2Hours = new Ticket("MOW", "LED", 5000, 1000, 1200); // 1200 - 1000 = 200 (2 часа)
        Ticket flight3Hours = new Ticket("MOW", "LED", 3000, 800, 1100);  // 1100 - 800 = 300 (3 часа)
        Ticket flight1Hour = new Ticket("MOW", "LED", 4000, 1400, 1500);  // 1500 - 1400 = 100 (1 час)

        manager.add(flight2Hours);
        manager.add(flight3Hours);
        manager.add(flight1Hour);

        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] expected = { flight1Hour, flight2Hours, flight3Hours };
        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", timeComparator);

        assertArrayEquals(expected, actual);
    }
}