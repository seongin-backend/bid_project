package bid.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidTeukseongDao {
    private String bidId;
    private String teukseongBunryuCode;
    private String teukseongBunryuGubnCode;
    private String teukseongBunryuGubnName;
    private String submitVal;
    private String updateRemk;
}