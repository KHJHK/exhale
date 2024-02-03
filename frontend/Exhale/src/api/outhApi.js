import axios from "axios";
import { ref } from "vue";
const formDataHeader = {"Content-Type":
"multipart/form-data"};

// const rules = ref({
//     userId: [
//       (value) => {
//         if (value) return true;
//         return "Username is requred.",
//         alert('이름은 좀 압시다.')
//       },
//     ],
//     password: [
//       (value) => {
//         if (value) return true;
//         return "Password is requred.";
//       },
//     ],
//     passwordConfirm: [
//       (value) => {
//         if (value) return true;
//         return "비밀번호확인을 빼먹었어요";
//       },  
//     ],
//     birthdate: [
//         (value) => {
//         if (value) return true;
//         return "생년월일을 입력해주세요" 
//         },
//     ],
//     nickName: [
//         (value) => {
//             if (value) return true;
//             return "아버지를 아버지라 부르지못하고..."
//         }
//     ],
//     fullname: [
//       (value) => {
//         if (value) return true;
//         return "Name is required.";
//       },
//       (value) => {
//         if (value?.length <= 10) return true;
//         return "Name must be less than 10 characters.";
//       },
//     ],
//     email: [
//       (value) => {
//         var regex = /^[a-z0-9]+@[a-z.-]+\.[a-z]+$/i;
//         if (regex.test(value)) return true;
//         return "Must be a valid e-mail.";
//       },
//     ],

//   });

//test시 path에 api빼고

    // 2 - 이미 존재하는 값
    const isIdDuplicated = async (userId) => {
        return await axios.post('http://i10b208.p.ssafy.io/api/general/id', {
            login_id: userId.value
        });
    };
    
    // const isEmailDuplicated = async (email, emailDomain) => {
        //     return await axios.post('/api/email', {
            //             email_id: email.value,
            //             email_domain : emailDomain.value
            //     });
            // };
            
            // 2 - 이미 존재하는 값, 3 - 유효하지 않은 이메일
            const verifyNumberCreate = async (email, emailDomain) => {
                return await axios.post('/api/email/certification', {
                    email_id: email.value,
                    email_domain : emailDomain.value
                })
            };
            
            const emailVerifyRequest = async (email, emailDomain) => {
                return await axios.post('/api/email/check',  {
                    email_id: email.value,
                    email_domain : emailDomain.value
                })
            };
            
            const signUp = async (userId, email, emailDomain, fullname, birthdate, password, nickName) => {
                console.log("k ",password)
                return await axios.post('http://i10b208.p.ssafy.io/general/join',  {
                    login_id : userId,
                    password : password,
                    name : fullname,
                    email_id : email,
                    email_domain : emailDomain,
                    birth : birthdate,
                    nickname : nickName
                })
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
                            return await axios.post('/api/general/login', {
                                login_id: userId,
                                password: password,
                            },{ headers:{
                                "Content-Type": "application/x-www-form-urlencoded"
                            }
                        })
                    };
                    
                    const tempPassword = async (userId, email, emailDomain) => {
                        return await axios.post('/api/users/temp-password',  {
                            login_id: userId,
                            email_id: email,
                            email_domain: emailDomain
                        })
                    };
                    
                    const reName = async (nickName, accessToken) => { 
                        return await axios.post('/api/users/nickname',  {
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
                        return await axios.post('api/users/profile-image', formData, {
                            headers: {
                                formDataHeader,
                                'Authorization': `Bearer ${accessToken}`                
                            }
                        })
                    };
                    
                    const checkPassword = async (password, accessToken) => {
                        return await axios.post('/api/users/check-password', {
                            password: password 
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                    };
                    
                    const rePassword = async (password, accessToken) => {
                        return await axios.post('/api/users/repassword', {
                            old_password: password,
                            new_password: password
                        }, {
                            headers: {
                                'Authorization': `Bearer ${accessToken}`
                            }
                        })
                    };
                    
                    const reFresh = async (refresh_token, member_id) => {
                        return await axios.post('/api/users/refresh', {
                            refresh_token : refresh_token,
                            member_id : member_id
                        }, {
                            headers : {
                                'Authorization': `Bearer ${refresh_token}`
                            }
                        })
                    };
                    
                    const withDraw = async (accessToken) => {
                        return await axios.get('/api/users/withdraw', {
                            None
                        },{
                            headers : {
                                'Authorization': `Bearer ${accessToken}`
                            }
                            
                        })
                    };
                    
                    const logout = async (accessToken) => {
                        return await axios.get('/api/users/logout', {
                            None
                        },{
                            headers : {
                                'Authorization': `Bearer ${accessToken}`
                            }
                            
                        })
                    };
                    
                    const kakaoLogin = () => {
                        const clientId = "64f53b3a322ebb16eabd9859392720c9"; // 클라이언트 ID를 문자열로 설정
                        const redirectUri = 'http://localhost:5173/';
                        const url = `https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code`
                        // 사용자를 카카오 로그인 페이지로 리디렉션
                        window.location.href = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirectUri}`;
                        
                        return kakaoLogin
                      };
                    

                    
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
                        logout,
                        kakaoLogin
                        
                    };