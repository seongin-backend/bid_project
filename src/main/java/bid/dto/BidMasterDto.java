package bid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidMasterDto {
    private String baljunkiId;
    private String baljunkiGubnCode;
    private String teukiRemk;
    private String baljunEmplSign;
    private String submitDate;
    private String submitTime;
    private String submitEmplName;
    private String submitEmplSign;
}
