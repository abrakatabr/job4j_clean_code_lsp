package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.isp.menu.SimpleMenu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.*;

class PrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    private void setOutput() {
     System.setOut(new PrintStream(output));
    }

    @Test
    public void whenPrintThenOk() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        String sep = System.lineSeparator();
        StringBuilder expected = new StringBuilder();
        expected.append("1.Сходить в магазин").append(sep)
                .append("----1.1.Купить продукты").append(sep)
                .append("--------1.1.1.Купить хлеб").append(sep)
                .append("--------1.1.2.Купить молоко").append(sep)
                .append("2.Покормить собаку").append(sep);
        Printer printer = new Printer();
        printer.print(menu);
        assertThat(output.toString()).isEqualTo(expected.toString());
    }

    @AfterEach
    private void setSystemOutput() {
        System.setOut(System.out);
    }
}