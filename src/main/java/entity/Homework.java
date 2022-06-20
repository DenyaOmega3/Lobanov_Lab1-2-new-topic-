package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Homework {
    int id;
    String name;
    String description;
    InputStream pdfFile;
    LocalDate deadline;
    User userID;
    Group groupID;


    public Homework(String name, String description, InputStream pdfFile, LocalDate deadline, User userID, Group groupID) {
        this.name = name;
        this.description = description;
        this.pdfFile = pdfFile;
        this.deadline = deadline;
        this.userID = userID;
        this.groupID = groupID;
    }
}
