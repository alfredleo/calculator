package uz.javalearn;


import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Задача: “Калькулятор”
 * Описание:
 * Создайте консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем
 * арифметические операции и выводить в консоль результат их выполнения.
 * <p>
 * Требования:
 * Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b,
 * a * b, a / b. Данные передаются в одну строку (смотрите пример)! Решения, в которых каждое число и
 * арифмитеческая операция передаются с новой строки считаются неверными.
 * Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
 * Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не
 * ограничиваются по величине и могут быть любыми.
 * Калькулятор умеет работать только с целыми числами.
 * Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем
 * строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
 * При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских
 * - ответ ожидается арабскими.
 * При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
 * При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
 * приложение выбрасывает исключение и завершает свою работу.
 * Пример работы программы:
 * Input:
 * 1 + 2
 * Output:
 * 3
 * <p>
 * Input:
 * VI / III
 * Output:
 * II
 * <p>
 * Принципы оценки работы:
 * Обратите внимание на принципы ООП, постарайтесь разбить программу на логические классы. Решения, в которых весь
 * код программы находится в одном классе будут низко оценены. Продемонстрируйте своё умение в работе с разными
 * синтаксическими конструкциями, не забудьте про исключительные ситуации, при которых выполнение программы невозможно
 * из-за некорректных входных данных.
 * <p>
 * Как отправить решение?
 * Вам нужно создать собственный репозиторий на Github и добавить туда проект с решением.
 * Ссылку на репозиторий отправьте нам на почту info@java-mentor.com с темой Тестовое задание калькулятор Java.
 * [\dIVXLCDM]+ [+-/*] [\dIVXLCDM]+$
 * <p>
 * <p>
 * 1 + 2
 * VI / III
 */
public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (true) {
            String math = console.nextLine();
            if (math.equals("")) {
                System.out.println("Finish");
                return;
            }

            String whitespace_chars = ""
                    + "\\u0009" // CHARACTER TABULATION
                    + "\\u000A" // LINE FEED (LF)
                    + "\\u000B" // LINE TABULATION
                    + "\\u000C" // FORM FEED (FF)
                    + "\\u000D" // CARRIAGE RETURN (CR)
                    + "\\u0020" // SPACE
                    + "\\u0085" // NEXT LINE (NEL)
                    + "\\u00A0" // NO-BREAK SPACE
                    + "\\u1680" // OGHAM SPACE MARK
                    + "\\u180E" // MONGOLIAN VOWEL SEPARATOR
                    + "\\u2000" // EN QUAD
                    + "\\u2001" // EM QUAD
                    + "\\u2002" // EN SPACE
                    + "\\u2003" // EM SPACE
                    + "\\u2004" // THREE-PER-EM SPACE
                    + "\\u2005" // FOUR-PER-EM SPACE
                    + "\\u2006" // SIX-PER-EM SPACE
                    + "\\u2007" // FIGURE SPACE
                    + "\\u2008" // PUNCTUATION SPACE
                    + "\\u2009" // THIN SPACE
                    + "\\u200A" // HAIR SPACE
                    + "\\u2028" // LINE SEPARATOR
                    + "\\u2029" // PARAGRAPH SEPARATOR
                    + "\\u202F" // NARROW NO-BREAK SPACE
                    + "\\u205F" // MEDIUM MATHEMATICAL SPACE
                    + "\\u3000" // IDEOGRAPHIC SPACE
                    ;
            /* A \s that actually works for Java’s native character set: Unicode */
            String white = "[" + whitespace_chars + "]";

            // TODO: Поправить аппер кейс и не мешать арабские и римские цифры
            Pattern pattern = Pattern.compile("([\\dIVXLCDMivxlcdm]+)" + white + "*([+-/*])" + white + "*([\\dIVXLCDMivxlcdm]+)$");
            Matcher matcher = pattern.matcher(math);
//            boolean matches = matcher.matches();
            while (matcher.find()) {
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
                System.out.println(matcher.group(3));

            }
        }
    }
}