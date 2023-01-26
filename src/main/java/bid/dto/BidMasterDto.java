package bid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidMasterDto {
    private String bidId;
    private String guraeDate;
    private String baljunkiCompanyCode;
    private String baljunkiGubnCode;
    private String baljunkiId;
    private String teukiRemk;
    private String baljunkiCompanySign;
    private String submitDate;
    private String submitTime;
    private String submitEmplNumb;
    private String submitEmplSign;
}
