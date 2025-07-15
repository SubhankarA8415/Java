package Project_2241016145;

public class Date {
    private int day;
    private int month;
    private int year;
    Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
        dateCheck(day,month, year);
    }
    protected String getDate(){
        String day1 = ""+day;
        String month1 = ""+month;
        if (day<10){
            day1 = "0"+day;
        }
        if(month<10){
            month1 = "0"+month;
        }
        String date1 = ""+day+month+year;
        return (""+day1+"-"+month1+"-"+year);
    }

    protected void dateCheck(int day, int month, int year) {

             if (month == 2){
                if(day>28 || day<0){
                    int r = (int)(Math.random()*28)+1;
                    this.day = r;
                }
            }
            else if (month == 4){
                if(day>30 || day<0){
                    int r = (int)(Math.random()*30)+1;
                    this.day = r;
                }
            }
            else if (month == 6){
                if(day>30 || day<0){
                    int r = (int)(Math.random()*30)+1;
                    this.day = r;
                }
            }
            else if (month == 9){
                if(day>30 || day<0){
                    int r = (int)(Math.random()*30)+1;
                    this.day = r;
                }
            }
            else if (month == 11){
                if(day>30 || day<0){
                    int r = (int)(Math.random()*30)+1;
                    this.day = r;
                }
            }

         }
    }
