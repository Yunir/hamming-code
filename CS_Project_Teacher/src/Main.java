import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {                                             /*  First||Second  */
    private static int[] correctAnswer = new int[]{0, 4, 2, 6, 1, 5, 3, 7, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
    private static int[] correctTextNum = new int[]{0, 23, 22, 13, 21, 12, 11, 14, 24, 23, 18, 22, 16, 13, 110, 21, 15, 22, 19, 11, 17, 14, 111};
    private static String[] correctText = new String[]{"---Ошибок не обнаружено---", "r3", "r2", "i3", "r1", "i2", "i1", "i4", "r4",
            "r3", "i8", "r2", "i6", "i3", "i10", "r1", "i5", "i2", "i9", "i1", "i7", "i4", "i11"};
    static int var = 0;
    static boolean[] answers = new boolean[]{false, false, false, false, false, false, false};
    static int[] keys = new int[5];
    static boolean[] taskMass1 = new boolean[7];
    static boolean[] taskMassRes1 = new boolean[3];
    static boolean[] taskMass2 = new boolean[15];
    static boolean[] taskMassRes2 = new boolean[4];
//Для ОНЛАЙН задания!!!
    static int[] tasks = new int[5];
    static int result = 0;
    final static int MAGIC_NUM = 21;
    static int decodedName;
    static int decodedSurname;
    static int v;
    static int year;
    static public long seed() {
        long seed = decodedName + decodedSurname + v + year;
        return seed;
    }

    static public int deCode(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res += (int)str.charAt(i);
        }
        return res;
    }

    static public void generateTasks(int max_task1, int max_task2, int year, int v) {
        int jumpingNum = (int)((seed() % max_task1) + 1);
        jumpingNum = ((jumpingNum == (max_task1-1)) ? (((jumpingNum+MAGIC_NUM) % max_task1)+1) : jumpingNum);
        tasks[0] = ((v+jumpingNum) % max_task1) + 1;
        for (int i = 1; i < 4; i++) {
            tasks[i] = ((tasks[i-1] + jumpingNum) % max_task1) + 1;
        }
        tasks[4] = ((year+v+jumpingNum) % max_task2) + 1;
    }
//Для ОНЛАЙН ПРОВЕРКИ!!! ^^^

    public static void validator(boolean task, int ans, int z) {
        int res = 0;
        if (!task) {
            taskMassRes1[2] = taskMass1[0] ^ taskMass1[2] ^ taskMass1[4] ^ taskMass1[6];
            taskMassRes1[1] = taskMass1[1] ^ taskMass1[2] ^ taskMass1[5] ^ taskMass1[6];
            taskMassRes1[0] = taskMass1[3] ^ taskMass1[4] ^ taskMass1[5] ^ taskMass1[6];
            for (int i = 1, j=0; i < 8; i*=2, j++) {
                res += i*(taskMassRes1[j]?1:0);
            }
            if (ans == -1) {
                if(res==0) System.out.println(correctText[0]);
                else {
                    for (int i = 1; i < 8; i++) {
                        if(correctAnswer[res] == i) System.out.print("**  ");
                        else System.out.print("..  ");
                    }
                    System.out.print("-  ошибка в " + correctText[res]);
                    System.out.println();
                }
            }
        } else {
            taskMassRes2[3] = taskMass2[0] ^ taskMass2[2] ^ taskMass2[4] ^ taskMass2[6] ^ taskMass2[8] ^ taskMass2[10] ^ taskMass2[12] ^ taskMass2[14];
            taskMassRes2[2] = taskMass2[1] ^ taskMass2[2] ^ taskMass2[5] ^ taskMass2[6] ^ taskMass2[9] ^ taskMass2[10] ^ taskMass2[13] ^ taskMass2[14];
            taskMassRes2[1] = taskMass2[3] ^ taskMass2[4] ^ taskMass2[5] ^ taskMass2[6] ^ taskMass2[11] ^ taskMass2[12] ^ taskMass2[13] ^ taskMass2[14];
            taskMassRes2[0] = taskMass2[7] ^ taskMass2[8] ^ taskMass2[9] ^ taskMass2[10] ^ taskMass2[11] ^ taskMass2[12] ^ taskMass2[13] ^ taskMass2[14];
            for (int i = 1, j=0; i < 16; i*=2, j++) {
                res += i*(taskMassRes2[j]?1:0);
            }
            if(res!=0) res+=7;
            if (ans ==-1) {
                if (res != 0) {
                    for (int i = 1; i < 16; i++) {
                        if (correctAnswer[res] == i) System.out.print("**  ");
                        else System.out.print("..  ");
                    }
                    System.out.print("-  ошибка в " + correctText[res]);
                    System.out.println();
                } else System.out.println(correctText[0]);
            }
        }
        keys[z] = correctTextNum[res];
        answers[z] = (keys[z] == ans);
    }

    static public boolean testViewNum(String t) {
        Pattern p = Pattern.compile("^[1-2]$");
        Matcher m = p.matcher(t);
        return m.matches();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int temp;
        boolean ok = true;
        boolean task = false;
        boolean typeWork = false;
        String tempo;
        if(args.length == 0 || (!args[0].contentEquals("1") && !args[0].contentEquals("2"))) {
            System.out.print("Какой способ проверки(ручн. ввод(РВ) - 1, файл-чтение(ФЧ) - 2): ");
            tempo = in.next();
            while (!(testViewNum(tempo)))
            {
                System.out.print("Ошибка. Введите цифры либо 1(РВ), либо 2(ФЧ): ");
                tempo = in.next();
            }
            typeWork = (Integer.parseInt(tempo)==1?true:false);
        } else typeWork = (Integer.parseInt(args[0])==1?true:false);

        Path task1 = Paths.get("task1");
        Path task2 = Paths.get("task2");
        List<String> sTask1 = null;
        List<String> sTask2 = null;
        try {
            sTask1 = Files.readAllLines(task1);
            sTask2 = Files.readAllLines(task2);
        } catch (IOException e) {
            System.out.println("Проблемы с файлами task1 и(или) task2");
            ok = false;
        }
        if(typeWork) {
            System.out.println("Шаблон: {[-Вариант] [1/2(задание)]} [№задания]+ ");
            while(ok) {
                temp = in.nextInt();
                if (temp < 0)
                {
                    var = Math.abs(temp);
                    temp = in.nextInt();
                    task = temp == 1 ? false : true;
                    while (task && temp != 2) {
                        System.out.print("вы ввели неверное задание(1, 2), повторите попытку: ");
                        temp = in.nextInt();
                        task = temp == 1 ? false : true;
                    }
                    System.out.println("===Вариант " + var + "(Задание №" + temp + ")" + "===");
                } else {
                    temp = temp-(var%10);
                    System.out.println((!task) ? sTask1.get(0) : sTask2.get(0));
                    System.out.println(((!task) ? sTask1.get(temp) : sTask2.get(temp)) + "      #" + (temp+(var%10)));
                    if(!task) {
                        for (int i = 0, j = 0; i < sTask1.get(temp).length(); i++) {
                            if((int)sTask1.get(temp).charAt(i) != 49 && (int)sTask1.get(temp).charAt(i) != 48) continue;
                            taskMass1[j] = sTask1.get(temp).charAt(i) == 48 ? false : true;
                            j++;
                        }
                    } else {
                        for (int i = 0, j = 0; i < sTask2.get(temp).length(); i++) {
                            if((int)sTask2.get(temp).charAt(i) != 49 && (int)sTask2.get(temp).charAt(i) != 48) continue;
                            taskMass2[j] = sTask2.get(temp).charAt(i) == 48 ? false : true;
                            j++;
                        }
                    }

                    validator(task, -1, 0);

                    System.out.println();
                }
            }
        } else {
            String name;
            String surname;
            int[] taskAnswers = new int[7];

            File f = new File(".");    //путь откуда начинается поиск
            FilenameFilter filter = new FilenameFilter(){
                public boolean accept(File dir, String name){
                    if(name.startsWith("inform_")) return true;   //если встречаем в имени файла ".rtf"
                    else return false;
                }
            };
            File[] files = f.listFiles(filter);
            for(int z=0; z<files.length; z++) {
                result = 0;
                for (int i = 0; i < 7; i++) {
                    answers[i] = false;
                }
                Path inform_online = Paths.get(files[z].getAbsolutePath());
                List<String> sInform_online = null;
                String[] info;
                try {
                    sInform_online = Files.readAllLines(inform_online, Charset.forName("windows-1251"));
                    info = sInform_online.get(0).split("~");
                    name = info[0];
                    decodedName = deCode(name);
                    surname = info[1];
                    decodedSurname = deCode(surname);
                    year = Integer.parseInt(info[2]);
                    v = Integer.parseInt(info[3]);
                    for (int i = 0; i < 7; i++) {
                        taskAnswers[i] = Integer.parseInt(info[i + 4]);
                    }
                    System.out.println();
                    System.out.println("Ученик: " + info[1] + " " + info[0] + " " + info[3] + " " + info[2]);
                    //System.out.println();
                    generateTasks(112, 127, year, v);
                    /*System.out.println();
                    System.out.println((sTask1.get(tasks[0])));
                    System.out.println((sTask1.get(tasks[1])));
                    System.out.println((sTask1.get(tasks[2])));
                    System.out.println((sTask1.get(tasks[3])));
                    System.out.println((sTask2.get(tasks[4])));
                    */
                    for (int k = 0; k < 4; k++) {
                        for (int i = 0, j = 0; i < sTask1.get(tasks[k]).length(); i++) {
                            if ((int) sTask1.get(tasks[k]).charAt(i) != 49 && (int) sTask1.get(tasks[k]).charAt(i) != 48)
                                continue;
                            taskMass1[j] = sTask1.get(tasks[k]).charAt(i) == 48 ? false : true;
                            j++;
                        }

                        validator(false, taskAnswers[k], k);
                    }
                    for (int i = 0, j = 0; i < sTask2.get(tasks[4]).length(); i++) {
                        if ((int) sTask2.get(tasks[4]).charAt(i) != 49 && (int) sTask2.get(tasks[4]).charAt(i) != 48)
                            continue;
                        taskMass2[j] = sTask2.get(tasks[4]).charAt(i) == 48 ? false : true;
                        j++;
                    }

                    validator(true, taskAnswers[4], 4);
                    int[] groupOfControlBits = {0, 0, 1, 4, 11, 26, 57, 120, 247, 502, 1013,2036};
                    int globalNum = tasks[0] + tasks[1] + tasks[2] + tasks[3] + tasks[4]; globalNum *= 4;
                    if (Math.pow(2, taskAnswers[5])-taskAnswers[5]-globalNum >= 1)
                    {
                        answers[5] = true;
                        double gb = 1.0*taskAnswers[5];
                        gb /= taskAnswers[5]+globalNum;
                        int gymmyBoy=(int)(gb*10000000)%100000;
                        if ((gymmyBoy+5 > taskAnswers[6]) && (gymmyBoy-5 < taskAnswers[6])) answers[6] = true;
                    }
                    System.out.print("Номера правильно выполненных заданий: ");
                    for (int i = 0, j=0; i < 7; i++) {
                        if (answers[i]){
                            if(j!=0) System.out.print(", ");
                            System.out.print(((int)(i+1)));
                            if(i<4) result += 15;
                            else if(i>4)result+=10;
                            else result += 20;
                            j++;
                        }
                        if (i==6 && j==0) System.out.print("ни одного");
                    }
                    System.out.println();
                    System.out.print("Ключи к заданиям: {"+keys[0]);
                    for (int i = 1; i < 5; i++) {
                        System.out.print(", "+keys[i]);
                    }
                    System.out.println("}");
                    System.out.println("Результат ученика "+ info[0] + " " + info[1] + ": "+result + "%");

                } catch (IOException e) {
                    System.out.println("Файл inform_online не обнаружен в директории");
                }
            }
        }
    }
}
