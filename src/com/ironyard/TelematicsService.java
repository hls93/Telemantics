package com.ironyard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TelematicsService {
    //Write the VehicleInfo to a file as json using the VIN as the name of the file and a "json" extension
    public  static void report(VehicleInfo newVehicle) throws JsonGenerationException, JsonMappingException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        String vinNum = newVehicle.getVIN();
        mapper.writeValue(new File(vinNum + ".json"), newVehicle);
        String jsonString = mapper.writeValueAsString(newVehicle);
        System.out.println("Here is your vehicle info: " + jsonString);
    }

    //Find all the files that end with ".json" and convert back to a VehicleInfo object.
    public static void convertJSONToObj() throws IOException {
        int incrementVIObject = 0;
        File file = new File(".");
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                ObjectMapper mapper = new ObjectMapper();
                f = new File(f.getName());
                VehicleInfo viFromRepToObj = mapper.readValue(f, VehicleInfo.class);

                System.out.println("Printing Java Object Information");
                System.out.println("Object Reference: " + viFromRepToObj);
                System.out.println("Vehicle VIN : " + viFromRepToObj.getVIN());
                System.out.println("Vehicle Odometer: " + viFromRepToObj.getOdometer());
                System.out.println("Vehicle gas consumed: " + viFromRepToObj.getConsumption());
                System.out.println("Vehicle odometer reading for last oil change: " + viFromRepToObj.getOdReadingLastOilChange());
                System.out.println("Vehicle VIN engine size: " + viFromRepToObj.getEngineSize());
            }
        }
    }

    //Update a dashboard.html
    public static void updateDashboard(VehicleInfo newVehicle) throws IOException {
        List<String> lines = new ArrayList<>();
        final String FILE_NAME = "dashboard.html";
        File file = new File(FILE_NAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        System.out.println(lines);
        String odometer = String.valueOf(newVehicle.getOdometer());
        System.out.println(odometer);

    }

    
}
