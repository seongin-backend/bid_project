package bid.controller;

import bid.vo.BidDetailVo;
import bid.vo.BidMasterVo;
import bid.vo.BidTeukseongVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.LinkedList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BidRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    BidMasterVo bidMasterVo;
    LinkedList<BidTeukseongVo> bidTeukseongVolinkedList = new LinkedList<BidTeukseongVo>();
    LinkedList<BidDetailVo> bidDetailVolinkedList = new LinkedList<BidDetailVo>();
    String bidId = "";

    @BeforeAll
    void setUp() {
        bidMasterVo = BidMasterVo.builder()
            .guraeDate("20230131")
            .baljunkiCompanyCode("00001")
            .baljunkiGubnCode("01")
            .baljunkiId("BAJUNKI001")
            .teukiRemk("특이사항")
            .baljunkiCompanySign("발전기회사서명이미지주소")
            .submitEmplNumb("vtw1606")
            .submitEmplSign("접수자서명이미지주소")
            .build();
    }

    @Test
    @Order(1)
    void 입찰_마스터_추가() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String bidMasterVoJson = objectMapper.writeValueAsString(bidMasterVo);
        ResultActions resultActions;
        resultActions = mockMvc.perform(
            post("/bid/master")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bidMasterVoJson)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("insertBidMaster"))
        ;

        resultActions = mockMvc.perform(
            get("/bid/master")
                .param("guraeDate", bidMasterVo.getGuraeDate())
                .param("baljunkiCompanyCode", bidMasterVo.getBaljunkiCompanyCode())
                .param("baljunkiGubnCode", bidMasterVo.getBaljunkiGubnCode())
                .param("baljunkiId", bidMasterVo.getBaljunkiId())
                .accept(MediaType.APPLICATION_JSON)
        );

        String bidID = bidMasterVo.getGuraeDate()+"_"+bidMasterVo.getBaljunkiCompanyCode() + "_"
            + bidMasterVo.getBaljunkiGubnCode()+"_"+bidMasterVo.getBaljunkiId(); //나오기를 원하는 pk

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidMaster"))
            .andExpect(jsonPath("$.bidId", is(bidID))) //PK채번 테스트
            .andExpect(jsonPath("$.guraeDate", is(bidMasterVo.getGuraeDate())))
            .andExpect(jsonPath("$.baljunkiCompanyCode", is(bidMasterVo.getBaljunkiCompanyCode())))
            .andExpect(jsonPath("$.baljunkiGubnCode", is(bidMasterVo.getBaljunkiGubnCode())))
            .andExpect(jsonPath("$.baljunkiId", is(bidMasterVo.getBaljunkiId())))
            .andExpect(jsonPath("$.teukiRemk", is(bidMasterVo.getTeukiRemk())))
            .andExpect(jsonPath("$.baljunkiCompanySign", is(bidMasterVo.getBaljunkiCompanySign())))
            .andExpect(jsonPath("$.submitDate").exists())
            .andExpect(jsonPath("$.submitTime").exists())
            .andExpect(jsonPath("$.submitEmplNumb", is(bidMasterVo.getSubmitEmplNumb())))
            .andExpect(jsonPath("$.submitEmplSign", is(bidMasterVo.getSubmitEmplSign())))
        ;

        this.bidId = bidID; //pk
        bidTeukseongVolinkedList.add(
            BidTeukseongVo.builder()
                .bidId(bidId)
                .teukseongBunryuCode("01")
                .teukseongBunryuGubnCode("01")
                .teukseongBunryuGubnName("열간 기동소요시간")
                .submitVal("8")
                .updateRemk("시간 더 걸림")
                .build()
        );

        bidTeukseongVolinkedList.add(
            BidTeukseongVo.builder()
                .bidId(bidId)
                .teukseongBunryuCode("01")
                .teukseongBunryuGubnCode("02")
                .teukseongBunryuGubnName("열간 최소발전용량도달시간")
                .submitVal("3")
                .updateRemk("시간 줄음")
                .build()
        );

        bidDetailVolinkedList.add(
            BidDetailVo.builder()
                .bidId(bidId)
                .bidGubnCode("01")
                .guraeTimeGubnCode("D-1")
                .guraeTime("19")
                .gubnCode("01")
                .gubnName("공급가용용량")
                .submitVal("1900")
                .build()
        );

        bidDetailVolinkedList.add(
            BidDetailVo.builder()
                .bidId(bidId)
                .bidGubnCode("01")
                .guraeTimeGubnCode("D-1")
                .guraeTime("20")
                .gubnCode("01")
                .gubnName("공급가용용량")
                .submitVal("2000")
                .build()
        );
    }

    @Test
    @Order(2)
    void 입찰_마스터_조회() throws Exception {
        String bidId = this.bidId;
        ResultActions resultActions = mockMvc.perform(
            get("/bid/master")
                .param("guraeDate", bidMasterVo.getGuraeDate())
                .param("baljunkiCompanyCode", bidMasterVo.getBaljunkiCompanyCode())
                .param("baljunkiGubnCode", bidMasterVo.getBaljunkiGubnCode())
                .param("baljunkiId", bidMasterVo.getBaljunkiId())
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidMaster"))
            .andExpect(jsonPath("$.bidId", is(bidId))) //PK채번 테스트
            .andExpect(jsonPath("$.guraeDate", is(bidMasterVo.getGuraeDate())))
            .andExpect(jsonPath("$.baljunkiCompanyCode", is(bidMasterVo.getBaljunkiCompanyCode())))
            .andExpect(jsonPath("$.baljunkiGubnCode", is(bidMasterVo.getBaljunkiGubnCode())))
            .andExpect(jsonPath("$.baljunkiId", is(bidMasterVo.getBaljunkiId())))
            .andExpect(jsonPath("$.teukiRemk", is(bidMasterVo.getTeukiRemk())))
            .andExpect(jsonPath("$.baljunkiCompanySign", is(bidMasterVo.getBaljunkiCompanySign())))
            .andExpect(jsonPath("$.submitDate").exists())
            .andExpect(jsonPath("$.submitTime").exists())
            .andExpect(jsonPath("$.submitEmplNumb", is(bidMasterVo.getSubmitEmplNumb())))
            .andExpect(jsonPath("$.submitEmplSign", is(bidMasterVo.getSubmitEmplSign())))
        ;
    }

    @Test
    @Order(3)
    void 입찰_특성_추가() throws Exception {
        String bidId = this.bidId;
        ObjectMapper objectMapper = new ObjectMapper();
        String bidTeukseongVoJson = objectMapper.writeValueAsString(bidTeukseongVolinkedList);

        ResultActions resultActions = mockMvc.perform(
            post("/bid/teukseong")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bidTeukseongVoJson)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("insertBidTeukseong"))
        ;

        resultActions = mockMvc.perform(
            get("/bid/teukseong")
                .param("bidId", bidTeukseongVolinkedList.get(0).getBidId())
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidTeukseongList"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[0].teukseongBunryuCode", is(bidTeukseongVolinkedList.get(0).getTeukseongBunryuCode()))) //pk
            .andExpect(jsonPath("$[0].teukseongBunryuGubnCode", is(bidTeukseongVolinkedList.get(0).getTeukseongBunryuGubnCode()))) //pk
            .andExpect(jsonPath("$[0].teukseongBunryuGubnName", is(bidTeukseongVolinkedList.get(0).getTeukseongBunryuGubnName())))
            .andExpect(jsonPath("$[0].submitVal", is(bidTeukseongVolinkedList.get(0).getSubmitVal())))
            .andExpect(jsonPath("$[0].updateRemk", is(bidTeukseongVolinkedList.get(0).getUpdateRemk())))

            .andExpect(jsonPath("$[1].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[1].teukseongBunryuCode", is(bidTeukseongVolinkedList.get(1).getTeukseongBunryuCode()))) //pk
            .andExpect(jsonPath("$[1].teukseongBunryuGubnCode", is(bidTeukseongVolinkedList.get(1).getTeukseongBunryuGubnCode()))) //pk
            .andExpect(jsonPath("$[1].teukseongBunryuGubnName", is(bidTeukseongVolinkedList.get(1).getTeukseongBunryuGubnName())))
            .andExpect(jsonPath("$[1].submitVal", is(bidTeukseongVolinkedList.get(1).getSubmitVal())))
            .andExpect(jsonPath("$[1].updateRemk", is(bidTeukseongVolinkedList.get(1).getUpdateRemk())))
        ;
    }

    @Test
    @Order(4)
    void 입찰_특성_조회() throws Exception {
        String bidId = this.bidId;
        ResultActions resultActions = mockMvc.perform(
            get("/bid/teukseong")
                .param("bidId", bidId)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidTeukseongList"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[0].teukseongBunryuCode", is(bidTeukseongVolinkedList.get(0).getTeukseongBunryuCode()))) //pk
            .andExpect(jsonPath("$[0].teukseongBunryuGubnCode", is(bidTeukseongVolinkedList.get(0).getTeukseongBunryuGubnCode()))) //pk
            .andExpect(jsonPath("$[0].teukseongBunryuGubnName", is(bidTeukseongVolinkedList.get(0).getTeukseongBunryuGubnName())))
            .andExpect(jsonPath("$[0].submitVal", is(bidTeukseongVolinkedList.get(0).getSubmitVal())))
            .andExpect(jsonPath("$[0].updateRemk", is(bidTeukseongVolinkedList.get(0).getUpdateRemk())))

            .andExpect(jsonPath("$[1].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[1].teukseongBunryuCode", is(bidTeukseongVolinkedList.get(1).getTeukseongBunryuCode()))) //pk
            .andExpect(jsonPath("$[1].teukseongBunryuGubnCode", is(bidTeukseongVolinkedList.get(1).getTeukseongBunryuGubnCode()))) //pk
            .andExpect(jsonPath("$[1].teukseongBunryuGubnName", is(bidTeukseongVolinkedList.get(1).getTeukseongBunryuGubnName())))
            .andExpect(jsonPath("$[1].submitVal", is(bidTeukseongVolinkedList.get(1).getSubmitVal())))
            .andExpect(jsonPath("$[1].updateRemk", is(bidTeukseongVolinkedList.get(1).getUpdateRemk())))
        ;
    }

    @Test
    @Order(5)
    void 입찰_특성_조회_피벗() throws Exception {
        String bidId = this.bidId;
        ResultActions resultActions = mockMvc.perform(
            get("/bid/teukseong/pivot")
                .param("bidId", bidId)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidTeukseongListPivot"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[0].teukseongBunryuCode", is("01"))) //pk
            .andExpect(jsonPath("$[0].submit_VAL_01_01", is("8")))
            .andExpect(jsonPath("$[0].submit_VAL_01_02", is("3")))
            .andExpect(jsonPath("$[0].update_REMK_01_01", is("시간 더 걸림")))
            .andExpect(jsonPath("$[0].update_REMK_01_02", is("시간 줄음")))
        ;
    }

    @Test
    @Order(6)
    void 입찰_디테일_추가() throws Exception {
        String bidId = this.bidId;
        ObjectMapper objectMapper = new ObjectMapper();
        String bidDetailVoJson = objectMapper.writeValueAsString(bidDetailVolinkedList);

        ResultActions resultActions = mockMvc.perform(
            post("/bid/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bidDetailVoJson)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("insertBidDetail"))
        ;

        resultActions = mockMvc.perform(
            get("/bid/detail")
                .param("bidId", bidId)
                .param("bidGubnCode", bidDetailVolinkedList.get(0).getBidGubnCode())
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidDetailList"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[0].bidGubnCode", is(bidDetailVolinkedList.get(0).getBidGubnCode()))) //pk
            .andExpect(jsonPath("$[0].guraeTimeGubnCode", is(bidDetailVolinkedList.get(0).getGuraeTimeGubnCode()))) //pk
            .andExpect(jsonPath("$[0].guraeTime", is(bidDetailVolinkedList.get(0).getGuraeTime())))//pk
            .andExpect(jsonPath("$[0].gubnCode", is(bidDetailVolinkedList.get(0).getGubnCode()))) //pk
            .andExpect(jsonPath("$[0].gubnName", is(bidDetailVolinkedList.get(0).getGubnName())))
            .andExpect(jsonPath("$[0].submitVal", is(bidDetailVolinkedList.get(0).getSubmitVal())))

            .andExpect(jsonPath("$[1].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[1].bidGubnCode", is(bidDetailVolinkedList.get(1).getBidGubnCode()))) //pk
            .andExpect(jsonPath("$[1].guraeTimeGubnCode", is(bidDetailVolinkedList.get(1).getGuraeTimeGubnCode()))) //pk
            .andExpect(jsonPath("$[1].guraeTime", is(bidDetailVolinkedList.get(1).getGuraeTime())))//pk
            .andExpect(jsonPath("$[1].gubnCode", is(bidDetailVolinkedList.get(1).getGubnCode()))) //pk
            .andExpect(jsonPath("$[1].gubnName", is(bidDetailVolinkedList.get(1).getGubnName())))
            .andExpect(jsonPath("$[1].submitVal", is(bidDetailVolinkedList.get(1).getSubmitVal())))
        ;
    }

    @Test
    @Order(7)
    void 입찰_디테일_조회() throws Exception {
        String bidId = this.bidId;
        ResultActions resultActions = mockMvc.perform(
            get("/bid/detail")
                .param("bidId", bidId)
                .param("bidGubnCode", bidDetailVolinkedList.get(0).getBidGubnCode())
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidDetailList"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[0].bidGubnCode", is(bidDetailVolinkedList.get(0).getBidGubnCode()))) //pk
            .andExpect(jsonPath("$[0].guraeTimeGubnCode", is(bidDetailVolinkedList.get(0).getGuraeTimeGubnCode()))) //pk
            .andExpect(jsonPath("$[0].guraeTime", is(bidDetailVolinkedList.get(0).getGuraeTime())))//pk
            .andExpect(jsonPath("$[0].gubnCode", is(bidDetailVolinkedList.get(0).getGubnCode()))) //pk
            .andExpect(jsonPath("$[0].gubnName", is(bidDetailVolinkedList.get(0).getGubnName())))
            .andExpect(jsonPath("$[0].submitVal", is(bidDetailVolinkedList.get(0).getSubmitVal())))

            .andExpect(jsonPath("$[1].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[1].bidGubnCode", is(bidDetailVolinkedList.get(1).getBidGubnCode()))) //pk
            .andExpect(jsonPath("$[1].guraeTimeGubnCode", is(bidDetailVolinkedList.get(1).getGuraeTimeGubnCode()))) //pk
            .andExpect(jsonPath("$[1].guraeTime", is(bidDetailVolinkedList.get(1).getGuraeTime())))//pk
            .andExpect(jsonPath("$[1].gubnCode", is(bidDetailVolinkedList.get(1).getGubnCode()))) //pk
            .andExpect(jsonPath("$[1].gubnName", is(bidDetailVolinkedList.get(1).getGubnName())))
            .andExpect(jsonPath("$[1].submitVal", is(bidDetailVolinkedList.get(1).getSubmitVal())))
        ;
    }

    @Test
    @Order(8)
    void 입찰_디테일_조회_피벗() throws Exception {
        String bidId = this.bidId;
        ResultActions resultActions = mockMvc.perform(
            get("/bid/detail/pivot")
                .param("bidId", bidId)
                .param("bidGubnCode", bidDetailVolinkedList.get(0).getBidGubnCode())
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(BidRestController.class))
            .andExpect(handler().methodName("selectBidDetailListPivot"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
            .andExpect(jsonPath("$[0].bidGubnCode", is(bidDetailVolinkedList.get(0).getBidGubnCode()))) //pk
            .andExpect(jsonPath("$[0].gubnCode", is(bidDetailVolinkedList.get(0).getGubnCode()))) //pk
            .andExpect(jsonPath("$[0].gubnName", is(bidDetailVolinkedList.get(0).getGubnName())))
            .andExpect(jsonPath("$[0].dminus1_19", is("1900")))
            .andExpect(jsonPath("$[0].dminus1_20", is("2000")))
        ;
    }

}