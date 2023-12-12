package file_system;

import java.io.BufferedWriter;
import java.io.FileWriter;

import enums.Status;

public class AddToFileBW implements Writable{
    @Override
    public Status writeToFile(String file_path, String data) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(file_path, true))) {
            writter.write(data + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
            return Status.ES_ERROR;
        }
        return Status.ES_SUCCSESS;
    }
}
