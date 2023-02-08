package bid.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BidDetailVo {
    private String bidId;
    private String bidGubnCode;
    private String guraeTimeGubnCode;
    private String guraeTime;
    private String gubnCode;
    private String gubnName;
    private String submitVal;
}
