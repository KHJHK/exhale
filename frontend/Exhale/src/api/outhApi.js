import axios from "axios";
import { useAuthStore } from "@/stores/auth";
const formDataHeader = {"Content-Type":
"multipart/form-data"};

    // 2 - 이미 존재하는 값
    const isIdDuplicated = async (userId) => {
        
        try {
        const response = await axios.post('http://i10b208.p.ssafy.io/api/general/id', {
            login_id: userId
        });
       
        console.log("사용 가능한 아이디입니다.");
        alert("사용 가능한 아이디입니다.");
        return false; 
    } catch (error) {
        if (error.response && error.response.status === 400) {
            console.log("아이디가 이미 사용 중입니다.");
            alert("아이디가 이미 사용 중입니다.");
            return true; 
        } else {
            console.error("아이디 중복 확인 중 오류가 발생했습니다:", error);
            
        }
    }
};

            const verifyNumberCreate = async (email_id, email_domain) => {
                try {
                    const response = await axios.post('http://i10b208.p.ssafy.io/api/email/certification', {
                        email_id: email_id,
                        email_domain : email_domain
                    })
                    console.log('인증 성공!', response)
                } catch (error) {
                    console.error('이메일 중복 확인 요청 에러:', error)
                    throw error;
                 }}; 
            
            const emailVerifyRequest = async (email_id, email_domain) => {
                return await axios.post('http://i10b208.p.ssafy.io/api/email/check',  {
                    email_id: email_id,
                    email_domain : email_domain
                })
            };
            
            const signUp = async (userId, email_id, email_domain, fullname, birthdate, password, nickName) => {      
                try {
                    const response = await axios.post('http://i10b208.p.ssafy.io/api/general/join', {
                      login_id: userId,
                      password: password,
                      email_id: email_id,
                      email_domain: email_domain,
                      name: fullname,
                      birth: birthdate,
                      nickname: nickName
                    });
                    console.log('회원가입 성공:', response);
                    alert('날숨의 가족이 되신것을 환영해요! 다시 로그인 해주세요');
                    
                    // return response.data; 
                  } catch (error) {
                    if (axios.isAxiosError(error)) {
                      // 서버 응답 에러 처리
                      console.error('서버 요청 에러:', error.response?.data || error.message);
                      alert(`서버 에러 발생: ${error.response?.statusText || error.message}`);
                    } else {
                      // 그 외 에러 처리
                      console.error('알 수 없는 에러:', error);
                      alert('알 수 없는 에러가 발생했습니다.');
                    }
                  }
                };
            
            



            const sendKakaoCode = async (kakaoCode) => {
                return await axios.post('/api/outh/kakao/join', {
                    code: kakaoCode
                })
            };
            
            
            // [로그인 성공]
            // { 
                //  token: {
                    //    JWT:STR,
                    //    refresh token:STR, 
                    //  },
                    //  member: {
                        //    member_id: LONG,
                        //    role : STR
                        //  }
                        // }
                        
                        // [로그인 실패]
                        // { }
            const logIn = async (userId, password) => {
                try {
                    const response = await axios.post('http://i10b208.p.ssafy.io/api/general/login', {
                        login_id: userId,
                        password: password,
                    }, {
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        }
                    });
                    localStorage.setItem('JWT_token', response.data.response.token.access_token)
                    localStorage.setItem('refresh_token', response.data.response.token.refresh_token)
                    localStorage.setItem('key',response.data.response.token.key);
                    console.log('로그인 성공:', response.data);
                    alert('환영합니다.')

                return response.data;
            } catch (error) {
                // 로그인 실패 시 에러 처리
                if (axios.isAxiosError(error) && error.response) {
                    console.error('로그인 실패:', error.response.data.message);
                    alert(`로그인 실패: ${error.response.data.message}`);
                } else {
                    console.error('알 수 없는 에러:', error);
                    alert('알 수 없는 에러가 발생했습니다.');
                }
                throw error;
            }};

                    const tempPassword = async (userId, email_id, emailDomain) => {
                        return await axios.post('http://i10b208.p.ssafy.io/api/users/temp-password',  {
                            login_id: userId,
                            email_id: email_id,
                            email_domain: emailDomain
                        })
                    };
                    
                    const reName = async (nickName, accessToken) => { 
                        return await axios.post('http://i10b208.p.ssafy.io/api/users/nickname',  {
                            nickname: nickName
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        }) 
                    };
                    
                    const changeProfileImg = async (file, accessToken) => {
                        const formData = new FormData();
                        formData.append('image', file)
                        return await axios.post('http://i10b208.p.ssafy.io/api/users/profile-image', formData, {
                            headers: {
                                formDataHeader,
                                'Authorization': `Bearer ${accessToken}`                
                            }
                        })
                    };
                    
                    const checkPassword = async (password, accessToken) => {
                        return await axios.post('http://i10b208.p.ssafy.io/api/users/check-password', {
                            password: password 
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                    };
                    
                    const rePassword = async (password, accessToken) => {
                        return await axios.post('http://i10b208.p.ssafy.io/api/users/repassword', {
                            old_password: password,
                            new_password: password
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                    };
                    
                    const reFresh = async (refresh_token, member_id) => {
                        return await axios.post('http://i10b208.p.ssafy.io/api/users/refresh', {
                            refresh_token : refresh_token,
                            member_id : member_id
                        }, {
                            headers : {
                                'Authorization': `Bearer ${refresh_token}`
                            }
                        })
                    };
                    
                    const withDraw = async (accessToken) => {
                        return await axios.get('http://i10b208.p.ssafy.io/api/users/withdraw', {
                            None
                        },{
                            headers : {
                                'Authorization': `Bearer ${accessToken}`
                            }
                            
                        })
                    };
                    
                    // const logout = async (accessToken) => {
                    //     try {
                    //         const response = await axios.get('http://i10b208.p.ssafy.io/api/users/logout', {
                    //             None
                    //         },{
                    //             headers : {
                    //                 'Authorization': `Bearer ${accessToken}`
                    //             }
                    //         })
                    //         token.value = null
                    //         localStorage.removeItem('token')
                    //         router.push( '/' )  
                    //          }  catch (error) {
                    //          }};
                
                    
                    const kakaoLogin = () => {
                        const clientId = "64f53b3a322ebb16eabd9859392720c9"; // 클라이언트 ID를 문자열로 설정
                        const redirectUri = 'http://localhost:5173/';
                        const url = `https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code`
                        // 사용자를 카카오 로그인 페이지로 리디렉션
                        window.location.href = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirectUri}`;
                        
                        return kakaoLogin
                      };
                    
                      const logOut = function () {
                        token.value = null
                        // username.value = '' // 로그아웃 시 username 제거
                        localStorage.removeItem('token')
                        // localStorage.removeItem('username')
                        router.push({ name: 'Intro' })
                      }
                    
                    export {
                        isIdDuplicated,
                        
                        emailVerifyRequest,
                        verifyNumberCreate,
                        signUp,
                        sendKakaoCode,
                        logIn,
                        tempPassword,
                        reName,
                        changeProfileImg,
                        checkPassword,
                        rePassword,
                        reFresh,
                        withDraw,
                        logOut,
                        kakaoLogin
                        
                    };