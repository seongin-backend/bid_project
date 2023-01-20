package bid.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BidMasterVo {
    private String baljunkiId;
    private String baljunkiGubnCode;
    private String teukiRemk;
    private String baljunEmplSign;
    private String submitDate;
    private String submitTime;
    private String submitEmplName;
    private String submitEmplSign;
}
