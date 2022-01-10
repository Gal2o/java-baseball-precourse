package baseball;

import static nextstep.utils.Randoms.pickNumberInRange;

import java.util.Arrays;
import java.util.stream.Stream;
import nextstep.utils.Console;

public class Application {

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현

        int[] randomNumbers;
        int[] inputNumbers;
        int[] result;

        randomNumbers = initNumber();

        while (true) {
            System.out.println("숫자를 입력해 주세요 : ");

            String input = Console.readLine();

            if (input.length() > 3 || !input.chars().allMatch(Character::isDigit)) {
                System.out.println("숫자 3자리만 입력이 가능합니다.");
                continue;
            } else if (isDuple(input)) {
                System.out.println("중복된 숫자가 있습니다.");
                continue;
            }

            inputNumbers = strToNum(input);

            result = makeResult(randomNumbers, inputNumbers);

            System.out.println(printResult(result));

            int choiceNumber = 0;

            if (result[0] == 3) {
                choiceNumber = choiceMode();
            }

            if (choiceNumber == 1) {
                randomNumbers = initNumber();
            } else if (choiceNumber == 2) {
                break;
            }
        }
    }

    // 랜덤 숫자 초기화
    private static int[] initNumber() {
        int[] number = new int[3];
        int[] duple = new int[10];

        for (int i = 0; i < 3; ++i) {
            number[i] = makeRandomNumber(duple);

            duple[number[i]]++;
        }

        return number;
    }

    // 랜덤 숫자 만들기
    private static int makeRandomNumber(int[] duple) {
        while (true) {
            int pickNumber = pickNumberInRange(1, 9);

            if (duple[pickNumber] == 0) {
                return pickNumber;
            }
        }
    }

    // 입력 받은 문자열 숫자 배열에 넣어주기
    private static int[] strToNum(String str) {
        int[] result = new int[3];

        for (int i = 0; i < 3; ++i) {
            result[i] = str.charAt(i) - '0';
        }

        return result;
    }

    // 입력 값 중복 체크
    private static boolean isDuple(String str) {
        int[] check = new int[10];

        for (int i = 0; i < 3; ++i) {
            int num = str.charAt(i) - '0';

            check[num]++;

            if (check[num] > 1) {
                return true;
            }
        }

        return false;
    }

    // 결과 비교하기
    private static int[] makeResult(int[] randomNumbers, int[] inputNumbers) {
        int[] result = new int[3]; // 0: 스트라이크, 1: 볼, 2: 일치 X
        int[] ballCheck = new int[10];

        for (int i = 0; i < 3; ++i) {
            ballCheck[randomNumbers[i]]++;

            if (randomNumbers[i] == inputNumbers[i]) {
                result[0]++;
            }
        }

        if (result[0] == 3) {
            return result;
        }

        result = new int[3];

        for (int i = 0; i < 3; ++i) {
            if (randomNumbers[i] == inputNumbers[i]) {
                result[0]++;
            } else if (ballCheck[inputNumbers[i]] > 0) {
                result[1]++;
            } else {
                result[2]++;
            }
        }

        return result;
    }

    private static String printResult(int[] result) {
        if (result[2] == 3) {
            return "낫싱";
        } else {
            String print = "";

            if (result[0] > 0) {
                print += result[0] + "스트라이크 ";
            }

            if (result[1] > 0) {
                print += result[1] + "볼";
            }

            return print;
        }
    }

    // 게임 끝내기 or 다시 하기
    private static int choiceMode() {
        System.out.println("게임 끝");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        String mode;

        while (true) {
            mode = Console.readLine();

            if (mode.length() == 1) {
                return Integer.parseInt(mode);
            } else {
                System.out.println("올바르지 않은 값입니다.");
            }
        }
    }
}
