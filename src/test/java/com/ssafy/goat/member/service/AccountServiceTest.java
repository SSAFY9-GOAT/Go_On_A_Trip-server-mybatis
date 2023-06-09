package com.ssafy.goat.member.service;

import com.ssafy.goat.common.exception.AccountException;
import com.ssafy.goat.common.exception.LoginException;
import com.ssafy.goat.member.Member;
import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.goat.common.exception.ExceptionMessage.ACCOUNT_EXCEPTION;
import static com.ssafy.goat.common.exception.ExceptionMessage.LOGIN_EXCEPTION;
import static com.ssafy.goat.member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired
    private AccountService accountService;// = AccountServiceImpl.getAccountService();
    @Autowired
    private MemberRepository memberRepository;// = MemberJdbcRepository.getMemberRepository();

    @BeforeEach
    void beforeEach() {
        memberRepository.save(Member.builder()
                .loginId("ssafy")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("010101")
                .gender("1")
                .nickname("광주5반")
                .authority(CLIENT)
                .build());
    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    @DisplayName("로그인")
    void login() {
        //given
        String loginId = "ssafy";
        String loginPw = "12345678";

        //when
        LoginMember loginMember = accountService.login(loginId, loginPw);

        //then
        assertThat(loginMember.getLoginId()).isEqualTo(loginId);
        assertThat(loginMember.getLoginPw()).isEqualTo(loginPw);
    }

    @Test
    @DisplayName("로그인#아이디 오류")
    void login_exception_loginId() {
        //given
        String loginId = "ssafy1234";
        String loginPw = "12345678";

        //when
        //then
        assertThatThrownBy(() -> accountService.login(loginId, loginPw))
                .isInstanceOf(LoginException.class)
                .hasMessageContaining(LOGIN_EXCEPTION);
    }

    @Test
    @DisplayName("로그인#비밀번호 오류")
    void login_exception_loginPw() {
        //given
        String loginId = "ssafy";
        String loginPw = "87654321";

        //when
        //then
        assertThatThrownBy(() -> accountService.login(loginId, loginPw))
                .isInstanceOf(LoginException.class)
                .hasMessageContaining(LOGIN_EXCEPTION);
    }

    @Test
    @DisplayName("아이디 찾기")
    void findLoginId() {
        //given
        String email = "ssafy@ssafy.com";
        String phone = "01012345678";

        //when
        String loginId = accountService.findLoginId(email, phone);

        //then
        assertThat(loginId).isEqualTo("ssafy");
    }

    @Test
    @DisplayName("아이디 찾기#이메일 오류")
    void findLoginId_exception_email() {
        //given
        String email = "ssafy1@ssafy.com";
        String phone = "01012345678";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginId(email, phone))
                .isInstanceOf(AccountException.class)
                .hasMessageContaining(ACCOUNT_EXCEPTION);
    }

    @Test
    @DisplayName("아이디 찾기#연락처 오류")
    void findLoginId_exception_phone() {
        //given
        String email = "ssafy@ssafy.com";
        String phone = "010987654321";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginId(email, phone))
                .isInstanceOf(AccountException.class)
                .hasMessageContaining(ACCOUNT_EXCEPTION);
    }

    @Test
    @DisplayName("비밀번호 찾기")
    void findLoginPw() {
        //given
        String loginId = "ssafy";
        String email = "ssafy@ssafy.com";
        String phone = "01012345678";

        //when
        String loginPw = accountService.findLoginPw(loginId, email, phone);

        //then
        assertThat(loginPw).isEqualTo("12345678");
    }

    @Test
    @DisplayName("비밀번호 찾기#아이디 오류")
    void findLoginPw_exception_loginId() {
        //given
        String loginId = "ssafy1";
        String email = "ssafy@ssafy.com";
        String phone = "01012345678";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginPw(loginId, email, phone))
                .isInstanceOf(AccountException.class)
                .hasMessageContaining(ACCOUNT_EXCEPTION);
    }

    @Test
    @DisplayName("비밀번호 찾기#이메일 오류")
    void findLoginPw_exception_email() {
        //given
        String loginId = "ssafy";
        String email = "ssafy1@ssafy.com";
        String phone = "01012345678";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginPw(loginId, email, phone))
                .isInstanceOf(AccountException.class)
                .hasMessageContaining(ACCOUNT_EXCEPTION);
    }

    @Test
    @DisplayName("비밀번호 찾기#연락처 오류")
    void findLoginPw_exception_phone() {
        //given
        String loginId = "ssafy";
        String email = "ssafy@ssafy.com";
        String phone = "01087654321";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginPw(loginId, email, phone))
                .isInstanceOf(AccountException.class)
                .hasMessageContaining(ACCOUNT_EXCEPTION);
    }
}