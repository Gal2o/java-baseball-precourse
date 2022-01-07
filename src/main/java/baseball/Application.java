package baseball;

import static nextstep.utils.Randoms.pickNumberInRange;

import nextstep.utils.Console;

public class Application {

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현

        int[] randomNumbers = new int[3];
        int[] duplicate = new int[10];

        for (int i = 0; i < 3; ++i) {
            randomNumbers[i] = makeRandomNumber(duplicate);
        }

        int[] inputNumbers;

        while (true) {
            System.out.println("숫자를 입력해 주세요 : ");

            inputNumbers = inputNumber();

            int[] result = compareNumber(randomNumbers, inputNumbers);

            if (result[1] == 3) {
                System.out.println("게임 끝");

                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

                if (choiceMode() == 2) {
                    break;
                }
            }
        }
    }

    // 랜덤 숫자 만들기
    public static int makeRandomNumber(int[] duple) {

        return 1;
    }


    // 숫자 입력 받기
    public static int[] inputNumber() {

        String input = Console.readLine();

        int[] numbers = new int[3];

        for (int i = 0; i < input.length(); ++i) {
            numbers[i] = input.charAt(i) - '0';
        }

        return numbers;
    }

    // 스트라이크, 볼 확인하기
    public static int[] compareNumber(int[] random, int[] input) {

        int[] result = new int[2];

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (random[i] == input[j]) {
                    if (i == j) {
                        result[1]++;
                    } else {
                        result[0]++;
                    }
                }
            }
        }

        return result;
    }

    // 게임 끝내기 or 다시 하기
    private static int choiceMode() {

        String mode;

        while (true) {
            mode = Console.readLine();

            if (mode.length() == 1) {
                return Integer.parseInt(Console.readLine());
            } else {
                System.out.println("올바르지 않은 값입니다.");
            }
        }
    }
}
