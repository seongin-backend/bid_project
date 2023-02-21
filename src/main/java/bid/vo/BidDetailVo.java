package bid.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BidDetailVo {
    private String bidId;
    private String bidGubnCode;
    private String guraeTimeGubnCode;
    private String guraeTime;
    private String gubnCode;
    private String gubnName;
    private String submitVal;

    public BidDetailVo() {
    }

    @Builder
    public BidDetailVo(String bidId, String bidGubnCode, String guraeTimeGubnCode, String guraeTime, String gubnCode, String gubnName, String submitVal) {
        this.bidId = bidId;
        this.bidGubnCode = bidGubnCode;
        this.guraeTimeGubnCode = guraeTimeGubnCode;
        this.guraeTime = guraeTime;
        this.gubnCode = gubnCode;
        this.gubnName = gubnName;
        this.submitVal = submitVal;
    }
}
