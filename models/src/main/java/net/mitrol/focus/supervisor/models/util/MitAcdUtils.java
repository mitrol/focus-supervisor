package net.mitrol.focus.supervisor.models.util;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

public class MitAcdUtils {

    private static final char SEPARATOR = ';';

    public static Integer[] StrToIntSin0(String value) {
        if (Strings.isNullOrEmpty(value))
            return new Integer[0];

        String[] strings = value.split(Character.toString(SEPARATOR)); // String.split borra los vacíos del final

        if (strings.length == 1 && Strings.isNullOrEmpty(strings[0]))
            return new Integer[0];

        List<Integer> list = new ArrayList<>();
        for (String s : strings) {
            Integer n;
            try {
                n = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                n = 0;
            }
            list.add(n);
        }

        Integer[] ret = new Integer[list.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = list.get(i);
        return ret;
    }

    public static String IntToStrSin0(Integer[] values) {
        if (values == null)
            return null;

        StringBuilder sb = new StringBuilder();
        for (int n : values) {
            if (n != 0)
                sb.append(n);
            sb.append(SEPARATOR);
        }

        if (sb.length() == 0)
            return "";

        while (sb.charAt(sb.length() - 1) == SEPARATOR)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
