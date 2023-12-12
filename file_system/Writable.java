package file_system;

import enums.Status;

public interface Writable {
    Status writeToFile(String file_path, String data);
}
