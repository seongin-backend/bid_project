package bid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidDetailDao {
    private String bidId;
    private String bidGubnCode;
    private String guraeTimeGubnCode;
    private String guraeTime;
    private String gubnCode;
    private String gubnName;
    private String submitVal;
}
