import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import enums.Gender;
import enums.Status;
import exceptions.FIOFormatException;
import exceptions.GenderFormatException;
import exceptions.MyDataFormatException;
import exceptions.PhoneFormatException;

public class HumanDataParsing {
    private final int HUMAN_NUM_PARAM = 6;

    private final int HUMAN_STRING_PARAM = 3;
    private final int HUMAN_LONG_PARAM = 1;
    private final int HUMAN_DATE_PARAM = 1;
    private final int HUMAN_GENDER_PARAM = 1;

    private LocalDate dateBirth;
    private Gender gender;
    private long phoneNumber;
    private String name;
    private String secondName;
    private String thirdName;

    private String dataToParsing;
    private String[] parsedData;

    public HumanDataParsing(){
        dateBirth = null;
        gender = null;
        phoneNumber = 0;
        name = null;
        secondName = null;
        thirdName = null;

        dataToParsing = null;
    }

    public String getSecondName(){
        return this.secondName;
    }
    public Status addDataToParsing(String dataToParsing){
        this.dataToParsing = dataToParsing;
        return dataParse();
    }

    private Status dataParse()
    {
        Status status_of_parsing = Status.ES_BAD_PARSING;
        this.parsedData = this.dataToParsing.split(" ");

        try{
            if(this.parsedData.length < HUMAN_NUM_PARAM)
            {
                throw new IllegalArgumentException("Num of parametrs is too small");
            }
            else if(this.parsedData.length > HUMAN_NUM_PARAM)
            {
                throw new IllegalArgumentException("Num of parametrs more than need");
            }
        }catch(IllegalArgumentException e)
        {
            e.printStackTrace();
        }

        status_of_parsing = parseFIO();
        if(status_of_parsing == Status.ES_BAD_PARSING)
        {
            return Status.ES_BAD_PARSING;
        }

        status_of_parsing = parsePhone();
        if(status_of_parsing == Status.ES_BAD_PARSING)
        {
            return Status.ES_BAD_PARSING;
        }

        status_of_parsing = parseDate();
        if(status_of_parsing == Status.ES_BAD_PARSING)
        {
            return Status.ES_BAD_PARSING;
        }        

        status_of_parsing = parseGender();
        if(status_of_parsing == Status.ES_BAD_PARSING)
        {
            return Status.ES_BAD_PARSING;
        }

        return Status.ES_SUCCSESS;
    }

    private Status parseFIO(){
        int found = 0;
        Status status = Status.ES_BAD_PARSING;
        try{
            for(int i = 0; i < this.parsedData.length; i++)
            {
                boolean result = this.parsedData[i].matches("^[a-zA-Z\\s]{2,}"); //check for fio
                if(result == true)
                {
                    found ++;
                    if(found == 1){
                        this.secondName = this.parsedData[i];
                    }
                    else if(found == 2){
                        this.name = this.parsedData[i];
                    }
                    else if(found == 3){
                        this.thirdName = this.parsedData[i];
                    }
                }
            }
            if(found != HUMAN_STRING_PARAM)
            {
                throw new FIOFormatException();
            }
            status = Status.ES_SUCCSESS;
        }catch(FIOFormatException e){
            e.printStackTrace();
            status = Status.ES_BAD_PARSING;
        }
        return status;
    }

    private Status parsePhone()
    {
        int found = 0;
        Status status = Status.ES_BAD_PARSING;
        try{
            for(int i = 0; i < this.parsedData.length; i++)
            {
                boolean result = this.parsedData[i].matches("\\d{12}"); //check for belarus phone
                if(result == true)
                {
                    found ++;
                    if(found == 1){
                        this.phoneNumber = Long.parseLong(parsedData[i]);
                    }
                }
            }
            if(found != HUMAN_LONG_PARAM)
            {
                throw new PhoneFormatException();
            }
            status = Status.ES_SUCCSESS;
        }catch(PhoneFormatException e){
            e.printStackTrace();
            status = Status.ES_BAD_PARSING;
        }

        return status;
    }

    private Status parseDate()
    {
        int found = 0;
        Status status = Status.ES_BAD_PARSING;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

        try{
            for(int i = 0; i < this.parsedData.length; i++)
            {
                boolean result = this.parsedData[i].matches("\\d+-\\d+-\\d+"); 
                if(result == true)
                {
                    found ++;
                    if(found == 1){
                        try{
                            this.dateBirth = LocalDate.from(formatter.ISO_LOCAL_DATE.parse(this.parsedData[i]));
                        }catch(DateTimeParseException e){
                            System.out.println(e);
                        }
                    }
                }
            }
            if(found != HUMAN_DATE_PARAM)
            {
                throw new MyDataFormatException();
            }
            status = Status.ES_SUCCSESS;
        }catch(MyDataFormatException e){
            e.printStackTrace();
            status = Status.ES_BAD_PARSING;
        }
        return status;
    }

    private Status parseGender()
    {
        int found = 0;
        Status status = Status.ES_BAD_PARSING;
        try{
            for(int i = 0; i < this.parsedData.length; i++)
            {
                boolean result = this.parsedData[i].matches("[f,m]{1}");
                if(result == true)
                {
                    found ++;
                    if(found == 1){
                        if(this.parsedData[i].equals("f")){
                            this.gender = Gender.Female;
                        }
                        else
                        {
                            this.gender = Gender.Male;
                        }
                    }
                }
            }
            if(found != HUMAN_GENDER_PARAM)
            {
                throw new GenderFormatException();
            }
            status = Status.ES_SUCCSESS;
        }catch(GenderFormatException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public String toString() {
        return this.name + " " +this.secondName + " " + this.thirdName + " " + this.dateBirth + " " +this.phoneNumber + " " +this.gender;
    }
}
