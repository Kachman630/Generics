package ru.netology;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        // Вычисляем длительность полета для каждого билета
        int duration1 = t1.getTimeTo() - t1.getTimeFrom();
        int duration2 = t2.getTimeTo() - t2.getTimeFrom();

        return Integer.compare(duration1, duration2);
    }
}
