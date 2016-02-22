package ua.snakes.ensables.input;

import ua.snakes.ensables.model.Snake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnsembleFileReader {

    public Map<Integer, Snake> read(String csvFile) {

        if (!isValidInput(csvFile)) {
            return new HashMap<Integer, Snake>();
        }

        return dataFromFile(csvFile);
    }

    private boolean isValidInput(String input) {
        return input != null && !input.isEmpty();
    }

    private Map<Integer, Snake> dataFromFile(String file){
        Map<Integer, Snake> data = new HashMap();

        BufferedReader br = null;
        String line = "";
        int lineNumber = 0;
        String splitter = ",";

        try {
            br = new BufferedReader(new FileReader(file));
            while((line = br.readLine())!= null){
                String[] values = line.split(splitter);
                if (values.length == 3){
                    data.put(lineNumber,
                            new Snake(
                                    values[0],
                                    Integer.parseInt(values[1].trim()),
                                    Integer.parseInt(values[2].trim())
                    ));
                }else if(values.length == 4){
                    data.put(lineNumber,
                            new Snake(
                                    values[0].trim(),
                                    Integer.parseInt(values[1].trim()),
                                    Integer.parseInt(values[2].trim()),
                                    Double.parseDouble(values[3].trim())
                            ));
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read file: " + file);
            e.printStackTrace();
        } catch(IOException e){
            System.out.println("Could not extract data from file: " + file);
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    return data;
                }
            }
        }

        return data;
    }
}
