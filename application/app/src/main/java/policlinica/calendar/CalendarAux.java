package policlinica.calendar;

import java.util.Calendar;

public class CalendarAux {

    public static int getCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static String parseToSQLDate(String javaDate){
        String month;
        String day;
        String year;
         int i =0 ;

        String token;

        token = ""+javaDate.charAt(i);
        if(javaDate.charAt(i+1) == '/'){
            token = "0" + token;
            i+=2;
        }
        else {
            i++;
            token += "" + javaDate.charAt(i);
            i+=2;
        }

        month = token;

        token = ""+javaDate.charAt(i);
        if(javaDate.charAt(i+1) == '/'){
            token = "0" + token;
            i+=2;
        }
        else {
            i++;
            token += "" + javaDate.charAt(i);
            i+=2;
        }

        day = token;

        token = ""+javaDate.charAt(i) + javaDate.charAt(i+1) + javaDate.charAt(i+2) + javaDate.charAt(i+3);

        return token+"-"+month+"-"+day;
    }

    public static int parser(String string){
        try{
            int i = Integer.parseInt(string);
            return i;
        }catch (Exception e)
        {
            System.err.println("Failed parsing in JavaDate to Sql date");
            return -1;
        }
    }

}
