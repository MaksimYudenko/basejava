package ru.javawebinar.basejava;

public class MainString {
    public static void main(String[] args) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
//        String result = "";
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str).append(", ");
        }
        System.out.println(sb.toString());

        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
        System.out.println(str1 == str2);

/*        Organization o1 = new Organization("ORG1", "www.o1.com", "function1"
                , "title1", "description1", LocalDate.of(2000, 5, 10),
                LocalDate.of(2005, 8, 14));
        System.out.println(o1);
        System.out.println();
        o1.addBusyPeriod("function3", "title3", "description3", LocalDate.of(2012, 5, 10),
                LocalDate.of(2015, 8, 14));
        System.out.println(o1);*/
    }
}
