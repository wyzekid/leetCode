package companies.intterra;

import java.util.Scanner;

public class MergeStarter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of users n=");
        int userNumber = scanner.nextInt();
        MergeIfImpl mergeIf = new MergeIfImpl(userNumber);
        for (int i = 0; i <= userNumber; i++) {
            System.out.print(String.format("Enter %d user string: ", i));
            String currentUser = scanner.nextLine();
            mergeIf.addAccountFromStdIn(currentUser);
        }
        scanner.close();
        mergeIf.merge();
    }

}
