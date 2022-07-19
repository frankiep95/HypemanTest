package controllers;


import models.lsoGrades;

import java.util.List;
import java.util.stream.Collectors;



public class lsoLogController {



    public static List<lsoGrades> sortPilotLsoLog(List<lsoGrades> log, String pilot) {
        return log.stream().filter(p -> p.callSign.contains(pilot))
                .collect(Collectors.toList());

    }

    public static List<lsoGrades> sortPilotLsoLogMonth(List<lsoGrades> log, String month){
        return log.stream().filter(p -> p.serverDate.substring(3,5).contains(month))
                .collect(Collectors.toList());

    }

   // public static List<lsoGrades> sortPilotLsoLogMission(List<lsoGrades> log){
    //    return log.stream().filter(p -> p.
   // }

}
