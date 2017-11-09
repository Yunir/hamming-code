import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static String[] NameInfo = new String[2]; // {name, surname}
    private static int[] numericInfo = new int[12];// {deName, deSurname, year, var, type, ans1, ans2, ans3, ans4, ans5, ans6, ans7}
    private static int[] taskInfo = new int[2]; // {maxOfTask1, maxOfTask2}
    private static int[] tasks = new int[5]; // line num of the task

    public static void main(String[] args) {
        setInfo();
        showTasks();
        System.out.println();
    }

    private static void setInfo() {
        //Instructions
        System.out.println("Лабораторная работа №4. Синтез помехоустойчивого кода\n" +
                "Цель задания: Овладеть навыками кодирования и декодирования сообщений с использованием самокорректирующихся кодов\n" +
                "\nИзучить: Лекции 5-6 (Сжатие информации и основы помехоустойчивого кодирования)\n" +
                "\nДля генерации вашего задания укажите свои данные");
        System.out.print("Имя: ");           writeCyrillic(0);  decodeString(0);
        System.out.print("Фамилия: ");       writeCyrillic(1);  decodeString(1);
        System.out.print("Нынешний год: ");  writeNumeric(0, 2);
        System.out.print("Вариант: ");       writeNumeric(1, 3);
        System.out.print("Вид работы(лаб. раб - 1, онлайн - 2): "); writeNumeric(2, 4);
        taskInfo[0] = 112; //max num of tasks of 1
        taskInfo[1] = 127; //max num of tasks of 2
    }

    private static void writeCyrillic(int index) {
        String s;
        Pattern p = Pattern.compile("^([А-Я]+)$");
        Matcher m;
        do {
            s = in.next().toUpperCase();
            if(s.startsWith("-512")) {
                for (int i = 4; i < s.length(); i++) {
                    System.out.print(s.charAt(i) + "["+ s.codePointAt(i) +"]" + " ");
                }
                System.out.println();
            }
            m = p.matcher(s);
            if (!m.matches()) {
                System.out.print("Ошибка. Введите имя используя кириллицу: ");
            }
        } while (!m.matches());
        NameInfo[index] = s;
    }

    private static void writeNumeric (int type, int index) {
        String num;
        Pattern p = Pattern.compile("");
        if(type==0) p = Pattern.compile("^([0-9]{4})$");
        if(type==1) p = Pattern.compile("^([0-9]{1,6})$");
        if(type==2) p = Pattern.compile("^[1-2]$");
        if(type==3) p = Pattern.compile("(^1[1-4])||(^2[1-3])||0"); //[11-14,21-23]
        if(type==4) p = Pattern.compile("(^1([1-9])||^1(1[0-1]))||(^2[1-4])||0"); //[11-19, 110-111, 21-24]
        if(type==5) p = Pattern.compile("^([0-9]{1,3})$");
        if(type==6) p = Pattern.compile("^([0-9]{1,5})$");
        Matcher m;
        do {
            num = in.next();
            m = p.matcher(num);
            if (!m.matches()) {
                if(type==0) System.out.print("Ошибка. Введите 4ех значный год[20xx]: ");
                if(type==1) System.out.print("Ошибка. Вариант должен содержать менее 7 символов: ");
                if(type==2) System.out.print("Ошибка. Введите цифры либо 1(лаб.раб.), либо 2(онлайн): ");
                if(type==3 || type==4) System.out.print("Ошибка. Введите цифры в соответствии с шаблоном: ");
                if(type==5) System.out.print("Ошибка. Слишком большое кол-ство проверочных разрядов: ");
                if(type==6) System.out.print("Ошибка. Нужно ввести от 1 до 5 цифр: ");

            }
        } while (!m.matches());
        numericInfo[index] = Integer.parseInt(num);
    }

    private static void decodeString(int index) {
        int res = 0;
        for (int i = 0; i < NameInfo[index].length(); i++) {
            res += (int)NameInfo[index].charAt(i);
        }
        numericInfo[index] = res;
    }

    private static void showTasks() {
        generator();
        String[] t1 = new String[]{"r1  r2  i1  r3  i2  i3  i4", "0  0	0	1	0	0	0",
                "0   0   1   0   0   0   0",
                "0   0   1   1   0   0   0",
                "0   1   0   0   0   0   0",
                "0   1   0   1   0   0   0",
                "0   1   1   0   0   0   0",
                "0   1   1   1   0   0   0",
                "1   0   0   0   0   0   0",
                "1   0   0   1   0   0   0",
                "1   0   1   0   0   0   0",
                "1   0   1   1   0   0   0",
                "1   1   0   0   0   0   0",
                "1   1   0   1   0   0   0",
                "1   1   1   1   0   0   0",
                "0   0   0   0   0   0   1",
                "0   0   0   1   0   0   1",
                "0   0   1   0   0   0   1",
                "0   1   0   0   0   0   1",
                "0   1   0   1   0   0   1",
                "0   1   1   0   0   0   1",
                "0   1   1   1   0   0   1",
                "1   0   0   0   0   0   1",
                "1   0   0   1   0   0   1",
                "1   0   1   0   0   0   1",
                "1   0   1   1   0   0   1",
                "1   1   0   0   0   0   1",
                "1   1   1   0   0   0   1",
                "1   1   1   1   0   0   1",
                "0   0   0   0   0   1   0",
                "0   0   0   1   0   1   0",
                "0   0   1   0   0   1   0",
                "0   0   1   1   0   1   0",
                "0   1   0   0   0   1   0",
                "0   1   1   0   0   1   0",
                "0   1   1   1   0   1   0",
                "1   0   0   0   0   1   0",
                "1   0   0   1   0   1   0",
                "1   0   1   0   0   1   0",
                "1   1   0   0   0   1   0",
                "1   1   0   1   0   1   0",
                "1   1   1   0   0   1   0",
                "1   1   1   1   0   1   0",
                "0   0   0   0   0   1   1",
                "0   0   0   1   0   1   1",
                "0   0   1   0   0   1   1",
                "0   0   1   1   0   1   1",
                "0   1   0   0   0   1   1",
                "0   1   0   1   0   1   1",
                "0   1   1   1   0   1   1",
                "1   0   0   1   0   1   1",
                "1   0   1   0   0   1   1",
                "1   0   1   1   0   1   1",
                "1   1   0   0   0   1   1",
                "1   1   0   1   0   1   1",
                "1   1   1   0   0   1   1",
                "1   1   1   1   0   1   1",
                "0   0   0   0   1   0   0",
                "0   0   0   1   1   0   0",
                "0   0   1   0   1   0   0",
                "0   0   1   1   1   0   0",
                "0   1   0   0   1   0   0",
                "0   1   0   1   1   0   0",
                "0   1   1   0   1   0   0",
                "1   0   0   0   1   0   0",
                "1   0   1   0   1   0   0",
                "1   0   1   1   1   0   0",
                "1   1   0   0   1   0   0",
                "1   1   0   1   1   0   0",
                "1   1   1   0   1   0   0",
                "1   1   1   1   1   0   0",
                "0   0   0   0   1   0   1",
                "0   0   0   1   1   0   1",
                "0   0   1   0   1   0   1",
                "0   0   1   1   1   0   1",
                "0   1   0   1   1   0   1",
                "0   1   1   0   1   0   1",
                "0   1   1   1   1   0   1",
                "1   0   0   0   1   0   1",
                "1   0   0   1   1   0   1",
                "1   0   1   1   1   0   1",
                "1   1   0   0   1   0   1",
                "1   1   0   1   1   0   1",
                "1   1   1   0   1   0   1",
                "1   1   1   1   1   0   1",
                "0   0   0   0   1   1   0",
                "0   0   0   1   1   1   0",
                "0   0   1   1   1   1   0",
                "0   1   0   0   1   1   0",
                "0   1   0   1   1   1   0",
                "0   1   1   0   1   1   0",
                "0   1   1   1   1   1   0",
                "1   0   0   0   1   1   0",
                "1   0   0   1   1   1   0",
                "1   0   1   0   1   1   0",
                "1   0   1   1   1   1   0",
                "1   1   0   1   1   1   0",
                "1   1   1   0   1   1   0",
                "1   1   1   1   1   1   0",
                "0   0   0   0   1   1   1",
                "0   0   1   0   1   1   1",
                "0   0   1   1   1   1   1",
                "0   1   0   0   1   1   1",
                "0   1   0   1   1   1   1",
                "0   1   1   0   1   1   1",
                "0   1   1   1   1   1   1",
                "1   0   0   0   1   1   1",
                "1   0   0   1   1   1   1",
                "1   0   1   0   1   1   1",
                "1   0   1   1   1   1   1",
                "1   1   0   0   1   1   1",
                "1   1   0   1   1   1   1",
                "1   1   1   0   1   1   1"};
        String[] t2 = new String[]{"r1  r2  i1  r3  i2  i3  i4  r4  i5  i6  i7  i8  i9  i10 i11",
                "0   1   1   1   0   0   0   0   0   0   1   0   0   0   0",
                "0   1   1   1   0   0   0   0   0   1   0   0   0   0   0",
                "0   1   1   1   0   0   0   0   0   1   1   0   0   0   0",
                "0   1   1   1   0   0   0   0   1   0   0   0   0   0   0",
                "0   1   1   1   0   0   0   0   1   0   1   0   0   0   0",
                "0   1   1   1   0   0   0   0   1   1   0   0   0   0   0",
                "0   1   1   1   0   0   0   0   1   1   1   0   0   0   0",
                "0   1   1   1   0   0   0   1   0   0   0   0   0   0   0",
                "0   1   1   1   0   0   0   1   0   0   1   0   0   0   0",
                "0   1   1   1   0   0   0   1   0   1   0   0   0   0   0",
                "0   1   1   1   0   0   0   1   0   1   1   0   0   0   0",
                "0   1   1   1   0   0   0   1   1   0   0   0   0   0   0",
                "0   1   1   1   0   0   0   1   1   0   1   0   0   0   0",
                "0   1   1   1   0   0   0   1   1   1   0   0   0   0   0",
                "0   1   1   1   0   0   0   1   1   1   1   0   0   0   0",
                "0   1   1   0   0   0   1   0   0   0   0   0   0   0   1",
                "0   1   1   0   0   0   1   0   0   0   1   0   0   0   1",
                "0   1   1   0   0   0   1   0   0   1   0   0   0   0   1",
                "0   1   1   0   0   0   1   0   0   1   1   0   0   0   1",
                "0   1   1   0   0   0   1   0   1   0   0   0   0   0   1",
                "0   1   1   0   0   0   1   0   1   0   1   0   0   0   1",
                "0   1   1   0   0   0   1   0   1   1   0   0   0   0   1",
                "0   1   1   0   0   0   1   0   1   1   1   0   0   0   1",
                "0   1   1   0   0   0   1   1   0   0   0   0   0   0   1",
                "0   1   1   0   0   0   1   1   0   0   1   0   0   0   1",
                "0   1   1   0   0   0   1   1   0   1   0   0   0   0   1",
                "0   1   1   0   0   0   1   1   0   1   1   0   0   0   1",
                "0   1   1   0   0   0   1   1   1   0   0   0   0   0   1",
                "0   1   1   0   0   0   1   1   1   0   1   0   0   0   1",
                "0   1   1   0   0   0   1   1   1   1   0   0   0   0   1",
                "0   1   1   0   0   0   1   1   1   1   1   0   0   0   1",
                "0   1   0   1   0   1   0   0   0   0   0   0   0   1   0",
                "0   1   0   1   0   1   0   0   0   0   1   0   0   1   0",
                "0   1   0   1   0   1   0   0   0   1   0   0   0   1   0",
                "0   1   0   1   0   1   0   0   0   1   1   0   0   1   0",
                "0   1   0   1   0   1   0   0   1   0   0   0   0   1   0",
                "0   1   0   1   0   1   0   0   1   0   1   0   0   1   0",
                "0   1   0   1   0   1   0   0   1   1   0   0   0   1   0",
                "0   1   0   1   0   1   0   0   1   1   1   0   0   1   0",
                "0   1   0   1   0   1   0   1   0   0   0   0   0   1   0",
                "0   1   0   1   0   1   0   1   0   0   1   0   0   1   0",
                "0   1   0   1   0   1   0   1   0   1   0   0   0   1   0",
                "0   1   0   1   0   1   0   1   0   1   1   0   0   1   0",
                "0   1   0   1   0   1   0   1   1   0   0   0   0   1   0",
                "0   1   0   1   0   1   0   1   1   0   1   0   0   1   0",
                "0   1   0   1   0   1   0   1   1   1   0   0   0   1   0",
                "0   1   0   1   0   1   0   1   1   1   1   0   0   1   0",
                "0   1   0   0   0   1   1   0   0   0   0   0   0   1   1",
                "0   1   0   0   0   1   1   0   0   0   1   0   0   1   1",
                "0   1   0   0   0   1   1   0   0   1   0   0   0   1   1",
                "0   1   0   0   0   1   1   0   0   1   1   0   0   1   1",
                "0   1   0   0   0   1   1   0   1   0   0   0   0   1   1",
                "0   1   0   0   0   1   1   0   1   0   1   0   0   1   1",
                "0   1   0   0   0   1   1   0   1   1   0   0   0   1   1",
                "0   1   0   0   0   1   1   0   1   1   1   0   0   1   1",
                "0   1   0   0   0   1   1   1   0   0   0   0   0   1   1",
                "0   1   0   0   0   1   1   1   0   0   1   0   0   1   1",
                "0   1   0   0   0   1   1   1   0   1   0   0   0   1   1",
                "0   1   0   0   0   1   1   1   0   1   1   0   0   1   1",
                "0   1   0   0   0   1   1   1   1   0   0   0   0   1   1",
                "0   1   0   0   0   1   1   1   1   0   1   0   0   1   1",
                "0   1   0   0   0   1   1   1   1   1   0   0   0   1   1",
                "0   1   0   0   0   1   1   1   1   1   1   0   0   1   1",
                "0   0   1   1   1   0   0   0   0   0   0   0   1   0   0",
                "0   0   1   1   1   0   0   0   0   0   1   0   1   0   0",
                "0   0   1   1   1   0   0   0   0   1   0   0   1   0   0",
                "0   0   1   1   1   0   0   0   0   1   1   0   1   0   0",
                "0   0   1   1   1   0   0   0   1   0   0   0   1   0   0",
                "0   0   1   1   1   0   0   0   1   0   1   0   1   0   0",
                "0   0   1   1   1   0   0   0   1   1   0   0   1   0   0",
                "0   0   1   1   1   0   0   0   1   1   1   0   1   0   0",
                "0   0   1   1   1   0   0   1   0   0   0   0   1   0   0",
                "0   0   1   1   1   0   0   1   0   0   1   0   1   0   0",
                "0   0   1   1   1   0   0   1   0   1   0   0   1   0   0",
                "0   0   1   1   1   0   0   1   0   1   1   0   1   0   0",
                "0   0   1   1   1   0   0   1   1   0   0   0   1   0   0",
                "0   0   1   1   1   0   0   1   1   0   1   0   1   0   0",
                "0   0   1   1   1   0   0   1   1   1   0   0   1   0   0",
                "0   0   1   1   1   0   0   1   1   1   1   0   1   0   0",
                "0   0   1   0   1   0   1   0   0   0   0   0   1   0   1",
                "0   0   1   0   1   0   1   0   0   0   1   0   1   0   1",
                "0   0   1   0   1   0   1   0   0   1   0   0   1   0   1",
                "0   0   1   0   1   0   1   0   0   1   1   0   1   0   1",
                "0   0   1   0   1   0   1   0   1   0   0   0   1   0   1",
                "0   0   1   0   1   0   1   0   1   0   1   0   1   0   1",
                "0   0   1   0   1   0   1   0   1   1   0   0   1   0   1",
                "0   0   1   0   1   0   1   0   1   1   1   0   1   0   1",
                "0   0   1   0   1   0   1   1   0   0   0   0   1   0   1",
                "0   0   1   0   1   0   1   1   0   0   1   0   1   0   1",
                "0   0   1   0   1   0   1   1   0   1   0   0   1   0   1",
                "0   0   1   0   1   0   1   1   0   1   1   0   1   0   1",
                "0   0   1   0   1   0   1   1   1   0   0   0   1   0   1",
                "0   0   1   0   1   0   1   1   1   0   1   0   1   0   1",
                "0   0   1   0   1   0   1   1   1   1   0   0   1   0   1",
                "0   0   1   0   1   0   1   1   1   1   1   0   1   0   1",
                "0   0   0   1   1   1   0   0   0   0   0   0   1   1   0",
                "0   0   0   1   1   1   0   0   0   0   1   0   1   1   0",
                "0   0   0   1   1   1   0   0   0   1   0   0   1   1   0",
                "0   0   0   1   1   1   0   0   0   1   1   0   1   1   0",
                "0   0   0   1   1   1   0   0   1   0   0   0   1   1   0",
                "0   0   0   1   1   1   0   0   1   0   1   0   1   1   0",
                "0   0   0   1   1   1   0   0   1   1   0   0   1   1   0",
                "0   0   0   1   1   1   0   0   1   1   1   0   1   1   0",
                "0   0   0   1   1   1   0   1   0   0   0   0   1   1   0",
                "0   0   0   1   1   1   0   1   0   0   1   0   1   1   0",
                "0   0   0   1   1   1   0   1   0   1   0   0   1   1   0",
                "0   0   0   1   1   1   0   1   0   1   1   0   1   1   0",
                "0   0   0   1   1   1   0   1   1   0   0   0   1   1   0",
                "0   0   0   1   1   1   0   1   1   0   1   0   1   1   0",
                "0   0   0   1   1   1   0   1   1   1   0   0   1   1   0",
                "0   0   0   1   1   1   0   1   1   1   1   0   1   1   0",
                "0   0   0   0   1   1   1   0   0   0   0   0   1   1   1",
                "0   0   0   0   1   1   1   0   0   0   1   0   1   1   1",
                "0   0   0   0   1   1   1   0   0   1   0   0   1   1   1",
                "0   0   0   0   1   1   1   0   0   1   1   0   1   1   1",
                "0   0   0   0   1   1   1   0   1   0   0   0   1   1   1",
                "0   0   0   0   1   1   1   0   1   0   1   0   1   1   1",
                "0   0   0   0   1   1   1   0   1   1   0   0   1   1   1",
                "0   0   0   0   1   1   1   0   1   1   1   0   1   1   1",
                "0   0   0   0   1   1   1   1   0   0   0   0   1   1   1",
                "0   0   0   0   1   1   1   1   0   0   1   0   1   1   1",
                "0   0   0   0   1   1   1   1   0   1   0   0   1   1   1",
                "0   0   0   0   1   1   1   1   0   1   1   0   1   1   1",
                "0   0   0   0   1   1   1   1   1   0   0   0   1   1   1",
                "0   0   0   0   1   1   1   1   1   0   1   0   1   1   1",
                "0   0   0   0   1   1   1   1   1   1   0   0   1   1   1",
                "0   0   0   0   1   1   1   1   1   1   1   0   1   1   1"};

        System.out.println(t1[0]);
        System.out.println(t1[tasks[0]]);
        System.out.println(t1[tasks[1]]);
        System.out.println(t1[tasks[2]]);
        System.out.println(t1[tasks[3]]);
        System.out.println();
        System.out.println(t2[0]);
        System.out.println(t2[tasks[4]]);
        int globalNum = tasks[0] + tasks[1] + tasks[2] + tasks[3] + tasks[4]; globalNum *= 4;
        if(numericInfo[4] == 1) {
            System.out.println();
            System.out.print("Ваш сгенерированный код: ");
            for (int i = 0; i < 5; i++) System.out.printf("%d ", tasks[i] + (numericInfo[3]%10));
            System.out.println();
            System.out.println("\nСодержание отчета:");
            System.out.println("1. Построить схему декодирования классического кода Хэмминга (7;4)");
            System.out.println("2. Переписать первые четыре сообщения и приписать рядом с вариантом сгенерированный код");
            System.out.println("3. Показать у первых четырех сообщенией имеются ли в них ошибки, и если имеются, то какие. Подробно прокомментировать.");
            System.out.println("4. Написать правильное сообщение");
            System.out.println("5. С помощью кругов Эйлера объяснить построение классического кода Хэмминга(7;4)");
            System.out.println("6. Построить схему декодирования классического кода Хэмминга(15;11)");
            System.out.println("7. Переписать последнее сообщение");
            System.out.println("8. Показать у последнего сообщения имеется ли в нем ошибка, и если имеется, то какой.");
            System.out.println("9. Принять число " + globalNum + " как число информационных разрядов в передаваемом сообщении.\n Вычислить для данного числа минимальное число проверочных разрядов и коэффициент избыточности.");
            System.out.println("10. Написать правильное сообщение");
        } else {
            generateFile(globalNum);
        }
    }

    private static void generator() {
        final int MAGIC_NUM = 21;
        int jumpNum = (int)((seed() % taskInfo[0]) + 1);
        jumpNum = ((jumpNum == (taskInfo[0]-1)) ? (((jumpNum+MAGIC_NUM) % taskInfo[0])+1) : jumpNum);
        tasks[0] = ((numericInfo[3]+jumpNum) % taskInfo[0]) + 1;
        for (int i = 1; i < 4; i++) {
            tasks[i] = ((tasks[i-1] + jumpNum) % taskInfo[0]) + 1;
        }
        tasks[4] = ((numericInfo[2]+numericInfo[3]+jumpNum) % taskInfo[1]) + 1;
    }

    private static void generateFile(int GN) {
        System.out.println();
        System.out.println("Введите ваши ответы(ввод информационного бита i5 представляется как 15, r3 => 23, 'нет ошибок' => 0): ");
        for (int i = 0; i < 4; i++) {
            System.out.print("Ответ на " + ((i+1)) + " комбинацию(7,4): ");
            writeNumeric(3, i+5);
        }
        System.out.print("Ответ на 5 комбинацию(15,11): ");  writeNumeric(4, 9);
        System.out.println("Принять число " + GN + " как число информационных разрядов в передаваемом сообщении.");
        System.out.print("Введите для данного числа минимальное число проверочных бит: ");  writeNumeric(5, 10);
        System.out.print("Выведите коэффициент избыточности в процентах и введите пять знаков после запятой полученного числа: ");  writeNumeric(6, 11);
        String finalGet = NameInfo[0] + "~" + NameInfo[1] + "~" + numericInfo[2] + "~" + numericInfo[3] + "~" +
                numericInfo[5] + "~" + numericInfo[6] + "~" + numericInfo[7] + "~" + numericInfo[8] + "~" + numericInfo[9] + "~" + numericInfo[10] + "~" + numericInfo[11];

        File file = new File("inform_"+numericInfo[3]);
        try {
            file.createNewFile();
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(finalGet);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            System.out.println("Произошла критическая ошибка при создании файла. Просьба обратиться к программисту по ВК: vk.com/yunir_s");
            //throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("Файл inform_" + numericInfo[3] + " сгенерирован, передайте его преподавателю, чтобы он проверил вашу работу.");
    }

    private static long seed() { return numericInfo[0] + numericInfo[1] + numericInfo[2] + numericInfo[3]; }
}
