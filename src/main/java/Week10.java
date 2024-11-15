import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.princeton.cs.algs4.In;

public class Week10 {
    List<String> getAllFunctions(/*String fileContent*/) throws ClassNotFoundException {
        List<String> result = new ArrayList<>();
        In in = new In("/run/media/rengumin/DATA/env/oais/src/main/resources/VolumeInfo.java");
        String[] fs = in.readAllLines();
        StringBuilder fsb = new StringBuilder();
        for (String f : fs) {
            fsb.append(f);
        }
        String fileContent = fsb.toString();
//        Pattern patternStatic = Pattern.compile("static (\\w+<?(\\w|,| )+>?) \\w+\\((<?(\\w|,| |\\.)+>?)?\\)");
        Pattern patternStatic = Pattern.compile("(\\*\\s+)?\\w+ static (\\w+<?(\\w|,| )+>?) \\w+\\((<?(\\w|,| |\\.)+>?)?\\)");
        Pattern fnPattern = Pattern.compile("\\w+\\(.+\\)");
        Pattern patternParams = Pattern.compile("\\(.+\\)");
        Pattern patternType = Pattern.compile("(\\w+)");
        Matcher matcher = patternStatic.matcher(fileContent);
        while(matcher.find()){
            if(fileContent.charAt(matcher.start())=='*'){
                continue;
            }
            String rawSign = fileContent.substring(matcher.start(), matcher.end());
            Matcher paramsMatcher = patternParams.matcher(rawSign);
            while(paramsMatcher.find()){
                String param = paramsMatcher.group(0);
                Matcher typeMatcher = patternType.matcher(param);
                while(typeMatcher.find()){
                    String type = typeMatcher.group(0);
                    Class<?> clazz = Class.forName(type);

                    System.out.println(type.getClass().getName());
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Week10 ins = new Week10();
        ins.getAllFunctions();
    }
}
