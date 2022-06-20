package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Group {
    int id;
    String codename;
    int number;

    public Group(String codename, int number) {
        this.codename = codename;
        this.number = number;
    }
}
