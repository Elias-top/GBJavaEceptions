import java.util.Scanner;

import enums.Status;
import file_system.AddToFileBW;
import file_system.FileSystem;

public class View {
    Scanner scanner = new Scanner(System.in);
    FileSystem fileSystem = new FileSystem(new AddToFileBW());
    private boolean inProgress;
    HumanDataParsing dataParsing = new HumanDataParsing();

    public void start(){
        inProgress = true;
        while(inProgress){
            printMenu();
            scanUserInput();
        }
    }

    private void scanUserInput(){
        String userInput = scanner.nextLine();
        if(userInput == "")
        {
            inProgress = false;
            return;
        }
        Status status = dataParsing.addDataToParsing(userInput);

        if(status == Status.ES_SUCCSESS)
        {
            fileSystem.Write(dataParsing.getSecondName() + ".txt", dataParsing.toString());
        }
    }

    private void printMenu(){
        System.out.println("Напишите ФИО, ДР в формате (yyyy-MMM-dd), номер телефона, пол через пробелы. Чтобы выйти нажмите Enter");
    }

}
