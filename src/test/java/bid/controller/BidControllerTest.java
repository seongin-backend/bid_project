package bid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 입찰_마스터_조회() throws Exception {
        ResultActions resultActions;
        ObjectMapper objectMapper = new ObjectMapper();
        resultActions = mockMvc.perform(
                get("/bid/master")
                        .param("guraeDate", "20230126")
                        .param("baljunkiCompanyCode", "00001")
                        .param("baljunkiGubnCode", "01")
                        .param("baljunkiId", "BAJUNKI001")
                        .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BidRestController.class))
                .andExpect(handler().methodName("selectBidMasterList"))
                .andExpect(jsonPath("$.bidId", is("20230126_00001_01_BAJUNKI001"))) //PK채번 테스트
                .andExpect(jsonPath("$.guraeDate", is("20230126")))
                .andExpect(jsonPath("$.baljunkiCompanyCode", is("00001")))
                .andExpect(jsonPath("$.baljunkiGubnCode", is("01")))
                .andExpect(jsonPath("$.baljunkiId", is("BAJUNKI001")));
    }

}
