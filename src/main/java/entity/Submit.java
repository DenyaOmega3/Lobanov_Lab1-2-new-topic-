package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@NoArgsConstructor
public class Submit {
    int id;
    InputStream pdf_homework;
    User user_id;
    Homework homework_id;
    int grade;

    public Submit(InputStream pdf_homework, User user_id, Homework homework_id, int grade) {
        this.pdf_homework = pdf_homework;
        this.user_id = user_id;
        this.grade = grade;
        this.homework_id = homework_id;
    }
}
