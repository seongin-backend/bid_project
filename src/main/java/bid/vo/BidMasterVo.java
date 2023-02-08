package bid.vo;

import lombok.*;

@Getter
@ToString
public class BidMasterVo {
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

    public BidMasterVo() {
    }

    @Builder
    public BidMasterVo(String bidId, String guraeDate, String baljunkiCompanyCode, String baljunkiGubnCode, String baljunkiId, String teukiRemk, String baljunkiCompanySign, String submitDate, String submitTime, String submitEmplNumb, String submitEmplSign) {
        this.bidId = bidId;
        this.guraeDate = guraeDate;
        this.baljunkiCompanyCode = baljunkiCompanyCode;
        this.baljunkiGubnCode = baljunkiGubnCode;
        this.baljunkiId = baljunkiId;
        this.teukiRemk = teukiRemk;
        this.baljunkiCompanySign = baljunkiCompanySign;
        this.submitDate = submitDate;
        this.submitTime = submitTime;
        this.submitEmplNumb = submitEmplNumb;
        this.submitEmplSign = submitEmplSign;
    }
}
