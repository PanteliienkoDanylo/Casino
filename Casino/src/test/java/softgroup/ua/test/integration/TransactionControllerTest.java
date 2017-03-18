/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import softgroup.ua.jpa.TransactionEntity;
import softgroup.ua.service.TransactionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import softgroup.ua.api.AddTransactionRequest;
import softgroup.ua.api.RolesListReply;
import softgroup.ua.api.TransactionsListReply;
import softgroup.ua.jpa.User;
import softgroup.ua.repository.UserRepository;
import softgroup.ua.service.TransactionMapper;
import softgroup.ua.utils.EntityIdGenerator;
/**
 *
 * @author alexche
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionMapper transactionMapper;
    
    TransactionEntity transactionEntity;
    User testUser;
    
    @Before
    public void setUp() {
        testUser = new User();
        testUser.setBalance(new BigDecimal(500));
        testUser.setEmail("test@casino.com");
        testUser.setLoginId("TestUserTrans");
        testUser.setPassword("qwerty");
        testUser.setLastLoginDate(new GregorianCalendar());
        userRepository.save(testUser);
        
        transactionEntity = new TransactionEntity(EntityIdGenerator.random(), new Date(System.currentTimeMillis()), new BigDecimal(150));
        transactionEntity.setInfo("Transaction information");
        transactionEntity.setUser(userRepository.findOne(testUser.getLoginId()));
        transactionService.save(transactionEntity);
    }
    
    @After
    public void tearDown() {
        transactionService.deleteTransaction(transactionEntity.getTransactionId());
        userRepository.delete(testUser);
    }
    
    @Test
    public void getAllTransactionsTest() throws  Exception {
        this.mockMvc.perform(get("/transactions/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(transactionEntity.getUser().getLoginId())))
                .andExpect(content().string(containsString(String.valueOf(transactionEntity.getTransactionId()))))
                .andExpect(content().string(containsString(String.valueOf(transactionEntity.getAmount()))));
    }
    
    @Test
    public void getTransactionsByLoginIdTest() throws  Exception {
        this.mockMvc.perform(get("/transactions/byloginid/TestUserTrans"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(transactionEntity.getUser().getLoginId())))
                .andExpect(content().string(containsString(String.valueOf(transactionEntity.getTransactionId()))))
                .andExpect(content().string(containsString(String.valueOf(transactionEntity.getAmount()))))
                .andExpect(content().string(containsString(transactionEntity.getInfo())));
    }
    
    @Test
    public void addTransaction() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AddTransactionRequest addTransactionRequest = new AddTransactionRequest();
        addTransactionRequest.transaction = transactionMapper.fromInternal(transactionEntity);
        String content = objectMapper.writeValueAsString(addTransactionRequest);
        MvcResult result = mockMvc.perform(post("/transactions/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();
        String reply = result.getResponse().getContentAsString();
        TransactionsListReply transactionsListReply = objectMapper.readValue(reply,TransactionsListReply.class);
        assertEquals("Return code isn't 0",0, transactionsListReply.retcode.intValue());
    }
}
