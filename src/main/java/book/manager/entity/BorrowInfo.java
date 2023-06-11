package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class BorrowInfo {
    int id;
    int bid;
    int sid;
    String bTitle;
    String uName;
    Date time;
    String status;
}
