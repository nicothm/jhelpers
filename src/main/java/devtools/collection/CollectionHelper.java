package devtools.collection;


import java.util.Collection;

public final class CollectionHelper {
    private CollectionHelper() {}

    public static <T> String mkString(final Collection<T> c, final String delimiter) {
        StringBuilder sb = new StringBuilder();

        for(T item : c) {
            sb.append(item+",");
        }

        return sb.substring(0, sb.length()-1).toString();
    }
}
