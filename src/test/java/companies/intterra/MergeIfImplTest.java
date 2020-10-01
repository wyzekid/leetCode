package companies.intterra;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MergeIfImplTest {

    @Test
    public void mergeEmptyOrNull() {
        MergeIfImpl mergeIf = new MergeIfImpl(0);
        Map<String, Set<String>> resultMap = mergeIf.merge();
        assertTrue(areEqualMaps(new HashMap<>(), resultMap));
    }

    @Test
    public void mergeIncorrectInput() {
        MergeIfImpl mergeIf = new MergeIfImpl(4);

        mergeIf.addAccountFromStdIn(null);
        mergeIf.addAccountFromStdIn("\t\n");
        mergeIf.addAccountFromStdIn("  ->");
        mergeIf.addAccountFromStdIn("adsadad");

        Map<String, Set<String>> resultMap = mergeIf.merge();
        assertTrue(areEqualMaps(new HashMap<>(), resultMap));

    }

    @Test
    public void mergeWithoutDuplicateEmails() {
        MergeIfImpl mergeIf = new MergeIfImpl(5);

        mergeIf.addAccountFromStdIn("user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru");
        mergeIf.addAccountFromStdIn("user2 -> ups@pisem.net");
        mergeIf.addAccountFromStdIn("user3 -> xyz@pisem.net, vasya@pupkin.com");
        mergeIf.addAccountFromStdIn("user4 -> ups@yahoo.com, aaa@bbb.ru");
        mergeIf.addAccountFromStdIn("user5 -> xyz@gmail.com");

        Map<String, Set<String>> expectedMap = new HashMap<>();
        expectedMap.put("user1", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        expectedMap.put("user2", new HashSet<>(Collections.singletonList("ups@pisem.net")));
        expectedMap.put("user3", new HashSet<>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        expectedMap.put("user4", new HashSet<>(Arrays.asList("ups@yahoo.com", "aaa@bbb.ru")));
        expectedMap.put("user5", new HashSet<>(Collections.singletonList("xyz@gmail.com")));

        Map<String, Set<String>> resultMap = mergeIf.merge();

        assertTrue(areEqualMaps(expectedMap, resultMap));
    }

    @Test
    public void mergeWithDuplicateEmails() {
        MergeIfImpl mergeIf = new MergeIfImpl(5);

        mergeIf.addAccountFromStdIn("user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru");
        mergeIf.addAccountFromStdIn("user2 -> foo@gmail.com, ups@pisem.net");
        mergeIf.addAccountFromStdIn("user3 -> xyz@pisem.net, vasya@pupkin.com");
        mergeIf.addAccountFromStdIn("user4 -> ups@pisem.net, aaa@bbb.ru");
        mergeIf.addAccountFromStdIn("user5 -> xyz@pisem.net");

        Map<String, Set<String>> resultMap = mergeIf.merge();

        assertEquals(2, resultMap.size());

        Set<String> expectedFirstEmails = new HashSet<>(Arrays
                .asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"));
        Set<String> expectedSecondEmails = new HashSet<>(Arrays
                .asList("xyz@pisem.net", "vasya@pupkin.com"));

        for (Map.Entry<String, Set<String>> account : resultMap.entrySet()) {
            assertTrue(expectedFirstEmails.equals(account.getValue()) ||
                    expectedSecondEmails.equals(account.getValue()));
        }
    }

    private boolean areEqualMaps(Map<String, Set<String>> first, Map<String, Set<String>> second) {
        if (first.size() != second.size()) {
            return false;
        }
        return first.entrySet().stream()
                .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }
}