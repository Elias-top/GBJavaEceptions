package file_system;

public class FileSystem {
    Writable writable;

    public FileSystem(Writable writable){
        this.writable = writable;
    }

    public void Write(String file_path, String data)
    {
        writable.writeToFile(file_path, data);
    }
}
