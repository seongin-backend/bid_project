package bid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidTeukseongDto {
    private String baljunkiId;
    private String teukseongBunryuCode;
    private String teukseongBunryuGubnCode;
    private String teukseongBunryuGubnName;
    private String submitVal;
    private String updateRemk;
}
