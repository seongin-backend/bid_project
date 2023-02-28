package bid.controller;

import bid.vo.BidMasterVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

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

    @BeforeAll
    void setUp() {
        bidMasterVo = BidMasterVo.builder()
                .guraeDate("20230126")
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
    void 입찰_마스터_추가_테스트() throws Exception {

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

        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidMaster"))
                .andExpect(jsonPath("$.bidId", is("20230126_00001_01_BAJUNKI001"))) //PK채번 테스트
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
    @Order(2)
    void 입찰_마스터_조회() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                get("/bid/master")
                        .param("guraeDate", bidMasterVo.getGuraeDate())
                        .param("baljunkiCompanyCode", bidMasterVo.getBaljunkiCompanyCode())
                        .param("baljunkiGubnCode", bidMasterVo.getBaljunkiGubnCode())
                        .param("baljunkiId",bidMasterVo.getBaljunkiId())
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidMaster"))
                .andExpect(jsonPath("$.bidId", is("20230126_00001_01_BAJUNKI001"))) //PK채번 테스트
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
    void 입찰_특성_조회() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
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
                .andExpect(jsonPath("$[0].teukseongBunryuCode", is("01"))) //pk
                .andExpect(jsonPath("$[0].teukseongBunryuGubnCode", is("01"))) //pk
                .andExpect(jsonPath("$[0].teukseongBunryuGubnName", is("열간 기동소요시간")))
                .andExpect(jsonPath("$[0].submitVal", is("8")))
                .andExpect(jsonPath("$[0].updateRemk", is("시간 더 걸림")))
        ;
    }

    @Test
    void 입찰_특성_조회_피벗() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
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
    void 입찰_디테일_조회() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
        String bidGubnCode = "01";
        ResultActions resultActions = mockMvc.perform(
                get("/bid/detail")
                        .param("bidId", bidId)
                        .param("bidGubnCode", bidGubnCode)
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidDetailList"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
                .andExpect(jsonPath("$[0].bidGubnCode", is(bidGubnCode))) //pk
                .andExpect(jsonPath("$[0].guraeTimeGubnCode").exists()) //pk
                .andExpect(jsonPath("$[0].guraeTime").exists()) //pk
                .andExpect(jsonPath("$[0].gubnCode").exists()) //pk
                .andExpect(jsonPath("$[0].gubnName").exists())
                .andExpect(jsonPath("$[0].submitVal").exists())
        ;
    }

    @Test
    void 입찰_디테일_조회_피벗() throws Exception {
        String bidId = "20230126_00001_01_BAJUNKI001";
        String bidGubnCode = "01";
        ResultActions resultActions = mockMvc.perform(
                get("/bid/detail/pivot")
                        .param("bidId", bidId)
                        .param("bidGubnCode", bidGubnCode)
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidDetailListPivot"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].bidId", is(bidId))) //pk
                .andExpect(jsonPath("$[0].bidGubnCode", is(bidGubnCode))) //pk
                .andExpect(jsonPath("$[0].gubnCode", is("01"))) //pk
                .andExpect(jsonPath("$[0].gubnName", is("공급가능용량")))
                .andExpect(jsonPath("$[0].d_1", is("100")))
                .andExpect(jsonPath("$[0].d_2", is("200")))
                .andExpect(jsonPath("$[0].d_3", is("300")))
        ;
    }

}