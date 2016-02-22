package ua.snakes.ensables.input;

import ua.snakes.ensables.model.Snake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Serves for reading Standard Snake Ensemble Serialisation Files.
 */
public class EnsembleFileReader {

    /**
     * Reads Standard Snake Ensemble Serialisation File.
     *
     * @param csvFile - path(relative or absolute) to Standard Snake Ensemble Serialisation File.
     *
     * @return map: Map<Integer, Snake> - represents data from Standard Snake Ensemble Serialisation File,
     * where the @key is the line number from the file and @value is data of the line.
     */
    public Map<Integer, Snake> read(String csvFile) {

        if (!isValidInput(csvFile)) {
            System.out.println("File has not been specified or has a wrong format." +
                    " Please use *.csv files.");
            return new HashMap<Integer, Snake>();
        }

        return dataFromFile(csvFile);
    }

    private boolean isValidInput(String input) {
        return input != null && !input.isEmpty() && input.endsWith(".csv");
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
        } catch(IOException e){
            System.out.println("Could not extract data from file: " + file);
        }catch(NumberFormatException e){
            System.out.println("Wrong integer value at line: " + lineNumber);
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
