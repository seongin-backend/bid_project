package bid.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BidTeukseongVo {
    private String bidId;
    private String teukseongBunryuCode;
    private String teukseongBunryuGubnCode;
    private String teukseongBunryuGubnName;
    private String submitVal;
    private String updateRemk;
}
