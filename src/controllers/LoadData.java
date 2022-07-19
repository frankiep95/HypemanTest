package controllers;

import models.flightLog;
import models.lsoGrades;
import utilities.ReadFlightLogCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static controllers.lsoLogController.sortPilotLsoLog;
import static controllers.lsoLogController.sortPilotLsoLogMonth;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static utilities.ReadFlightLogCSV.readFlightLogCSV;
import static utilities.readLsoGradesCSV.readLsoGradesCSV;

public class LoadData {
    static List<lsoGrades> pilotLsoLog = new ArrayList<>();
    static List<flightLog> pilotFlightlog = new ArrayList<>();
    static List<flightLog> pilotFlightLogMonth = new ArrayList<>();
    static List<flightLog> pilotFlightLogMission = new ArrayList<>();
    static List<lsoGrades> pilotLsoLogMonth = new ArrayList<>();
    static List<lsoGrades> pilotLsoLogMission = new ArrayList<>();
    static List<flightLog> individualFlightLog = new ArrayList<>();
    static List<lsoGrades> individualLsoLog = new ArrayList<>();
    static String path = "C:\\Users\\fpere\\OneDrive\\Documents\\logs\\data_lso_grades(1).txt";
    static String path2 = "C:\\Users\\fpere\\OneDrive\\Documents\\logs\\data_flight_logs(1).txt";
    static ArrayList<lsoGrades> lsoLogs = new ArrayList<>();

    static ArrayList<flightLog> flightLogs = new ArrayList<>();
    public static void  LoadData(){
        try{
            lsoLogs.addAll(readLsoGradesCSV(String.valueOf(path)));
            flightLogs.addAll(readFlightLogCSV(String.valueOf(path2)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        pilotLsoLog.addAll(sortPilotLsoLog(lsoLogs,"VFA-86"));
        pilotLsoLogMonth.addAll(sortPilotLsoLogMonth(pilotLsoLog, "12"));
        pilotFlightlog.addAll(flightLogs.stream().filter(p -> p.callSign.contains("VFA-86")).collect(Collectors.toList()));
        pilotFlightLogMonth.addAll(pilotFlightlog.stream().filter(p -> p.serverDate.substring(21,23).contains("12")).collect(Collectors.toList()));
        pilotFlightLogMission.addAll(calculateMissionCarrierTraps(pilotFlightLogMonth, "18"));


        for(int a = 0; a < pilotFlightLogMission.size(); a++) {
            System.out.println(pilotFlightLogMission.get(a).getCallSign() + pilotFlightLogMission.get(a).getServerDate() + pilotFlightLogMission.get(a).getServerTime());
        }
        pilotLsoLogMission.addAll(pilotLsoLogMonth.stream().filter(p -> p.callSign.contains("Matty")).collect(Collectors.toList()));
        for(int a = 0; a < pilotLsoLogMission.size(); a++) {
            System.out.println(pilotLsoLogMission.get(a).getCallSign() + " " +  pilotLsoLogMission.get(a).getServerDate() + " " + pilotLsoLogMission.get(a).getServerTime());
        }
        individualFlightLog.addAll(pilotFlightLogMission.stream().filter(p -> p.callSign.contains("Matty")).collect(Collectors.toList()));
        individualLsoLog.addAll(pilotLsoLogMission.stream().filter(p -> p.callSign.contains("Matty")).collect(Collectors.toList()));

        for(int s =0; s < individualFlightLog.size(); s++){
            System.out.println(individualFlightLog.get(s).getCallSign() + individualFlightLog.get(s).getServerDate() + individualFlightLog.get(s).getServerTime());
            System.out.println(pilotLsoLogMission.get(s).getCallSign() + pilotLsoLogMission.get(s).getServerDate() + pilotLsoLogMission.get(s).getServerTime());
        }

    }

    public static void main(String[] args) {
        LoadData();
    }

    public static List<flightLog> calculateMissionCarrierTraps(List<flightLog> log, String airframe) {
        List<flightLog> logs = new ArrayList<>();
        int missionTraps = 0;
        for (flightLog flightLog : log) {
            if (flightLog.arrivalField1.contains("CVN") && flightLog.aircraftType.contains(airframe) && parseDouble(flightLog.getFlightTime()) > 1200.0) {
                missionTraps = missionTraps + parseInt(flightLog.landings);
                logs.add(flightLog);

            }
        }
        return logs;
    }
}
