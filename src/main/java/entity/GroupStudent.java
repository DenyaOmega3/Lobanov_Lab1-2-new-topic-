package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupStudent {
    User student;
    Group group;

    public GroupStudent(User student) {
        this.student = student;
    }
}