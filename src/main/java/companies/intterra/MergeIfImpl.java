package companies.intterra;

import java.util.*;

/**
 * Имеется n пользователей, каждому из них соответствует список email-ов
 * (всего у всех пользователей m email-ов).
 * Например:
 * user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru
 * user2 -> foo@gmail.com, ups@pisem.net
 * user3 -> xyz@pisem.net, vasya@pupkin.com
 * user4 -> ups@pisem.net, aaa@bbb.ru
 * user5 -> xyz@pisem.net
 * Считается, что если у двух пользователей есть общий email, значит это один
 * и тот же пользователь. Требуется построить
 * и реализовать алгоритм, выполняющий слияние пользователей. На выходе
 * должен быть список пользователей с их email-ами (такой же как на входе).
 * В качестве имени объединенного пользователя можно брать любое из исходных
 * имен. Список email-ов пользователя должен содержать только уникальные
 * email-ы.
 * Параметры n и m произвольные, длина конкретного списка email-ов никак не
 * ограничена.
 * Требуется, чтобы асимптотическое время работы полученного решения было
 * линейным, или близким к линейному.
 * Возможный ответ на задачу в указанном примере:
 * user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru, ups@pisem.net, aaa@bbb.ru
 * user3 -> xyz@pisem.net, vasya@pupkin.com
 */
public class MergeIfImpl implements MergerIf {

    private Map<String, Collection<String>> accounts;
    private int accountSize;

    public MergeIfImpl(int accountSize) {
        accounts = new HashMap<>(accountSize);
        this.accountSize = accountSize;
    }

    @Override
    public void add(String name, Collection<String> emails) {
        if (this.accounts != null) {
            accounts.put(name, emails);
        }
    }

    @Override
    public Map<String, Set<String>> merge() {
        if (this.accounts == null || this.accounts.isEmpty()) {
            System.out.println("Accounts map is empty, no account to merge");
            return new HashMap<>(0);
        }
        Map<String, Integer> emailToIdx = new HashMap<>();
        Map<Integer, String> idxToName = new HashMap<>();
        int emailSize = accounts.values().stream()
                .filter(Objects::nonNull)
                .mapToInt(Collection::size)
                .sum();
        DisjointUnion emailUnion = new DisjointUnion(emailSize);
        int idx = 0;
        for (Map.Entry<String, Collection<String>> account : accounts.entrySet()) {
            String accountName = account.getKey();
            Collection<String> emails = account.getValue();
            String accHeadEmail = (String) emails.toArray()[0];
            for (String email : emails) {
                if (email == null || email.isEmpty()) {
                    continue;
                }
                emailToIdx.putIfAbsent(email, idx);
                idxToName.put(idx, accountName);
                if (!email.equals(accHeadEmail)) {
                    emailUnion.union(emailToIdx.get(accHeadEmail), emailToIdx.get(email));
                }
                idx++;
            }
        }

        Map<Integer, List<String>> emailClusters = new HashMap<>();
        for (String email : emailToIdx.keySet()) {
            int parentId = emailUnion.find(emailToIdx.get(email));
            emailClusters.computeIfAbsent(parentId, emails -> new ArrayList<>()).add(email);
        }

        Map<String, Set<String>> result = new HashMap<>();
        for (Integer index : emailClusters.keySet()) {
            result.computeIfAbsent(idxToName.get(index), emails -> new HashSet<>()).addAll(emailClusters.get(index));
        }

        System.out.println(String
                .format("%d accounts merged, result size is %d accounts", accountSize, result.keySet().size()));
        System.out.println(accounts);
        return result;
    }

    public void addAccountFromStdIn(String stdInputString) {
        try {
            if (stdInputString == null || stdInputString.isEmpty()) {
                System.out.println("Input string is null or empty, no accounts added");
                return;
            }
            String[] nameEmails = stdInputString
                    .replaceAll(" ", "")
                    .split("->");
            List<String> emails = Arrays.asList(nameEmails[1].split(","));
            add(nameEmails[0], emails);
        } catch (Exception e) {
            System.out.println("Incorrect input data format, no accounts added");
        }
    }

    public Map<String, Collection<String>> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Collection<String>> accounts) {
        this.accounts = accounts;
    }

    public int getAccountSize() {
        return accountSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MergeIfImpl{");
        sb.append("accounts=").append(accounts);
        sb.append(", accountSize=").append(accountSize);
        sb.append('}');
        return sb.toString();
    }
}
