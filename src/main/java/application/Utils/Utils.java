package application.Utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {
    public static <T> String formatList(List<T> list, Function<T, String> mapper) {
        if (list == null || list.isEmpty()) return "[]";
        return list.stream()
                .map(mapper)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
