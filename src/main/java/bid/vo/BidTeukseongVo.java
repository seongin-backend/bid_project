package bid.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BidTeukseongVo {
    private String bidId;
    private String teukseongBunryuCode;
    private String teukseongBunryuGubnCode;
    private String teukseongBunryuGubnName;
    private String submitVal;
    private String updateRemk;

    public BidTeukseongVo() {
    }

    @Builder
    public BidTeukseongVo(String bidId, String teukseongBunryuCode, String teukseongBunryuGubnCode, String teukseongBunryuGubnName, String submitVal, String updateRemk) {
        this.bidId = bidId;
        this.teukseongBunryuCode = teukseongBunryuCode;
        this.teukseongBunryuGubnCode = teukseongBunryuGubnCode;
        this.teukseongBunryuGubnName = teukseongBunryuGubnName;
        this.submitVal = submitVal;
        this.updateRemk = updateRemk;
    }
}
