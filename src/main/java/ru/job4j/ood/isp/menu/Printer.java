package ru.job4j.ood.isp.menu;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        StringBuilder builder = new StringBuilder();
        String sep = System.lineSeparator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo item = iterator.next();
            String number = item.getNumber();
            String tab = "----";
            Pattern pattern = Pattern.compile("\\.");
            Matcher matcher = pattern.matcher(number);
            matcher.find();
            while (matcher.find()) {
                builder.append(tab);
            }
            builder.append(number).append(item.getName()).append(sep);
        }
        System.out.print(builder.toString());
    }
}
