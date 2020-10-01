package companies.intterra;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MergerIf {

    /*
     * Обработать следующую строку: user -> [email1,email2]
     */
    void add(String name, Collection<String> emails);

    /*
     * Получить результат работы алгоритма
     */
    Map<String, Set<String>> merge();

}
